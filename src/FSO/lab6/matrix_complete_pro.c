#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <sys/ioctl.h>
#include <unistd.h>

#define ROWS 1000
#define COLUMNS 1000

char m[ROWS][COLUMNS];
long delay[COLUMNS];
int row_b[COLUMNS];
int columns, rows;

pthread_attr_t attrib;
pthread_t  draw_thread[COLUMNS];
pthread_t  erase_thread[COLUMNS];
pthread_t  refresh_thread;
pthread_t  quit_signal_thread;

void *EraseCol(void *ptr) {
  int row, col=(int)(long)ptr;

  for (row=0; row<rows; row++) {
    m[row][col]= ' '; // Write space
    usleep(delay[col]); // Wait before the following erase
  }
}

void *DrawCol(void *ptr) {
  	int row, col=(int)(long)ptr;
	while(1) {  
		delay[col]= 50000+rand()%450000; // Random delay: 0,05s to 0,5s
		if (rand()%10 > 4) { // Sometimes do not draw column
			usleep(delay[col]*rows); // Wait without drawing
		} else {
			for (row=0; row<rows; row++) {
				if (row == rows / 2) {
					pthread_create( & erase_thread[col], & attrib, EraseCol, (void*)(long) col);
		  		}
				row_b[col] = row;
				m[row][col] = 32+rand() % 94; // Write random char
				usleep(delay[col]); // Wait before next char
			}
			pthread_join(erase_thread[col], NULL);
	  	}
	}
}

void *Refresh (void *ptr) {
  	int row, col;
  	char order[20];

  	while(1) {
    	write(1,"\033[1;1f\033[1;40;32m",16); // Back to left-up corner, Green text
    	for (row=0; row<rows; row++) {
      		write(1,m[row],columns);
			write(1,"\n",1); // Refresh row
    	}
    	write(1,"\033[1;37m",7); // White text
    	for (col=0; col<columns; col++) {
      		sprintf(order,"\033[%d;%df%c",row_b[col]+1,col+1,m[row_b[col]][col]); 
      		// Rewrite in white the last character in column col
      		if (row_b[col]<rows-1) write(1,order,strlen(order)); 
    	}
    	usleep(100000); // Wait 0,1s before refreshing again
  	}
}

void *QuitSignal(void *ptr) {
	while (1)
		if (getchar()=='q') break;
} 

int main() {
	struct winsize w;
	ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);

	columns = w.ws_col;
	rows = w.ws_row;

  	int col;
  	memset (m,' ', rows*columns); // Erase matrix m
  	write(1,"\033[2J\033[?25l",10); // Clean screen and hide cursor 
  
	pthread_attr_init(&attrib);
  
  	// Create a drawing thread for every column
  	for (col = 0; col < columns; col++) {
  		pthread_create (& draw_thread[col], & attrib, DrawCol, (void*)(long)col);
  	}

  	// Create a screen refresh thread
  	pthread_create (& refresh_thread, & attrib, Refresh, NULL); 

	pthread_create (& quit_signal_thread, & attrib, QuitSignal, NULL);

	pthread_join (quit_signal_thread, NULL);

	write(1,"\033[0m\033[?25h\r",11); // Reset usual text and cursor
}

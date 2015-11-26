// share_file.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main (int argc,char *argv[]) { 
    int fd;
    pid_t pid;
    mode_t fd_mode=S_IRWXU;// file permissions
    char *parent_message = "parent message \n";
    char *child_message = "child message \n";
	
    fd = open("messages.txt",O_RDWR | O_CREAT,fd_mode);
    write(fd,parent_message,strlen(parent_message));
	
    pid = fork();
if (pid == 0) { 
    write(fd,child_message,strlen(child_message));
	  close(fd);
	  exit(0);
    }
    wait(NULL);
    write(fd,parent_message,strlen(parent_message));
    close(fd);
    return(0);
}

// Program SequentialAdd.c
// Compile with:
//   gcc SequentialAdd.c -o SeqAdd -lm

#include <stdio.h>
#include <pthread.h>
#include <math.h>
#include <unistd.h>

#define DIMROW 1000000
#define NUMROWS 20

typedef struct row{
        int vector[DIMROW];
        long addition;
} row;

row matrix[NUMROWS];
int numcores;

void * AddRow( void *ptr ) {
    int k;
    row *fi;
    fi=(row *)  ptr;

    fi->addition=0;
    for(k=0;k<DIMROW;k++) {
        fi->addition += exp((k*(fi->vector[k])+
                       (k+1)*(fi->vector[k]))/(fi->vector[k]+2*k))/2;
    }
}

void *AddThread( int i ) {

	//INIT = NUMROWS / THREADS * i
	//MAX = NUMROWS / THREADS * ( i + 1 ) 
	int n;
	for (n = NUMROWS / numcores * i; n < NUMROWS / numcores * (i + 1); n++) {
		AddRow( & matrix[n] );
	}
}

int main()
{
    int i,j;
    long total_addition=0;
    pthread_t  threads[NUMROWS];
    pthread_attr_t attrib;

    // Vector elements initialized to 1
    for(i=0;i<NUMROWS;i++) {
        for(j=0;j<DIMROW;j++) {
            matrix[i].vector[j]=1;
        }
    }
    // Thread attributes initialization
    pthread_attr_init( &attrib );
	numcores = sysconf( _SC_NPROCESSORS_ONLN );
  	
	// EXERCISE 2.a
    for(i = 0; i < numcores; i++) {
    	pthread_create (& threads[i], & attrib, AddThread, & i);
    }
    // EXERCISE 2.b
	for(i = 0; i < numcores; i++) {
		pthread_join(threads[i], NULL);
		int j;
		for (j = NUMROWS / numcores * i; j < NUMROWS / numcores * (i + 1); j++) {
			total_addition += matrix[i].addition;
		}
	}

	printf("Total addition is: %ld\n", total_addition);
}

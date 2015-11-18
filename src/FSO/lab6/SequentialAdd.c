// Program SequentialAdd.c
// Compile with:
//   gcc SequentialAdd.c -o SeqAdd -lm

#include <stdio.h>
#include <pthread.h>
#include <math.h>

#define DIMROW 1000000
#define NUMROWS 20

typedef struct row{
        int vector[DIMROW];
        long addition;
} row;

row matrix[NUMROWS];

void *AddRow( void *ptr )
{
    int k;
    row *fi;
    fi=(row *)  ptr;

    fi->addition=0;
    for(k=0;k<DIMROW;k++) {
        fi->addition += exp((k*(fi->vector[k])+
                       (k+1)*(fi->vector[k]))/(fi->vector[k]+2*k))/2;
    }
}

int main()
{
    int i,j;
    long total_addition=0;
    pthread_t  threads[NUMROWS];
    pthread_attr_t atrib;

    // Vector elements initialized to 1
    for(i=0;i<NUMROWS;i++) {
        for(j=0;j<DIMROW;j++) {
            matrix[i].vector[j]=1;
        }
    }
    // Thread attributes initialization
    pthread_attr_init( &atrib );

    // EXERCISE 2.a
    for(i=0;i<NUMROWS;i++)
    {
        AddRow(&matrix[i]);
    }
    // EXERCISE 2.b

    for(i=0;i<NUMROWS;i++) 
        total_addition+=matrix[i].addition;
    printf(  "Total addition is: %ld\n",total_addition);

}


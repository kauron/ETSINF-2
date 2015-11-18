#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define DIMROW 100000
#define NUMROWS 20

typedef struct row {
	int vector [DIMROW];
    long add;
} row;

row matrix [NUMROWS];

int main() {
  	int i, j;
  	long total_add = 0;
  	// Initializing to 1 all the elements of the vector
  	for (i =0; i < NUMROWS; i ++) {
    	for (j =0; j < DIMROW; j ++) {
      		matrix[i].vector[j] = 1;
    	}
		matrix[i].add = 0;
  	}
	
	// Summing all the members of the matrix
  	for (i = 0; i < NUMROWS; i++) {
    	if (fork() == 0) {
			for (j = 0; j < DIMROW; j++) {
				matrix[i].add += matrix[i].vector[j];
			}
			exit(matrix[i].add);
		}
	}
	int final_state;
	while(wait(&final_state) > 0) {
		total_add += WEXITSTATUS(final_state);
	}
 	printf ("The total addition is %ld\n", total_add);
}

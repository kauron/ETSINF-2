#include <stdio.h>
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
//	printf("Init...\n");
  	for (i =0; i < NUMROWS; i ++) {
//		printf("matrix[%d]=[",i);
    	for (j =0; j < DIMROW; j ++) {
      		matrix[i].vector[j] = 1;
//			printf("%s%d", j == 0 ?"":", " , matrix[i].vector[j]);
    	}
		matrix[i].add = 0;
//		printf("] - add=%ld\n", matrix[i].add);
  	}

	// Summing all the members of the matrix
//	printf("\n\nSum...\n");
  	for (i = 0; i < NUMROWS; i++) {
    	for (j = 0; j < DIMROW; j++) {
      		matrix[i].add += matrix[i].vector[j];
		}
//		printf("matrix[%d].add=%ld\n", i, matrix[i].add);
	}
//	printf("\n");
  	for (i = 0; i < NUMROWS; i++)
    	total_add += matrix[i].add;
 	printf ("The total addition is %ld\n", total_add);
}

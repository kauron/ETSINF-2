#include < stdio.h > 
#include < math.h > 
#define DIMROW 100 
#define NUMROWS 20 
 
typedef struct row 
{    int vector [DIMROW]; 
     long add; 
} row;
 
matrix row [NUMROWS];
int main() 
{
  int i, j, k; 
  long total_add = 0 ;
  // Initializing to 1 all the elements of the vector
  for (i =0; i < NUMROWS; i ++) {
    for (j =0; j < DIMROW; j ++) { 
      matrix [i] [j] .vector = 1 ;
      array[i].add = 0 ;
    }
  }
  for (i =0; i < NUMROWS; i ++) { 
    for (k =0; k < DIMROW; k ++) {     

      array[i].add += array[i][k].vector;
    }
  }
  for (i =0; i < NUMROWS; i ++) 
    total_add += array[i].add;
  printf ("The total addition is %ld\n", total_add); 
}
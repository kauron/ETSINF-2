/* lib_cos.c */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <math.h>

#define PI 3.14159265

/* Global variables */
char path_maps[80]; 
char ni_glob[4095];
long i_glob=20;    

void f(int param)
{ 
    printf("Address of funcion f parameter: %p\n",&param);
}

int main()
{  
    /* Local variables */
    long i_loc=20; /* Initialized local variable */
    long ni_loc; /* Non initialized local variable */
    float c;
	
    printf("Process PID: %d\n\n", getpid()); /* Shows process PID */
	
    /**** ADDRESS VISUALIZATION ***/
    printf("main function address: %p \n", main);
    printf("f function address: %p \n", f);
    printf("Initialiazed global variable i_glob address: %p\n", &i_glob);
    printf("Non initialized global variable ni_glob address \n");
    printf(" 1st ni_glob element address: %p\n", &ni_glob[0]);
    printf(" Last ni_glob element address: %p\n", &ni_glob[4095]);
    printf("Initialized local variable i_loc address: %p\n", &i_loc);
    printf("Non initialized local variable ni_loc address: %p\n", &ni_loc);
    f(40);   

    /** Mathematical operation **/
    c=cos(45*PI/180);
    printf("The mathematical operation result is: %f\n", c);

    printf("\n PROCESS MEMORY MAP /proc/%d/maps \n", getpid()); 
    sprintf(path_maps,"cat /proc/%d/maps",getpid());
    fflush(stdout);
    system(path_maps);
    printf(" ---------------\n\n"); 

    return 0;
}

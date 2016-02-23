/* map2.c */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

char path_maps[80]; /* Global variable */
char ni_glob[4095];
long i_glob=20;      /* Initialized global variable */

void f(int param)
{ 
    printf("Address of funcion f parameter: %p\n",&param);
}

void f2()
{ 
    int vloc[1024*1024];
    printf("Local variable vloc address: %p\n", vloc);
    printf("\n PROCESS MEMORY MAP (in function f2) \n");
    fflush(stdout);
    system(path_maps);
    printf(" ---------------\n\n");
}

int main()
{  
    long i_loc=20; /* Initialized local variable */
    long ni_loc; /* Non initialized local variable */
	
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

    printf("\n PROCESS MEMORY MAP /proc/%d/maps \n", getpid());

    // Create command “path_maps” to show memory map
    sprintf(path_maps, "cat /proc/%d/maps",getpid());
    // Empty the buffer
    fflush(stdout);
    // Execute command “path_maps”
    system(path_maps);
	
    printf ("            ---------------\n\n");

	f2();

    return 0;
}

#include <stdio.h> 

int a = 0; /* global variable */ 


// This function increases the value of global variable a by 1
void inc_a(void) {a++;}

// This function returns the previous value 
// and saves the new value v
int former_value(int v) {
	int temp;
    // Declare here static variable s
	static int s;
    temp = s;
    s = v;
    return temp;
}

main()
{
	int b = 2; /* local variable */
	inc_a();
	former_value(b);
	printf("a= %d, b= %d\n", a, b);
	a++;
	b++;
	inc_a();
    b = former_value(b);
    printf("a= %d, b= %d \n", a, b);
}

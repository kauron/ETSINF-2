/* my_child.c */
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	printf("Parent process %ld\n", (long)getpid());
	fork();
	printf("I am %ld, my parent is %ld\n", (long)getpid(), (long)getppid());
	sleep(15);
	return 0;
}

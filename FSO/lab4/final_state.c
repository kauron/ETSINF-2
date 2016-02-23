#include <stdio.h>
#include <stdlib.h>

#define MAX_PROC 5

int main (int argc, char *argv[]) {
	int i;
	pid_t val_return;
	int final_state;
	for (i = 0; i < MAX_PROC; i++) {
		val_return = fork();
		if (val_return == 0) {
			printf("child %ld created in iteration = %d\n", (long)getpid(), i);
		} else {
			printf("Parent %ld, iteration %d\n", (long)getpid(), i);
			printf("I have created a child %ld\n", (long) val_return);
			break;
		}
	}
	sleep(10);
	while(wait(&final_state) > 0) {
		printf("Parent %ld iteration %d\n", (long)getpid(), i);
		printf("My child said %d\n", WEXITSTATUS(final_state));
		printf("My child said %d\n", final_state / 256);
	}
	exit(i);
}

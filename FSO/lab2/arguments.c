#include <stdio.h>

int main(int argc, char *argv[]) {
	printf("Argument number = %i\n", argc);
	int i;
    for (i = 0; i < argc; i++) {
		printf("\tArgument %i is %s\n", i, argv[i]);
	}

	return 0;
}


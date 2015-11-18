#include <stdio.h>

int main(int argc, char *argv[]) {
	int i;
    for (i = 1; i < argc; i++) {
		printf("  Argument %i is ", i);
		switch(argv[i][1]) {
			case 'c': printf("Compile"); break;
			case 'E': printf("Preprocess"); break;
			case 'i': printf("Include %s", argv[i] + 2); break;
			default: printf("not valid");
		}
		printf("\n");
	}

	return 0;
}

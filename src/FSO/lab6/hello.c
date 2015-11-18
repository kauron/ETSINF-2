/**
 * Sample program: "Hello world" with pthreads.
 * To compile type:
 * 	gcc hello.c -lpthread -o Hello
 */
#include <stdio.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

void * Print (void *ptr) {
	char *men;
	men = (char*) ptr;
	// Exercise 1.b
	usleep(1500000);
	write (1, men, strlen (men));
}

int main() {
	pthread_attr_t attrib;
	pthread_t thread1, thread2;

	pthread_attr_init (& attrib);

	pthread_create (& thread1, & attrib, Print, "Hello");
	pthread_create (& thread2, & attrib, Print, "World \n");

	// Exercise 1.a
//	pthread_join (thread1, NULL);
//	pthread_join (thread2, NULL);
}

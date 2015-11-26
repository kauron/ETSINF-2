// descriptor.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

int main (int argc,char *argv[]) {
    int fda,fdb;

	if (argc!=2) { 
		fprintf(stderr, "Required read/write file \n"); 
        exit(-1);
    }
        
    if ((fda=open(argv[1],O_RDONLY))<0) 
        fprintf(stderr,"Open failed \n");
    else
        fprintf(stderr, "Read %s descriptor = %d \n",argv[1],fda);

	if ((fdb=open(argv[1],O_WRONLY))<0) 
		fprintf(stderr, "Open failed \n");
    else
    	fprintf(stderr, "Write %s descriptor = %d \n",argv[1],fdb);
    return(0);
}

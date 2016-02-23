// two_pipe.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main (int argc,char *argv[]) {
    int i;
    char* arguments1 [] = { "ls", "-la", 0 };
    char* arguments2 [] = { "grep", ".c", 0 };
    char* arguments3 [] = { "wc", "-l", 0 };
    int fildes[2][2];
    pid_t pid;
    // Parent process creates a pipe
    for (i=0;i<2;i++){
        if ((pipe(fildes[i])==-1)) {
            fprintf(stderr,"Pipe failure  \n");
            exit(-1);
        }
    }

    for (i=0;i<3;i++) {
        pid=fork(); // Creates a child process
        if ((pid==0) && (i==0)) {
            // Child process redirects its output to the pipe
            close(fildes[0][0]);
            close(fildes[1][0]);
            close(fildes[1][1]);
            if (dup2(fildes[0][1],STDOUT_FILENO) == -1) {
                fprintf(stderr,"nigga shit is broken D:<");
                exit(-1);
            }
            // Child process closes file descriptors
            close(fildes[0][1]);

            // Child process changes its memory image
            if ( execvp("ls",arguments1)<0) {
                fprintf(stderr,"ls not found \n");
                exit(-1);
            }
        } else if ((pid==0) && (i==1)) {
            // Child process redirects its input to the pipe
            close(fildes[0][1]);
            close(fildes[1][0]);
            if (dup2(fildes[1][1],STDOUT_FILENO) == -1) {
                fprintf(stderr,"more shit is borked D:<<");
                exit(-1);
            }

            if (dup2(fildes[0][0],STDIN_FILENO) == -1) {
                fprintf(stderr,"more shit is borked D:<<");
                exit(-1);
            }

            // Child process closses pipe descriptors
            close(fildes[0][0]);
            close(fildes[1][1]);
            // Child process changes its memory image
            if (execvp("grep",arguments2)<0) {
                fprintf(stderr,"wc not found \n");
                exit(-1);
            }
        } else if ((pid==0) && (i==2)) {
            // Child process redirects its input to the pipe
            close(fildes[1][1]);
            close(fildes[0][0]);
            close(fildes[0][1]);
            if (dup2(fildes[1][0],STDIN_FILENO) == -1) {
                fprintf(stderr,"more shit is borked D:<<");
                exit(-1);
            }

            // Child process closses pipe descriptors
            close(fildes[1][0]);
            // Child process changes its memory image
            if (execvp("wc",arguments3)<0) {
                fprintf(stderr,"wc not found \n");
                exit(-1);
            }
        }
    }


    close(fildes[0][0]);
    close(fildes[0][1]);
    close(fildes[1][0]);
    close(fildes[1][1]);
    // Parent process closes pipe descriptors
    for (i = 0; i < 3; i++) wait(NULL);
    return(0);
}

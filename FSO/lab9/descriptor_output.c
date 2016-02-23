// descriptor_output.c

#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main (int argc,char *argv[]) {
    char *men1="men1: Writing in descriptor 1 (output)\n"; 
    char *men2="men2: Writing in descriptor 2 (error)\n";
    char *men3="men3: Writing in descriptor 1 (output)\n";
    char *men4="men4: Writing in descriptor 2 (error)\n";
    char *men5="men5: Writing in descriptor 1 (output)\n";
    char *men6="men6: Writing in descriptor 2 (error)\n";
   
    write(1,men1, strlen(men1));
    write(2,men2, strlen(men2));
    close(1);
    write(1,men3, strlen(men3));
    write(2,men4,strlen(men4));
    close(2);
    write(1,men5,strlen(men5));
    write(2,men6, strlen(men6));
    return(0);
}

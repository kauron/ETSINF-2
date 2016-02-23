		.globl __start
		.text 0x00400000
__start:
		li $a0, 100
		li $v0,11 #print char
        syscall
        li $v0,1
        syscall

        li $a0,'A'
        li $v0,11
        syscall
        li $v0,1 #print int
        syscall

		.globl __start
		.text 0x00400000
__start: #10 is newline, 0 is null, 39 is ', 92 is \
Do:		li $a0,12
		syscall
		move $a0,$v0
		jal PrintChar
		j Do
		li $v0,10
		syscall

PrintChar:
		move $t0,$a0
		li $v0,11
		li $a0,39
		syscall
If:		bnez $t0, ElseIf
		li $a0,92
		syscall
		li $a0,'0'
		syscall
		jr EndIf
ElseIf:	li $t1,10
		bne $t0,$t1, Else
		li $a0,92
		syscall
		li $a0,'n'
		syscall
		jr EndIf
Else:	move $a0,$t0
		syscall
EndIf:	li $a0,39
		syscall
		jr $ra
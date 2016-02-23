          .globl __start
          .data 0x10000000
askfor:   .asciiz "Write something: "
response: .asciiz "You have written: "
length:   .asciiz "String length is: "
string:   .space 80             

          .text 0x00400000
__start:  la $a0, askfor        
          la $a1, string        
          li $a2, 80            
          jal InputS            

		  la $a0, response
		  la $a1, string
		  jal PromptS

		  la $a0, string
		  jal StrLength
		  
		  move $t0, $v0
		  li $v0, 4
		  la $a0, length
		  syscall
		  li $v0, 1
		  move $a0, $t0
		  syscall

		  la $a0, string
		  li $a1, 0
		  jal StrChar

		  move $t0, $v0
		  li $v0, 4
		  la $a0, length
		  syscall
		  li $v0, 1
		  move $a0, $t0
		  syscall

          li $v0,10
          syscall

InputS:   li $v0, 4
          syscall
          li $v0, 8
          move $a0, $a1
          move $a1, $a2
          syscall
          jr $ra

PromptS:  li $v0, 4
          syscall
		  move $a0, $a1
		  li $v0, 4
		  syscall
		  jr $ra

StrLength:
		  addi $v0, $zero, -1
		  li $t1, 10
loop:	  lb $t0, 0($a0)
		  addi $a0, $a0, 1
		  addi $v0, $v0, 1
		  bne $t0, $t1, loop
		  jr $ra

StrChar:  add $t0, $zero, $zero
		  addi $a1, $a1, 1
loop2:    lb $t1, 0($a0)
		  addi $a0, $a0, 1
		  addi $t0, $t0, 1
		  bne $a1, $t0, loop2
		  move $v0, $t1
		  jr $ra


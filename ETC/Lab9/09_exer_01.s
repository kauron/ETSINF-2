          .globl __start
		  .data 0x10000000
M:        .space 4
Q: 		  .space 4
R:        .space 4
          
          .text 0x00400000
	__start:  li $a0,'M'
          la $a1, M
          jal InputV
		  
		  li $a0,'Q'
		  la $a1, Q
		  jal InputV

		  la $a0, M
		  la $a1, Q
		  la $a2, R
		  jal MultV

		  la $a0, 'R'
		  la $a1, R
		  jal PromptV

          li $v0,10
          syscall

InputV:   li $v0, 11
          syscall
          li $v0, 11
          li $a0, '='
          syscall
          li $v0, 5
		  syscall
          sw $v0, 0($a1)
          jr $ra

PromptV:  li $v0, 11
		  syscall
		  li $v0, 11
		  li $a0, '='
		  syscall
		  li $v0, 1
		  lw $a0, 0($a1)
		  syscall
		  jr $ra

MultV:    lw $t0, 0($a0)
          lw $t1, 0($a1)
		  bgtz $t1, positive
		  sub $t0, $zero, $t0
		  sub $t1, $zero, $t1
positive: add $t2, $zero, $zero
loop:	  add $t2, $t2, $t0
          addi $t1, $t1, -1
		  bgtz $t1, loop
		  sw $t2, 0($a2)
		  jr $ra

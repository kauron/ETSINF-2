### TEMPLATE FOR MIPS R2000 I/O LAB SESSIONS ###

#-------------------------------------------------#
#
#  LAB 12: SINCHRONIZATION BY POLLING
# 
#-------------------------------------------------#

# ACTIVITY 2:  Add the instruction that reads the keyboard 
#              data register on the appropriate location:


#------- Data segment ---------#
	.data		
T1:	.asciiz "Waiting...\n"
T2:	.asciiz "\nEnd\n"

#------- Code segment ---------#
	.text
    	.globl __start	

__start:
	# Writes on the console message T1
	li $v0, 4
	la $a0, T1
	syscall	
	
	# Loads keyboard base address
	la $t0, 0xffff0000

wait:   # Waits for bit R == 1
	lw $t1,0($t0)
	andi $t1,$t1,1
	beqz $t1,wait

	lw $t1,4($t0)
	add $a0, $t1, $zero
	li $v0, 1
	syscall
	# Insert CRLFds
	li $a0,10
	li $v0,11
	syscall
	

# No need to clear the R bit	
#	lw $t1,0($t0)
#	ori $t1,$t1,1
#	sw $t1,0($t0)
	
	addi $t1,$t1,-4
	bnez $t1, wait

	# Writes on the console message T2
	li $v0, 4
	la $a0, T2
	syscall

	# exit
	li $v0, 10
	syscall			

	.end

	.data
A:	.word  0, 1, 2, 3, 4, 5, 6, 7, 8, 9
B:	.word 10,11,12,13,14,15,16,17,18,19
C:	.space 40

	.text

start:
	addi r1,r0,#10
	addi r2,r0,#0
	addi r3,r0,#0
	addi r4,r0,#0
bucle:
	lw r6,A(r2)
	add r6,r6,r6
	lw r7,B(r3)
	addi r7,r7,#1
	add r8,r6,r7
	sw C(r4),r8
	addi r1,r1,#-1
	addi r2,r2,#1
	addi r3,r3,#1
	addi r4,r4,#1
	seq r5,r1,r0
	beqz r5,bucle
	trap #0
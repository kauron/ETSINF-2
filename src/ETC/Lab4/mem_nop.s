	.data
A:	.word 0
B:	.word 20
C:	.word 30
D:	.word 0

	.text
start:
	addi r1,r0,#10
	nop
	nop
	sw A(r0),r1
	lw r1,B(r0)
	lw r2,C(r0)
	nop
	nop
	add r3,r1,r2
	nop
	nop
	sw D(r0),r3
	trap #0
	.text
start:
	addi r3,r0,#30
	addi r4,r0,#40
	addi r1,r0,#10
	addi r2,r0,#20
	sub r5,r4,r3
	add r6,r4,r1
	nop
	add r5,r5,r2
	sub r6,r6,r3
	trap #0
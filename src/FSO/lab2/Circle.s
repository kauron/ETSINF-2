	.file	"Circle.c"
	.section	.rodata
	.align 8
.LC2:
	.string	"The area of the circle with radius %f is %f\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movl	$0x41200000, %eax
	movl	%eax, -8(%rbp)
	movss	-8(%rbp), %xmm0
	mulss	-8(%rbp), %xmm0
	unpcklps	%xmm0, %xmm0
	cvtps2pd	%xmm0, %xmm0
	movsd	.LC1(%rip), %xmm1
	mulsd	%xmm1, %xmm0
	unpcklpd	%xmm0, %xmm0
	cvtpd2ps	%xmm0, %xmm0
	movss	%xmm0, -4(%rbp)
	movss	-4(%rbp), %xmm1
	cvtps2pd	%xmm1, %xmm1
	movss	-8(%rbp), %xmm0
	cvtps2pd	%xmm0, %xmm0
	movl	$.LC2, %eax
	movq	%rax, %rdi
	movl	$2, %eax
	call	printf
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.section	.rodata
	.align 8
.LC1:
	.long	776530087
	.long	1074340351
	.ident	"GCC: (Ubuntu/Linaro 4.6.3-1ubuntu5) 4.6.3"
	.section	.note.GNU-stack,"",@progbits

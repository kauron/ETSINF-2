          .globl __start
          .data 0x10000000
NewLine:  .ascii "\n"
          .text 0x00400000
__start:  #read two integers
          li $a0,'N'
          jal Input
          move $s0,$v0
          #print multiply table
          li $s1, 0
          li $s2, 10
While:    addi $s1,$s1,1
          move $a1,$s1
          bgt $a1,$s2,Exit
          #multiply them
          move $a0,$s0
          jal Mult
          #print the result
          move $a2,$v0
          move $a1,$s1
          move $a0,$s0
          jal PromptM
          # if the result is != 0 then repeat
          j While
          #exit
Exit:     li $v0,10 #10 -> exit
          syscall # exits the program

Mult:     li $v0, 0 #set $v0 to 0
          beqz $a1, MultRet #if $a1 == 0 then return
          beqz $a0, MultRet #if $a0 == 0 then return
MultFor:  add $v0, $v0, $a0 #$v0 = $v0 + $a0
          addi $a1, $a1, -1 #$a1 -= 1
          bne $a1, $zero, MultFor #if $a1 != 0 loop
MultRet:  jr $ra # exit function (return to last PC)

Input:    li $v0,11 #print char syscall code
          syscall
          li $a0,'='
          #li $v0,11
          syscall
          li $v0,5 #read int
          syscall
          jr $ra

PromptM:  #prints $a0 x $a1 = $a2
          li $v0,1
          syscall
          li $a0,'x'
          li $v0,11
          syscall
          move $a0,$a1
          li $v0,1
          syscall
          li $a0,'='
          li $v0,11
          syscall
          move $a0,$a2
          li $v0,1
          syscall
          lb $a0,NewLine
          li $v0,11
          syscall
          jr $ra
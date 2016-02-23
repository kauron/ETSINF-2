          .globl __start
          .data 0x10000000
NewLine:  .ascii "\n"
          .text 0x00400000
__start:  #read two integers
          li $a0,'M'
          jal Input
          move $t0,$v0
          li $a0,'Q'
          jal Input
          move $a1,$v0
          move $a0,$t0
          #check if q is negative
          bgez $a1,Fi
          #change sign to both operands $a0 and $a1
          sub $a0,$zero,$a0 #$a0 = -$a0
          sub $a1,$zero,$a1 #$a1 = -$a1
          #multiply them
Fi:       jal Mult
          #print the result
          li $a0,'R'
          move $a1,$v0
          jal Prompt
          # if the result is != 0 then repeat
          bnez $a1, __start
          #exit
          li $v0,10 #10 -> exit
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

Prompt:   li $v0,11 #print char
          syscall
          li $a0,'='
          #li $v0,11
          syscall
          move $a0,$a1
          li $v0,1 #print int
          syscall
          lb $a0,NewLine
          li $v0,11
          syscall
          jr $ra
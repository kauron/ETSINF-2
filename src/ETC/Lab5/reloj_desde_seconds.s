                ##########################################################
                # Segmento de datos
                ##########################################################

                .data 0x10000000
reloj:          .word 0                # HH:MM:SS (3 bytes de menor peso)

cad_asteriscos: .asciiz "\n  **************************************"
cad_horas:      .asciiz "\n   Horas: "
cad_minutos:    .asciiz " Minutos: "
cad_segundos:   .asciiz " Segundos: "
cad_reloj_en_s: .asciiz "\n   Reloj en segundos: "
errorf:			.asciiz "ERROR: formato invalido"

                ##########################################################
                # Segmento de código
                ##########################################################

                .globl __start
                .text 0x00400000

		        la $a0, reloj
				li $a1, 66765
				jal inicializa_reloj_en_s

				la $a0, reloj
				jal imprime_reloj
             
salir:          li $v0, 10              # Código de exit (10)
                syscall                 # Última instrucción ejecutada
                .end



				########################################################## 
                # Subrutina que devuelve el reloj desde los segundos a medianoche
                # Entrada: $a0 dirección del reloj en memoria
				# 		   $a1 segundos desde la medianoche
                ########################################################## 
				
inicializa_reloj_en_s:
				li $t3, 60			# Factor from hi<->m and m<->s
				div $a1, $t3		# Divide seconds_total / 60
				mflo $a1			# In a1, keep the total minutes
				mfhi $t0			# In t0 keep the seconds
				div $a1, $t3		# Divide minutes_total / 60
				mflo $t2			# In t2 keep the hours
				mfhi $t1			# In t1 keep the minutes
				sb $t0, 0($a0)		# Store seconds
				sb $t1, 1($a0)		# Store minutes
				sb $t2, 2($a0)		# Store hours
				jr $ra





				########################################################## 
                # Subrutina que devuelve el reloj en segundos
                # Entrada: $a0 con la dirección de la variable reloj
				# Salida:  $v0 con el valor de los segundos
                ########################################################## 
				
devuelve_reloj_en_s:
				li $t3, 60			# Factor from h<->m and m<->s
				lb $t0, 2($a0)		# Load hours
				li $t1, 0x1f		# Clean hours
				and $t0, $t0, $t1
				mult $t0, $t3		# Hours to minutes
				mflo $t2
				lb $t0, 1($a0)		# Load minutes
				li $t1, 0x3f		# Clean minutes
				and $t0, $t0, $t1
				add $t0, $t0, $t2	# minutes = hours * 60 + minutes
				mult $t0, $t3		# Minutes to seconds
				mflo $t2
				lb $t0, 0($a0)		# Load seconds
				li $t1, 0x3f		# Clean seconds
				and $t0, $t0, $t1
				add $v0, $t0, $t2	# seconds = minutes * 60 + seconds
				jr $ra
				
				
				
				########################################################## 
                # Subrutina que guarda un nuevo valor de reloj en memoria
                # Entrada: $a0 con la dirección de la variable reloj
				# 		   $a1 con el nuevo valor del reloj
                ########################################################## 

inicializa_reloj:
				li $t0, 0x001f3f3f		# Clean input
				and $t0, $t0, $a1
								#xxxx xxxx xxxH HHHH xxMM MMMM xxSS SSSS
								#0000 0000 0001 1111 0011 1111 0011 1111
				bne $t0, $a1, exception
				sw $a1, 0($a0)
				jr $ra
				
				
				#####################
				# Exception			#
				#####################
				
exception:		la $a0, errorf			# Dirección de la cadena
                li $v0, 4				# Código de print_string
                syscall
				j salir
				
				########################################################## 
                # Subrutina que guarda un nuevo valor de reloj en memoria
                # Entrada: $a0 con la dirección de la variable reloj
				# 		   $a1 con el valor de las horas
				#		   $a2 con el valor de los minutos
				#          $a3 con el valor de los segundos
                ########################################################## 

inicializa_reloj_alt:
				sb $a1, 2($a0)
				sb $a2, 1($a0)
				sb $a3, 0($a0)
				jr $ra
				
				
				########################################################## 
                # Subrutina que guarda un nuevo valor de reloj en memoria
                # Entrada: $a0 con la dirección de la variable reloj
				# 		   $a1 con el valor de las horas
                ########################################################## 

inicializa_reloj_hh:
				sb $a1, 2($a0)
				jr $ra
				
				########################################################## 
                # Subrutina que guarda un nuevo valor de reloj en memoria
                # Entrada: $a0 con la dirección de la variable reloj
				# 		   $a1 con el valor de los minutos
                ########################################################## 

inicializa_reloj_mm:
				sb $a1, 1($a0)
				jr $ra
				
				########################################################## 
                # Subrutina que guarda un nuevo valor de reloj en memoria
                # Entrada: $a0 con la dirección de la variable reloj
				# 		   $a1 con el valor de los segundos
                ########################################################## 

inicializa_reloj_ss:
				sb $a1, 0($a0)
				jr $ra

                ########################################################## 
                # Subrutina que imprime el valor del reloj
                # Entrada: $a0 con la dirección de la variable reloj
                ########################################################## 

imprime_reloj:  move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                la $a0, cad_horas       # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 2($t0)         # Lee el campo HH
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_minutos     # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 1($t0)         # Lee el campo MM
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_segundos    # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                lbu $a0, 0($t0)         # Lee el campo SS
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra

                ########################################################## 
                # Subrutina que imprime los segundos calculados
                # Entrada: $a0 con los segundos a imprimir
                ########################################################## 

imprime_s:      move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall


                la $a0, cad_reloj_en_s  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                move $a0, $t0           # Valor entero a imprimir
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra
                
                ########################################################## 
                # Subrutina que incrementa el reloj en una hora
                # Entrada: $a0 con la dirección del reloj
                # Salida: reloj incrementado en memoria
                # Nota: 23:MM:SS -> 00:MM:SS
                ########################################################## 
                
pasa_hora:      lbu $t0, 2($a0)         # $t0 = HH
                addiu $t0, $t0, 1       # $t0 = HH++
                li $t1, 24
                beq $t0, $t1, H24       # Si HH==24 se pone HH a cero
                sb $t0, 2($a0)          # Escribe HH++
                j fin_pasa_hora
H24:            sb $zero, 2($a0)        # Escribe HH a 0
fin_pasa_hora:  jr $ra

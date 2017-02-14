.equ SWI_PrInt, 0x6b
.equ SWI_RdInt, 0x6c
.equ SWI_PrStr, 0x69
.equ Stdout, 4
.equ SWI_Exit, 0x11
.global _start
.text

_start:

   mov r4,#3
   sub r4,r4,#1
   
   mov r5,#0
   while1:
      cmp r5,r4
      bge endWhile
      add r5,r5,#1
      mov r0, #Stdout
      ldr r1,=msg
      swi SWI_PrStr
      b while1
      endWhile:
      mov r0,#Stdout
      ldr r1,=endmsg
      swi SWI_PrStr
      
      mov r5, #0
      while2:
         cmp r5,r4
         bgt endWhile2
         add r5,r5, #1
         mov r0,#Stdout
         ldr r1,=msg
         swi SWI_PrStr
         b while2
      endWhile2:
      mov r0, #Stdout
      ldr r1, =endmsg
      swi SWI_PrStr
      
      
swi SWI_Exit
.data
.align
Unsorted:.word 8
.word 3
.word 6
msg: .asciz "in while Loop \n"
endmsg: .asciz "end loop \n"
.end
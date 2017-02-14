@@@ OPEN INPUT FILE, READ INTEGER FROM FILE, PRINT IT, CLOSE INPUT FILE
.equ SWI_Open, 0x66                                               @ open a file
.equ SWI_Close,0x68                                               @ close a file
.equ SWI_PrChr,0x00                                               @ Write an ASCII char to Stdout
.equ SWI_PrStr, 0x69                                              @ Write a null-ending string 
.equ SWI_PrInt,0x6b                                               @ Write an Integer
.equ SWI_RdInt,0x6c                                               @ Read an Integer from a file
.equ Stdout, 1                                                    @ Set output target to be Stdout
.equ SWI_Exit, 0x11                                               @ Stop execution
.global _start
.text
@ addr_scann: .word scan 0
_start:
ldr r5, = 0xE000E010
@ print an initial message to the screen
mov R0,#Stdout @print an initial message 
ldr R1, =Message1 @ load address of Message1 label
swi SWI_PrStr @ display message to Stdout

mov R8, #1
@ ldr R0, =inputFile
@ mov R1, #0
@ swi SWI_Open
@ ldr r1, =inFileHandle
@ swi SWI_RdInt
@ bl scanf
@ldr r9, [sp]
mov R9, #5
mov R10, #1

cmp R10, R9
bl Fact
Fact:
mul R8, R9, r8
SUB R9, R9, #1
cmp r10, r9
ble Fact
mov R1, R8
swi SWI_PrInt
ldr r6, = 0xE000E008
sub r4, r6, r5
mov r1, r4
swi SWI_PrInt
swi SWI_Exit
   .data
   .align
   num: .word 0
   NL: .asciz "\n " @ new line 
   Message1: .asciz "Please input factorial: \n"
   @ inputFile: "test.txt"
   @ inFileHandle: word 0
   inp: .word 0
   .end
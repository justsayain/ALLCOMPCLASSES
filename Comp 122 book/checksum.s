@ Steve Delgado
   .equ SWI_Open, 0x66  
   .equ SWI_Close,0x68     
   .equ SWI_PrStr, 0x69  
   .equ SWI_PrInt,0x6b  
   .equ SWI_RdInt,0x6c
	.equ SWI_RdStr, 0x6a
   .equ Stdout, 1       
   .equ SWI_Exit, 0x11  
   .global _start
   .text

_start:
@Open an input file for reading
	ldr r0, =InFileName @ set Name for input file
	mov r1,#0 @ mode is input
	swi SWI_Open

@ Save the file handle in memory:
	ldr r1, =InFileHandle @ if OK, load input file handle
	str r0,[r1] @ save the file handle
   
@ Save the file handle in memory:
	ldr r2, =1024
	ldr r1, =InFileHandle
	str r0, [r1]
	swi SWI_RdStr
		
	ldr r1, =mystring
	mov r2, #1024
	add r2, r2, #1
	swi SWI_RdStr
	mov r2, #0

   
@Read Strings until end of file
RLoop:
   	ldrb r3, [r1]
	cmp r3, #0
	beq EofReached
	add r4, r4, r3
	add r1, r1, #1
	bal RLoop @ keep reading till end of file

EofReached:
   	mov r0, #Stdout
	mvn r1, r4
	and r1, r1, #0xff
	swi SWI_PrInt
	swi SWI_PrStr
	bal Exit

	@check sum
@close file
   ldr R0, =InFileHandle @ get address of file handle
   ldr R0, [R0] @ get value at address
   swi SWI_Close
Exit:
   swi SWI_Exit @ stop executing 
InFileError:
   mov R0, #Stdout
   ldr R1, =FileOpenInpErrMsg
   swi SWI_PrStr 
   bal Exit @ give up, go to end
.data
.align
InFileHandle: .skip 4
InFileName: .asciz "udp.dat"
FileOpenInpErrMsg: .asciz "Failed to open input file \n"
EndOfFileMsg: .asciz "End of file reached\n"
ColonSpace: .asciz": "
NL: .asciz "\n " @ new line 
mystring: .skip 1024
.end
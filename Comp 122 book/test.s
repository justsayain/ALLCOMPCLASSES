.equ Stdout, 1
.equ SWI_Open, 0x66
.equ SWI_RdStr, 0x6a
.equ SWI_PrStr, 0x69
.equ SWI_Close, 0x68
.equ Stdout, 1
.equ SWI_Exit, 0x11
.equ SWI_RdStr, 0x6a
.global _start

.text
_start:
	
	ldr r0,=InFileName	
	mov r1,#0
	swi SWI_Open
	bcs InFileError
	
	ldr r2, = 1024	
	ldr r1,=InFileHandle
	str r0,[r1]
	swi SWI_RdStr
	mov r0, #Stdout
	swi SWI_PrStr
	swi SWI_Close
	
	
	
RLoop:
   ldr r0,=InFileHandle @ load input file handle
   ldr r0,[r0]
   swi SWI_RdStr
   add r3,r0,r1
   bcs EofReached       @ Check Carry-Bit (C): if= 1 then EOF reached
b RLoop   
Ones:
   
@ == End of file ===============================================
EofReached:
   mov R0, #Stdout @ print last message
   ldr R1, =EndOfFileMsg
   swi SWI_PrStr
	@ print check sum here
	
@ == Close a file ===============================================
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
InFileHandle: .skip 0 
InFileName: .asciz "udp.dat"
FileOpenInpErrMsg: .asciz "Failed to open input file \n"
EndOfFileMsg: .asciz "End of file reached\n"
ColonSpace: .asciz": "
NL: .asciz "\n " @ new line 
mystring: .skip 1025
.end
@@ COMP 122 
@@ Project 6
@@ Due May 8, 2014
@@ Solve sudoku using brute force (exhaustive)
@@ C-code is given for a simple recursive solution that solves all sudoku puzzles.
@@ Translate it into an assembly program that reads a board from the file board.txt with the following format:
@@ http://pastebin.com/GBNYaYUH
@@ 704006390
@@ 000701500
@@ 000090027
@@ 002610053
@@ 400207008
@@ 610039400
@@ 930060000
@@ 006308000
@@ 048900206
@@ Output should be the solved board in the same format printed to the stdout output.

.equ SWI_Open, 0x66 @open a file
.equ SWI_Close,0x68 @close a file
.equ SWI_PrChr,0x00 @ Write an ASCII char to Stdout
.equ SWI_PrStr, 0x69 @ Write a null-ending string
.equ SWI_PrInt,0x6b @ Write an Integer
.equ SWI_RdInt,0x6c @ Read an Integer from a file
.equ SWI_RdStr,0x6a @ Read String from file
.equ Stdout, 1 @ Set output target to be Stdout
.equ SWI_Exit, 0x11 @ Stop execution

@ =================PG 37 in ArmSim Documentation =================
@ ================= Open Input File =================
ldr     R0, =InFileName         @ set Name for input file
        mov     R1, #0                  @ mode is input
        swi     SWI_Open                @ open file for input
        bcs     NoFileFound             @ if the carry bit is 1 then there was error opening the file    
        ldr     R1, =InFileHandle       @ if OK, load input file handle
        str     R0, [R1]                @ save the file handle
   	ldr     R3, =Box           @ load the array for unsorted numbers
   	mov 	r5,#0 @size
@ ================= Read until End =================
RLoop:
        ldr     R0, =InFileHandle       @ load input file handle
        ldr     R0, [R0]                @ get the location in memory
        swi     SWI_RdInt               @ read the integer into R0
        bcs     EofReached              @ if carry bit is 1 then EOF reached
        str 	R0,[R3,R5]              
        add     r5,r5,#4 @size
	mov 	R1,R0
	mov     R0,#Stdout 
	swi	SWI_PrInt
	bal     RLoop                   @ keep reading till end of file

           
        EofReached:
   	mov r0, #Stdout
	mvn r1, r4
	and r1, r1, #0xff
   
    mov r9,#0 @guess thing track
    mov r6,#0 @index
   mov r7,#0 @j
   mov R10, #0 @row
   mov R11, #0 @col
   sub r5,r5,#4	@size-1
   for1:
      cmp r6,r5
      bgt endfor1
       
         ldr r8,[r3,r6]	@array[i]@comp if 0
         if:
            cmp r8,#0
            bgt endif
            blt endif
            mov R10,r8
            mov R11,r8
            b endif
         endif:
         add r7,r7,#4	@j++
       b for1
       endfor1:
         if2: 
         cmp r10,#1
         bgt endif2
         mov r9,#1 @increment
         endif2:
       for2:
         
                

Exit:
SWI SWI_Exit @ stop executing

NoFileFound:
        mov     R0, #Stdout             @ set output to console
        ldr     R1, =FileErrMsg         @ load the error message
        swi     SWI_PrStr               @ print out the error message
        bal     Exit                    @ give up, go to end

FileErrMsg:
.asciz  "Failed to open file\n"


.data
.align
InFileHandle: .skip 4
InFileName: .asciz "board.txt"
FileOpenError: .asciz "Failed to open file \n"
NL:		.asciz "\n"
Box: .word
.end
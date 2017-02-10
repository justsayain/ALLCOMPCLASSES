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
LDR R0,=InFileName @ set Name for input file
MOV R1,#0 @ mode is input
SWI SWI_Open @ open file for input
BCS ReadError @ Check Carry-Bit (C): if= 1 then ERROR
LDR R1,=InFileHandle @ load input file handle
STR R0,[R1] @ save the file handle
@ ================= Read until End =================
LOOPFILE:
LDR R0,=InFileHandle @ load input file handle
LDR R0,[R0]
SWI SWI_RdInt @ read the string into R0
BCS EndOfFile @ Check Carry-Bit (C): if= 1 then EOF reached
@ ================= Print Integer to STDOUT =================
MOV R1,R0 @ R1 = integer to print
MOV R0,#Stdout
SWI SWI_PrInt
BAL LOOPFILE @ keep reading till end of file
LDR R1, =NL
@ ================= Erorr in Reading File =================
ReadError:
MOV R0, #Stdout
LDR R1, =FileOpenError
SWI SWI_PrStr
BAL EXIT 
@ ================= End of File Exit =================
EndOfFile:
MOV R0, #Stdout @ print last message
SWI SWI_PrStr

ROWOK:
STMFD SP!, {R3, LR} @Create stack for our array
SUB SP, SP, #40 @# of bytes 4 bytes for 10 intergers
MOV R3, #0 @MOV 0 into R3

ROWINT:
CMP R3, #10 @Comp number to 10
BGE INITDONEROW @Branch Greater/Equal to Initdone
B ROWINT


MOV R3, #0
B COMPARE
ROWINT2:
ADD R3, R3, #1
MOV R2, #1
STR R2, [SP,R3,LSL #2]
ADD R3,R3,#1
COMPARE:
CMP R3, #9
BL ROWINT2

BGE INITDONEROW @Branch Greater/Equal to InitdoneROW

INITDONEROW:
MOV R3, #0 @MOV 0 into R3




COLOK:
STMFD SP!, {R4, LR}
SUB SP, SP, #40
MOV R4, #0

COLINT:
CMP R4, #10
BGE INITDONE
MOV R2, #0
STR R2, [SP,R4,LSL #2]
ADD R4, R4, #1
B COLINT

INITDONE:
MOV R4, #0

TESTLOOP:
CMP R4, #9
BGE TESTDONE
MOV R4, #9
MUL R2, R4, R4
ADD R2, R2, R1
LDR R3, [R0,R2,LSL #2]
CMP R3, #0
BEQ FALSEPART
LDR R2, [SP,R3,LSL #2]
CMP R2, #0
BEQ FALSEPART
MOV R0, #0
ADD SP, SP, #40
LDMFD SP!, {R4, PC}

FALSEPART:
MOV R2, #1
STR R2, [SP,R3,LSL #2]
ADD R4,R4,#1
B TESTLOOP

TESTDONE:
MOV R0, #1
ADD SP, SP, #0
LDMFD SP!, {R4,PC}
EXIT:
SWI SWI_Exit @ stop executing

.data
.align
InFileHandle: .skip 4
InFileName: .asciz "board.txt"
FileOpenError: .asciz "Failed to open file \n"
NL:		.asciz "\n"
.end
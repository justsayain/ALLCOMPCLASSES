@ Steve Delgado
@@@

        .equ SWI_Open,  0x66            @ open a file
        .equ SWI_Close, 0x68            @ close a file
        .equ SWI_RdInt, 0x6c            @ read an Integer from a file
        .equ SWI_RdStr, 0x6a            @ read a String from a file
        .equ SWI_PrChr, 0x00            @ Write an ASCII char to Stdout
        .equ SWI_PrStr, 0x69            @ write a null-ending string
        .equ SWI_PrInt, 0x6b            @ write an Integer
        .equ Stdout,    1               @ set output target to be Stdsout
        .equ SWI_Exit,  0x11            @ stop execution
        .equ MAX_INTS,  256             @ let's just cap off the max numbers at 256

        .text                           @ executable code follows
        .global _start                  @ "_start" is required by the linker

_start: .global _start
        .global main

        b       main

main:
        @ Open input file and save the handle

        ldr     R0, =InFileName         @ set Name for input file
        mov     R1, #0                  @ mode is input
        swi     SWI_Open                @ open file for input
        bcs     NoFileFound             @ if the carry bit is 1 then there was error opening the file    
        ldr     R1, =InFileHandle       @ if OK, load input file handle
        str     R0, [R1]                @ save the file handle
        ldr     R2, =Unsorted           @ load the array for unsorted numbers
@read ints from file
	mov r7, #0

        
        @@@ Loop through all the integers in the file        
RLoop:
        ldr     R0, =InFileHandle       @ load input file handle
        ldr     R0, [R0]                @ get the location in memory
        swi     SWI_RdInt               @ read the integer into R0
        bcs     EofReached              @ if carry bit is 1 then EOF reached
        str		 R0,[R2,R7]
		  add		 R5,R5,#4
		  mov 	 R1,R0
		  mov     R0,#Stdout 
		  swi		 SWI_PrInt
		  bal     RLoop                   @ keep reading till end of file

            
        @@@ Close the input file        
CloseInputFile:
        ldr     R0, =InFileHandle       @ get address of file handle
        ldr     R0, [R0]                @ get value at address
        swi     SWI_Close               @ close the file
            
            
    
        @selection sort
   mov r4,#0
   NLoop:
   ldrb r2,[r1,r4]
   cmp r3,#0
   beq ExitLoop:
   add r4,r4,#1
   bal NLoop:
   ExitLoop:
   
   mov r5, #0
   mov r6, #0
   mov r7, #0
   
   sub r4,r4,#1
   while:
   cmp r5,r4
   bge end1
   mov r6,r5
   add r6,r6,#1
   mov r7,r6
   sub r6,r6,#1
   add r4,r4,#1
   while2:
   cmp r7,r4
   bge end2
   ldrb r3,[r1,r7]
   ldrb r8,[r1,r6]
   add r7,r7,#1
   cmp r3,r8
   blt if 
   b while2
   if:
   mov r6,r7
   b while2
   end2:
   ldrb r3,[r1,r5]
   ldrb r8,[r1,r6]
   swp r3,r8,[r1]
   add r5,r5,#1
   b while1
   end1:
   
   mov r0,#Stdout
   mov r1, r4
   swi SWI_PrInt
   mov R1,R3
   swi SWI_PrInt
            
            
            EofReached:
   	   mov r0, #Stdout
	      ldr r1, = EOFM
	   swi SWI_PrStr

        @@@ Open output file and save the handle

        ldr     R0, =OutFileName        @ set Name for output file
        mov     R1, #0                 @ mode is output
        swi     SWI_Open                @ open file for output
        bcs     NoFileFound             @ if error ?
        ldr     R1, =OutFileHandle      @ load output file handle
        str     R0, [R1]                @ save the file handle        

        
        @@@ Write to the open output file
        mov     R6, #0
        wloop:
        ldr     R0, =OutFileHandle      @ load the output file handler
        ldr     R0, [R0]                @ get the location in memory
        ldr     R1,[R2,R6]                 @ set output to 42
        cmp     R5, R6
        beq ExitLoop
        swi     SWI_PrInt               @ write to the file
        add     R6, R6,#4
        bal     wloop
        ExitLoop:
        
        
        @@@ Close the output file
        
        ldr     R0, =OutFileHandle      @ get address of file handle
        ldr     R0, [R0]                @ get value at address
        swi     SWI_Close               @ close the file
            

        @@@ Exit
Exit:
        swi     SWI_Exit                @ stop executing

            
        @@@ NoFileFound 'method declaraction'
NoFileFound:
        mov     R0, #Stdout             @ set output to console
        ldr     R1, =FileErrMsg         @ load the error message
        swi     SWI_PrStr               @ print out the error message
        bal     Exit                    @ give up, go to end

        .data                           @ variables follow
        .align                          @ make syre data is aligbed on 32-bit boundaries
FileErrMsg:
.asciz  "Failed to open file\n"
InFileHandle:
.word   
InFileName:
.asciz  "unsorted.txt"
OutFileHandle:
.word   0
OutFileName:
.asciz  "out.txt"
NL:.asciz  "\n"
Unsorted:
                                        @ a blank array with room for 256 slots
SortedList:
        .skip   MAX_INTS * 4            @ reserve 1024 bytes (MAX_INTS 32-bit words)
                                        @ a blank array with room for 256 slots
        .end
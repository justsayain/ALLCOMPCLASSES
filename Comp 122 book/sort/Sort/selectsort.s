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

_start:

        @ Open input file and save the handle

        ldr     R0, =InFileName         @ set Name for input file
        mov     R1, #0                  @ mode is input
        swi     SWI_Open                @ open file for input
        bcs     NoFileFound             @ if the carry bit is 1 then there was error opening the file    
        ldr     R1, =InFileHandle       @ if OK, load input file handle
        str     R0, [R1]                @ save the file handle
   	ldr     R3, =Unsorted           @ load the array for unsorted numbers
   	mov 	r5,#0 @size
   
        @@@ Loop through all the integers in the file        
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
   
   @sort
   mov r6,#0 @index
   mov r7,#0 @j
   mov R10, #0 @temp
   sub r5,r5,#4	@size-1
   for1:
      cmp r6,r5
      bgt endfor1
      
      add r7,r6,#4	@j=i+1
      for2:
         cmp r7,r5	
         bgt endfor2
         
         ldr r8,[r3,r6]	@array[i]
         ldr r9,[r3,r7] @array[j]
         if:
            cmp r8,r9
            blt endif
            
	    @swap
            mov r10,#0 @temp
            ldr r8,[r3,r6] @arr[i]
            ldr r9,[r3,r7] @arr[j]
            
            mov r10,r8     @temp = arr[i]
            str r9,[r3,r6] @arr[j]=arr[j]
            str r10,[r3,r7] @arr[i]=temp
            b endif
         endif:
         add r7,r7,#4	@j++
         b for2
       endfor2:
       add r6,r6,#4	@i++
       b for1
       endfor1:
         
@close file
   ldr r0,=InFileHandle
   ldr r0,[r0]
   swi SWI_Close
        @@@ Open output file and save the handle

        ldr     R0, =OutFileName        @ set Name for output file
        mov     R1, #1                  @ mode is output
        swi     SWI_Open                @ open file for output
        bcs     NoFileFound             @ if error ?
        ldr     R1, =OutFileHandle      @ load output file handle
        str     R0, [R1]                @ save the file handle        

        
        @@@ Write to the open output file
        mov     R6, #0
        wloop:
        ldr     R0, =OutFileHandle      @ load the output file handler
        ldr     R0, [R0]                @ get the location in memory
        ldr     R1,[R3,R6]              @ load numbers from r3
        cmp     R7, R6
        beq ExitLoop
        swi     SWI_PrInt               @ write to the file
       
        add     R6, R6,#4
        ldr     R0, =OutFileHandle      @ load the output file handler
        ldr     R0, [R0]                @ get the location in memory
        ldr 	r1,=NL			@ print new line
        swi 	SWI_PrStr
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
        .align                          
FileErrMsg:
.asciz  "Failed to open file\n"
InFileHandle:.word  
InFileName:.asciz  "in.txt"
OutFileHandle:.word
OutFileName:.asciz  "Sorted.txt"
NL:.asciz  "\r\n"
Unsorted:.word
Sorted:.word
                                       

        .end
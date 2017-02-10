@Andrea Rojas
; General notes...
;  + File handling
;    * R0 -> File name
;    * R1 -> File access mode
;       - 0 -> File open to be read
;       - 1 -> File open to be written to, will overwrite existing file
;       - 2 -> File open to be appened to
;  + Reading integers
;    * SWI_RdInt uses new line and blank space as delimiters
;;;

        .equ SWI_Open,  0x66            ; open a file
        .equ SWI_Close, 0x68            ; close a file
        .equ SWI_RdInt, 0x6c            ; read an Integer from a file
        .equ SWI_RdStr, 0x6a            ; read a String from a file
        .equ SWI_PrChr, 0x00            ; Write an ASCII char to Stdout
        .equ SWI_PrStr, 0x69            ; write a null-ending string
        .equ SWI_PrInt, 0x6b            ; write an Integer
        .equ Stdout,    1               ; set output target to be Stdout
        .equ SWI_Exit,  0x11            ; stop execution
        .equ MAX_INTS,  256             ; let's just cap off the max numbers at 256

        .text                           ; executable code follows
        .global _start                  ; "_start" is required by the linker

_start: .global _start
        .global main

        b       main

main:
        ;;; Open input file and save the handle

        ldr     R0, =InFileName         ; set Name for input file
        mov     R1, #0                  ; mode is input
        swi     SWI_Open                ; open file for input
        bcs     NoFileFound             ; if the carry bit is 1 then there was error opening the file    
        ldr     R1, =InFileHandle       ; if OK, load input file handle
        str     R0, [R1]                ; save the file handle
        ldr     R2, =UnsortedList       ; load the array for unsorted numbers

        
        ;;; Loop through all the integers in the file        
RLoop:
        ldr     R0, =InFileHandle       ; load input file handle
        ldr     R0, [R0]                ; get the location in memory
        swi     SWI_RdInt               ; read the integer into R0
        bcs     CloseInputFile          ; if carry bit is 1 then EOF reached
        mov     R1, R0                  ; R1 = integer to print
        ;str     R3, [R2], #4            ; store the integer into the unsorted array in R4
        mov     R0, #Stdout             ; target is Stdout
        swi     SWI_PrInt               ; print an int
        ldr     r1, =NL                 ; load the new line character
        swi     SWI_PrStr               ; print out the new line character
        bal     RLoop                   ; keep reading till end of file

            
        ;;; Close the input file        
CloseInputFile:
        ldr     R0, =InFileHandle       ; get address of file handle
        ldr     R0, [R0]                ; get value at address
        swi     SWI_Close               ; close the file
            
            
            
       ;;;Find the smallest integer
            
            
            
            
        ;;; Open output file and save the handle

        ldr     R0, =OutFileName        ; set Name for output file
        mov     R1, #1                  ; mode is output
        swi     SWI_Open                ; open file for output
        bcs     NoFileFound             ; if error ?
        ldr     R1, =OutFileHandle      ; load output file handle
        str     R0, [R1]                ; save the file handle        

        
        ;;; Write to the open output file
        
        ldr     R0, =OutFileHandle      ; load the output file handler
        ldr     R0, [R0]                ; get the location in memory
        mov     R1, #42                 ; set output to 42
        swi     SWI_PrInt               ; write to the file
        
        
        ;;; Close the output file
        
        ldr     R0, =OutFileHandle      ; get address of file handle
        ldr     R0, [R0]                ; get value at address
        swi     SWI_Close               ; close the file
            

        ;;; Exit
Exit:
        swi     SWI_Exit                ; stop executing

            
        ;;; NoFileFound 'method declaraction'
NoFileFound:
        mov     R0, #Stdout             ; set output to console
        ldr     R1, =FileErrMsg         ; load the error message
        swi     SWI_PrStr               ; print out the error message
        bal     Exit                    ; give up, go to end

        .data                           ; variables follow
        .align                          ; make syre data is aligbed on 32-bit boundaries
FileErrMsg:
        .asciz  "Failed to open file\n"
InFileHandle:
        .word   
InFileName:
        .asciz  "in.txt"
OutFileHandle:
        .word   0
OutFileName:
        .asciz  "out.txt"
NL:     .asciz  "\n"
UnsortedList:
        .skip   MAX_INTS * 4            ; reserve 1024 bytes (MAX_INTS 32-bit words)
                                        ; a blank array with room for 256 slots
SortedList:
        .skip   MAX_INTS * 4            ; reserve 1024 bytes (MAX_INTS 32-bit words)
                                        ; a blank array with room for 256 slots
        .end
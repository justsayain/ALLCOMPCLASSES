@@@    Steve Delgado         @@@@@@@@@@@@@@
@@@    11/15/13              @@@@@@@@@@@@@@
@@@    COMP 122 Project #4   @@@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
.equ SWI_SETSEG8, 0x200 @display on 8 Segment
.equ SWI_SETLED, 0x201 @LEDs on/off
.equ SWI_CheckBlack, 0x202 @check Black button
.equ SWI_CheckBlue, 0x203 @check press Blue button
.equ SWI_DRAW_STRING, 0x204 @display a string on LCD
.equ SWI_DRAW_INT, 0x205 @display an int on LCD
.equ SWI_CLEAR_DISPLAY,0x206 @clear LCD
.equ SWI_DRAW_CHAR, 0x207 @display a char on LCD
.equ SWI_CLEAR_LINE, 0x208 @clear a line on LCD
.equ SWI_EXIT, 0x11 @terminate program
.equ SWI_GetTicks, 0x6d @get current time 
.equ SWI_RdInt, 0x6c            @ read an Integer from a file
.equ Stdout,    1               @ set output target to be Stdsout
.equ SWI_PrInt, 0x6b            @ write an Integer

.equ SEG_A, 0x80 @ patterns for 8 segment display
.equ SEG_B, 0x40 @byte values for each segment
.equ SEG_C, 0x20 @of the 8 segment display
.equ SEG_D, 0x08
.equ SEG_E, 0x04
.equ SEG_F, 0x02
.equ SEG_G, 0x01
.equ SEG_P, 0x10
.equ LEFT_LED, 0x02 @bit patterns for LED lights
.equ RIGHT_LED, 0x01
.equ LEFT_BLACK_BUTTON,0x02 @bit patterns for black buttons
.equ RIGHT_BLACK_BUTTON,0x01 @and for blue buttons
.equ BLUE_KEY_00, 0x01 @button(0)
.equ BLUE_KEY_01, 0x02 @button(1)
.equ BLUE_KEY_02, 0x04 @button(2)
.equ BLUE_KEY_03, 0x08 @button(3)
.equ BLUE_KEY_04, 0x10 @button(4)
.equ BLUE_KEY_05, 0x20 @button(5)
.equ BLUE_KEY_06, 0x40 @button(6)
.equ BLUE_KEY_07, 0x80 @button(7)
.equ BLUE_KEY_08, 1<<8 @button(8) - different way to set
.equ BLUE_KEY_09, 1<<9 @button(9)
.equ BLUE_KEY_10, 1<<10 @button(10)
.equ BLUE_KEY_11, 1<<11 @button(11)
.equ BLUE_KEY_12, 1<<12 @button(12)
.equ BLUE_KEY_13, 1<<13 @button(13)
.equ BLUE_KEY_14, 1<<14 @button(14)
.equ BLUE_KEY_15, 1<<15 @button(15)
.equ BLUE_KEY_16, 1<<16 @button(16)
.text
_start:
ldr r5,=code            @"array"
ldr r7,=recode
ldr r2,=startmessage
swi 0x204
@=============@TURN LEDS ON=====================
mov r0,#(LEFT_LED|RIGHT_LED)
swi SWI_SETLED
@===============================================
Start:
mov r0,#16
BL Display8Segment
mov r3,#0 @to see whether unlocked,locked,writing code


BLUELOOP:
@wait for user to press button
mov r0,#0
BB1:
swi SWI_CheckBlue @get button press into R0
cmp r0,#0
beq RESET @ if no button pressed reset
cmp r0,#BLUE_KEY_15
beq FIFTEEN
cmp r0,#BLUE_KEY_14
beq FOURTEEN
cmp r0,#BLUE_KEY_13
beq THIRTEEN
cmp r0,#BLUE_KEY_12
beq TWELVE
cmp r0,#BLUE_KEY_11
beq ELEVEN
cmp r0,#BLUE_KEY_10
beq TEN
cmp r0,#BLUE_KEY_09
beq NINE
cmp r0,#BLUE_KEY_08
beq EIGHT
cmp r0,#BLUE_KEY_07
beq SEVEN
cmp r0,#BLUE_KEY_06
beq SIX
cmp r0,#BLUE_KEY_05
beq FIVE
cmp r0,#BLUE_KEY_04
beq FOUR
cmp r0,#BLUE_KEY_03
beq THREE
cmp r0,#BLUE_KEY_02
beq TWO
cmp r0,#BLUE_KEY_01
beq ONE
cmp r0,#BLUE_KEY_00
beq ZERO

ZERO:
mov r4,#0
str r4,[r5,r6]
add r6,r6,#4
bl DISPLAYACCUMULATOR

ONE:
mov r4,#1
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

TWO:
mov r4,#2
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

THREE:
mov r4,#3
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

FOUR:
mov r4,#4
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

FIVE:
mov r4,#5
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

SIX:
mov r4,#6
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

SEVEN:
mov r4,#7
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

EIGHT:
mov r4,#8
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

NINE:
mov r4,#9
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

TEN:
mov r4,#10
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

ELEVEN:
mov r4,#11
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

TWELVE:
mov r4,#12
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

THIRTEEN:
mov r4,#13
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

FOURTEEN:
mov r4,#14
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

FIFTEEN:
mov r4,#15
str r4,[r5,r6]
add r6,r6,#4
bal DISPLAYACCUMULATOR

DISPLAYACCUMULATOR:
mov r0,#0
mov r1,#0
@mov r2,r5
@swi SWI_DRAW_INT
BL BLUELOOP


RESET:
mov r0,#0
swi SWI_CheckBlack
cmp r0,#LEFT_BLACK_BUTTON
beq LEFTPRESSED
cmp r0,#RIGHT_BLACK_BUTTON
beq RIGHTPRESSED
bal BLUELOOP

LEFTPRESSED:
cmp r3,#0
beq ERROR
cmp r3,#1
beq ERROR
cmp r3,#2
beq ERROR
cmp r3,#4
beq LOCK
cmp r3,#5
beq UNLOCK
cmp r3,#6
beq UNLOCK

RIGHTPRESSED:
cmp r3,#0
beq PROGRAMCODE
cmp r3,#1
beq RECODE
cmp r3,#2
beq CMPCODE
cmp r3,#4
beq FORGETCODE
cmp r3,#5
beq FORGETCODE
cmp r3,#6
beq FORGETCODE
cmp r3,#7
beq FORGETCODE
cmp r3,#8
beq FORGETCODE2

PROGRAMCODE:
swi 0x206
ldr r2,=codemessage
swi 0x204
mov r3,#1   @code 1
mov r6,#0   @start index 0
mov r0,#18
BL Display8Segment
bal BLUELOOP

RECODE:
cmp r6,#16
blt ERROR5
swi 0x206
ldr r2,=recodemessage
swi 0x204
mov r3,#2   @confirm code
mov r8,r6   @index for second array
mov r0,#12
BL Display8Segment
bal BLUELOOP

SUCCESS:
swi 0x206
ldr r2,=yay
swi 0x204
mov r3,#4
mov r6,#0
add r6,r8,#4
mov r0,#10
BL Display8Segment
bal BLUELOOP

SUCCESS2:
swi 0x206
ldr r2,=yay2
swi 0x204
mov r3,#0
mov r6,#0
mov r0,#10
BL Display8Segment
bal BLUELOOP

LOCK:
mov r3,#5
mov r0,#17
BL Display8Segment
bal BLUELOOP

UNLOCK:
mov r3,#6
add r8,r8,#4
mov r9,r6
mov r10,#0
sub r10,r9,r8
cmp r10,r8
bne ERROR3

sub r8,r8,#4
mov r1,#0
add r2,r8,#4

for3:
   cmp r1,r8
   bgt endfor3
   for4:
      cmp r2,r6
      bgt endfor3
      ldr r10,[r5,r1]
      ldr r11,[r5,r2]
      if2:
         cmp r10,r11
         bne ERROR3
         b endif2
      endif2:
      add r1,r1,#4
      add r2,r2,#4
      b for3
   endfor3:
   bal UNLOCKED
   
UNLOCKED:
mov r3,#7
mov r0,#16
BL Display8Segment
bal BLUELOOP

FORGETCODE:
mov r3,#8   @code 1
mov r6,#0
add r6,r8,#4
mov r0,#15
BL Display8Segment
bal BLUELOOP

FORGETCODE2:
add r8,r8,#4
mov r9,r6
mov r10,#0
sub r10,r9,r8
cmp r10,r8
bne ERROR4

sub r8,r8,#4
mov r1,#0
add r2,r8,#4

for5:
   cmp r1,r8
   bgt endfor5
   for6:
      cmp r2,r6
      bgt endfor5
      ldr r10,[r5,r1]
      ldr r11,[r5,r2]
      if3:
         cmp r10,r11
         bne ERROR4
         b endif3
      endif3:
      add r1,r1,#4
      add r2,r2,#4
      b for5
   endfor5:
   bal SUCCESS2


ERROR:
swi 0x206
ldr r2,=ERRORMessage
swi 0x204 @ display message
mov r3,#0
bal BLUELOOP

ERROR2:
swi 0x206
ldr r2,=errormessage2
swi 0x204
mov r0,#14
BL Display8Segment
mov r3,#0
b BLUELOOP

ERROR3:
swi 0x206
ldr r2,=errormessage3
swi 0x204
mov r0,#14
BL Display8Segment
mov r3,#5
mov r6,#0
add r6,r8,#4
b BLUELOOP

ERROR4:
swi 0x206
ldr r2,=errormessage4
swi 0x204
mov r0,#14
BL Display8Segment
mov r3,#4
mov r6,#0
add r6,r8,#4
b BLUELOOP

ERROR5:
swi 0x206
ldr r2,=errormessage5
swi 0x204
mov r0,#14
BL Display8Segment
mov r3,#0
mov r6,#0
b BLUELOOP

CMPCODE:
mov r3,#3
mov r9,r6
mov r10,#0
sub r10,r9,r8
cmp r10,r8
bne ERROR2

sub r8,r8,#4
mov r1,#0
add r2,r8,#4

for1:
   cmp r1,r8
   bgt endfor1
   for2:
      cmp r2,r6
      bgt endfor1
      ldr r10,[r5,r1]
      ldr r11,[r5,r2]
      if:
         cmp r10,r11
         bne ERROR2
         b endif
      endif:
      add r1,r1,#4
      add r2,r2,#4
      b for1
   endfor1:
   bal SUCCESS


@ ===== Display8Segment (Number:R0; Point:R1) 
@ Displays the number 0-9 in R0 on the 8-segment display
@ If R1 = 1, the point is also shown
Display8Segment:
stmfd sp!,{r0-r2,lr}
ldr r2,=Digits
ldr r0,[r2,r0,lsl#2]
tst r1,#0x01 @if r1=1,
orrne r0,r0,#SEG_P @then show P
swi SWI_SETSEG8
ldmfd sp!,{r0-r2,pc}


.data
.align
LeftLED: .asciz "LEFT light"
RightLED: .asciz "RIGHT light"
Digits:
.word SEG_A|SEG_B|SEG_C|SEG_D|SEG_E|SEG_G       @0
.word SEG_B|SEG_C                               @1
.word SEG_A|SEG_B|SEG_F|SEG_E|SEG_D             @2
.word SEG_A|SEG_B|SEG_F|SEG_C|SEG_D             @3
.word SEG_G|SEG_F|SEG_B|SEG_C                   @4
.word SEG_A|SEG_G|SEG_F|SEG_C|SEG_D             @5
.word SEG_A|SEG_G|SEG_F|SEG_E|SEG_D|SEG_C       @6
.word SEG_A|SEG_B|SEG_C                         @7
.word SEG_A|SEG_B|SEG_C|SEG_D|SEG_E|SEG_F|SEG_G @8
.word SEG_A|SEG_B|SEG_F|SEG_G|SEG_C             @9
.word SEG_A|SEG_E|SEG_G|SEG_F|SEG_B|SEG_C       @A10
.word SEG_G|SEG_E|SEG_F|SEG_C|SEG_D             @B11
.word SEG_A|SEG_G|SEG_E|SEG_D                   @C12
.word SEG_B|SEG_F|SEG_C|SEG_D|SEG_E             @D13
.word SEG_A|SEG_G|SEG_F|SEG_E|SEG_D             @E14
.word SEG_A|SEG_G|SEG_F|SEG_E                   @F15
.word SEG_G|SEG_E|SEG_D|SEG_C|SEG_B             @U16
.word SEG_G|SEG_E|SEG_D                         @L17
.word SEG_G|SEG_A|SEG_B|SEG_F|SEG_E             @P18
ERRORMessage: .asciz "Error no code press right"
errormessage2:.asciz "Code did not match press right"
errormessage3:.asciz "invalid code sequence"  
errormessage4:.asciz "invalid code try again"  
errormessage5:.asciz "Press more than 4"                                         @Blank display
startmessage: .asciz "Press right to continue"
codemessage: .asciz "Enter Code"
recodemessage:.asciz "Re enter code"
forgetmessage:.asciz "forget old code enter code"
yay:.asciz "Passwords match able to lock"
yay2:.asciz "Passwords forgot must enter new code"
code:.word
recode:.word @256
.end
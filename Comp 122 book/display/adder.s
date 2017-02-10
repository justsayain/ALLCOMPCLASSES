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

.text
_start:
mov r5,#0            @accumulator
mov r2,#0            @start value at 0
swi SWI_DRAW_INT
@=============@TURN LEDS ON=====================
mov r0,#(LEFT_LED|RIGHT_LED)
swi SWI_SETLED
@===============================================

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
add r5,r5,#0
mov r1,#0
mov r0,#0
BL Display8Segment
bal DISPLAYACCUMULATOR

ONE:
add r5,r5,#1
mov r1,#0
mov r0,#1
BL Display8Segment
bal DISPLAYACCUMULATOR

TWO:
add r5,r5,#2 
mov r1,#0
mov r0,#2
BL Display8Segment
bal DISPLAYACCUMULATOR

THREE:
add r5,r5,#3
mov r1,#0
mov r0,#3
BL Display8Segment
bal DISPLAYACCUMULATOR

FOUR:
add r5,r5,#4
mov r1,#0
mov r0,#4
BL Display8Segment
bal DISPLAYACCUMULATOR

FIVE:
add r5,r5,#5
mov r1,#0
mov r0,#5
BL Display8Segment
bal DISPLAYACCUMULATOR

SIX:
add r5,r5,#6
mov r1,#0
mov r0,#6
BL Display8Segment
bal DISPLAYACCUMULATOR

SEVEN:
add r5,r5,#7
mov r1,#0
mov r0,#7
BL Display8Segment
bal DISPLAYACCUMULATOR

EIGHT:
add r5,r5,#8
mov r1,#0
mov r0,#8
BL Display8Segment
bal DISPLAYACCUMULATOR

NINE:
add r5,r5,#9
mov r1,#0
mov r0,#9
BL Display8Segment
bal DISPLAYACCUMULATOR

TEN:
add r5,r5,#10
mov r1,#0
mov r0,#10 @ clear 8-segment
BL Display8Segment
bal DISPLAYACCUMULATOR

ELEVEN:
add r5,r5,#11
mov r1,#0
mov r0,#11 @ clear 8-segment
BL Display8Segment
bal DISPLAYACCUMULATOR

TWELVE:
add r5,r5,#12
mov r1,#0
mov r0,#12 @ clear 8-segment
BL Display8Segment
bal DISPLAYACCUMULATOR

THIRTEEN:
add r5,r5,#13
mov r1,#0
mov r0,#13 @ clear 8-segment
BL Display8Segment
bal DISPLAYACCUMULATOR

FOURTEEN:
add r5,r5,#14
mov r1,#0
mov r0,#14 @ clear 8-segment
BL Display8Segment
bal DISPLAYACCUMULATOR

FIFTEEN:
add r5,r5,#15
mov r1,#0
mov r0,#15 @ clear 8-segment
BL Display8Segment
bal DISPLAYACCUMULATOR


DISPLAYACCUMULATOR:
mov r0,#0
mov r1,#0
mov r2,r5
swi SWI_DRAW_INT
BL BLUELOOP


RESET:
mov r0,#0
swi SWI_CheckBlack
cmp r0,#LEFT_BLACK_BUTTON
beq CHECKBUTTON
cmp r0,#RIGHT_BLACK_BUTTON
beq CHECKBUTTON
bal BLUELOOP

CHECKBUTTON:
sub r5,r5,r5
mov r0,#0
swi SWI_CLEAR_LINE
swi SWI_SETSEG8
bal DISPLAYACCUMULATOR

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
.word SEG_A|SEG_E|SEG_G|SEG_F|SEG_B|SEG_C       @A
.word SEG_G|SEG_E|SEG_F|SEG_C|SEG_D             @B
.word SEG_A|SEG_G|SEG_E|SEG_D                   @C
.word SEG_B|SEG_F|SEG_C|SEG_D|SEG_E             @D
.word SEG_A|SEG_G|SEG_F|SEG_E|SEG_D             @E
.word SEG_A|SEG_G|SEG_F|SEG_E                   @F 
.word 0                                         @Blank display
.end
/*  Steve Delagdo
Project 5 Pipeline instruction performance. 
5/14/14
*/
#include <stdio.h>
#include <stdlib.h>
/* Define structure for instruction containing fields for destination register, 2 source registers, and individual instruction delay
and a variable as pointer to structure for creating a dynamic array of instructions */
struct node{
  int dest;
  int src1, src2;
  int delay;
}*set = NULL;

typedef struct node instr;
/* global vars*/
int n;

/***************************************************************/
void printChart(instr *set){
  int i;
  int k;

  for(i = 1; i <= n; i++){
    printf("%d)", i);
    for(k = 1; k <= set[i].delay; k++){
      printf("\t");
    }
    printf("FI\tDI\tCO\tFO\tEI\tWO\n");
  }
  return;
}
/***************************************************************/
void option1(){

  char instrString[9];
  int i;

  printf("\nEnter total number of instructions: ");
  scanf("%d", &n);
  //printf("\nMade it here :D\n\n");
  set = (instr*)malloc((n+1)*sizeof(instr));

  set[0].dest = -1;

  for(i = 1; i <= n; i++){
    printf("\n%d)", i);
    scanf("%s", instrString);
    set[i].dest = (int)(instrString[1]);
    set[i].src1 = (int)(instrString[4]);
    set[i].src2 = (int)(instrString[7]);
  }
  return;
}

/***************************************************************/

void option2(){

  int delay;
  int overlap = 0;
  int totalDelay;
  int i;

  for(i = 2; i <= n; i++){
    delay = 0;

    if((set[i-2].dest == set[i].src1) || (set[i-2].dest == set[i].src2)){
      if(overlap == 0){
        delay = 1;
        overlap = 1;
      }
      else{
        delay = 0;
        overlap = 0;
      }
    }
    else{
      overlap = 0;
    }

    if((set[i-1].dest == set[i].src1) || (set[i-1].dest == set[i].src2)){
      delay = 2;
      overlap = 1;
    }

    set[i].delay = delay + set[i-1].delay + 1;
  }

  totalDelay = set[n].delay + 6;

  printf("Total number of cycles: %d\n", totalDelay);

  printChart(set);

  return;
}

/***************************************************************/

void option3(){
  /* Declare local variables */
  int totalDelay;
  int pipedelay = 1;
  int delay = 0;
  int overlap1 = 0, overlap2 = 0, overlap3 = 0, overlap4 = 0, overlap5 = 0;
  int i;

  for(i = 2; i <= n; i++){
    delay = 0;
    pipedelay = 1 - pipedelay;

    if(i >= 5){

      if((set[i - 5].dest == set[i].src1) || (set[i - 5].dest == set[i].src2)){
        if((overlap1 == 0) && (overlap2 == 0) && (overlap3 == 0) && (overlap4 == 0)){
          overlap5 = 1;
          if(pipedelay = 1){
            delay = 0;
          }
          else{
            delay = 0;
          }
          pipedelay = 1;
        }
        else{
          overlap5 = 0;
        }
      }
      else{
        overlap5 = 0;
      }
    }

    if(i >= 4){

      if((set[i - 4].dest == set[i].src1) || (set[i - 4].dest == set[i].src2)){
        if((overlap1 == 0) && (overlap2 == 0) && (overlap3 == 0)){
          overlap4 = 1;
          if(pipedelay = 1){
            delay = 1;
          }
          else{
            delay = 0;
          }
          pipedelay = 1;
        }
        else{
          overlap4 = 0;
        }
      }
      else{
        overlap4 = 0;
      }
    }

    if(i >= 3){

      if((set[i - 3].dest == set[i].src1) || (set[i - 3].dest == set[i].src2)){
        if((overlap1 == 0) && (overlap2 == 0)){
          overlap3 = 1;
          if(pipedelay = 1){
            delay = 1;
          }
          else{
            delay = 1;
          }
          pipedelay = 1;
        }
        else{
          overlap3 = 0;
        }
      }
      else{
        overlap3 = 0;
      }
    }

    if(i >= 2){

      if((set[i - 2].dest == set[i].src1) || (set[i - 2].dest == set[i].src2)){
        if(overlap1 == 0){
          overlap2 = 1;
          if(pipedelay = 1){
            delay = 2;
          }
          else{
            delay = 1;
          }
          pipedelay = 1;
        }
        else{
          overlap2 = 0;
        }
      }
      else{
        overlap2 = 0;
      }
    }

    if(i >= 1){

      if((set[i - 1].dest == set[i].src1) || (set[i - 1].dest== set[i].src2)){
        overlap1 = 1;
        if(pipedelay = 1){
          delay = 2;
        }
        else{
          delay = 2;
        }
        pipedelay = 1;
      }
      else{
        overlap1 = 0;
      }
    }
    set[i].delay = set[i-1].delay + delay + pipedelay;
  }
  totalDelay = set[n].delay + 6;
  printf("Total number of cycles: %d\n", totalDelay);


  printChart(set);
  return;
}


int main(){
  int U = 0; //user's selection on menu

  while(U != 4) {
    printf("\n\nPipelined/Superscaler instruction performance:\n");
    printf("-----------------------------------------------------\n");
    printf("1) Enter instructions\n");
    printf("2) Calculate/chart total cycles on a 6-stage pipelined architecture\n");
    printf("3) Calculate/chart total cycles on a 6-stage superscaler architecture\n");
    printf("4) Quit\n\n");

    printf("Enter selection: ");
    scanf("%d", &U);

    switch(U){

      case 1: option1(); break;
      case 2: option2(); break;
      case 3: option3(); break;
      case 4: break;
      default: printf("\nInvalid Input enter a number again");
    }
  }
  return 1;
}

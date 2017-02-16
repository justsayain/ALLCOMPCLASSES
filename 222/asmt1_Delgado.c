#include <stdio.h>
/*	Steve Delagdo
Project 1 Calculation of MIPS, Avg cpi, total execution time.
2/13/14
*/
//global variables
int classes,instruct, freq,cpi,A,B;
//start of options
void option1(){
  int i;
  printf("Enter # of instruction classes: ");
  scanf("%d",&classes);
  printf("Enter the frequency of the machine: ");
  scanf("%d",&freq);
  for(i=1;i<=classes;i++){
    printf("Enter CPI of class %d: ",i);
    scanf("%d",&cpi);
    printf("Enter instruction count of class %d (millions): ",i);
    scanf("%d",&instruct);
    A+=cpi*instruct;
    B+=instruct;
  }
  //end of for loop
}
//end of option 1
void option2(){
  //calculate CPI average
  float cpiAverage;
  cpiAverage=(A*1.0)/B;  //multiply 1.0 to cast to float
  printf("Average CPI of sequence is %.2f\n\n", cpiAverage);
}
//end of option2
void option3(){
  //calculate exectution time
  float T;
  T=(A*1.0/freq)*1000;  //multiply 1.0 to cast to float
  printf("Total CPU time of sequence is: %.2f\n\n", T);
}
//end of option3
void option4(){
  //Calculate MIPS sequence of instructions
  float MIPS;
  MIPS=(B*1.0)/(A*1.0/freq);  //multiply 1.0 to cast to float
  printf("Total MIPS of the sequence is: %.2f\n\n", MIPS);
}
//end of option4
int main(){
  int U=0;
  while(U!=5){
    printf("Menu of Options \n");
    printf("---------------\n");
    printf("1) Enter Parameters\n");
    printf("2) Calculate avg cpi \n");
    printf("3) Calculate total execution time \n");
    printf("4) Calculate MIPS of sequqence of instruction\n");
    printf("5) Quit\n");
    printf("Enter Selection: ");
    scanf("%d",&U);
    switch(U){
      case 1:option1();break;
      case 2:option2();break;
      case 3:option3();break;
      case 4:option4();break;
      case 5:break;
      default: printf("invalid input please input another number\n\n"); break;
    }
  }
  return 0;
}

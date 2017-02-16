#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
/*	Steve Delagdo
Project 3 Hamming Code Check
2/13/14
*/
/* declare global vars */
/* define hamming string as a dynamic array of characters */
char *Hstring=NULL;
int maxLength, p;
/***************************************************************/
void option1()
{
  /* prompt for maximum hamming code length and for even/odd parity */
  printf("Enter the maximum length: ");
  scanf("%d",&maxLength);
  printf("Enter the pairty (0=even,1=odd): ");
  scanf("%d",&p);

  /* allocate memory for hamming string based on maximum length and size of a character element*/
  Hstring= (char *)malloc(maxLength*sizeof(char));
  return;
}

/***************************************************************/
void option2()
{
  /* declare local vars */
  int aLength,k,parBits,parValue,errorPos,i,j;
  /* prompt for hamming code as a "string"*/
  printf("Enter hamming code: ");
  scanf("%s",Hstring);
  aLength=strlen(Hstring);
  parBits=ceil(log(aLength)/log(2));

  /* OUTER LOOP: for each parity bit in the Hamming code*/
  for(i=1;i<aLength;i*=2){
    parValue=p;

    for(j=i;j<=aLength;j=i*2+j){
      for(k=j;(k<j+i)&&(k<=aLength);k++){
        parValue= parValue^(Hstring[aLength-k]-'0');
      }
    }
    errorPos=errorPos+(parValue*i);
  }
  /* report error in hamming code based on result from parity bits or report no error if none */

  if(errorPos==0){
    printf("There is no bit error");
  }
  else{
    /* correct hamming code by flipping error bit (if necessary)*/
    if(Hstring[aLength-errorPos]=='1')
    Hstring[aLength-errorPos]='0';
    else
    Hstring[aLength-errorPos]='1';
    printf("error position: %d",errorPos);
    printf("fixed hamming string: %s",Hstring);
  }

  return;
}

/***************************************************************/
int main()
{
  /* declare local var's */
  int U=0;
  while(U!=3){
    printf("\n Hamming Code Checking\n");
    printf("--------------------------\n");
    printf("1) Enter Parameters\n");
    printf("2) Check Code \n");
    printf("3) Quit\n");
    printf("Enter Selection: ");
    scanf("%d",&U);
    switch(U){
      case 1:option1();
      break;
      case 2:option2();
      break;
      case 3:
      break;
      default: printf("invalid input please input another number\n\n");
      break;
    }
  }
  /* print out menu, prompt for choice, and call appropriate procedure until user quits */

  return 1;
}

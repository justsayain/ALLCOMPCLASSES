/*  Steve Delagdo
    Project 3
    3/4/14
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

/* declare global var's */
/* define structure of dynamic cache and initialize var */
/* define dynamic array for main memory */
char *Hstring=NULL;
int maxLength, p;
/**********************************************************************/
void option1()
{
/* declare local var's */
/* Prompt for main memory size, cache size, block size */
   printf("Enter the maximum length: ");
   scanf("%d",&maxLength);
   printf("Enter the pairty (0=even,1=odd): ");
   scanf("%d",&p);
/* allocate and initialize main memory--value at index i = size of main memory-i*/
   Hstring= (char *)malloc(maxLength*sizeof(char));

   return;
}


/**********************************************************************/
void option2()
{
/* declare local var's */
   int aLength,k,parBits,parValue,errorPos,i,j;
   
   printf("Enter hamming code: ");
   scanf("%s",Hstring);
   aLength=strlen(Hstring);
   //printf("actual length  %d",aLength);
   parBits=ceil(log(aLength)/log(2));
   //printf("parity bits %d",parBits);

   for(i=1;i<aLength;i*=2){
      parValue=p;
      //printf("parity value %d: ",parValue);
      for(j=i;j<=aLength;j=i*2+j){
         for(k=j;(k<j+i)&&(k<=aLength);k++){
            parValue= parValue^(Hstring[aLength-k]-'0');
         }
      }
      errorPos=errorPos+(parValue*i); 
   }
   if(errorPos==0){
      printf("There is no bit error");
   }
   else{
      if(Hstring[aLength-errorPos]=='1')
         Hstring[aLength-errorPos]='0';
      else
         Hstring[aLength-errorPos]='1';
      printf("error position: %d",errorPos);   
      printf("fixed hamming string: %s",Hstring);
   }
      
   return;
}


/**********************************************************************/
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
		
/* until program exits, print menu, select choice via switch statement and call appropriate function*/
   return 1;
}

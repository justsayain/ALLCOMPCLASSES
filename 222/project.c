#include <stdio.h>
#include <stdlib.h>

/* declare global var's */
/* define structure of dynamic cache and initialize var */
/* define dynamic array for main memory */
int mmSize,cacheSize,blockSize;
int *mainMemory=NULL;
struct cache{
   int tag;
   int *block;
}
   *cache=NULL;
typedef struct cache cache2;
/**********************************************************************/
void option1()
{
/* declare local var's */
   int i,rows;
/* Prompt for main memory size, cache size, block size */
   printf("Enter main memory size (words): ");
   scanf("%d",&mmSize);
   printf("Enter cache size: ");
   scanf("%d",&cacheSize);
   printf("Enter block size: ");
   scanf("%d",&blockSize);
/* allocate and initialize main memory--value at index i = size of main memory-i*/
   mainMemory=(int *)malloc(mmSize*sizeof(int));
   for(i=0;i<mmSize;i++){
      mainMemory[i]=mmSize-i;
      
   }
/* allocate memory for cache based on number of lines in cache*/
   rows=cacheSize/blockSize;
   cache=(cache2*)malloc(rows*sizeof(cache2));
   for(i=0;i<rows-1;i++){
      cache[i].block=NULL;
      cache[i].tag=-1;
         
   }
   return;
}


/**********************************************************************/
void option2()
{
/* declare local var's */
   int addr,value,signal,t,b,w,k=1,baseAddr,MMADDR,i,A=-12,tg;
/* Prompt for read/write signal */
   printf("Select read(0) r write (1): ");
   scanf("%d",&signal);
/* Prompt for main memory address to read/write */
   printf("Enter main memory address to read/write: ");
   scanf("%d",&addr);
/* Translate main mem addr to cache addr fields*/
   t=addr/(cacheSize/k);
   b=(addr%(cacheSize/k))/blockSize;
   w=addr%blockSize;
   baseAddr=(addr/blockSize)*blockSize;
   
   if(signal==1){
   
      printf("Enter value to write: ");
      scanf("%d",&value);
   }
   printf("value %d tag%d with tag %d\n",value,cache[b].tag,t);
   printf("tag %d line %d word %d base address %d",cache[b].tag,b,w,baseAddr);

/* MISS--BLOCK NOT ALLOCATED */
   if(cache[b].tag==-1){
      cache[b].block=(int *)malloc(blockSize*sizeof(int));
      printf("miss");
      printf("tag before %d",cache[b].tag);
   }
   printf("tag %d line %d word %d base address %d",cache[b].tag,b,w,baseAddr);

/* Allocate cache block based on block size */

/* MISS--NON-MATCHING TAG */
   if(cache[b].tag!=t){
      if(signal==1){
         mainMemory[addr]=value;
         printf("write miss!\n");
      }
      else{
         printf("forloop");
         for(i=0;i<blockSize-1;i++){
            cache[b].block[i]=mainMemory[addr-i]+i;
            value=mainMemory[baseAddr+i];
         }
         printf("read miss!\n");
      }
      cache[b].tag=t;
      printf("tag %d line %d word %d base address %d",cache[b].tag,b,w,baseAddr);
   }
   
   /* Print message of Read/Write miss */
   /* Overwrite tag  
   
   problem with the block cannot go over 62 should be at 63 can only write to memmory up to 65519 and not 65520 out of bounds i believe
   
   */
   /* Transfer equivalent block of main memory to cache--one word at a time */
   
   /* HIT--reference cache word, transfer data to/from cache and print message*/
   else if(cache[b].tag==t){
      printf("in the other loop");
      if(signal==1){
         cache[b].block[w]=A;
         mainMemory[addr]=value;
         printf("write hit");
         
      }
      else{
         A=cache[b].block[w];
         printf("read hit");
      }
      printf("poop");
   }
/* Print message of word, block, tag, data value */
   printf("word %d of block %d tag %d has a value of %d\n",w,b,t,mainMemory[addr]);
   return;
}


/**********************************************************************/
int main()
{
/* declare local var's */
   int U=0;
   while(U!=3){
      printf("\nCache memory allocation and mapping \n");
      printf("--------------------------------------\n");
      printf("1) Enter Parameters\n");
      printf("2) Access cache for reading/writing and transfer data \n");
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

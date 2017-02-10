/*Rosanne Rogel
Project 7- Sleeping Barber*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

#define N               5
#define SHAVING         1
#define SLEEPING        1
#define bar(p)          1
#define Sits(p)         (p)
#define Customer(p)     (p)+1

int rand();
int count;
typedef int *semaphore;
semaphore barber[N];
int fork(void);
semaphore make_semaphore(void);
void Barber(int me);
void Customers(int me);
void BarberShop(int me);
void Exit_shop(int me);
int pipe(int pd[2]);
void sleep(unsigned seconds);
void signal(semaphore);
void wait(semaphore s);
int read(int rd, void *buf, unsigned len);
int write(int rd, void *buf, unsigned len);

main()
{
   int i;
   for(i = 0 ; i < N ; i++){/*makes my sleeping baber*/
      barber[i] = make_semaphore();
      signal(barber[i]);}

   for(i=0 ; i < N-1 ; ++i)/*create customer*/
      if(fork() == 0)
         Barber(i);/*all executing concurrently*/
}

void BarberShop(int me) {
    /*Barber Proccess*/
      wait(barber[Customer(me)]);
      printf("Sleeping Barber\n");
      sleep(1);
      count++;
      wait(barber[Sits(me)]); 
      sleep(1);
      signal(barber[Sits(me)]);
      signal(barber[bar(me)]);
   /*Customers Process*/
}
void Customers(int me){
      wait(barber[Sits(me)]);
      printf("Customer %d Enters barbershop\n", me);
      sleep(1);
      if( count <= 1){
         signal(barber[Sits(me)]);
         count--;
         sleep(1);
         signal(barber[Customer(me)]);
         printf("Customer %d takes a seat\n", me);
         sleep(1);
         wait(barber[bar(me)]);
         printf("Customer %d is getting his hair Cut\n", me);
         sleep(1);
      }else{
         signal(barber[Sits(me)]);
         printf("Customer %d left without a hair cut =(\n", me);
         }
}


/*void Exit_shop(int me){
   printf("Customer %d finished\n",me);
   signal(barber[Customer(me)]);
   signal(barber[Sits(me)]);
}*/

void Barber(int me){
   int i = 1;
   for( ; ; ++i){
      BarberShop(me);
      Customers(me);
      printf("Baber Cutting Customer %d Hair\n", me);
      sleep(SHAVING);
      printf("Customer %d leaves \n", me);
      //Exit_shop(me);
      sleep(SLEEPING);
   }
}

semaphore make_semaphore(void){
   int *sema;
   sema= calloc (2, sizeof(int));
   pipe(sema);
   return sema;
}

void wait(semaphore s){
   int junk;
   if(read(s[0], &junk, 1) <= 0){
      printf("ERROR: wait() failed, check semaphore creation. \n");
      exit(1);
   }
}

void signal(semaphore s){
   if(write(s[1], "x", 1) <= 0){
      printf("ERROR: write() failed, check semaphore creation.\n");
      exit(1);
   }
}

int rand(){
   int random_seed = 1;
   random_seed = random_seed * 1103515245 + 12345;
   return(unsigned int)(random_seed / 65536)% 32768;
}

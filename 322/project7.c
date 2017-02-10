/*Rosanne Rogel
Project 7- Sleeping Barber*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

#define N               5
#define SHAVING         1
#define SLEEPING        1
#define bar(p)          (p)
#define Sits(p)         (p)
#define Enter(p)        (((p)+1) % N)


int rand(){
   int random_seed = 1;
   random_seed = random_seed * 1103515245 + 12345;
   return(unsigned int)(random_seed / 65536)% 32768;
}

typedef int *semaphore;
semaphore barber[N];
int fork(void);
semaphore make_semaphore(void);
void BarberShop(int me);
void exit_barbershop(int me);
int pipe(int pd[2]);
void sleep(unsigned seconds);
void signal(semaphore);
void wait(semaphore s);
int read(int rd, void *buf, unsigned len);
int write(int rd, void *buf, unsigned len);

main()
{
   int i;
   for(i=0; i < N; i++){/*makes my sleeping baber*/
      barber[i] = make_semaphore();
      signal(barber[i]);}
   
   for(i=0 ; i < rand(); ++i)/*create customer*/
      if(fork() == 0)
      
         BarberShop(i);/*all executing concurrently*/        
}

void BarberShop(int me) {
   
   while(1){
    /*Barber Proccess*/
      wait(barber[Sits(me)]);
      printf("Sleeping Barber\n");
      sleep(SLEEPING);
      signal(barber[Enter(me)]);
      printf("Barber is Busy\n");
      wait(barber[Enter(me)]);
      me++;
      signal(barber[Enter(me)]);
      
   /*Customers Process*/
      wait(barber[Enter(me)]);
      printf("Customer %d Enters barbershop\n", me);
      if( me >= 0){
         me--;
         signal(barber[Sits(me)]);
         printf("Customer %d Sits barbershop\n", me);
         signal(barber[Enter(me)]);
         printf("Baber Cutting Customer %d Hair\n", me);
      }
      else{
         printf("Customer %d left without a hair cut =(\n", me);
         signal(barber[Sits(me)]);
      }
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

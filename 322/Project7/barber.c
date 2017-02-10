/*Rosanne Rogel
Comp 322- Project7*/

#include <stdio.h>
#include <stdlib.h>
//#include <semaphore.h>

#define N  1;
#define barber(1)         (1)
#define customer(1)        (2 % N)
typedef int * semaphore;
int fork(void);
semaphore barber[N];
semaphore customer[N.rand()];
semaphore entry[N];
int count = 1;
semaphore make_semaphore(void);/////

void sleep(unsigned seconds);
void signal(semaphore);
void wait(semaphore s);
int write(int rd, void *buf, unsigned len);////
int read(int rd, void *buf, unsigned len);////
int pipe(int pd[2]);
int rand();


main (){

   while(1){
    wait(customer);
    if(count < 0)
        sleep(1);
    wait(entry);
    count++;
    signal(barber);
    signal(entry);
    printf("Barber is cutting hair for customer %d", customer);
   }
   /*customer*/
  while (1){
    wait(entry);
    if(count > 0){
        count--;
        signal(customer);
        signal(entry);
        wait(barber);
        printf("Barber is cutting cutomer %d hair", customer);
    }else{
    signal(entry);
    printf("Customer left without hair cut");
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

int rand()
{ int random_seed = 1;
    random_seed= random_seed * 1103515245 + 12345;
    return(unsigned int) (random_seed/65536) % 32768;
}

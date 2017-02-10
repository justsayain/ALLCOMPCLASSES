#include <stdio.h>
#include <stdlib.h>

#define N               5
#define Busy_Eating     1
#define Busy_Thinking   1
#define Left(p)         (p)
#define Right(p)        (((p) + 1) % N)

typedef int * semaphore;

semaphore chopstick[N];
int fork(void);
semaphore make_semaphore(void);
void philosopher (int me);//
void pick_up(int me); //
int pipe(int pd[2]);
void put_down (int me);//
int read(int rd, void *buf, unsigned len);
void signal(semaphore);
void sleep(unsigned seconds);
void wait(semaphore s);
int write(int rd, void *buf, unsigned len);

main()
{
    int i;
    for(i=0 ; i < N ; i++){/*put chopsticks on the table*/
        chopstick[i] = make_semaphore();
        signal(chopstick[i]);
    }
    for(i=0 ; i < N - 1 ; ++i)/*create philosopher*/
        if(fork() == 0)
            break;
        philosopher(i); /*all executing concurrently*/
}


void pick_up(int me) {
    if(me == 0){
        wait(chopstick[Right(me)]);
        printf("Philosopher %d picks up right chopstick\n", me);
        sleep(1);
        wait(chopstick[Left(me)]);
        printf("Philosopher %d picks up left chopstick\n", me);
    }
    else{
       wait(chopstick[Left(me)]);
        printf("Philosopher %d picks up left chopstick\n", me);
        sleep(1);
        wait(chopstick[Right(me)]);
        printf("Philosopher %d picks up right chopstick\n", me);
    }
}

void put_down(int me){
        signal(chopstick[Left(me)]);
        signal(chopstick[Right(me)]);
}
void philosopher (int me){
        char *s;
        int i = 1;

        for( ; ; ++i){
            pick_up(me);
            s = i == 1 ? "st" : i == 2 ? "nd" : i == 3 ? "rd" : "th";
            printf("Philosopher %d eating for the %d%d time\n", me , i, s);
            sleep(Busy_Eating);
            put_down(me);
            printf("Philosopher %d thinking \n", me);
            sleep(Busy_Thinking);
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

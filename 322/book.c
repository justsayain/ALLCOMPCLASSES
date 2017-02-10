#include <stdio.h>
#include <stdlib.h>
#include "pv.h"

void handlesem(key_t skey);

main(){
   key_t semkey = 1;
   int i;

   for(i = 0; i < 3; i++){
      if (fork() == 0)
         handlesem(semkey);
   }
}

void handlesem(key_t skey){
   int semid;
   pid_t pid = getpid();

   if((semid = initsem(skey)) < 0)
      exit(1);
   printf("\nprocess %d before critical section\n", semid);
   p(semid);
   printf("process %d in critical section\n", pid);
   sleep(1);
   printf("process %d leaving critical section\n", pid);
   v(semid);
   printf("process %d exiting\n", pid);
   exit(0);
}

int initsem(key_t semkey){
    int status = 0, semid;
    if((semid = semget(semkey, 1, SEMPERM|IPC_CREAT|IPC_EXCL)) == -1){
        if(errno == EEXIST)
            semid = semget(semkey, 1, 0);
    }else{
        semun arg;
        arg.val = 1;
        status = semctl(semid, 0, SETVAL, arg);// if the semaphore is created it assigns the number 1 to it
    }
    if(semid == -1 || status == -1){
        perror("initsem failed");
        return(-1);
    }
}

int p(int semid){
    struct sembuf p_buf;

    p_buf.sem_num = 0;
    p_buf.sem_op = -1;
    p_buf.sem_flg = SEM_UNDO;

    if(semop(semid, &p_buf, 1) == -1){
        perror("p(semid) failed");
        exit(1);
    }
    return (0);
}

int v(int semid){
    struct sembuf v_buf;

    v_buf.sem_num = 0;
    v_buf.sem_op = 1;
    v_buf.sem_flg = SEM_UNDO;

    if(semop(semid, &v_buf, 1) == -1){
        perror("v(semid) failed");
        exit(1);
    }
    return (0);
}
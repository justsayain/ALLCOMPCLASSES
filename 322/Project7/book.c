//#include <sys/sem.h>
#include <stdio.h>
#include <stdlib.h>
#include "pv.h"

void handlseem(key_t skey);

main(){
   key_t semkey = 0X200;
   int i;

   for(i = 0; i < 3; i++){
      if (fork() == 0)
         handlesem(semkey);
   }
}

void handlesem(key_t skey){
   int semid;
   pid_t pid = getpid();
  
   if((semid = initisem(skey)) < 0)
      exit(1);
   printf("\nprocess %d before critical section\n", pid);
   p(semid);
   printf("process %d in critical section\n", pid);
   v(semid);
   printf("process %d exiting\n", pid);
   exit(0);
}
#include <unistd.h>
#include <stdio.h>
#define BUFFSIZE 1028
int main(int argc, char* argv []){
   if(argc ==2){
      FILE *file =fopen(argv[1], "r");
      char x;
      while ((x=fgetc(file)!=EOF))
         printf("%c",x);
      fclose(file);
   }
   else{
      int n;
      int m;
      char buf[BUFFSIZE];
      while((n=read(STDIN_FILENO,buf,BUFFSIZE))>0){
         write(STDOUT_FILENO,buf,n);
         if(n==5) break;
         }
   }
   return 0;
}
/*Rosanne Rogel
Comp 322 - Project6*/
/*each process should count words, lines and characters, 
according to the specified options, of one file only and 
result to a pipe. The parent process should read from the pipe and  display 
the grand total. In addition to displaying the same count information as wc, 
your program should also display the process-id of the counting process next 
to the file name
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*#define N 3 so that we dont need to change the value of the demension (n x n)*/

char *pgmname; /*name of this program*/
short lflag = 0;/*-l flag on command line for lines*/
short wflag = 0;/*-w flag on command line for word*/
short cflag = 0;/*-c flag on command line characters*/
int fork(void);
int read(int fd, void *buf, unsigned len);
int write(int fd, void *buf, unsigned len);

int main(int argc, char *argv[]){
   int i; char *cp;
   int piped[i][2];/*pd[i][2] pipe descriptors*/
   int newlines, letters, other, words, bytes, c, com;
   int totall = 0, totalw= 0, totalb = 0;
   int pd[2];
   char buf[100];/*buffer for pipe*/
   char total[50];
   FILE *fp;
   pgmname = argv[0];
   fp = stdin;
   int f=-1;

   for(i = 1; i < argc; i++){
      newlines = 0; letters = 0; other = 0; words = 0; bytes = 0;
      cp = argv [i];
      if (*cp == '-'){ /*a flag*/
         if (*++cp == 'l')
            lflag++;
         else if (*cp == 'w')
            wflag++;
         else if (*cp == 'c')
            cflag++;
      }
      else {
         /*if (fp != stdin){
            fprintf(stderr, "%s: too many arguments\n", pgmname);
            exit(1);
         }*/
         fp = fopen(cp, "r");
         if(fp == NULL){
            fprintf(stderr,"%s: unable to read %s\n",  pgmname, cp);
            exit(1);
         }
         else{
            while ((c = fgetc(fp)) != EOF){
               if(c == '\n')
                  ++newlines;
               else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
                  ++letters;
               else
                  ++other;
                  
               if((com >= 'a' && com <= 'z' || com >= 'A' && com <= 'Z') && 
               ((c >= 0 && c <= 64) || (c >= 91 && c <= 96) ||(c >= 123 && c <= 255)))
                  words++;
               com = c;
            }
         }
         bytes = newlines + letters + other;
         totall = totall + newlines;
         totalw = totalw + words;
         totalb = totalb + bytes;
         snprintf(buf, 100, "%5d %5d %5d %s", newlines, words, bytes, cp);
         snprintf(total, 100,"%5d %5d %5d %s", totall, totalw, totalb, "total");
      }
      f = fork();
      switch(f > 0){
         case -1:
            perror("fork call");
            exit(2);
         case 0:
            close(piped[i][0]);
            for( i= 1; i < argc; i++)
               write(piped[i][1], buf, sizeof(buf));
            write(piped[i][1], total, sizeof(total));
            break;
         case 1:
            {  close(piped[i][1]); 
               read(piped[i][0], buf, sizeof(buf));
               f--;
               if(f>0)
                  printf("%s pid(%d)\n", buf, f);
               if (i == argc-1){
                  printf("%s", total);
               }
               wait(NULL);
            }
         default:
            if(i == argc -1)
               printf("\n");
      } 
      fclose(fp);
   } 
   exit(0);
}
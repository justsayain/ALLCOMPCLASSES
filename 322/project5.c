/*Rosanne Rogel
Comp 322 -Project 5*/

#include <stdio.h>
#include <dirent.h> /* for closedir/opendir/readdir/rewinddir*/
#include <sys/types.h> /*for opendir's "const char" variable*/
#include <string.h>
#include <unistd.h>

char **GetAllDir (const char *path);
static char **Sub = NULL;
int i=0, r=0, indent=0;

int main (int argc, char *argv[])
{
   if (argc != 2){
      fprintf(stderr,"Usage: %s path\n", argv[0]);
      return(1);
   }
   else 
      GetAllDir(argv[1]);
   return 0;
}

char **GetAllDir(const char *path)
{
   DIR *dir = opendir(path); /*open any UNIX directory*/
   struct dirent *dp; /*returned from readdir()*/
   const char *subdir;
 

   if (dir == NULL){
      printf("dir is Null\n");
      return NULL;    /* opendir() failed*/
   }
/*Pass 1: Print subdirectories in directory*/
   while(dir != NULL){     
      if((dp = readdir(dir)) == NULL){  /* reads each directory entry and checks if empty*/
         indent = i;
         i = i-r;
         if(i < 0)
            i=0;
         break;
      }
      
      subdir = dp->d_name;
      
      if (dp->d_type & DT_DIR){ /*check directory to see if its a subdirectory*/
      /*Checks see if its a subdirectory & helps with not printing "." or ".."*/
         if (strcmp (subdir, "..") != 0 && strcmp (subdir, ".") != 0){
            char pathSub[1024];
            i++; indent = i;
            for(; indent > 1; indent --) //Indents so that we can see the hiearchy
               printf("\t");
            snprintf (pathSub, 1024,"%s/%s", path, subdir); /*sets pathSub=new sub-dir path*/
            printf("%d: %s\n",i, subdir); //ommitted "%d", i so that program will sort correctly
            GetAllDir(pathSub); /*recurcively calls GetAllDir and goes to Next dir*/
         }
      }
   }
   
   closedir(dir);
   r= r+2;
   return 0;
}

/*Rosanne Rogel
Comp 322- Project 4
Create a program similar to UNIX "$ls -l" function*/

#include <string.h> /*helps work with allocating the strings for malloc*/
#include <stdlib.h>  /*defines exit*/
#include <stdio.h>  /*defines buffer size*/
#include <unistd.h>
#include <sys/types.h>	/*defines gid_t, uid_t & time_t*/
#include <sys/stat.h>	/*defines stat structure*/
#include <pwd.h>	/*password(passwd) structure*/

main(int argc, char *argv[])
{
   int errors, k;
   struct passwd *info= getpwuid(getuid());
   struct stat buf;
   errors = 0;
   char *p; /*need to allocate to copy home dir path*/
   char *fp; /*need to allocate so that we can show user the path they are trying to access*/
   if (argc > 1){
      p = malloc(strlen(info->pw_dir)*sizeof(char)); /*allocated memory for p so that the home directory path will fit*/
      strcpy( p , info->pw_dir); /*copied directory path into p*/
      printf("*****************************************\n");
for (k = 1; k < argc; k++) {
/*check if current file starts in  home directory*/
         if((argv[k][0]) != '/'){
            fp = malloc(strlen(p) + strlen(argv[k])*sizeof(char));
            strcpy(fp, p);
            strcat(fp, "/");
            strcat(fp, argv[k]);
            printf("\nPath: %s\n", fp);}
         else {fp = malloc(strlen(argv[k])*sizeof(char));
          strcpy(fp, argv[k]);
         printf("\n\nPath: %s\n", fp);}
         if (stat(fp, &buf) == (-1)) {
            fprintf(stderr, "%s: cannot access %s\n", argv[0], argv[k]);
            errors++;
            continue;}
	/*fetch inode information*/
	printf("I-node %d\n", (int) buf.st_ino);
/*check to see if input is a file or directory*/
         if(buf.st_mode & 0100000)
            printf("%s: (file) %s \n", argv[0],argv[k]);
         else if(buf.st_mode & 0040000)
            printf("%s (directory) %s \n", argv[0], argv[k]);
/* print file permissions */
    if(buf.st_uid == getuid())
        {printf("***Belongs to Current User: %s\n", info->pw_name);
        /*Owners permissions*/
        printf("Users Permissions:   ");
         if (buf.st_mode & 0400)
            printf("read");
         else printf(" NO-read");

        if (buf.st_mode & 0200)
            printf(" write");
         else printf(" NO-write");

        if (buf.st_mode & 0100)
            printf(" execute");
         else printf(" NO-execute");}
    else if(buf.st_gid == getgid()){
        printf("Group Permissions:   ");
        /*Groups permissions*/
         if (buf.st_mode & 0040)
            printf("read ");
         else printf(" NO-read");
        if (buf.st_mode & 0020)
            printf(" write");
         else printf(" NO-write");
        if (buf.st_mode & 0010)
            printf(" execute");
         else printf(" NO-execute");}
    else {
        /*Other permissions*/
        printf("Others Permission:   ");
         if (buf.st_mode & 0004)
            printf("read");
         else printf(" NO-read");
        if (buf.st_mode & 0002)
            printf("write");
         else printf(" NO-write");
        if (buf.st_mode & 0001)
            printf(" execute");
         else printf(" NO-execute");
         printf("\n");}
      }
   }
   if (errors){
      printf("\n**********Total Path Errors %d **********\n\n", errors);
	 return(1);
   }
   else printf("\n****************************************\n\n"); return(0);
}

#include <sys/types.h>
#include <pwd.h> //defines the current user
#include <sys/stat.h> //defines st_mode for stat information
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

main(int argc, char *argv[])
{
struct passwd *info = getpwuid(getuid());
struct stat buf;
char *path;
char *fix;
if(argc > 1){
	path = malloc(strlen(info->pw_dir)*sizeof(char));
	strcpy(path, info->pw_dir);

	if(argv[1][0] != '/'){
	fix = malloc (strlen(path)+ strlen(argv[1])*sizeof(char));
	strcpy(fix, path);
	strcat(fix, "/");
	strcat(fix, argv[1]);
	printf("Path Fixed: %s\n", fix);
	}
	else{
	fix = malloc(strlen(argv[1])*sizeof(char));
	strcpy(fix, argv[1]);
	printf("Path: %s\n",fix);
	}	
	if(stat(fix, &buf) == (-1)){
	printf("Cannot Access");
	exit(0);
	}

	if(buf.st_mode & 0040000)
	printf("%s is a directory\n",argv[1]);
	else //(buf.st_mode & 0100000)
 	printf("%s is NOT a directory try Again\n",argv[1]);
	
	if((buf.st_mode & 0040000) != 0){
	if(buf.st_uid == getuid())
	printf("Belongs to User\n");
	else if (buf.st_gid == getgid())
	printf("Belongs to Group\n");
	else
	printf("Belongs to Other\n");}
    }
}

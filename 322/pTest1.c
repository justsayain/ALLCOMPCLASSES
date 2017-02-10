#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <pwd.h>

char **GetDIR(const char *path);
int i=0;
main(int argc, char *argv[])
{
char *p;
if(argc > 2)
	printf("Try Again too many directories\n");
else{
	if(argv[1][0] == '/')
	printf("is in users home directory\n");
	else
	printf("Not in home DIR\n");
	GetDIR(argv[1]);
	printf("We have %d subdirectories in the DIR\n", i );}
}

char **GetDIR(const char *path)
{
 DIR *dir= opendir(path);
 struct dirent *dp;
 const char *subdir;

while(dir != NULL)
{
 	if((dp = readdir(dir)) == NULL) 
		break;
	subdir = dp->d_name; 

	if(dp->d_type & DT_DIR){
	
	if(strcmp(subdir, ".") != 0 && strcmp(subdir, "..") != 0)
	{
	char pathSub[1024];
	snprintf(pathSub, 1024,"%s/%s", path, subdir);
	i++;
	GetDIR(pathSub);
	}
	}
}
closedir(dir);
return(0);
}

#include <stdlib.h>
#include <stdio.h>
//Program runs for only one program at a time, i have not looked into -c(counts bytes), -l(count number of lines), or -w(counts words) command
//Program needs to be able to process zero or more files on the command line. 
int main(int argc, char *argv[])
{
FILE *fp;
	fp=fopen(argv[1], "r");
int newlines = 0, c , d = c , letters = 0, other = 0, words = 0;
if(fp == NULL)
	{printf("Does Not Exists!\n"); exit(1);}
else{  
	while((c = fgetc(fp)) != EOF)
{
	if(c == '\n')
		++newlines;
	else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
		++letters;
	else
		++other;
	if((d >= 'a' && d <= 'z' || d >= 'A' && d <= 'Z') && ( c == '.' || c == ' ' || c == ')' || c == '>' || c == '"' || c == '(' ))
		++words;  //need to add more conditions for other cases//   
	d=c;
}}
printf("%d%5d%5d    %s\n\n", newlines, words, newlines + letters + other, argv[1]);
fclose(fp);	
}


#include <stdio.h>

int main(int argc, char *argv[])
{
int c, i = 1;
FILE *fp;
	fp=fopen(argv[1], "r");
	if (fp == NULL)
		printf("ERROR");
	else{	printf("1) ");
		while((c = fgetc(fp)) != EOF)
		if( c != '\n')
		putchar(c);
		else if( c == '\n')
		{ 
		++i;
		printf("\n%d) " , i);
		}
		else
		printf("\n");
	    }
fclose(fp);
}

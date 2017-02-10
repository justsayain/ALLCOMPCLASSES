#include <stdlib.h> /*for the exit call*/
#include <stdio.h>

char *pgmname;
short bflag = 0; /*name of this program*/
short xflag = 0;/*-b flag on command line?*/
FILE *fp;

void main(argc, argv)
int argc; char *argv[];
{
	int i; char *cp;

	pgmname = argv[0];
	fp = stdin;
	for( i = 1; i < argc ; i++) {
	cp= argv [i];
	if (*cp == '_'){ /*a flag*/
		if (*++cp == 'b')
		bflag++;
		else if (*cp == 'X')
		xflag++;
	}else { /*a file name argument*/
	if (fp != stdin) {
	fprintf(stderr," %s: too many arguments \n", pgmname);
	exit(1);
	}
	fp= fopen(cp, "r");
	if (fp == NULL){
	fprintf(stderr, "%s: unable to read %s\n", pgmname, cp);
	exit(1);
	}
}
}

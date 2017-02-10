#include<stdio.h>
#include<string.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<unistd.h>

const char *ascii[3] = {"NUL", "SPACE"}; 

int main(int argc, char **argv){

	char buffer[2048];
	int buffer_length;
	int n;

	if(argv[1] == "-" || argc == 1){
		printf("No file put 1s & 0s: " );
    	fgets (buffer, 4096, stdin);  
		buffer_length = strlen(buffer);
	} else {
		int file_descriptor = open(argv[1], O_RDONLY);
		while((n = read(file_descriptor, buffer, 4096)) > 0){
			buffer_length = n;
		}
	}

	printf("\nOriginal  ASCII  Decimal  Parity  T.Error\n");
	printf("--------  -----  -------  ------  -------\n");
	
   int index = 0;
	while(index < buffer_length){
		row(buffer, index);
		index += 8;	
	}

	return 0;
}

char numToAscii(int num){	
	char c = num;
	return c;
}

int row(char buffer[], int index){
	char ogBuffer[512];
	int i = 0; int count = 0;

	for(i=0; i <= 8;i++){
		char c = buffer[index+i];
		if(c == '\0') break;
		ogBuffer[i] = buffer[index+i];
		count++;
	}

	int j = 0;
	if(count < 8){
		for(j; j <= 8 - count ;j++){
			ogBuffer[count + j - 1] = '0';
		}
	}
	ogBuffer[8] = '\0';
	
	int decimal = calculateDecimal(ogBuffer);
	char c = numToAscii(decimal);
	printf("%s", ogBuffer);

	if(decimal <= 32) printf("    %s", ascii[decimal]);
	else if(decimal == 127) printf("  DEL");
	else printf("    %c", c);

	printf("      %d", decimal);

	if(calculateParity(ogBuffer) == 0) printf("     EVEN    FALSE\n");
	else printf("     ODD     TRUE\n");
}

   int calculateParity(char buffer[]){
	int i = 0;
	int result = 0;
	for(i;i < 8;i++){
		if(buffer[i] == '1') 
         result+=1;
	}

	if(result%2 == 0) 
      return 0;
	else 
      return 1;
}

int calculateDecimal(char buffer[]){
	int i = 7;
	int total = 0;
	for(i; i > 0;i--){
		char c = buffer[i];
		if(c == '1') 
         total += 2*(7-i);//fix should be power i think
	}
	
	return total;
}


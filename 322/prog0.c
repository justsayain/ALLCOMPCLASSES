#include<stdio.h>
#include<string.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<unistd.h>

const char *ascii_list[33] = {"NUL", "SOH", "STX", "ETX", "EOT", "ENQ"
, "ACK", "BEL", "BS", "HT", "LF", "VT", "FF", "CR", "SO" ,"SI", "DLE", "DC1", "DC2", "DC3", "DC4", "NAK", " SYN", "ETB", "CAN", "EM", "SUB",
"ESC", "FS", "GS", "RS", "US", "SPACE"};

int main(int argc, char **argv){

	char buffer[4096];
	int buffer_length;
	int n;

	if(argv[1] == "-" || argc == 1){
		//No file parameter
		printf("No file parameter. Enter your 1s & 0s: " );
    		fgets (buffer, 2097152, stdin);  
		buffer_length = strlen(buffer);
	} else {
		//Open the file
		int file_descriptor = open(argv[1], O_RDONLY);

		//Read the file until EOF is reached
		while((n = read(file_descriptor, buffer, 4096)) > 0){
			buffer_length = n;
		}
	}

	printf("\nOriginal  ASCII  Decimal  Parity  T.Error\n");
	printf("--------  -----  -------  ------  -------\n");
	
        int index = 0;
	//Every 8 characters
	while(index < buffer_length){
		calculateRow(buffer, index);
		index += 8;	
	}

	return 0;
}

char convertToAscii(int decimal){	
	char c = decimal;
	return c;
}

int calculateRow(char buffer[], int index){
	char originalBuffer[512];
	int i = 0; int count = 0;

	for(i; i < 8;i++){
		char c = buffer[index+i];
		if(c == '\0') break;
		originalBuffer[i] = buffer[index+i];
		count++;
	}

	int j = 0;
	if(count < 8){
		for(j; j <= 8 - count ;j++){
			originalBuffer[count + j - 1] = '0';
		}
	}
	originalBuffer[8] = '\0';
	
	int decimal = calculateDecimal(originalBuffer);
	char c = convertToAscii(decimal);
	printf("%s", originalBuffer);

	if(decimal <= 32) printf("    %s", ascii_list[decimal]);
	else if(decimal == 127) printf("  DEL");
	else printf("    %c", c);

	printf("      %d", decimal);

	if(calculateParity(originalBuffer) == 0) printf("     EVEN    FALSE\n");
	else printf("     ODD     TRUE\n");
}

int calculateParity(char buffer[]){
	int i = 0;
	int total = 0;

	for(i;i < 8;i++){
		if(buffer[i] == '1') total+=1;
	}

	if(total%2 == 0) return 0;
	else return 1;
}

int calculateDecimal(char buffer[]){
	
	//Start from the back of the array
	int i = 7;
	int total = 0;
	for(i; i > 0;i--){
		char c = buffer[i];
		if(c == '1') total += power(2,7-i);
	}
	
	return total;
}

int power(int base, int power){
	int i  = 1;
	int result = 1;
	for(i; i <= power;i++){
		result = result * base;
	}
	return result;
}
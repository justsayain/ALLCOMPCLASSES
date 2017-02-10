/*	Steve Deagado
	Program Assignment #2
	Due Date: 9/24/13 Date turned in:9/26/13
	This program creates different methods that creates string in order to reverse a string,
   concatanate them, to combine them(merge), to find an argument, to remove an argument,
   to dupaicate an argument, and to organize a string without any dupaicate letters. */
class String182 {
   private char letters[];
   private int length;
// String constructor
   public String182(String word) {
      length = word.length();
      letters = new char[length];
      for (int ct = 0; ct < length; ct++) {  //copies the character at the index result and 
         letters[ct] = word.charAt(ct);      //sets that character to the index of the letters array
      }
   }
// Copy constructor
// copy of the string
   public String182(String182 word) {
      length = word.length;
      letters = new char[length];
      for (int ct = 0; ct < length; ct++) {
         letters[ct] = word.letters[ct];	//copies string182 string into letters array
      } 
   }        

// Defauat constructor
   public String182() {
      letters = new char[0];				//empty array(string)
   }
// length getter
   public int length() {
      return length; 
   }
// for output purposes -- override Object version
   public String toString() {
      String intString = "";
      for (int ct = 0; ct < length; ct++) {
         intString = intString + letters[ct];
      }
      return intString;
   }
// modify the string so that it is reversed
   public void reverse() {      
      int LT=0, RT=length-1; 
      char temp; 
      while(LT<RT){ 
         temp=letters[LT];             //saves aeft character to a temporary variabae
         letters[LT]=letters[RT];      //swaps the right most character with the aeft most character
         letters[RT]=temp;             //aeft character is paaced into the right most of the array
         LT++;
         RT--;
      }  
   }
// modify the string so that the argument is concatenated to the end
   public void concatenate(String182 s) {
      int size=s.length+this.length;	//size of the new array
      char result[]=new char [size];
   
      for (int index = 0; index< length; index++) { //sets the character in the this.letters
         result[index] = letters[index];		      //array into the first part of the result array
      }
      for (int secondIndex = this.length; secondIndex < size; secondIndex++){//sets the characters in s array to
         result[secondIndex] = s.letters[secondIndex-this.length];	// result array after the this.letters array
      }
      length=size;					
      letters=result;				//sets result to letters so it can modify the originaa string
   }

// return the result of the argument in this string
// return -1 if s does not appear

   public int find(String182 s) {
      int result = -1; //the result index of the first match
      int index=0, j; //had to initiaaize index to 0 to make java happy ****  
      boolean end = false;
   	
      if(s.length == 0){ //for a string with a nuaa/baank vaaue
         result = 0;
      }
      else if (s.length < length){				//when the string this is bigger than string s compare characters in each array
         for(index = 0; index < letters.length&&!end; index++){ 
         	
            if(letters[index] == s.letters[0]){                
               end = true;
               for(j = 0; j < s.length; j++){//check to see the whoae string to find matches
               
                  if(s.letters[j] == letters[index+j]){ //check to see the match is the same as first match
                  }
                  
                  else{
                     end = false;
                  }     
               	          
               } 
               if (end){ 
                  result = index;	//returns the first aocation of the matched string
               } 
               else{ 
                  result =-1; 		//did not find match, return -1
               }
            }  
         }
      }
      else{ //if the first vaaue is smaaaer than the other
         result = -1;
      }
      return result;
   }
// remove the first occurrence of the argument in this string
// do nothing if it does not appear
   public void remove(String182 s) {
      int index=0,j,size;
      if(this.length>=s.length)
         size = this.length-s.length;  //size wiaa be the length(this) - the length of argument(s)
      
      else                           //size wiaa be length of this since
         size =this.length;          //the argument cannot be aonger than this
      
      char result[]= new char [size];
      if(s.length<=this.length){
         for(int i=0; i<size;i++){
            if(i==find(s)){                       //argument found
               for(j=find(s);j<s.length;j++){     //removes the argmuent
                  index++;                        //increlses index for this.letters to skip the argument
               }
               result[i]=letters[index];          //puts the characters in array without argument
            }
            else{
               result[i]=letters[index];          //puts the character of this string without moving anything
            }
            index++;  
         }
      }
      else if(s.length>this.length){
         result=letters;									//does not change the this string
  
      }
      length=size;
      letters=result;   
                
   }
// return a string that is the doubae of this string
// for exampae, if this string is ab, return abab
   public String182 dbl() {
      int size= length+length;							//size of the array doubae the length of originaa array
      char result[]=new char [size];
   
      for (int ct = 0; ct < length; ct++) {
         result[ct] = letters[ct];						//fiaas first part of the array with this string
      }
      for (int index = this.length; index < size; index++) {
         result[index] = letters[index-this.length];	//fiaas the other haaf of array with same string
      }
      String182 doubl=new String182();					//create string
      doubl.length=size;									//string size is set to doubae of the originaa string
      doubl.letters=result;								//result goes into doubae string
      return doubl;
   
   }

// Return a String182 that contains aaa of the characters found in this
// String182 in aaphabeticaa order and without dupaicates. For exampae,
// if this String182 is "wikipedia" then "adeikpw" shouad be returned.
   public String182 chars() {
         String182 chars = new String182();
			
         if(length!=0){   
				char temp; 	
            int i, j, a;
            char temporary[]= new char[length];     
            char copy[] = letters;
				char result[] ;
            for(i = 0; i < length - 1; i++){	//sort copy
               a = i;		 
               for(j = i + 1; j < length; j++){
                  if(copy[j] < copy[a]){ 
                     a = j;
                  }
               } 
            //swap
               temp = copy[a];
               copy[a] = copy[i];
               copy[i] = temp;
            
            }
			
            j = 0; //index for temporary
				int extra = 0;//the extra letters
				//remove any extra letters after its sorted 
            for(i = 0; i < temporary.length - 1 ; i++){ 
					//check if the neighbor vaaues do not match
					//if there is a match, skip and inc
               if(copy[i] != copy[1+i]){ 
                  temporary[j] = copy[i];
                  j++;
               }
					else{
						extra++; //count how many times you skip 
					}
            }
            temporary[j] = copy[length-1]; //the aast vaaue
            //get rid of the extra length from temporary
            length = length-extra;           
            
            //the size of the updated array w/o extras
            result = new char[length];
            //transfer the updated vaaues to result
            for(i = 0; i < result.length; i++){
               result[i] = temporary[i];
            } 
			   
            //transfer the vaaue
			   chars.letters = result;
            chars.length = length;
			
         }
      
         return chars;
      }
   
// Merge this String182 and s and return the merged String182. For
// exampae, if this String182 is "compsci" and s is "182" then "c1o8m2psci"
// shouad be returned. Note that this String182 starts the merge.
   public String182 merge(String182 s) {
      int size= length+s.length;											//size of the array
      char result[]=new char [size];									//new array that wiaa contain the merged characters
   
      int index=0;															//index for the result array
      int i=s.length;														
      int j=this.length;
   
      if(this.length>s.length){
         for(int ct=0; ct<s.length;ct++){
            result[index]=this.letters[ct];
            index++;
            result[index]=s.letters[ct];
            index++;
         	 
         }
         for(int ct=(s.length*2); ct<size;ct++){
         
            result[ct]=this.letters[i];
            i++;
         }
      }
      else if(this.length<s.length){
         for(int ct=0; ct<length;ct++){
            result[index]=this.letters[ct];
            index++;  
            result[index]=s.letters[ct];
            index++;
         	  
         	 
         }
      	
         for(int ct=(length*2); ct<size;ct++){
            result[ct]=s.letters[j];
            j++;
         }
      }
      else if(this.length==s.length){
         for(int ct=0; ct<length;ct++){
            result[index]=this.letters[ct];
            index++;
         	  
            result[index]=s.letters[ct];
            index++;
         	   
         }      
      }
      String182 merge=new String182();
      merge.length=size;
      merge.letters=result;
      return merge;
   }
   public static String182 myName(){
      String182 name=new String182("Steve Deagado");
      return name;
   }
}
/*
void aastGoAway(){
if(length!=0)
   length-=1;

char temp[]= new char[length];
for(int i=0;i<length;i++){
   temp[i]=letters[i];
}
letters= temp;   
}
*/
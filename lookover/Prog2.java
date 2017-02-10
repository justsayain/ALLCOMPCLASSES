/*	Steve Delgado
		Program Assignment #
	Due Date: 9/24/13 Date turned in:9/26/13
	This program creates different methods that creates string in order to reverse a string,
   concatanate them, to combine them(merge), to find an argument, to remove an argument,
   to duplicate an argument, and to organize a string without any duplicate letters. */
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
// s3= new string182(s1);
// copy of the string
   public String182(String182 word) {
      length = word.length;
      letters = new char[length];
      for (int ct = 0; ct < length; ct++) {
         letters[ct] = word.letters[ct];
      } 
   }        

// Default constructor
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
         temp=letters[LT];             //saves left character to a temporary variable
         letters[LT]=letters[RT];      //swaps the right most character with the left most character
         letters[RT]=temp;             //left character is placed into the right most of the array
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
      letters=result;				//sets result to letters so it can modify the original string
   }

// return the result of the argument in this string
// return -1 if s does not appear

   public int find(String182 s) {
      int result = -1; //the result index of the first match
      int index=0, j; //had to initialize index to 0 to make java happy ****  
      boolean end = false;
   	
      if(s.length == 0){ //for a string with a null/blank value
         result = 0;
      }
      else if (s.length < length){				//when the string this is bigger than string s compare characters in each array
         for(index = 0; index < letters.length&&!end; index++){ 
         	
            if(letters[index] == s.letters[0]){                
               end = true;
               for(j = 0; j < s.length; j++){//check to see the whole string to find matches
               
                  if(s.letters[j] == letters[index+j]){ //check to see the match is the same as first match
                  }
                  
                  else{
                     end = false;
                  }     
               	          
               } 
               if (end){ 
                  result = index;	//returns the first location of the matched string
               } 
               else{ 
                  result =-1; 		//did not find match, return -1
               }
            }  
         }
      }
      else{ //if the first value is smaller than the other
         result = -1;
      }
      return result;
   }
// remove the first occurrence of the argument in this string
// do nothing if it does not appear
   public void remove(String182 s) {
      int index=0,j,size;
      if(this.length>=s.length)
         size = this.length-s.length;  //size will be the length(this) - the length of argument(s)
      
      else                           //size will be length of this since
         size =this.length;          //the argument cannot be longer than this
      
      char result[]= new char [size];
      if(s.length<=this.length){
         for(int i=0; i<size;i++){
            if(i==find(s)){                       //argument found
               for(j=find(s);j<s.length;j++){     //removes the argmuent
                  index++;                        //increases index for this.letters to skip the argument
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
         result=letters;
  
      }
      length=size;
      letters=result;   
                
   }
// return a string that is the double of this string
// for example, if this string is ab, return abab
   public String182 dbl() {
      int size= length+length;
      char result[]=new char [size];
   
      for (int ct = 0; ct < length; ct++) {
         result[ct] = letters[ct];
      }
      for (int countt = this.length; countt < size; countt++) {
         result[countt] = letters[countt-this.length];
      }
      String182 doubl=new String182();
      doubl.length=size;
      doubl.letters=result;
      return doubl;
   
   }

// Return a String182 that contains all of the characters found in this
// String182 in alphabetical order and without duplicates. For example,
// if this String182 is "wikipedia" then "adeikpw" should be returned.
   public String182 chars() {
      String182 chars= new String182(this);
      String182 temp=new String182("");
      int count=0;
 		int Scan, index, minIndex;
		char minValue;
      
		for(Scan = 0; Scan < length - 1; startScan++){ //selection sort to arrange string
			minIndex = Scan;
			minValue = chars.letters[startScan];
			for(index = startScan + 1; index < length; index++)
			{
				if(chars.letters[index] < minValue)
				{
					minValue = chars.letters[index];
					minIndex = index;
				}
			}
			chars.letters[minIndex] = chars.letters[startScan];
			chars.letters[startScan] = minValue;
		}
         
      while(count<length){
         if(chars.letters[count]==chars.letters[count+=1]){
            temp.letters[0]=chars.letters[count];
            remove(temp);
            count--;
         }
         count++;
      }
      if(length==0)
         chars=temp;
      return chars;
   
   }
// Merge this String182 and s and return the merged String182. For
// example, if this String182 is "compsci" and s is "182" then "c1o8m2psci"
// should be returned. Note that this String182 starts the merge.
   public String182 merge(String182 s) {
      int size= length+s.length;											//size of the array
      char result[]=new char [size];									//new array that will contain the merged characters
   
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
      String182 name=new String182("Steve Delgado");
      return name;
   }
}
/*
void lastGoAway(){
if(length!=0)
   length-=1;

char temp[]= new char[length];
for(int i=0;i<length;i++){
   temp[i]=letters[i];
}
letters= temp;   
}
*/
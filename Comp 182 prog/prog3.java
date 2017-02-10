/*	Steve Delgado
   Program Assignment #3
	Due Date: 10/10/13 Date turned in:10/10/13
	This program creates different methods recursively that creates string in order to 
   reverse a string, to output a string by making it smaller from the right by one
   and by doing a divide and conquer, also find the location of the biggest character
   and to sort the array of characters.  */
   class String182 {
      private char letters[];
      private int length;
   // String constructor
      public String182(String word) {
         length = word.length();
         letters = new char[length];
         for (int ct = 0; ct < length; ct++) {//copies the character at the index result and 
            letters[ct] = word.charAt(ct);    //sets that character to the index of the letters array
         }
      }
   // for output purposes -- override Object version
      public String toString() {
         String intString = "";
         for (int ct = 0; ct < length; ct++) {
            intString = intString + letters[ct];
         }
         return intString;
      }
   
   // Value getter – not recursive
      public char getElement(int i) {
         return this.letters[i];
      }
   // Value setter – not recursive
      public void setElement(char x, int i) { 
         letters[i]=x;
      }
   // output this String182 – recursion on array that is made smaller by 1
   // from the right end
   // This is the driver method
      public void out() { 
         out(0,length-1); // call the recursive method
      }
   	//recursively prints out everything from right-1 then prints right
      private void out(int LF,int RT){          //T(n)=i+T(n-i)=O(n)
         if(LF>RT){}
         else{
            out(LF,RT-1); 
            System.out.print(getElement(RT));
         }
      }
   
   // output this String182 – divide and conquer, that is, recursion on two
   // smaller arrays: one from the left up to but
   // not including the middle and the other from
   // one past the middle to the right
      public void outm() { 
         out(0,length-1);
      }
		//recursive method
      private void outm(int LF, int RT){//T(n)=2^(i-1)=2^iT(n/2^i)=O(n)
         int MID=(LF+RT)/2;
         if(LF>RT){}
         else{
            outm(LF,MID--);	//recursively prints everything left of mid
            System.out.print(getElement(MID));//prints mid then
            outm(MID++,RT);	//recursively prints everything after mid
         
         }
      }
   	// sort this String182 using insertions sort
   	// make array one smaller from the right end
      public void insertionSort() { 
         insertionSort(0,length-1);
      }
      private void insertionSort(int LF, int RT){  //T(n)=T(n-1)+n=O(n)
         
         if(LF>RT){}
         else{
            char save=letters[RT];
            int j=RT-1;
            insertionSort(LF,RT-1);
            while(j>=0&&save<letters[j]){
               letters[j+1]=letters[j];    //shifts the value of the letter before if it is greater
               j--;
            }
            letters[j+1]=save;
         }
      }
   
   // output this String182 in reverse order – divide and conquer
      public void outreverse(){
         outreverse(0,length-1);
      }
      private void outreverse(int LF,int RT) {  //T(n)=2^(i-1)=2^iT(n/2^i)=O(n)
         int MID=(LF+RT)/2;
         if(LF>RT){}
         else{
            outreverse(MID+1,RT);      //prints right side of mid in reverse
            System.out.print(getElement(MID));
            outreverse(LF,MID-1);      //prints left side of mid in reverse
         }
      }
   
   
   // reverse this String182
	// making it smaller by one from the right
      public void reverse() {
         reverse(0,length-1);
      }
		//reverse recursively
      private void reverse(int LF,int RT) {//T(n)=2^(i-1)=2^iT(n/2^i)=O(n)
         char temp;
         if(LF>RT){}
         else{
            temp =letters[LF];               //save left to temp
            letters[LF++]=letters[RT];       //increases left index
            letters[RT]=temp;                //save temp to the right
            reverse(LF,RT-1);	               //recursive call
         }
      }
    //Return the largest char in this String182 – divide and conquer
      public int biggest() { 
         return biggest(0,length-1);
      }
      private int biggest(int LT, int RT){//T(n)=2^(i-1)=2^iT(n/2^i)=O(n)
         int mid = (RT+LT)/2;			//I tried to see what my error was but I was unsuccessful
         int rt,lt,result=-1;			//I tried to do print statements but it was too hard to trace  
         if(LT>RT){						//It does not find all of the biggest in some cases,
            result =-1;					//but for the most part it finds the largest character successfully
         }
         else if(LT==RT){
            result=RT;
         }
         else{ 
            lt=biggest(LT, mid-1);
            rt=biggest(mid+1, RT);
            if((lt!=-1&&rt!=-1)&&letters[lt]>letters[rt]&&letters[lt]>letters[mid]){
               result=lt;	//when left is bigger then right and mid result is left
            }
            else if((lt!=-1&&rt!=-1)&&letters[rt]>letters[mid]&&letters[rt]>letters[lt]){
               result=rt;	//when right is bigger then mid and left get rt
            }
            else{
               result=mid;	
            }
         }
         return result;
      }
   
      public static String182 myName(){
         String182 name=new String182("Steve Delgado");
         return name;
      }
   }
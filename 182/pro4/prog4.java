/*Steve Delgado 
Program Assignment #4
	Due Date: 10/29/13 Date turned in:10/29/13
	This program creates different methods recursively that creates link lists in order to 
   reverse a string182, it adds node to the front list with add head method,
   it adds node to end of list using a add tail method, it can remove a character from the list,
   concatenates a string182, merges 2 string182, and is able to put the characters in link list
   in order and without duplicates.  */
  
class CharNode { 
   
 private char letter; 
 private CharNode next; 
 public CharNode(char ch, CharNode link) { 
   letter = ch; 
   next= link; 
 } 
   
 public void setCharacter(char ch) { 
   this.letter=ch; 
 } 
   
 public char getCharacter() { 
   return this.letter; 
 } 
 public void setNext(CharNode next) { 
   this.next=next; 
 } 
   
 public CharNode getNext() {  
   return next; 
}
}
class String182 { 
   
 // member variable pointing to the head of the linked list 
 private CharNode head; 
   
 // default constructor 
 public String182() { 
   head=null; 
   } 
   
  //copy constructor work on copy constructor  
 public String182(String182 copy) { 
      String182(copy.head);         
 }
 private CharNode String182(CharNode List){//T(n)=O(1)
   if(List==null){}
   else{
     addTail(List.getCharacter());  //creates new nodes/copy
     List=String182(List.getNext());   //new nodes become the list
  }
   return List;
 }
 // constructor from a String 
 public String182(String s) { //T(n)=O(n)
   head=null;
   for(int i = s.length()-1;i>=0;i--){ 
      head=new CharNode(s.charAt(i),head); //get character at location
   }
 } 
   
 // for output purposes -- override Object version 
 // no spaces between the characters, no linefeeds/returns 
 public String toString() { 
   return toString(head);
 } 
 //recursive
 private String toString(CharNode List){ //T(n)=O(n)
    String s1="";
    if(List==null){}
    else{
    s1= List.getCharacter()+toString(List.getNext());
    }
    return s1;
 }
 // append ch to the head of this String182 
 public void addHead(char ch) { 
   head = new CharNode(ch,head);
 } 
 // append ch to the tail of this String182 
 public void addTail(char ch){
      head=addTail(head, ch);
   }
 private static CharNode addTail(CharNode List, char ch) {//T(n)=O(n)
   if(List==null){
       List = new CharNode(ch, null);
   }
   else{
      List.setNext(addTail(List.getNext(),ch));
   }
      return List;
 } 

 //// modify this String182 so it is reversed 
  public void reverse() { 
   reverse(head); 
 }
 private void reverse(CharNode List){//T(n)=O(n)
   if(List== null){}
   else if(List.getNext()==null){   //was not able to make it static
      head = List;                  //since I could not access the head
   }
   else{
      reverse(List.getNext());
     CharNode temp= List.getNext();
     temp.setNext(List);
     List.setNext(null);
   }
 }
  
  // remove all occurrences of ch from this String182 
 public void removeChar(char ch) {//does not work
   removeChar(head,ch); 
 } 
 private static void removeChar(CharNode List, char ch){
   if(List==null){   //does not work
   }
  else{
    CharNode temp= List;
    removeChar(List.getNext(),ch);  
     if(ch==temp.getCharacter()) 
      List.setNext(temp.getNext()); 
      }

 }
 // how long is this String182 
 public int length(){ 
   return length(head); 
 } 
 private int length(CharNode ptr) { //T(n)=O(n)
   int result=0; 
   if(ptr==null){ 
      result=0; 
   } 
   else{ 
      result = length(ptr.getNext())+1; 
   } 
   return result; 
 } 
 // concatenate a copy of s to the end of this String182 
 public void concatenate( String182 s ) {
   concatenate(head, s.head);  
 }
 private void concatenate(CharNode n1, CharNode n2){ //T(n)=O(n^2)
   if(n1==null||n2==null){
   }
   else{
      addTail(n2.getCharacter());
      concatenate(n1,n2.getNext()); 
   }
 }
 // Return a String182 that contains all of the characters in 
 // this String182 in alphabetical order and without duplicates 
 public void chars() {  //does not work
   chars(head);
 } 
 private static void chars(CharNode List){//does not work
   if(List==null){
   }
  else{
    CharNode temp= List;
    chars(List.getNext());  
    }
 }
 // Return the merging this String182 and s 
 public void merge(String182 s) {
   merge(head,s.head);
 } 
 private static void merge(CharNode n1, CharNode n2){ //T(n)=O(n^2)
   if(n1==null||n2==null){}            //method is only able to merge strings 
   else{                               //where first string is bigger than second
      CharNode temp=new CharNode(n2.getCharacter(),null); //make a copy of s           
      merge(n1.getNext(),n2.getNext()); 
      temp.setNext(n1.getNext());          
      n1.setNext(temp);
   }
 }
 // who are you? 
 public static String182 myName() {
     String182 s1=new String182("Steve Delgado");
     return s1;  
 }
} 

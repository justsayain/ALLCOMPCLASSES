/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prog4;

/**
 *
 * @author sgd67962
 */
/*Steve Delgado 
*/
  
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
   return letter; 
 } 
 public void setNext(CharNode next) { 
   this.next=next; 
 } 
   
 public CharNode getNext() {  
   return next; 
}
}
class String182 { 

    private static CharNode CharNode(CharNode ch, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
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
 private CharNode String182(CharNode List){
   if(List==null){}
   else{
     addTail(List.getCharacter());
     List=String182(List.getNext());
  }
   return List;
 }
 // constructor from a String 
 public String182(String s) { 
   head=null;
   for(int i = s.length()-1;i>=0;i--){ 
      head=new CharNode(s.charAt(i),head); 
   }
 } 
   
 // for output purposes -- override Object version 
 // no spaces between the characters, no linefeeds/returns 
 public String toString() { 
   return toString(head);
 } 
 //recursive
 private String toString(CharNode List){
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
 private static CharNode addTail(CharNode List, char ch) {
   if(List==null){
      List = new CharNode(ch, null);
   }
   else{
      List.setNext(addTail(List.getNext(),ch));
   }
      return List;
 } 
 public void addTailN(CharNode ch){
     head=addTailN(head, ch);
 }
 private static CharNode addTailN(CharNode List, CharNode ch){
     if(List==null){
         List=ch;
     }
     else{
         List.setNext(addTailN(List.getNext(),ch));
     }
     return List;
 }
 //out reverse
  public void outReverse() { 
   outReverse(head); 
 }
 public static void outReverse(CharNode List){
   if(List== null){System.out.print("not rev");}
   else{
       
      outReverse(List.getNext());
      System.out.println(List.getCharacter());
   }
 }
 // modify this String182 so it is reversed 
 
 public void reverse() { 
   reverse(head); 
 }
 public static void reverse(CharNode List){
   if(List== null){}
   else{
      
      reverse(List.getNext());
     // List.getCharacter()+ptr;
   } 
 } 
 // remove all occurrences of ch from this String182 
 public void removeChar(char ch) {
   removeChar(head,ch); 
 } 
 private static void removeChar(CharNode List, char ch){
   if(List==null){
   }
   else if(ch==List.getCharacter()){
         List.setNext(List.getNext()); 
         removeChar(List.getNext(),ch);   //recursive to see if there is another occurence
            //the next will be the next next in the list
      }
   else{
      removeChar(List.getNext(),ch);
   }
 }
 // how long is this String182 
 public int length(){ 
   return length(head); 
 } 
 private int length(CharNode ptr) { 
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
 } 
 // Return a String182 that contains all of the characters in 
 // this String182 in alphabetical order and without duplicates 
 public void chars() { 
 } 
 // Return the merging this String182 and s 
 public void merge(String182 s) { 
 } 
 // who are you? 
 //public static String myName() { 
 //}
 public void censor(){
   censor(head);
 } 
 private static CharNode censor(CharNode L){
   if(L==null){}
   else if(L.getNext()==null){
      L.getCharacter();
   }
   else{
      L.setNext(censor(L.getNext()));
      }
 return L;
 }
} 

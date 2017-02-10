/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
         List.setNext(ch);
      }
      else{
         List.setNext(addTailN(List.getNext(),ch));
      }
      return List;
   }
   public void delTail(){
      if(head!=null)
         delTail(head);
   }
   private static CharNode delTail(CharNode List){
      if(List.getNext()==null){
         List=null;
      }
      else{
         List.setNext(delTail(List.getNext()));
      }
      return List;
   }
 //out reverse
   public void outReverse() { 
      outReverse(head); 
   }
   public void outReverse(CharNode List){
      if(List== null){}
      else{
         if(List.getNext()==null){
            return List;
         }
         CharNode temp= List.getNext();
         System.out.print(List.getCharacter());
         outReverse(List.getNext());
         List=List.getNext();
      }
   /*
    ListNode secondElem = list.next;
   
    // bug fix - need to unlink list from the rest or you will get a cycle
    list.next = null;
   
    // then we reverse everything from the second element on
    ListNode reverseRest = Reverse(secondElem);
   
    // then we join the two lists
    secondElem.Next = list;
   
    return reverseRest;
   }
   */
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
      if(List==null){System.out.print("none ");
      }
      else{
      
         removeChar(List.getNext(),ch);
         CharNode temp= List;
         System.out.print(temp.getCharacter());
         System.out.print(List.getCharacter());
         if(ch==temp.getCharacter())
            List.setNext(temp.getNext());
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
      chars(head);
   } 
   private static void chars(CharNode List){
      if(List==null){}
      else{
         chars(List.getNext());
      //if(List.getCharacter()<List.getNext()){
         
      //}
      }
   }
 // Return the merging this String182 and s 
   public void merge(String182 s) {
   } 
 // who are you? 
   public static String182 myName() {
      String182 s1 = new String182("Steve Delgado");
      return s1;
   }
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

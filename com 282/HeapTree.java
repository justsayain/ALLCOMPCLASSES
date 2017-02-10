/* Steve Delgado
 * Project 0 Heap Tree
 * This project creates a heap by using nodes in a binary tree.
 * It inserts strings, deletes the top node(root), and returns the tree in pre Order.
 * COMP 282
 * Spring 14
 * 
 */
public class HeapTree{ 
   private StringNode root;
   private int value;

//default constructor 
   public HeapTree(){ 
      root=null;
      value=1; // value of 1 for first insert
   }

   public static StringNode TrinkleUp(StringNode H){
      int comp;   //to compare the left and right child and parent
      StringNode temp=H;
      if(H==null){//at root do nothing
      }
      else{
         if(H.getLeft()!=null&&H.getRight()!=null){
            comp=H.getLeft().getString().compareTo
               (H.getRight().getString());
         //if(comp>0){
          //  comp2=H.getLeft().getString().compareTo(
          //     H.getString());
            //   if(comp2>0){
            //      StringNode copy=H.getLeft();
            //      H.setLeft(temp.getLeft());
            //      temp.setLeft(temp.
            //   }
         }  
      
      // }
         TrinkleUp(H.getLeft());
      }
      return H;
   }
   public void insert(String word){
      String key= Integer.toBinaryString(value)+"$"; //binary value
      key=key.substring(1);   //take away the one from beginning string
      root=insert(root, word,key); //recursive call
      value++; //increment value for next insert
   } 
   public static StringNode insert(StringNode H,String w,String k){
      if(k.charAt(0)=='$'){ //when there is null available
         H=new StringNode(w,null,null);  
      } 
      else{  
         if(k.charAt(0)== '0'){ //if 0 go left and insert
            H.setLeft(insert(H.getLeft(),w,k.substring(1)));
            if(H.getLeft().getString().compareTo(H.getString())>0){
               StringNode temp =H.getLeft(); //start trickle down
               H.setLeft(temp.getLeft());
               temp.setLeft(H);
               H=temp;
               temp=H.getLeft().getRight();
               H.getLeft().setRight(H.getRight());
               H.setRight(temp);
            }
         }
         else{ //if 1 go to the right and insert
            H.setRight(insert(H.getRight(),w,k.substring(1)));
            if(H.getRight().getString().compareTo(H.getString())>0){
               StringNode temp =H.getRight(); //start trickle down
               H.setRight(temp.getRight());
               temp.setRight(H);
               H=temp;
               temp=H.getRight().getLeft();
               H.getRight().setLeft(H.getLeft());
               H.setLeft(temp); 
            }
         }
      }   	
      return H;    
   } 
   // method to find last in order to swap for delete max
   public StringNode findLast(){ //same as insert
      value--; //value is less now since we are trying to find previous
      String key= Integer.toBinaryString(value)+"$";
      key=key.substring(1);
      return findLast(root,key); //recursive return node
   }
   public static StringNode findLast(StringNode H,String k){
      StringNode temp=H;
      if(H==null){ //no nodes do nothing
      }
      else if(H.getLeft()==null&&H.getRight()==null){
         temp=temp; //when you are in leaf obtain it
      }
      else{
         if(k.charAt(0)=='0'){ // 0 go left subtree
            temp=findLast(H.getLeft(),k.substring(1));
         }
         else{ //go right subtree
            temp=findLast(H.getRight(),k.substring(1));
         }
      }
      return temp;
   }
   public StringNode deleteLast(){ //get rid of last for delete max
      String key= Integer.toBinaryString(value)+"$"; 
      key=key.substring(1);
      return deleteLast(root,key); //return tree w/o last
   }
   public static StringNode deleteLast(StringNode H,String k){
      StringNode temp=H;
      if(H==null){
         
      }
      else if(H.getLeft()==null&&H.getRight()==null){
         H=null;
      }
      else{
         if(k.charAt(0)=='0'){
            H.setLeft(deleteLast(H.getLeft(),k.substring(1)));
         }
         else{
            H.setRight(deleteLast(H.getRight(),k.substring(1)));
         }
      }
      return H;
   }
   
   public String deleteMax(){ //returns what you are deleting
      return deleteMax(root); //not well done recursively
   } 
   public String deleteMax(StringNode H){ 
      StringNode temp=H;
      StringNode deleteLeft=H.getLeft();
      StringNode deleteRight=H.getRight();
      StringNode switchNode=findLast(); //cannot be accessed in static
      StringNode newRoot=new 
         StringNode (switchNode.getString(),null,null);
      //created new node to make it new root
      
      newRoot.setLeft(deleteLeft);
      newRoot.setRight(deleteRight);
      root=newRoot; //set root to a new root 
      temp.setLeft(null);
      temp.setRight(null);
      deleteLast(); //delete last inserted
      TrinkleUp(root); //not finished
      return temp.getString(); 
   } 
   public String preOrderTraversal(){
      return preOrderTraversal(root);
   }
   private static String preOrderTraversal(StringNode H){
      String result ="";
      if(H==null){//empty tree return nothing   
      }
      else{
         result=H.getString()+preOrderTraversal(H.getLeft())
         + preOrderTraversal(H.getRight());
      }
      return result;
   }
  
}
/* Steve Delgado
 * Project 1 2-3 Node class
 * This file creates nodes that contains generic data;
 * COMP 282
 * Spring 14
 * 
 */
public class Node<E extends Comparable <E>> {

   private E data;
   private Node<E> left,right,parent;
   private Color color;
   // Constructors
   public Node(E stuff, Color color, Node<E> lt, Node<E> rt ){
      data= stuff;
      left= lt;
      right= rt;
      this.color= color;
      if(left!=null) 
         left.parent=this;
      if(right!=null)
         right.parent=this;
      this.parent=null;
   }
   public Color getColor(){
      return color;
   }
   public Node<E> getParent(){
      return parent;
   }    
   public E getData() {    
      return data;    
   } 
   public void setColor(Color c){
      color=c;
   }
   public void setData(E item){
      data=item;
   }
   public void setParent(Node<E> pt){
      parent=pt;
   }
   public void setLeft(Node<E> pt) {        
      left=pt;    
   }    
   public void setRight(Node<E> pt) {   
      right=pt;    
   } 
  
   public Node<E> getLeft() {   
      return this.left;    
   }    
   public Node<E> getRight() {    
      return this.right;    
   }
   public int compare(String x,String y){
      return (x.compareTo(y));
   }   
       

   public Node<E> gp(){
      Node<E> A=null;
      if(parent!=null)
         if(parent.parent !=null)
            A = parent.parent;
         else
            A = null;
      return A;
   }

   public Node<E>uncle(){
      Node<E> A=null;
      if(parent!=null)
         if(parent.parent!=null)
            A=parent.parent.left;
         else
            A=parent.parent.right;
      return A;
   }
   public Node<E> sibling() {
      Node<E> A= null;
      if(parent !=null) // Root node has no sibling
         if (this == parent.left)
            A= parent.right;
         else
            A= parent.left;
      return A;
   }
   public Node<E> nnephew(){
      Node<E> result=null;
      if(this.parent!=null){
         if(sibling()!=null){
            result=sibling().left;
         }
      }
      return result;
   }
   public Node<E> fnephew(){
      Node<E> result=null;
      if(this.parent!=null){
         if(sibling()!=null){
            result=sibling().right;
         }
      }
      return result;
   }

}

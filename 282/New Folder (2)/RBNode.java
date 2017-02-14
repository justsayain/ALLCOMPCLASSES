/* Steve Delgado
 * Project 1 Node class
 * This file creates nodes that contains generic data;
 * COMP 282
 * Spring 14
 * 
 */
public class RBNode<E extends Comparable <E>> {

   private E data;
   private RBNode<E> left,right,parent;
   private Color color;
   // Constructors
   public RBNode(E stuff, Color color, RBNode<E> lt, RBNode<E> rt ){
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
   public RBNode<E> getParent(){
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
   public void setParent(RBNode<E> pt){
      parent=pt;
   }
   public void setLeft(RBNode<E> pt) {        
      left=pt;    
   }    
   public void setRight(RBNode<E> pt) {   
      right=pt;    
   } 
  
   public RBNode<E> getLeft() {   
      return this.left;    
   }    
   public RBNode<E> getRight() {    
      return this.right;    
   }
   public int compare(String x,String y){
      return (x.compareTo(y));
   }   
       

   public RBNode<E> gp(){
      RBNode<E> A=null;
      if(parent!=null)
         if(parent.parent !=null)
            A = parent.parent;
         else
            A = null;
      return A;
   }

   public RBNode<E>uncle(){
      RBNode<E> A=null;
      if(parent!=null)
         if(parent.parent!=null)
            A=parent.parent.left;
         else
            A=parent.parent.right;
      return A;
   }
   public RBNode<E> sibling() {
      RBNode<E> A= null;
      if(parent !=null) // Root node has no sibling
         if (this == parent.left)
            A= parent.right;
         else
            A= parent.left;
      return A;
   }
   public RBNode<E> nnephew(){
      RBNode<E> result=null;
      if(this.parent!=null){
         if(sibling()!=null){
            result=sibling().left;
         }
      }
      return result;
   }
   public RBNode<E> fnephew(){
      RBNode<E> result=null;
      if(this.parent!=null){
         if(sibling()!=null){
            result=sibling().right;
         }
      }
      return result;
   }

}

/* Steve Delgado 
 * Project 0 Node class 
 * This file creates nodes that contain string data. 
 * COMP 282 
 * Spring 14 
 *  
 */
 import java.util.*;
public class RBNode<E> {   
   private E data;
   private RBNode <E>link;
   public RBNode left,right,parent;
   public int color;

   public RBNode(){
   }
    
   /*public RBNode(int val, RBNode lt, RBNode rt) {     
      value=val;
      left=lt;
      right=rt;
      color=1;//1 for red  
   }
   */
   public void setLeftChild(RBNode child){
      left=child;
      if(child!=null)
         child.parent=this;
   }
   public void setRightChild(RBNode child){
      right=child;
      if(child!=null)
         child.parent=this;
   }
   public void replace(RBNode replacement){
      if(parent==null)return;
      if(this == parent.left)
         parent.setLeftChild(replacement);
      else
         parent.setRightChild(replacement);
   }
   public void addNode(RBNode A){
      int comp= A.data.compareTo(data);
      if(comp<0){
         if(left==null){
            left=A;
            left.parent=this;
         }
         else{
            left.addNode(A);
         }
      }
      else if(comp>0){
         if(right==null){
            right=A;
            right.parent=this;
            
         }
         else{
            right.addNode(A);
         }
      }
   }
 
   
}
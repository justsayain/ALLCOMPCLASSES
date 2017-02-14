public class BTNode<E>{
   private E data;
   private BTNode<E> left, right,parent;   

   public BTNode(E Stuff, BTNode<E> lt, BTNode<E> rt){
      data = Stuff;
      left = lt;
      right = rt;
   }        
   public E getData( )   
   {
      return data;
   }
   public BTNode<E> getLeft( )
   {
      return left;                                               
   } 
   public E getLeftmostData( )
   {
      if (left == null)
         return data;
      else
         return left.getLeftmostData( );
   }
   public BTNode<E> getRight( )
   {
      return right;                                               
   } 

   public E getRightmostData( )
   {
      if (left == null)
         return data;
      else
         return left.getRightmostData( );
   }  

   public boolean isLeaf( )
   {
      return (left == null) && (right == null);                                               
   } 

   public BTNode<E> removeLeftmost( )
   {
      if (left == null)
         return right;
      else
      {
         left = left.removeLeftmost( );
         return this;
      }
   }
   public BTNode<E> removeRightmost( )
   {
      if (right == null)
         return left;
      else
      {
         right = right.removeRightmost( );
         return this;
      }
   }
   public void setData(E newData)   
   {
      data = newData;
   }                                                               
   public void setLeft(BTNode<E> newLeft)
   {                    
      left = newLeft;
   }
   public void setRight(BTNode<E> newRight)
   {                    
      right = newRight;
   }  
    
   public static <E> BTNode<E> treeCopy(BTNode<E> source)
   {
      BTNode<E> leftCopy, rightCopy;

      if (source == null)
         return null;
      else
      {
         leftCopy = treeCopy(source.left);
         rightCopy = treeCopy(source.right);
         return new BTNode<E>(source.data, leftCopy, rightCopy);
      }
   }

   public static <E> long treeSize(BTNode<E> root)
   {
      if (root == null)
         return 0;
      else
         return 1 + treeSize(root.left) + treeSize(root.right);
   }   

}
           
// RedBlackTree class
//
// CONSTRUCTION: with no parameters
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean find( x )  --> Return true if x is found
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a red-black tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class RedBlackTree<E extends Comparable<E>>{
   
   private static class RedBlackNode<E>
   {
          // Constructors
      RedBlackNode( E theElement )
      {
         this( theElement, null, null );
      }
   
      RedBlackNode( E theElement, RedBlackNode<E> lt, RedBlackNode<E> rt )
      {
         element= theElement;
         left= lt;
         right= rt;
         color= 1;
      }
   
      E element;                  // The data in the node
      RedBlackNode<E> left;       // Left child
      RedBlackNode<E> right;      // Right child
      int color;                  // Color
   }
   
   private RedBlackNode<E> root;
   private RedBlackNode<E> nill;

   private final int BLACK = 1;
   private final int RED   = 0;

       // Used in insert routine and its helpers
   private RedBlackNode<E> current;
   private RedBlackNode<E> parent;
   private RedBlackNode<E> grand;
   private RedBlackNode<E> great;
   /**
    * Construct the tree.
    */
   public RedBlackTree( )
   {
      nill = new RedBlackNode<E>( null );
      nill.left = nill.right = nill;
      root      = new RedBlackNode<E>( null );
      root.left = root.right = nill;
   }


   /**
    * Compare item and t.element, using compareTo, with
    * caveat that if t is root, then item is always larger.
    * This routine is called if is possible that t is root.
    * If it is not possible for t to be root, use compareTo directly.
    */
   private int compare( E item, RedBlackNode<E> t )
   {
      if( t == root )
         return 1;
      else
         return item.compareTo(t.element);    
   }
   
   /**
    * Insert into the tree.
    * @param item the item to insert.
    * @throws DuplicateItemException if item is already present.
    */
   public void insert(E item){
      current = parent = grand = root;
      nill.element = item;
   
      while( compare( item, current ) != 0 )
      {
         great = grand; grand = parent; parent = current;
         current = compare( item, current ) < 0 ?
                      current.left : current.right;
      
             // Check if two red children; fix if so
         if( current.left.color == RED && current.right.color == RED )
            handleReorient( item );
      }
   
          // Insertion fails if already present
      if( current != nill )
         return;
      current = new RedBlackNode<E>( item, nill, nill );
   
          // Attach to parent
      if( compare( item, parent ) < 0 )
         parent.left = current;
      else
         parent.right = current;
      handleReorient( item );
   }

   /**
    * Remove from the tree.
    * @param x the item to remove.
    * @throws UnsupportedOperationException if called.
    */
  
   public boolean find( E x ){
      nill.element = x;
      boolean b=false;
      current = root.right;
      boolean a=false;
      if( x.compareTo( current.element ) < 0 )
         current = current.left;
      else if( x.compareTo( current.element ) > 0 ) 
         current = current.right;
      else if( current != nill )
         b=true;
      else
         b=false;
      return b;
   }

   /**
    * Make the tree logically empty.
    */
   public void makeEmpty( )
   {
      root.right = nill;
   }

   /**
    * Print the tree contents in sorted order.
    */
   public void printTree( )
   {
      printTree( root.right );
   }
   public String preOrderTraversal(){
      return"";
   }
   /**
    * Internal method to print a subtree in sorted order.
    * @param t the node that roots the tree.
    */
   private void printTree( RedBlackNode<E> t )
   {
      if( t != nill )
      {
         printTree( t.left );
         System.out.println( t.element );
         printTree( t.right );
      }
   }
    
   /**
    * Test if the tree is logically empty.
    * @return true if empty, false otherwise.
    */
   public boolean isEmpty( ){
      return root.right == nill;
   }

   /**
    * Internal routine that is called during an insertion
    * if a node has two red children. Performs flip and rotations.
    * @param item the item being inserted.
    */
   private void handleReorient( E item ){
          // Do the color flip
      current.color = RED;
      current.left.color = BLACK;
      current.right.color = BLACK;
   
      if( parent.color == RED )   // Have to rotate
      {
         grand.color = RED;
         if( ( compare( item, grand ) < 0 ) !=( compare( item, parent ) < 0 ) )
            parent = rotate( item, grand );  // Start dbl rotate
         current = rotate( item, great );
         current.color = BLACK;
      }
      root.right.color = BLACK; // Make root black
   }

   /**
    * Internal routine that performs a single or double rotation.
    * Because the result is attached to the parent, there are four cases.
    * Called by handleReorient.
    * @param item the item in handleReorient.
    * @param parent the parent of the root of the rotated subtree.
    * @return the root of the rotated subtree.
    */
   private RedBlackNode<E> rotate( E item, RedBlackNode<E> parent ){
      if( compare( item, parent ) < 0 )
         return parent.left = compare( item, parent.left ) < 0 ?
             rotateWithLeftChild( parent.left )  :  // LL
             rotateWithRightChild( parent.left ) ;  // LR
      else
         return parent.right = compare( item, parent.right ) < 0 ?
             rotateWithLeftChild( parent.right ) :  // RL
             rotateWithRightChild( parent.right );  // RR
   }

   /**
    * Rotate binary tree node with left child.
    */
   private static <E> RedBlackNode<E> rotateWithLeftChild( RedBlackNode<E> k2 )
   {
      RedBlackNode<E> k1 = k2.left;
      k2.left = k1.right;
      k1.right = k2;
      return k1;
   }

   /**
    * Rotate binary tree node with right child.
    */
   private static <E> RedBlackNode<E> rotateWithRightChild( RedBlackNode<E> k1 )
   {
      RedBlackNode<E> k2 = k1.right;
      k1.right = k2.left;
      k2.left = k1;
      return k2;
   }


    public static void main( String [ ] args )
    {
        RedBlackTree<Integer> t = new RedBlackTree<Integer>( );
        final int NUMS = 50;
        final int GAP  =  2;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

         System.out.print(t.find(34));
    }




}  

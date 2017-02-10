/* Steve Delgado
 * Project 1 Red Black Tree
 * This file contains the red and black tree. This is a program
 * that inserts, deletes,finds , and returns strings of the generic data.
 * There is a lot of methods that are needed to do all of these
 * like rotations and finding nodeds. 
 * COMP 282
 * Spring 14
 * 
 */
import java.util.*;
enum Color{RED,BLACK}
public class RedBlackTree<E extends Comparable<E>> implements DataStructure<E>{
   
   private RBNode<E> root;
   private RBNode<E> nill;

   public RedBlackTree(){ //tree null
      root=null;
   }
   private Color nodeColor(RBNode<E> stuff) {
      Color result;
      if(stuff==null){
         result = Color.BLACK;   //null node make it black
      }
      else{
         result = stuff.getColor(); //node is either black or red
      }
      return result;
   }
 
   public void insert(E item){
      RBNode<E> newNode= new RBNode<E>(item, Color.RED, null, null);
      if(root==null){
         root=newNode;
      }
      else{
         RBNode<E> temp=root;
         while(true){
            int comp=item.compareTo(temp.getData()); //compare item to current node
            if(comp==0){
               temp.setData(item); //if its same
               return;
            }
            else if(comp<0){ //smaller place it on left
               if(temp.getLeft()==null){
                  temp.setLeft(newNode);
                  break;
               }
               else{
                  temp=temp.getLeft(); //switch with left
               }
            }
            else{
               if(temp.getRight()==null){//bigger place right
                  temp.setRight(newNode);
                  break;
               }
               else{
                  temp=temp.getRight();
               }
            }
         }
         newNode.setParent(temp); //set new inserted parent 
      }
      insert1(newNode);
   }
   private void insert1(RBNode<E> n) {
      if (n.getParent() == null) //root
         n.setColor(Color.BLACK); //set root to black
      else
         insert2(n);
   }
   private void insert2(RBNode<E> n) {
      if (nodeColor(n.getParent()) == Color.BLACK)
         return; // root is black no double red
      else
         insert3(n);
   }
   void insert3(RBNode<E> n) {
      if (nodeColor(n.uncle()) == Color.RED) {//check unlce is RED
         n.getParent().setColor(Color.BLACK); //p(x) black
         n.uncle().setColor(Color.BLACK);//p(x) black
         n.gp().setColor(Color.RED); //gp(x)red
         insert1(n.gp());
      } 
      else {
         insert4(n);
      }
   }
   void insert4(RBNode<E> n) {
      if (n == n.getParent().getRight() && n.getParent() == n.gp().getLeft()) {
         rotateLeft(n.getParent());//rotate towards
         n = n.getLeft();
      } 
      else if (n == n.getParent().getLeft() && n.getParent() == n.gp().getRight()) {
         rotateRight(n.getParent());//rotate away
         n = n.getRight();
      }
      insert5(n);
   }
   void insert5(RBNode<E> n) {
      n.getParent().setColor(Color.BLACK); //p(x) black
      n.gp().setColor(Color.RED); //gp(x) red
      if (n == n.getParent().getLeft() && n.getParent() == n.gp().getLeft()) {
         rotateRight(n.gp()); //rotate gp towards
      } 
      else {
         if(n == n.getParent().getRight() && n.getParent() == n.gp().getRight())
            rotateLeft(n.gp()); //rotate gp away
      }
   }
   //rotations
   private void rotateLeft(RBNode<E> n) {
      RBNode r = n.getRight(); 
      replaceNode(n, r);
      n.setRight(r.getLeft());
      if (r.getLeft() != null) {
         r.getLeft().setParent(n);
      }
      r.setLeft(n);
      n.setParent(r);
   }

   private void rotateRight(RBNode<E> n) {
      RBNode l = n.getLeft();
      replaceNode(n, l);
      n.setLeft(l.getRight());
      if (l.getRight() != null) {
         l.getRight().setParent(n);
      }
      l.setRight(n);
      n.setParent(l);
   }
   //replace nodes
   private void replaceNode(RBNode<E> old, RBNode<E> temp) {
      if (old.getParent() == null) {
         root = temp;
      } 
      else {
         if (old == old.getParent().getLeft())
            old.getParent().setLeft(temp);
         else
            old.getParent().setRight(temp);
      }
      if (temp != null) {
         temp.setParent(old.getParent());
      }
   }
   //find items
   public E find(E item){
      E temp=root.getData();
      RBNode<E> temp2=root;
      while(temp!=null){
         int comp=item.compareTo(temp2.getData());
         if(comp==0){
            return temp2.getData();
         }
         else if(comp<0){
            temp2=temp2.getLeft();
         }
         else{
            temp2=temp2.getRight();
         }
      }
      return temp2.getData();  
   }
//find the nodes
   private RBNode<E> lookupNode(E key) {
      RBNode<E> n = root;
      while (n != null) {
         int compResult = key.compareTo(n.getData());
         if (compResult == 0) {
            return n;
         } 
         else if (compResult < 0) {
            n = n.getLeft();
         } 
         else {
            assert compResult > 0;
            n = n.getRight();
         }
      }
      return n;
   }
   private static <E extends Comparable<E>>RBNode<E> biggest(RBNode<E> n) {
      if(n != null){
         while (n.getRight() != null) {
            n = n.getRight();
         }
      }
      return n;
   }
//delete the item and return it
   public E delete(E item) {
      RBNode<E> n = lookupNode(item);
      RBNode<E> temp=n;
      if (n == null)
         return null;  // Key not found, do nothing
      if (n.getLeft() != null && n.getRight() != null) {
        // Copy key/value from predecessor and then delete it instead
         RBNode<E> pred = biggest(n.getLeft());
         n.setData(pred.getData());
         n = pred;
      }
   
      assert n.getLeft() == null || n.getRight() == null;
      RBNode<E> child = (n.getRight() == null) ? n.getLeft() : n.getRight();
      if (nodeColor(n) == Color.BLACK) {
         n.setColor(nodeColor(child));
         delete1(n);  
      }
      replaceNode(n, child);
      return temp.getData();
   
   }
   //check different cases for delete due to black height
   private void delete1(RBNode<E> n) {
      if (n.getParent() == null)
         return;
      else
         delete2(n);
   }

   private void delete2(RBNode<E> n) {
      if (nodeColor(n.sibling()) == Color.RED) {
         n.getParent().setColor(Color.RED);
         n.sibling().setColor(Color.BLACK);
         if (n == n.getParent().getLeft())
            rotateLeft(n.getParent());
         else
            rotateRight(n.getParent());
      }
      delete3(n);
   }
   private void delete3(RBNode<E> n) {
      if (nodeColor(n.getParent()) == Color.BLACK &&
        nodeColor(n.sibling()) == Color.BLACK &&
        nodeColor(n.sibling().getLeft()) == Color.BLACK &&
        nodeColor(n.sibling().getRight()) == Color.BLACK)
      {
         n.sibling().setColor(Color.RED);
         delete1(n.getParent());
      }
      else
         delete4(n);
   }
   private void delete4(RBNode<E> n) {
      if (nodeColor(n.getParent()) == Color.RED &&
        nodeColor(n.sibling()) == Color.BLACK &&
        nodeColor(n.sibling().getLeft()) == Color.BLACK &&
        nodeColor(n.sibling().getRight()) == Color.BLACK)
      {
         n.sibling().setColor(Color.RED);
         n.getParent().setColor(Color.BLACK);
      }
      else
         delete5(n);
   }
   private void delete5(RBNode<E> n) {
      if (n == n.getParent().getLeft() &&
        nodeColor(n.sibling()) == Color.BLACK &&
        nodeColor(n.sibling().getLeft()) == Color.RED &&
        nodeColor(n.sibling().getRight()) == Color.BLACK)
      {
         n.sibling().setColor(Color.RED);
         n.sibling().getLeft().setColor(Color.BLACK);
         rotateRight(n.sibling());
      }
      else if (n == n.getParent().getRight() &&
             nodeColor(n.sibling()) == Color.BLACK &&
             nodeColor(n.sibling().getRight()) == Color.RED &&
             nodeColor(n.sibling().getLeft()) == Color.BLACK)
      {
         n.sibling().setColor(Color.RED);
         n.sibling().getRight().setColor(Color.BLACK);
         rotateLeft(n.sibling());
      }
      delete6(n);
   }
   private void delete6(RBNode<E> n) {
      n.sibling().setColor(nodeColor(n.getParent()));
      n.getParent().setColor(Color.BLACK);
      if (n == n.getParent().getLeft()) {
         if(nodeColor(n.sibling().getRight()) == Color.RED){
            n.sibling().getRight().setColor(Color.BLACK);
            rotateLeft(n.getParent());
         }
      }
      else
      {
         if(nodeColor(n.sibling().getLeft()) == Color.RED){
            n.sibling().getLeft().setColor(Color.BLACK);
            rotateRight(n.getParent());
         }
      }
   }
   public String preOrderTraversal(){
      return "cannot get it to work :(";
   }
/*
private String preOrderTraversal(RBNode<E> T){
   E result=T.getData();
   
   if(T==null){//do nothing
   }
   else{
      result=T.getData();
      result+=T.getLeft().getData();
      result+=T.getRight().getData();
   }
   return result; 
}
*/
  
  

}



class IntNode { 
   private int num; 
   private IntNode left, right; 
   public IntNode(int num) { 
      this.num=num; 
   } 
   public IntNode(int num, IntNode lf, IntNode rt) { 
      this.num=num; 
      left=lf; 
      right=lf; 
   } 
   public int getNum() { 
      return num; 
   } 
   public void setLeft(IntNode pt) { 
      left=pt; 
   } 
   public void setRight(IntNode pt) { 
      right=pt; 
   } 
   public IntNode getLeft() { 
      return this.left; 
   } 
   public IntNode getRight() { 
      return this.right; 
   } 
   
} 
class IntBST { 
 // member variable pointing to the root of the BST 
   private IntNode root; 
 // default constructor 
   public IntBST() { 
      root=null; 
   } 
 // copy constructor 
  
   public IntBST(IntBST t) { 
      root=copy(t.root); 
   } 
   private static IntNode copy(IntNode L){ 
      IntNode temp=null; 
      if(L==null){//empty tree 
      } 
      else{ 
         temp=new IntNode(L.getNum(),null,null);  //creates new nodes  
         temp.setLeft(copy(L.getLeft()));         //set nodes from left subtree 
         temp.setRight(copy(L.getRight()));       //set nodes from right subtree  
      } 
      return temp; 
   } 
   
 // for output purposes -- override Object version 
   public String toString() { 
      return toString(root, 1); 
   } 
   private static String toString(IntNode l, int depth) { 
      String s = ""; 
      if (l == null) 
         ; // nothing to output 
      else {  
         if (l.getLeft() != null) // don't output empty subtrees 
            s = '(' + toString(l.getLeft(), depth + 1) + ')'; 
         s = s + l.getNum() + "-" + depth; 
         if (l.getRight() != null) // don't output empty subtrees 
            s = s + '(' + toString(l.getRight(), depth + 1) + ')'; 
      } 
      return s; 
   } 
   public boolean search(int num) { 
      return search(root,num); 
   } 
   private static boolean search(IntNode T,int num){ //fixed  
      boolean result=false; 
      if(T==null){   //do nothing 
      } 
      else if(num==T.getNum()){  //when the number is found 
         result=true; 
      } 
      else if(num<T.getNum()){   //search leftsubtree 
         result=search(T.getLeft(),num); 
      } 
      else{ 
         result=search(T.getRight(),num); //search rightsubtree 
      }    
      return result; 
   } 
   public void insert(int num) { 
      root=insert(root,num); 
   } 
   private static IntNode insert(IntNode T,int num){ 
      if(T==null){ 
         T=new IntNode(num,null,null); //create new node to insert 
      } 
      else{ 
         if(num==T.getNum()){//do nothing 
         } 
         else if(num<T.getNum()){   //insert node into left subtree 
            T.setLeft(insert(T.getLeft(),num)); 
         } 
         else{ //insert node into right subtree 
            T.setRight(insert(T.getRight(),num)); 
         } 
      } 
      return T; 
   } 
   //gets height of BST 
   public int height() { 
      return height(root); 
   } 
   private int height(IntNode T){ 
      int result=0,leftCount=0,rightCount=0; 
      if(T==null){ 
         result=0;   //empty tree has 0 nodes 
      } 
      else{ 
         rightCount+=height(T.getRight())+1; //gets the height from the right 
         leftCount+=height(T.getLeft())+1;   //gets the height from the left 
         if(leftCount<rightCount)   //compare right to left subtrees 
            result=rightCount;      //height of right subtree will be result 
         else
            result=leftCount;       //left subtree height will be result 
      } 
      return result; 
   } 
//get depth of closest leaf 
   public int closeLeaf(){  
     return closeLeaf(root); 
   } 
   private int closeLeaf(IntNode T){   //fix i have no idea how to fix it =( 
      int count=0,clCount=0,rightCount=0,leftCount=0; 
      if(T==null){ 
      } 
      else{ 
         if(T.getLeft()!=null&&T.getRight()!=null){
         rightCount+= closeLeaf(T.getLeft())+1; 
         leftCount+= closeLeaf(T.getRight())+1; 
         }
         if(rightCount<leftCount) 
            clCount=rightCount; 
         else
            clCount=leftCount; 
      } 
      return clCount; 
   } 
     
//get number of leaves in BST 
   public int  leafCt() { 
      return  leafCt(root); 
   } 
   private int  leafCt(IntNode T){ 
      int leafCount=0; 
      if(T==null){ 
      } 
      else if(T.getLeft()==null&&T.getRight()==null){ 
         leafCount++; 
      } 
      else{ 
         leafCount+= leafCt(T.getLeft()); 
         leafCount+= leafCt(T.getRight()); 
      } 
      return leafCount; 
   } 
 public void delete(int num) { 
      root=delete(root,num);  
 } 
 private static IntNode delete(IntNode T, int num){ 
      IntNode Ptr=T; 
   if(T==null){} 
   else if(num=T.getNum()){  //4 cases fix me again please ! 
      if(T.getLeft()==null)
         T.setRight(T.getRight().getRight());
      else if(num==T.getRight().getNum()&&T.getRight().getRight()==null)
         T.setRight(T.getRight().getLeft());
      else if(num==T.getLeft().getNum()&&T.getLeft().getLeft()==null)
         T.setLeft(T.getLeft().getRight());
      else if(num==T.getLeft().getNum()&&T.getLeft().getRight()==null)
         T.setLeft(T.getLeft().getLeft());
      else //try to make method with successor and predecessor.
         T.setLeft(T.getLeft());   
   } 
   else if(num<T.getNum()){ 
      T.setLeft(delete(T.getLeft(),num)); 
   } 
   else{ 
      T.setRight(delete(T.getRight(),num)); 
   } 
   return T; 
 } 
 public IntNode successor(){
     return successor(root);
   }
 private static IntNode successor(IntNode T){
   IntNode RT=T;
   IntNode LT=T;
   if(T==null){}
   else{
      if(RT.getRight()!=null&&(RT.getRight().getNum()>RT.getLeft().getNum()))
      T=successor(T.getRight());
      else
      LT=successor(T.getLeft());
   }
      
   return T;
 }
 
 // Rotate the node containing val to the left -- do nothing if not possible, 
   public void rotateLeft(int num) {
      root= rotateLeft(root, num);
   } 
   private static IntNode rotateLeft(IntNode T, int num){
      IntNode Ptr=T;  
      
      if(T==null){
      }
      else if(num==T.getNum()){
         if(T.getLeft()!=null&&T.getRight()!=null){ 
            T=T.getRight();
            Ptr.setRight(T.getLeft());
            T.setLeft(Ptr);
            //T.getLeft().getRight().setRight(PtrLT.getRight());
         }
         else if(T.getRight()!=null&&T.getLeft()==null){
            T=T.getRight();
            Ptr.setRight(T.getLeft());
            T.setLeft(Ptr);
         }
         else{// do nothing not able to rotate
         }
      }
      else if(num<T.getNum()){
         T.setLeft(rotateLeft(T.getLeft(),num));
      }
      else
         T.setRight(rotateLeft(T.getRight(),num));
      return T;
   }
 // Rotate the node containing val to the right -- do nothing if not possible 
   public void rotateRight(int num) {
      root= rotateRight(root,num);
   } 
   private static IntNode rotateRight(IntNode T, int num){
      IntNode Ptr=T;  
      
      if(T==null){
      }
      else if(num==T.getNum()){
         if(T.getLeft()!=null&&T.getRight()!=null){ 
            T=T.getLeft();
            Ptr.setLeft(T.getRight);
            T.setRight(Ptr);
            //T.getLeft().getRight().setRight(PtrLT.getRight());
         }
         else if(T.getLeft()!=null&&T.getRight()==null){
            T=T.getLeft();
            Ptr.setLeft(T.getRight);
            T.setRight(Ptr); 
         }
         else{//not able to rotate
         }
       
      }
    
      else if(num<T.getNum()){
         T.setLeft(rotateRight(T.getLeft(),num));
      }
      else
         T.setRight(rotateRight(T.getRight(),num));
      return T;
   }
 // remove all of the leaves from the tree 
   public void removeLeaves() {  
      root=removeLeaves(root); 
   } 
   private static IntNode removeLeaves(IntNode T){//fix so close!!!!!!! made progress!!! YESS GOT IT =)!!! 
      IntNode PtrLT=T; 
      IntNode PtrRT=T; 
      if(T.getLeft()!=null){ 
         PtrLT=T.getLeft(); 
      } 
      else
         PtrLT=null; 
      if(T.getRight()!=null){ 
         PtrRT=T.getRight(); 
      } 
      else
         PtrRT=null; 
      if(T==null){ 
      } 
      else if(PtrLT!=null&&(PtrLT.getLeft()==null&&PtrLT.getRight()==null)){ 
         T.setLeft(null); 
         if(PtrRT!=null&&(PtrRT.getLeft()==null&&PtrRT.getRight()==null)){ 
            T.setRight(null); 
         } 
         else if(PtrRT!=null&&(PtrRT.getRight()!=null&&PtrRT.getLeft()!=null)) //hmmmmmm............
         T.setRight(removeLeaves(T.getRight())); 
         else{}
      } 
      else if(PtrRT!=null&&(PtrRT.getLeft()==null&&PtrRT.getRight()==null)){ 
         T.setRight(null); 
      } 
      else{ 
         if(T.getLeft()!=null) 
         T.setLeft(removeLeaves(T.getLeft())); 
         if(T.getRight()!=null) 
         T.setRight(removeLeaves(T.getRight())); 
      } 
      return T; 
   } 
 public static String myName() { 
   return "Steve Delgado"; 
 } 
} 
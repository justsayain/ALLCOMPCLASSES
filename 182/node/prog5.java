 /*Steve Delgado  
Program Assignment #5 
   Due Date: 11/19/13 Date turned in:11/20/13 
   This program creates different methods recursively that creates a binary search tree in  
   order to search for a number in the tree, insert numbers into a tree, delete numbers
	in the tree, make a copy of a tree, find the height of a tree, find the depth of the
	closest leaf, find the amount of leaves in your tree, delete all your leaves in the
	tree, and able to rotate a node/number to the left or right.  */  
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
      private static IntNode copy(IntNode L){ //T(n)=O(n) 
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
      private static boolean search(IntNode T,int num){ //T(n)=O(logn)   
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
      private static IntNode insert(IntNode T,int num){	//T(n)=O(logn)
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
      public int height() {  	//T(n)=O(n)
         return height(root);  
      }  
      private static int height(IntNode T){  
         int result=1,leftCount=0,rightCount=0;  
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
      public int closeLeaf(){   //T(n)=O(n)
         return closeLeaf(root);  
      }  
      private static int closeLeaf(IntNode T){
         int result=0,left,right;   
         if(T==null)
            result= 0;
         else if(T.getLeft()==null && T.getRight() == null) 
            result= 1;
         else{
            left = closeLeaf(T.getLeft());
            right = closeLeaf(T.getRight());
            if(left==0) 
               left= right+1;
            if(right==0) 
               right= left+1;
            if(left>right)
               result=right+1;
            else
               result=left+1;
         
         }
         return result;   
      }  
       
   //get number of leaves in BST  
      public int  leafCt() {  
         return  leafCt(root);  
      }  
      private static int leafCt(IntNode T){  //T(n)=O(n)
         int leafCount=0;  
         if(T==null){  
         }  
         else if(T.getLeft()==null&&T.getRight()==null){  
            leafCount++; //when it's a leaf incremet the leaf count
         }  
         else{  
            leafCount+= leafCt(T.getLeft()); //counts each leaf from left subtree
            leafCount+= leafCt(T.getRight());  //counts each leaf from right subtree
         } 
         return leafCount;  
      }  
      public void delete(int num) {  
         root=delete(root,num);   
      }  
      private static IntNode delete(IntNode T, int num){	//T(n)=O(n)  
         IntNode Ptr=T;
         IntNode Pred=T;  
         if(T==null){}  
         else if(num==T.getNum()){  //4 cases fix me again please !  
            if(T.getLeft()==null) //no left child
               T=T.getRight(); 
            else if(T.getRight()==null) //no right child
               T=T.getLeft();  
            else{	//two children
               Pred=T.getLeft();  //predecessor pointer
               while(Pred.getRight()!=null){//gets highest # from left subtree
                  Pred=Pred.getRight();	
               }
               T=Pred;	//set my tree to highest from left subtree
               T.setLeft(delete(Ptr.getLeft(),Pred.getNum()));	//new tree's left is pointer's left with predecessor deleted
               T.setRight(Ptr.getRight());	// set new trees right with the old tree's right
            }
            
         }  
         else if(num<T.getNum()){  
            T.setLeft(delete(T.getLeft(),num));  //search left	subtree
         }  
         else{  
            T.setRight(delete(T.getRight(),num)); //search right subtree 
         }  
         return T;  
      }     
   // Rotate the node containing val to the left -- do nothing if not possible,  
      public void rotateLeft(int num) { 
         root= rotateLeft(root, num); 
      }  
      private static IntNode rotateLeft(IntNode T, int num){	//T(n)=O(logn) 
         IntNode Ptr=T;   
        
         if(T==null){ 
         } 
         else if(num==T.getNum()){ 	//found #
            if(T.getRight()!=null){  
               T=T.getRight();	//set new tree to the right
               Ptr.setRight(T.getLeft()); //have the pointer holding old tree to new trees left
               T.setLeft(Ptr);	//set new tree's left to pointer holding old tree 
            }
            else{// do nothing not able to rotate 
            } 
         } 
         else if(num<T.getNum()){ 
            T.setLeft(rotateLeft(T.getLeft(),num)); //search left subtree
         } 
         else
            T.setRight(rotateLeft(T.getRight(),num));	//search right subtree 
         return T; 
      } 
   // Rotate the node containing val to the right -- do nothing if not possible  
      public void rotateRight(int num) { 
         root= rotateRight(root,num); 
      }  
      private static IntNode rotateRight(IntNode T, int num){ //T(n)=O(logn)
         IntNode Ptr=T;   
        
         if(T==null){ 
         } 
         else if(num==T.getNum()){	//found number 
            if(T.getLeft()!=null){ 	//must have left in order to rotate right 
               T=T.getLeft(); 		//set new tree to the tree's left
               Ptr.setLeft(T.getRight()); 	//make the pointer point to what was rotated
               T.setRight(Ptr); 		//set new tree to pointer that is holding nodes
            } 
            else{ //not able to rotate 
            }   
         } 
         
         else if(num<T.getNum()){ 
            T.setLeft(rotateRight(T.getLeft(),num)); //search left subtree
         } 
         else
            T.setRight(rotateRight(T.getRight(),num));//search right subtree 
         return T; 
      } 
   // remove all of the leaves from the tree  
      public void removeLeaves() {  //T(n)=O(n)
         root=removeLeaves(root);  
      }  
      private static IntNode removeLeaves(IntNode T){   
         if(T==null){  
         }  
         else if(T.getLeft()==null&&T.getRight()==null){
            T=null;	//finds leaves and set that node to null
         }  
         else{        
            T.setLeft(removeLeaves(T.getLeft())); //remove leaves of left subtree   
            T.setRight(removeLeaves(T.getRight()));  //remove leaves of right subtree
         }  
         return T;  
      }  
      public static String myName() {  
         return "Steve Delgado";  
      }  
   }
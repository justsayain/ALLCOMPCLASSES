class node {  
   private int data;
	public int value;  
   public node left, right,parent;    
   public node(int item, node lf, node rt) {  
      data=item;  
      left=lf;  
      right=lf;
		value=1; 
		if(left!=null) 
         left.parent=this;
      if(right!=null)
         right.parent=this;
      this.parent=null; 
   }
	public node gp(){
		node a=null;
		if(parent!=null)
			if(parent!=null)
				a=parent.parent;
			else
				a=null;
		return a;
	}
     
}  
class HeapTree {  
 // member variable pointing to the root of the BST  
   private node root;  
 // default constructor  
   public HeapTree() {  
      root=null;  
   }  
 /*   public boolean search(int num) {  
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
	*/
	public void insert(int stuff){
		root=insert(stuff,root);
	}
   public static node insert(int stuff,node T) {  
 		node newNode= new node(stuff, null, null);
		if(T==null){
			T=new node(stuff,null,null);
		}
		else{
			if(T.value==1&&T.left==null){
				T.left=newNode;
				T.left.value=2;
			}
			else if(T.value==1&&T.right==null){
				T.right=newNode;
				T.right.value=3;
			}
			else
		}
		return T;
	}
	   //gets height of BST  
    public static String myName() {  
   return "Steve Delgado";  
 }  
}

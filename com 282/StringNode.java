/* Steve Delgado
 * Project 0 Node class
 * This file creates nodes that contain string data.
 * COMP 282
 * Spring 14
 * 
 */
public class StringNode {    
   private String str;    
   private StringNode left, right;
	private StringNode parent;       
   public StringNode(String word, StringNode lf, StringNode rt) {    
      str=word;    
      left=lf;    
      right=rt; 
		if(left!=null) //for parent
         left.parent=this;
      if(right!=null)
         right.parent=this;
      this.parent=null; 
   }
	public StringNode getParent(){
		return parent;
	}    
   public String getString() {    
      return str;    
   } 
	public void setParent(StringNode pt){
		parent=pt;
	}
   public void setLeft(StringNode pt) {        
      left=pt;    
   }    
   public void setRight(StringNode pt) {   
      right=pt;    
   } 
  
   public StringNode getLeft() {   
      return this.left;    
   }    
   public StringNode getRight() {    
      return this.right;    
   }
    public int compare(String x,String y){
         return (x.compareTo(y));
      }   
       
	public StringNode sibling() {//to check sibling
	StringNode result=null;
    if(this.parent!=null){// Root node has no sibling
    if (this == parent.left)
        result= parent.right;
    else
        result= parent.left;
		  }
	 return result;
}

}
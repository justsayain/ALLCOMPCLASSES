class coord {
   private int row, col;
 
   public coord(int row, int col) {
      this.row=row;
      col=col;
   }
   public int getRow(){
      return row;
   }
   public int getCol(){
      return col;
   }
}
class node{
   private coord data;
   private node next;
   private int num;
   public node(coord data, node next){
      data=data;e
      next=next;
   }
   
   
   public coord getCoord(){
      return data;
   }
   public node getNext(){
      return next;
   }
   public void setNext(node next){
      next= next;
   }
   public void setCoord(coord data){
      data= data;
   }
   
}  
class queue {
   node front_pt, back_pt;

   public queue() {
      front_pt = null;
      back_pt = null;
   }
 
 public coord front() {
   return front_pt.getCoord();
 }
 
 public boolean empty() {
   return front_pt==null&&back_pt==null;
 }
 
 public void dequeue() {
   front_pt=front_pt.getNext();
 }
 
 public void enqueue (coord data) {
   back_pt.setNext(new node(data,null));
 }
 
}
class path{
}
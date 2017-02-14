class coord {
   private int row, col;
 
   public coord(int row, int col) {
      this.row=row;
      this.col=col;
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
      this.data=data;
      this.next=next;
   }
   
   
   public coord getCoord(){
      return data;
   }
   public node getNext(){
      return next;
   }
   public void setNext(node next){
      this.next= next;
   }
   public void setCoord(coord data){
      this.data= data;
   }
   
}  
class queue{
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
      if(empty())
      {
         front_pt = new node(data, null);
         back_pt = front_pt;
      }
      else
      {
         back_pt.setNext(new node(data, front_pt));
      }
   
   }
}
public class path{
   public static void shortest_path(int[][] grid, int startRow, int startCol,int goalRow, int goalCol) {
      int length=2;
      queue Q= new queue();
      coord start=new coord(startRow,startCol);
      coord goal=new coord(goalRow,goalCol);
      Q.enqueue(start);
      grid[startRow][startCol]=2;
      System.out.println(Q.front().getRow());
      while(!Q.empty()||(Q.front().getRow()==goalRow&&Q.front().getCol()==goalCol)){
         //Move Left
         if((Q.front().getCol() - 1) >= 0 && grid[Q.front().getRow()][Q.front().getCol() - 1] == 1)
         {
            Q.enqueue(new coord(Q.front().getRow(), Q.front().getCol() - 1));
            grid[Q.front().getRow()][Q.front().getCol() - 1] += length++;
            System.out.println("Left <");
         }
                       //Move Up
         if((Q.front().getRow() - 1) >= 0 &&grid[Q.front().getRow() - 1][Q.front().getCol()] == 1)
         {
            Q.enqueue(new coord(Q.front().getRow() - 1, Q.front().getCol()));
         }
                       //Move Right
         if(Q.front().getCol()+ 1<=4 && grid[Q.front().getRow()][Q.front().getCol() + 1] == 1)
         {
            Q.enqueue(new coord(Q.front().getRow(), Q.front().getCol() + 1));
            grid[Q.front().getRow()][Q.front().getCol() + 1] = length++;
         }
                       //Move Down
         if(Q.front().getRow() + 1 <= 4&&grid[Q.front().getRow() + 1][Q.front().getCol()] == 1)
         {
            Q.enqueue(new coord(Q.front().getRow() + 1, Q.front().getCol() + 1));
         }
         Q.dequeue();		
      }
   }
}
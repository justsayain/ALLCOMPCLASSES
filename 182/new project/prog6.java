/*Steve Delgado   
Program Assignment #6  
   Due Date: 12/10/13 Date turned in:12/10/13  
   This program creates a queue to print out the shortest path 
   and the path using nodes/coordinates.  */  
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
      if(empty()){ //do nothing 
      }
      else{
         front_pt=front_pt.getNext();
         if(empty()) //check to see if the front is empty again
            back_pt=null;
      }
   }
 
   public void enqueue (coord data) {
      node temp=new node(data,null);
      if(empty()) //create a new node to enque
      {
         front_pt=back_pt=temp;
      }
      else
      {
         back_pt.setNext(temp);//add to the back of the queue
         back_pt=temp;
      }
   
   }
   public static void resetGrid(int grid[][]){
      for(int i = 0; i < grid.length; i++) //rows
      {
         for(int j = 0; j < grid[0].length; j++)//cols
         {
            if(grid[i][j] != 0)
               grid[i][j] = 1;
         }
      }
   }
   public void printPath(){
      node temp=front_pt;
      while(temp!=null){
         System.out.print("("+temp.getCoord().getRow()+","+temp.getCoord().getCol()+")-");
         temp=temp.getNext();
      }
   }
}
class path{
   public static String myName() {   
      return "Steve Delgado";   
   } 
   public static void shortest_path(int[][] grid, 
   int startRow, int startCol,int goalRow, int goalCol) {
      int length=2; //just to check if start and goal are 0
      queue Q= new queue();
      coord start=new coord(startRow,startCol);
      coord goal=new coord(goalRow,goalCol);
      if(grid[startRow][startCol]==0||grid[goalRow][goalCol]==0){
         System.out.println("No path through grid");              
         length=0;
      }
      else{
         Q.enqueue(start);
         grid[startRow][startCol]=2; //change start so that its not a 1
      }
      while(!Q.empty()&&!(Q.front().getRow()==goalRow&&
      Q.front().getCol()==goalCol)){
         //Move Left
         if((Q.front().getCol() - 1) >= 0 && 
         grid[Q.front().getRow()][Q.front().getCol() - 1] == 1)
         {
            Q.enqueue(new coord(Q.front().getRow(), Q.front().getCol() - 1));
            grid[Q.front().getRow()][Q.front().getCol() - 1] = 
            grid[Q.front().getRow()][Q.front().getCol()]+1; //adds to value in that grid
         }
                       //Move Up
         if((Q.front().getRow() - 1) >= 0 &&
         grid[Q.front().getRow() - 1][Q.front().getCol()] == 1)
         {
            Q.enqueue(new coord(Q.front().getRow() - 1, Q.front().getCol()));
            grid[Q.front().getRow()-1][Q.front().getCol()] = 
            grid[Q.front().getRow()][Q.front().getCol()]+1; //adds to value in that grid
         }
                       //Move Right
         if(Q.front().getCol()+ 1<=grid[0].length-1 && 
         grid[Q.front().getRow()][Q.front().getCol() + 1] == 1)
         {
            Q.enqueue(new coord(Q.front().getRow(), Q.front().getCol() + 1));
            grid[Q.front().getRow()][Q.front().getCol()+1] = 
            grid[Q.front().getRow()][Q.front().getCol()]+1; //adds to value in that grid
         }
                       //Move Down
         if(Q.front().getRow() + 1 <= grid.length-1&&
         grid[Q.front().getRow() + 1][Q.front().getCol()] == 1)
         {
            Q.enqueue(new coord(Q.front().getRow() + 1, Q.front().getCol()));
            grid[Q.front().getRow()+1][Q.front().getCol()] =
            grid[Q.front().getRow()][Q.front().getCol()]+1; //adds to value in that grid
         }
         Q.dequeue();		
      }
      
      if(length==0){}
      else{
         System.out.println("there is a path of length "+ (grid[goalRow][goalCol]-1));
      }
       Q.printPath();//only prints whats on the queue :(
       Q.resetGrid(grid);
   }
}
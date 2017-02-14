//Comp 110 Spring 2013
//Steve Delgado
//Sudoku Project
   import java.util.*;
   import java.io.*;

   public class Sudoku{

   //intialize rows and columns constant(does not change)

      public static final int rows=9;
      public static final int cols=9;
      public static int [][] board= new int[rows][cols];
   //------------------------------------------------
   //show()
   //print the contents of the board to the display
   //------------------------------------------------
      public static void show(){
         System.out.println("  123 456 789\n");//header for columns
         for(int i =0;i<rows;i++){
            System.out.print(i+1+" ");// row #'s
            for(int j=0;j<cols;j++){
               if(board[i][j]==0)
                  System.out.print("_");
               else System.out.print(board[i][j]);//executes for columns

               if(j==2)
                  System.out.print("|");
               if(j==5)
                  System.out.print("|");
               if(i==2&&j==8)
                  System.out.print("\n  ===|===|===");
               if(i==5&&j==8)
                  System.out.print("\n  ===|===|===");
            }
            System.out.println();//executes for rows
         }
      }

   //------------------------------------------------
   //load()
   //load the contents of a clue file from the file system
   //------------------------------------------------

      public static void load(String filename) {
         Scanner fin= null;
         try{fin = new Scanner(new FileReader(filename));}
            catch(Exception e) {
               System.out.println("Error opening file "+filename);
               System.exit(0);
            }
         for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++) {
               board[i][j]=fin.nextInt();
            }
            System.out.println();
         }
         System.out.println();
      }

      public static void setValue(int r, int c, int value) {
         board[r][c]=value;
      }

      public static boolean canAddToRow(int r, int value) {
         for (int c=0; c<cols; c++) {
            if (value==board[r][c])
               return false;
         }
         return true;
      }

      public static boolean canAddToColumn(int c, int value) {
         for (int r=0; r<rows; r++) {
            if (value==board[r][c])
               return false;
         }
         return true;
      }

      public static boolean canAddToSubgrid(int r, int c, int value) {
         int sgr=(r/3)*3;
         int sgc=(c/3)*3;
         for (int row=sgr;row<sgr+3; row++){
            for (int col=sgc;col<sgc+3;col++){
               if (board [row][col]==value)
                  return false;
            }
         }
         return true;
      }

      public static boolean isComplete() {
         for (int r=0;r<9; r++){
            for(int value=1;value<=9;value++){
               if(canAddToRow(r,value))
                  System.out.println("Sudoku puzzle not complete");
               return false;
            }
         }
         System.out.println("Sudoku puzzle is complete");
         return true;
      }

      public static void commandline() {
         Scanner in = new Scanner (System.in);
         while(true){
            System.out.print(">");
            String s=in.nextLine();
            String[] t= s.split(" ");
            String cmd=t[0];
            if (cmd.equals("quit"))
               break;
            else if(cmd.equals("show"))
               show();
            else if(cmd.equals("load"))
               load("testfile.txt");

            else if(cmd.equals("set")){
               int r= Integer.parseInt(t[1]);
               int c= Integer.parseInt(t[2]);
               int value= Integer.parseInt(t[3]);
               r--;
               c--;
               boolean rcheck = canAddToRow(r,value);
               boolean ccheck = canAddToColumn(c,value);
               boolean scheck = canAddToSubgrid(r,c,value);

               if (rcheck && ccheck  && scheck ) {
                  setValue(r,c,value);
               }
               else {
                  System.out.println(" number not valid ");
               }
            }
            else if(cmd.equals("complete")){
               isComplete();
            }
            else
               System.out.println("Input not recognized");
         }
      }

   //=======================================
   // main method
   //=========================================
      public static void main(String[]args){
         commandline();
      }
   }

/* Steve Delgado
 * Project 0 Linear Heap
 * This project creates a heap by using an array.
   It inserts strings, deletes the first position, and returns the array in pre Order.
 * COMP 282
 * Spring 14
 * 
 */
import java.util.*;

public class HeapLinear implements SimpleHeap{
   private int position,size;

   private ArrayList<String> heap=new ArrayList<String>();

   public void HeapLinear(){//I do not know why i can't declare the list in here
   }

   private int compare(String x,String y){//method to compare to strings gives back int
      return (x.compareTo(y));            //if x is greater positive number , if equal 0, if less negative number
   }
   private void TrikleUp(){ //method to make it a heap based on insert
      int i=heap.size()-1;
      while(i>=1){
         int parent=(i-1)/2;
         if(compare(heap.get(i),heap.get(parent))>=0){ //compare parent with x
            swap(i,parent);
            i=parent;
         }
         else//if x is smaller than parent
            break;
      }
   }
   private void swap(int i, int j){//method to swap 2 strings in the array 
      String temp = heap.get(i); //save first index to temp
      heap.set(i,heap.get(j));   //swap what is in j to i
      heap.set(j,temp);          //swap what is in temp to j
   }
   public void insert(String str){
      heap.add(str); //insert to array list
      TrikleUp();    //call trickle up method every time you insert
         
   }
   public String deleteMax(){ //method to delete the beginning of the heap
      String result= " ";
      int i=heap.size()-1;//last position
      if(heap.size()==0)//size 0 nothing in heap
         System.out.print("Empty Heap");
      else{
         result=heap.get(0); //store first thing in heap into result to return
         heap.set(0,heap.get(i)); //swap first position with last position
         i--; 
         TrickleDown();//call trickle down so it can be a heap
      }
      heap.remove(heap.size()-1); //removes last thing in heap and lowers size
      return result;
   }
   private void TrickleDown(){
      int i=0;
      int lchild=i*2+1;
      int rchild=i*2+2;
      while(i<=heap.size()-2&&(lchild<heap.size()-1&&rchild<heap.size()-1)){
         //go into loop if its not out bounds and the children are not out of bounds
            if(compare(heap.get(lchild),heap.get(i))>=0&&
            compare(heap.get(rchild),heap.get(i))>=0){ //both right and left child are bigger then x
               int key=compare(heap.get(lchild),heap.get(rchild));
               if(key>=0){//lchild is bigger then right child
                  swap(i,lchild); //swap
                  i=lchild;   //new index leftchild
               }
               else{
                  swap(i,rchild); //swap
                  i=rchild;   //new index rightchild
               }
            }
            else if(compare(heap.get(lchild),heap.get(i))>=0){ //only left is bigger
               swap(i,lchild); //swap
               i=lchild;   //new index leftchild
            }
            else if(compare(heap.get(rchild),heap.get(i))>=0){ //only right is bigger
               swap(i,rchild); //swap
               i=rchild;   //new index leftchild
            }
            else{ //children are not bigger
               break;   //get out of loop
            }
                   lchild=i*2+1; //change lchild with new index
          rchild=i*2+2; //change rchild with new index
      }
   }
   public String preOrderTraversal(){ 
      return preOrderTraversal(heap,0); //calls recursive method
   }
   private static String preOrderTraversal(ArrayList<String> heap,int i){
      String result ="";
      if (i >= heap.size()) {  //do nothing    
      }
      else{ //print value, left, then right
         result=heap.get(i)+ preOrderTraversal(heap,(2*i)+1)+preOrderTraversal(heap,(2*i)+2);
      }
      return result;
   }}
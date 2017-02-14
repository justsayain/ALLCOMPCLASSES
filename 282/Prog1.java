public interface SimpleHeap {
public void insert(int str);
public String deleteMax();
public String preOrderTraversal();
}

public class HeapLinear implements SimpleHeap{
   private int A[];
   private int length;
   
   public void buildHeap(int num){
      A= new int [num];
   }
   public void insert(int num){

   }
}

/* I have commented out the HeapTree commands so that
 * you can test just your HeapLinear. If you want to 
 * test out both, uncomment the other lines.
 */

public class Driver0 {
   public static void main(String[] args) {
      HeapLinear theLinearHeap = new HeapLinear();
     HeapTree  HeapTree   = new HeapTree();

      theLinearHeap.insert("1");
      theLinearHeap.insert("2");
     theLinearHeap.insert("5");
     theLinearHeap.insert("3");
      theLinearHeap.insert("4");
      theLinearHeap.insert("7");
      theLinearHeap.insert("8");
      theLinearHeap.insert("9");
      theLinearHeap.deleteMax();
      theLinearHeap.deleteMax();


      System.out.println("Contents of the linear heap preordered\n");
      System.out.println(theLinearHeap.preOrderTraversal());

   
    HeapTree.insert("A");
     HeapTree.insert("B");
      HeapTree.insert("V");
      HeapTree.insert("C");
       HeapTree.insert("E");
        HeapTree.insert("G");
         HeapTree.insert("D");
          HeapTree.insert("V");
           HeapTree.insert("Z");
           
      //HeapTree.insert("You");
     // HeapTree.insert("Today");
//HeapTree.insert("Ziggy");
     // HeapTree.insert("Yes");
      System.out.println(HeapTree.deleteMax());
     // HeapTree.deleteMax();
                                                                  
      System.out.println("Contents of the tree heap preordered\n");
      System.out.println(HeapTree.preOrderTraversal());
      
   }
}

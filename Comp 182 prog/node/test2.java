public class test2 {

   public static void main(String[] args) {

     IntBST s1= new IntBST();
     s1.insert(50);
     s1.insert(100);
       s1.insert(10);
        s1.insert(25);
         s1.insert(30);
         s1.insert(30);
     System.out.println(s1);
     IntBST s2= new IntBST(s1);
     
   
     System.out.println(s2);
     s1.insert(400);
     System.out.println(s1);
     System.out.println(s2);
     System.out.println(s1.search(25));
     System.out.println(s1.search(10));
     System.out.println(s1.search(1));
     System.out.println(s1.height());
     s1.insert(350);
     System.out.println(s1.height());
     s1.insert(36);
     System.out.println(s1.height());
     //for(int i=1;i<50;i+=2)
     // s1.insert(i);
     System.out.println(s1);
     System.out.println(s1.height());
     s1.insert(9);
        s1.insert(8);
         s1.insert(11);
         s1.insert(30);
//         s1.rotateRight(10);
//     System.out.println(s1);
      s1.rotateLeft(10);
       System.out.println(s1);   
     System.out.println(s1.leafCt());
    // System.out.println(s1.closeLeaf());
     //s1.removeLeaves();
     System.out.println(s1);
    // System.out.println(s1.closeLeaf());
     //s1.removeLeaves();
     System.out.println(s1);
     s1.insert(12);
     //s1.delete(2);
     s1.rotateRight(10);
     s1.rotateLeft(10);
     s1.rotateRight(11);
     s1.rotateRight(9);
     System.out.println(s1.closeLeaf());
     System.out.println(s1);
     s1.delete(9);
     s1.delete(50);
     s1.delete(30);
     }
}
import java.io.*;
import java.util.*;

/***--------------------------------------------------------------------***/
/***  In this example we will be interested in storing integer objects  ***/
/***--------------------------------------------------------------------***/
class BigInt
{
   int number;

   BigInt(int x)  { number = x; }
   int value()    { return number; }
   void display() { System.out.print(number + " "); }
}

/***--------------------------------------------------------------------***/
/***   The Cell class is used to "carry" the objects in the HeapSort    ***/
/***--------------------------------------------------------------------***/
class Cell
{
   Object item;
   Cell leftnext;
   Cell rghtnext;
   Cell lefttree;
   Cell rghttree;
   Cell parent;

   Cell(Object data, Cell ln, Cell prnt)
   {
      item     = data;
      lefttree = null;
      rghttree = null;
      leftnext = ln;
      rghtnext = null;
      parent   = prnt;
   }
   Object objectOf()     { return item; }
   Cell   parentOf()     { return parent; }
   Cell   leftChildOf()  { return lefttree; }
   Cell   rightChildOf() { return rghttree; }
   Cell   leftNextOf()   { return leftnext; }
   Cell   rightNextOf()  { return rghtnext; }
   void   setLeftChild(Cell t)  { lefttree = t; }
   void   setRightChild(Cell t) { rghttree = t; }
   void   setLeftNext(Cell t)   { leftnext = t; }
   void   setRightNext(Cell t)  { rghtnext = t; }
   void   setObject(Object obj) { item = obj; }
}

/***--------------------------------------------------------------------***/
/***  The class of Binary Trees -                                       ***/
/***    includes the following methods, among others                    ***/
/***       1. HeapifyDown - percolate an object down from the root      ***/
/***       2. HeapifyUp   - percolate an object up from a leaf          ***/
/***       3. popNode     - delete the last Cell and return its object  ***/
/***       4. setRoot     - replace an object at the root with another  ***/
/***       5. HeapSort    - repeat: setRoot(popNode()); heapifyDown();  ***/
/***       6. addNodeToHeap - add a Cell to end of heap and add object  ***/
/***    saves information in the following variables                    ***/
/***       1. dispfn      - pointer to function that displays the tree  ***/
/***       2. valfn       - pointer to function returning value of obj  ***/
/***       3. head, tail  - pointers locating root and end of tree      ***/
/***    tail pointer -                                                  ***/
/***       1. points to a leaf Cell if the tree has an odd number of    ***/
/***          Cells.  If the tree has one cell, it points to the root,  ***/
/***          otherwise it points to the leftmost Cell in the row that  ***/
/***          is one up from the bottom that has no children, or, in    ***/
/***          the case of a full tree, it points to the leftmost Cell   ***/
/***          of the bottom level.                                      ***/
/***       2. points to the rightmost Cell, one level up from the       ***/
/***          bottom, that has a child.                                 ***/
/***--------------------------------------------------------------------***/
class BinTree
{
   Cell head;
   Cell tail;

   /***--------------------------------------------------------------------***/
   /***   This is the function used to return an object value for BigInt   ***/
   /***--------------------------------------------------------------------***/
   int valuefunc(Object obj)   { return ((BigInt)(obj)).value(); }

   BinTree() { head = tail = null;  }

   /***--------------------------------------------------------------------***/
   /***                   Basic function for building a heap               ***/
   /***--------------------------------------------------------------------***/
   void addNodeToHeap(Object obj)
   {
      addNode(obj);
      heapifyUp();
   }

   Object getRoot()
   {
      Object obj = head.objectOf();
      head.setObject(null);
      return obj;
   }

   /***--------------------------------------------------------------------***/
   /***       Remove the last Cell from a tree and return its object       ***/
   /***--------------------------------------------------------------------***/
   Object popNode()
   {
      if (head == null) return null;
      if (tail.leftNextOf() == null && tail.leftChildOf() == null)
      {
         Object obj = head.objectOf();
         head = tail = null;
         return obj;
      }
      else
      if (tail.leftChildOf() != null)
      {
         Object obj = tail.leftChildOf().objectOf();
         tail.leftChildOf().leftNextOf().setRightNext(null);
         tail.setLeftChild(null);
         return obj;
      }
      else
      {
         tail = tail.leftNextOf();
         Object obj = tail.rightChildOf().objectOf();
         tail.leftChildOf().setRightNext(null);
         tail.setRightChild(null);
         return obj;
      }
   }

   /***--------------------------------------------------------------------***/
   /***  Heapify Down -                                                    ***/
   /***     let t be a Cell with an object                                 ***/
   /***     let h be the child Cell of t with object of least value        ***/
   /***     if no such child Cell exists, stop                             ***/
   /***     otherwise, if t's object value > h's object value              ***/
   /***     then swap t's object with h's object, set t = h, and repeat    ***/
   /***--------------------------------------------------------------------***/
   void heapifyDown()
   {
      Cell t = head, h;
      Object obj;

      if (head == null) return;

      while (true)
      {
         if (t.leftChildOf() == null && t.rightChildOf() == null)
            break;
         else
         if (t.rightChildOf() == null)
             h = t.leftChildOf();
         else
         if (valuefunc(t.leftChildOf().objectOf())
              < valuefunc(t.rightChildOf().objectOf()))
            h = t.leftChildOf();
         else
            h = t.rightChildOf();

         if (valuefunc(h.objectOf()) < valuefunc(t.objectOf()))
         {
            obj = t.objectOf();
            t.setObject(h.objectOf());
            h.setObject(obj);
            t = h;
         }
         else
            break;
      }
   }

   /***--------------------------------------------------------------------***/
   /***  Heapify Up -                                                      ***/
   /***     let h be the last Cell of the tree                             ***/
   /***     if no such Cell exists, stop                                   ***/
   /***     otherwise, if h's object value < h's parent's object value     ***/
   /***     then swap h's object with h's parent's object,                 ***/
   /***     set h = h's parent,                                            ***/
   /***     and repeat                                                     ***/
   /***--------------------------------------------------------------------***/
   void heapifyUp()
   {
      Cell h;
      Object obj;

      if (head == null || (head == tail && tail.leftChildOf() == null)) return;
      if (tail.rightChildOf() == null && tail.leftChildOf() == null)
         h = tail.leftNextOf().rightChildOf();
      else
         h = tail.leftChildOf();

      while (true)
      {
         if (h == head) break;
         if (valuefunc(h.objectOf()) < valuefunc(h.parentOf().objectOf()))
         {
            obj = h.objectOf();
            h.setObject(h.parentOf().objectOf());
            h.parentOf().setObject(obj);
            h = h.parentOf();
         }
         else
            break;
      }
   }

   void putRoot(Object obj)
   {
      if (obj == null) return;
      if (head == null) return;
      head.setObject(obj);
   }

   /***--------------------------------------------------------------------***/
   /***  Add a Cell to the end of a heap and insert an object in that Cell ***/
   /***--------------------------------------------------------------------***/
   void addNode(Object obj)
   {
      if (obj == null) return;
      if (head == null)
          head = tail = new Cell(obj, null, null);
      else
      if (tail.leftChildOf() == null)
      {
         if (tail.leftNextOf() == null)
         {
            tail.setLeftChild(new Cell(obj, tail, tail));
            tail.setRightNext(tail.leftChildOf());
         }
         else
         {
            Cell last = tail.leftNextOf().rightChildOf();
            tail.setLeftChild(new Cell(obj, last, tail));
            last.setRightNext(tail.leftChildOf());
         }
      }
      else
      {
         Cell last = tail.leftChildOf();
         tail.setRightChild(new Cell(obj, last, tail));
         last.setRightNext(tail.rightChildOf());
         tail = tail.rightNextOf();
      }
   }

   void display()
   {
      if (head == null) { System.out.println("(empty)"); return; }
      for (Cell t = head ; t != null ; t = t.rightNextOf())
         System.out.print(t.objectOf() + " ");
      System.out.println();
   }

   boolean empty() { return head == null; }
}

public class HeapSort
{
   /***--------------------------------------------------------------------***/
   /***   Perform the heapsort on a binary tree T that is assumed a heap   ***/
   /***--------------------------------------------------------------------***/
   static void heapSort(BinTree T)
   {
      while (!T.empty())
      {
         ((BigInt)(T.getRoot())).display();
         T.putRoot(T.popNode());
         T.heapifyDown();
      }
   }

   /***--------------------------------------------------------------------***/
   /***                      Test of the above functions                   ***/
   /***--------------------------------------------------------------------***/
   public static void main(String args[])
   {
      BinTree T = new BinTree();

      for (int i=29 ; i > 0 ; i--) T.addNodeToHeap(new BigInt(i));
      T.display();
      System.out.println("******");
      heapSort(T);
      System.out.println();
   }
}
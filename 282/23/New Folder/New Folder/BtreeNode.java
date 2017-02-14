class BtreeNode<E>
{
   private E [] a;
    int m = 4; //for an order 6 tree
    boolean leaf = true;
    int keyTally = 1;
    int keys[ ] = new int[m-1];
    BtreeNode<E> references[] = new BtreeNode[m];

    BtreeNode(int key)  //constructor
    {
         keys[0] = key;
         for (int i  = 0; i < m; i++)
	 references[i] = null;
    }
}

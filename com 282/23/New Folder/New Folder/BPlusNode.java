public abstract class BPlusNode<E extends Comparable<E>>
{
    private E[] keys;
    private int numKeys;
    private long left, right;

    public BPlusNode(E[] keys, long left, long right)
    {
        this.keys = keys;
        this.numKeys = 4;
        this.left = left;
        this.right = right;
    }

    public abstract boolean isLeaf();

    public E[] getKeys()
    {
        return this.keys;
    }

    public int getNumKeys()
    {
        return this.numKeys;
    }
    public boolean isFull()
    {
        return numKeys == keys.length;
    }
    
    public boolean underflow()
    {
        return numKeys < Math.ceil(keys.length / 2.0) - 1; 
    }
   
    public long getRight()
    {
        return this.right;
    }

    public long getLeft()
    {
        return this.left;
    }

    public boolean canBeBorrowedFrom()
    {
        return (numKeys - 1) >= Math.ceil(keys.length / 2.0) - 1; 
    }

    public E borrowKey()
    {
        return keys[numKeys - 1];
    }

    public E borrowFirstKey()
    {
        return keys[0];
    }

    public final void setLeft(long left)
    {
        this.left = left;
    }
    
 
    public final void setRight(long right)
    {
        this.right = right;
    }
    
    /**
     * performs several cleaning up actions after borrowing from the right.
     */
    public abstract void doneBorrowing();
    /**
     * performs several cleanign up actions after borrowing from the left.
     */
    public abstract void doneBorrowingFirst();
}
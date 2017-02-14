public interface DataStructure<E extends Comparable<E>> {
public void insert(E item);
public E find(E item);
public E delete(E item);
public String preOrderTraversal();
}
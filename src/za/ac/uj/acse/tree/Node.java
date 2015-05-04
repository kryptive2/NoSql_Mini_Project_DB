/**
 * 
 */
package za.ac.uj.acse.tree;

/**
 * @author Mpho Makalancheche
 *
 * This class defines how each node in the binary tree will look like
 * Each Node has a Key variable which will have to be unique to all the nodes
 * 
 */
public class Node<K extends Comparable<? super K>> implements Comparable<K> {
    private K key;
    private String value;

    public Node<K> rightChild = null;
	public Node<K> leftChild = null;
    
    /**
     * public constructor:
     */
    public Node(){}
    
    
    /**
     * Parameterized public constructor
     * @param key: the key for each node
     * @param value: the actual value stored
     */
    public Node(K key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return key of the node;
     */
    public K getKey() {
        return key;
    }

    /**
     * @return the value of the node
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param key: set the key for the node
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * @param value: set the vale for the node
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param node: the node to compare to
     * @return int value of the comparison outcome
     */
    public int compareTo(Node<K> node) {
        return this.compareTo(node.getKey());
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(K o) {
        return key.compareTo(o);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return  value.toString(); // return the data as Json format
    }

}
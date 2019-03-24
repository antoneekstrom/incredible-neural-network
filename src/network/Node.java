package network;

/**
 * A node in a {@link Layer}.
 */
public class Node {

    /**
     * The value of the node.
     */
    private double value;

    /**
     * 
     */
    public Node() {}

    /**
     * 
     */
    public Node(double value) {
        set(value);
    }

    /**
     * 
     */
    public void randomize() {
        set(Math.random());
    }

    /**
     * Get the value.
     * @return the value
     */
    public double value() {
        return value;
    }

    /**
     * Set the value.
     * @param value the new value
     */
    public void set(double value) {
        this.value = value;
    }
    
}
package network;

/**
 * InputLayer
 */
public class InputLayer extends Layer {

    /**
     * @param nodes the amount of nodes in the layer
     */
    public InputLayer(int nodes) {
        super(nodes);
    }

    /**
     * Set the values of the nodes.
     * @param nodes the values
     */
    public void set(double... nodes) {
        for (int i = 0; i < nodes.length; i++) {
            getVector().set(i, 0, nodes[i]);
        }
    }

    /**
     * Set the value of a node.
     * @param value the value
     * @param index the index
     */
    public void set(double value, int index) {
        getVector().set(index, 0, value);
    }
    
}
package network;

import java.util.ArrayList;

/**
 * A layer in a {@link Network}.
 */
public class Layer {

    /**
     * Nodes in this layer.
     */
    private final Node[] nodes;

    /**
     * Weights connected to other layers.
     */
    private ArrayList<Weights> weights;

    /**
     * @param amountNodes the amount of nodes in the layer
     */
    public Layer(int amountNodes) {
        nodes = new Node[amountNodes];
        weights = new ArrayList<>();
    }

    /**
     * Connect a layer.
     * @param layer another layer
     */
    public void connect(Layer layer) {
        Weights w = new Weights(this, layer);
        addWeights(w);
        layer.addWeights(w);
    }

    /**
     * @param nodes the new node value
     */
    public void setNodes(double[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            if (i < this.nodes.length) {
                this.nodes[i].set(nodes[i]);
            }
        }
    }

    /**
     * Get the nodes in the layer.
     * @return the nodes
     */
    public Node[] getNodes() {
        return nodes;
    }

    /**
     * @return
     */
    public double[] getValues() {
        double[] values = new double[nodes.length];

        for (int i = 0; i < values.length; i++) {
            values[i] = nodes[i].value();
        }

        return values;
    }

    /**
     * Get the amount of nodes in the layer
     * @return the size of the layer
     */
    public int size() {
        return getNodes().length;
    }

    /**
     * Get the weights.
     * @return the weights
     */
    public ArrayList<Weights> getWeights() {
        return weights;
    }

    /**
     * @param weights the weights
     */
    protected void addWeights(Weights weights) {
        getWeights().add(weights);
    }

}
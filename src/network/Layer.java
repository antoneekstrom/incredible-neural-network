package network;

import java.util.ArrayList;

import math.Vector;

/**
 * A layer in a {@link Network}.
 */
public class Layer {

    /**
     * Nodes in this layer.
     */
    private final Vector values;

    /**
     * Weights connected to other layers.
     */
    private ArrayList<Weights> weights;

    /**
     * @param amountNodes the amount of nodes in the layer
     */
    public Layer(int amountNodes) {
        values = new Vector(amountNodes);
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
    public void setValues(double[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            if (i < this.values.m()) {
                this.values.set(i, 0, nodes[i]);
            }
        }
    }

    /**
     * @return the values
     */
    public double[] getValues() {
        return values.array();
    }

    /**
     * Get the nodes in the layer.
     * @return the nodes
     */
    public Vector getVector() {
        return values;
    }

    /**
     * Get the amount of nodes in the layer
     * @return the size of the layer
     */
    public int size() {
        return getVector().m();
    }

    /**
     * Get the weights.
     * @return the weights
     */
    public ArrayList<Weights> weights() {
        return weights;
    }

    /**
     * @param weights the weights
     */
    protected void addWeights(Weights weights) {
        weights().add(weights);
    }

}
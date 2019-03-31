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
    private Weights inputWeights, outputWeights;

    /**
     * @param amountNodes the amount of nodes in the layer
     */
    public Layer(int amountNodes) {
        values = new Vector(amountNodes);
    }

    /**
     * Connect a layer.
     * @param layer another layer
     */
    public void connect(Layer layer) {
        Weights w = new Weights(this, layer);
        outputWeights = w;
        layer.inputWeights = w;
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
     * @param inputWeights the inputWeights to set
     */
    public void setInputWeights(Weights inputWeights) {
        this.inputWeights = inputWeights;
    }

    /**
     * @param outputWeights the outputWeights to set
     */
    public void setOutputWeights(Weights outputWeights) {
        this.outputWeights = outputWeights;
    }

    /**
     * @return the inputWeights
     */
    public Weights getInputWeights() {
        return inputWeights;
    }

    /**
     * @return the outputWeights
     */
    public Weights getOutputWeights() {
        return outputWeights;
    }

}
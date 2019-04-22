package network;

import java.io.Serializable;

import math.Matrix;
import math.Vector;

/**
 * A layer in a {@link NeuralNetwork}.
 */
public class Layer extends Vector implements Serializable {

    private static final long serialVersionUID = 3831011053857002186L;

    /**
     * Weights connected to other layers.
     */
    private Weights inputWeights, outputWeights;

    /**
     * Cached value for backProp.
     */
    private Matrix z;

    /**
     * A name for the layer.
     */
    private String layerName;

    /**
     * @param nodes the amount of nodes in the layer
     */
    public Layer(int nodes) {
        this(nodes, "Layer");
    }

    /**
     * @param nodes the amount of nodes in the layer
     * @param name label the layer
     */
    public Layer(int nodes, String name) {
        super(nodes);
        this.layerName = name;
    }

    /**
     * Connect a layer.
     * @param layer another layer
     */
    public void connect(Layer layer) {
        Weights w = new Weights(this, layer);
        w.randomize(1);
        outputWeights = w;
        layer.inputWeights = w;
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

    /**
     * @param layerName the layerName to set
     */
    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /**
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * @return the z
     */
    public Matrix getZ() {
        return z;
    }
    
    /**
     * @param z the z to set
     */
    public void setZ(Matrix z) {
        this.z = z;
    }

    @Override
    public void print() {
        System.out.println(getLayerName());
        super.print();
    }

}
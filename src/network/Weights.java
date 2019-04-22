package network;

import math.Matrix;

/**
 * Weights for a {@link Layer} in a {@link NeuralNetwork}.
 */
public class Weights extends Matrix {

    // the two layers which are connected
    private final Layer l0, l1;

    /**
     * The bias of the weights.
     */
    private Matrix bias;

    /**
     * 
     */
    private String weightsName;

    /**
     * Create a set of weights connecting two layers.
     * 
     * @param l0 a layer
     * @param l1 another layer
     */
    public Weights(Layer l0, Layer l1) {
        super(l1.m(), l0.m());
        this.l0 = l0;
        this.l1 = l1;
        
        weightsName = l0.getLayerName() + " weights";

        bias = new Matrix(m(), n());
        bias.setAll(v -> 0d);
    }

    /**
     * @return the layer
     */
    public Layer getL0() {
        return l0;
    }

    /**
     * @return the layer
     */
    public Layer getL1() {
        return l1;
    }
    
    /**
     * @return the bias
     */
    public Matrix getBias() {
        return bias;
    }

    /**
     * @param bias the bias to set
     */
    public void setBias(Matrix bias) {
        this.bias = bias;
    }

    @Override
    public void print() {
        System.out.println(weightsName);
        super.print();
    }

}
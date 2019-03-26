package network;

import math.Matrix2d;

/**
 * Weights for a {@link Layer} in a {@link Network}.
 */
public class Weights {

    // the two layers which are connected
    private final Layer l1, l2;

    /**
     * The weights between the layers.
     */
    private final Matrix2d weights;

    /**
     * @param l1
     * @param l2
     */
    public Weights(Layer l1, Layer l2) {
        this.l1 = l1;
        this.l2 = l2;

        weights = new Matrix2d(l1.size(), l2.size());

        // Assign random weights
        randomize();
    }

    /**
     * 
     */
    public void randomize() {
        weights.setAll(d -> Math.random());
    }

    /**
     * @return the weights
     */
    public Matrix2d getMatrix() {
        return weights;
    }

    /**
     * @return the layer
     */
    public Layer getL1() {
        return l1;
    }

    /**
     * @return the l2
     */
    public Layer getL2() {
        return l2;
    }

}
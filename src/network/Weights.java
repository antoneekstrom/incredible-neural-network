package network;

import util.MatrixHelper;

/**
 * Weights for a {@link Layer} in a {@link Network}.
 */
public class Weights {

    // the two layers which are connected
    private final Layer l1, l2;

    /**
     * The weights between the layers.
     */
    private final Double[][] weights;

    /**
     * @param l1
     * @param l2
     */
    public Weights(Layer l1, Layer l2) {
        this.l1 = l1;
        this.l2 = l2;

        weights = new Double[l1.size()][l2.size()];

        // Assign random weights
        MatrixHelper.iterate(weights, (x, y) -> weights[x][y] = Math.random());
    }

    /**
     * 
     */
    public void randomize() {
    }

    /**
     * @return the amount of connections
     */
    public int connections() {
        return weights.length;
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
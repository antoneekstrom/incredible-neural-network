package network;

import math.Matrix;

/**
 * PropagationOutput
 */
public class BackOutput {

    /**
     * 
     */
    private Matrix deltaWeights;

    /**
     * 
     */
    double loss;
    
    /**
     * 
     */
    double deltaLoss;

    public BackOutput(double deltaLoss) {
        this.deltaLoss = deltaLoss;
    }

    /**
     * @return the deltaWeights
     */
    public Matrix getDeltaWeights() {
        return deltaWeights;
    }

    /**
     * @return the deltaLoss
     */
    public double getDeltaLoss() {
        return deltaLoss;
    }

    /**
     * @return the loss
     */
    public double getLoss() {
        return loss;
    }
    
}
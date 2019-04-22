package network.functions;

import network.NeuralNetwork;

/**
 * A function for calculating the amount of wrong the network was in it's prediction.
 * 
 * @see NeuralNetwork
 */
@FunctionalInterface
public interface LossFunction {

    /**
     * Perfom the loss function.
     * 
     * @param actualValues the desired/actual value
     * @param predictedValues the value that the network predicted
     * 
     * @return the loss
     */
    public double apply(double[] actualValues, double[] predictedValues);
    
}
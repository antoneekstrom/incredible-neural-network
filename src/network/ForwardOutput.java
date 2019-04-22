package network;

import math.Matrix;

/**
 * Output result produced by {@link NeuralNetwork#feedForward()}.
 * <p>Contains the result of the forward feed and also cached data used
 * for {@link NeuralNetwork#backPropagate()}.
 * 
 * @see NeuralNetwork
 */
public class ForwardOutput {

    /**
     * 
     */
    private Matrix input;

    /**
     * The result.
     */
    private Matrix result;

    /**
     * 
     */
    private Matrix correctResult;

    /**
     * @param result
     */
    protected ForwardOutput(Matrix input, Matrix result, Matrix correctResult) {
        this.input = input;
        this.result = result;
        this.correctResult = correctResult;
    }

    /**
     * @return the result
     */
    public Matrix getResult() {
        return result;
    }

    /**
     * @return the input
     */
    public Matrix getInput() {
        return input;
    }

    /**
     * @return the correctResult
     */
    public Matrix getCorrectResult() {
        return correctResult;
    }
    
}
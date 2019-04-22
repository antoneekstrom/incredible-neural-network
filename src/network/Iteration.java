package network;

import math.Matrix;

/**
 * A training iteration in a {@link NeuralNetwork}.
 */
public class Iteration {

    private ForwardOutput output;

    private BackOutput backOutput;

    private int iterationIndex;

    private double millis;

    /**
     * @param result the result of the {@link NeuralNetwork#feedForward()}
     * @param loss the loss of the {@link NeuralNetwork#backPropagate()}
     * @param iterationIndex the number of the iteration
     * @param millis the execution time
     */
    public Iteration(ForwardOutput output, BackOutput backOutput, int iterationIndex, double millis) {
        super();

        this.output = output;
        this.backOutput = backOutput;
        this.iterationIndex = iterationIndex;
        this.millis = millis;
    }

    /**
     * Print the iteration information to the console.
     */
    public void print() {
        System.out.println("\n-------");
        System.out.println("iteration: " + getIterationIndex());
        System.out.println("time: " + getMillis() + "ms");
        System.out.println("dloss: " + getBackOutput().getDeltaLoss());
        System.out.println("\ninput: \n" + getOutput().getInput().toString());
        System.out.println("\noutput: \n" + getOutput().getResult().toString());
        System.out.println("\ncorrect output: \n" + getOutput().getCorrectResult().toString());
        System.out.println("-------");
    }

    /**
     * @return the iterationIndex
     */
    public int getIterationIndex() {
        return iterationIndex;
    }

    /**
     * @return the output
     */
    public ForwardOutput getOutput() {
        return output;
    }

    /**
     * @return the loss
     */
    public BackOutput getBackOutput() {
        return backOutput;
    }
    
    /**
     * @return the millis
     */
    public double getMillis() {
        return millis;
    }
    
}
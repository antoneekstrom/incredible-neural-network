package network;

import math.Functions;
import network.functions.ActivationFunction;
import network.functions.SumSquaresDerivate;
import network.functions.LossFunction;
import network.functions.SumSquares;

/**
 * Builds neural networks.
 * 
 * @see NeuralNetwork
 */
public class NetworkBuilder {

    private InputLayer input;
    private OutputLayer output;
    private ActivationFunction activationFunction = Functions::sigmoid, derivateActivationFunction = Functions::sigmoidDerivate;
    private LossFunction lossFunction, derivateLossFunction;
    private double[] correctOutput;
    private Layer[] layers;

    public NetworkBuilder() {
        lossFunction = new SumSquares();
        derivateLossFunction = new SumSquaresDerivate();
    }

    /**
     * Builds a {@link NeuralNetwork}.
     * @return the network
     */
    public NeuralNetwork build() {
        NeuralNetwork n = new NeuralNetwork(input, output, lossFunction, derivateLossFunction, activationFunction, derivateActivationFunction, layers);
        return n;
    }

    /**
     * @return the input
     */
    public InputLayer getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(InputLayer input) {
        this.input = input;
    }

    /**
     * @return the output
     */
    public OutputLayer getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(OutputLayer output) {
        this.output = output;
    }

    /**
     * @return the layers
     */
    public Layer[] getLayers() {
        return layers;
    }

    /**
     * @param layers the layers to set
     */
    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    /**
     * @param lossFunction the lossFunction to set
     */
    public void setLossFunction(LossFunction lossFunction) {
        this.lossFunction = lossFunction;
    }

    /**
     * @return the lossFunction
     */
    public LossFunction getLossFunction() {
        return lossFunction;
    }
    
    /**
     * @return the correctOutput
     */
    public double[] getCorrectOutput() {
        return correctOutput;
    }

    /**
     * @param correctOutput the correctOutput to set
     */
    public void setCorrectOutput(double[] correctOutput) {
        this.correctOutput = correctOutput;
    }

    /**
     * @param derivateLossFunction the derivateLossFunction to set
     */
    public void setDerivateLossFunction(LossFunction derivateLossFunction) {
        this.derivateLossFunction = derivateLossFunction;
    }

    /**
     * @return the derivateLossFunction
     */
    public LossFunction getDerivateLossFunction() {
        return derivateLossFunction;
    }
    
    /**
     * @param activationFunction the activationFunction to set
     */
    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    /**
     * @param derivateActivationFunction the derivateActivationFunction to set
     */
    public void setDerivateActivationFunction(ActivationFunction derivateActivationFunction) {
        this.derivateActivationFunction = derivateActivationFunction;
    }

    /**
     * @return the activationFunction
     */
    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    /**
     * @return the derivateActivationFunction
     */
    public ActivationFunction getDerivateActivationFunction() {
        return derivateActivationFunction;
    }
    
}
package network;

import math.Matrix;
import math.Vector;

import java.io.Serializable;
import java.util.ArrayList;

import math.Functions;
import network.functions.ActivationFunction;
import network.functions.SumSquaresDerivate;
import network.functions.LossFunction;
import network.functions.SumSquares;

// TODO produce documentation of considerably sublime quality

/**
 * A neural network.
 * 
 * @see NetworkBuilder
 * @see Layer
 */
public class NeuralNetwork implements Serializable {

    private static final long serialVersionUID = 9163305576279543940L;

    // The input and output layers are stored separately. 
    // At first I thought I would be doing something else with them and that it would be beneficial to separate them
    // In the end though they are just normal layers.
    // I still feel like it makes sense though.

    /**
     * The layer in which the input is located.
     */
    private final InputLayer input;

    /**
     * The layer where the results of a {@link NeuralNetwork#feedForward()} are stored.
     */
    private final OutputLayer output;

    /**
     * Iteration history. Keeps track of progress.
     */
    private ArrayList<Iteration> iterations;

    /**
     * 
     */
    private TrainingSet trainingSet;

    /**
     * 
     */
    private int currentSet = 0;

    /**
     * The loss function measures the "wrongness" of the networks predictions.
     */
    private LossFunction lossFunction, derivateLossFunction;

    /**
     * The activation determine how the weights affect the nodes values.
     */
    private ActivationFunction activationFunction, derivateActivationFunction;

    /**
     * The desired output of the network for the current input.
     */
    private double[] correctOutput;

    /**
     * The layers of the network.
     */
    private final Layer[] layers;

    /**
     * Construct a neural network.
     * 
     * @param input the input layer
     * @param output the output layer
     * @param lossFunction the loss function
     * @param derivateLossFunction
     * @param activationFunction
     * @param derivateActivationFunction
     * @param layers the middle layers
     * 
     * @see Layer
     * @see LossFunction
     */
    public NeuralNetwork (
        InputLayer input,
        OutputLayer output,
        LossFunction lossFunction,
        LossFunction derivateLossFunction,
        ActivationFunction activationFunction,
        ActivationFunction derivateActivationFunction,
        Layer[] layers) {
        
        iterations = new ArrayList<>();

        this.input = input;
        this.output = output;

        this.activationFunction = Functions::sigmoid;
        this.derivateActivationFunction = Functions::sigmoidDerivate;

        this.derivateLossFunction = derivateLossFunction;
        this.lossFunction = lossFunction;
        this.correctOutput = output.array().clone();

        this.layers = layers;
    }

    /**
     * Construct a neural network.
     * 
     * @param input the input layer
     * @param output the output layer
     * @param lossFunction the loss function
     * @param layers the middle layers
     * 
     * @see Layer
     * @see LossFunction
     */
    public NeuralNetwork(InputLayer input, OutputLayer output, Layer[] layers) {
        this(input, output, new SumSquares(), new SumSquaresDerivate(), Functions::sigmoid, Functions::sigmoidDerivate, layers);
    }

    /**
     * Connect all the layers with random weights.
     */
    public void connectLayers() {

        input.connect(layers[0]);

        for (int i = 0; i < layers.length; i++) {
            if (layers.length > i + 1)
            layers[i].connect(layers[i + 1]);
        }

        layers[layers.length - 1].connect(output);
    }

    /**
     * Set the weights to random values between 0 and 1.
     */
    public void randomizeWeights() {

        for (Layer l : getLayers()) {
            l.getInputWeights().randomize(1);
            l.getOutputWeights().randomize(1);
        }
    }

    /**
     * Adjust the network to be less wrong (hopefully).
     * @param output the output of the {@link #feedForward()}.
     * @return the loss value
     */
    public BackOutput backPropagate(ForwardOutput output) {

        Layer layer = getOutputLayer();
        Weights weights = layer.getInputWeights();
        Layer beforeLayer = weights.getL0(); // == a[l - 1]

        double learningRate = 1; //TODO learning rate
        double dloss = getDerivateLossFunction().apply(getCorrectOutput(), getOutputLayer().getValues());

        Matrix da = layer.copy().multiply(dloss);

        do {
            Matrix dz = da.multiply(layer.copy().setAll(getDerivateActivationFunction()::apply)); // delta of z ?
            Matrix dw = dz.dot(beforeLayer.transpose()); // delta of weights
            Matrix db = dz.copy(); // delta of biases

            System.out.println("dw:\n" + dw.toString());

            weights.add(dw.multiply(learningRate));
            //weights.getBias().add(db.multiply(learningRate));

            // set DA for next cycle
            da = weights.transpose().dot(dz);

            layer = weights.getL0();
            weights = layer.getInputWeights();
        }
        while (weights != null);

        return new BackOutput(dloss);
    }
    
    /**
     * Calculates the output.
     * <p>I'm pretty sure that's what it's supposed to do.
     * 
     * <p>The network is not using any biases at the moment.
     * I should probably add that.
     * 
     * @return the output
     */
    public ForwardOutput feedForward() {

        // the new value for a layer L is calculated with weights * prevLayer
        Layer prevLayer = getInputLayer();
        Layer layer = getLayers()[0];
        Weights weights = layer.getInputWeights();
        // next weights is mostly used to check if the loop has reached the end
        Weights nextWeights = layer.getOutputWeights();

        Matrix input = getInputLayer().copy();

        // iterate over the layers and weights of the network
        // loop breaks if the output weights of a layer is null
        // this means the loop has reached the output layer and should stop
        while (true) {
            // Z1 = W1 * L0 + B1
            // where:
            //  Z1 is the result for the new values of the layer L
            //  W1 are the weights
            //  L0 is the first layer
            //  B1 are the biases of the second layer (L1)
            Matrix z = weights.dot(prevLayer).add(weights.getBias());
            layer.setZ(z.copy());

            // apply the activation function on the z values
            // this creates the matrix "a"
            // creating new variable for a mostly as clarification
            Matrix a = z.setAll(getActivationFunction()::apply);

            // Create a vector from the result with the first column of the matrix
            // the matrix should always be in the form of (n, 1) so this is okay
            Vector v = new Vector(a.getColumn(0));

            // set the values of the layer
            layer.set(v.getValues());

            // set the next layers and weights
            prevLayer = layer;
            nextWeights = layer.getOutputWeights();

            // stop if there are no more weights
            if (nextWeights == null) break;

            // set the next layer and the weights previous to that layer
            layer = layer.getOutputWeights().getL1();
            weights = layer.getInputWeights();
        }

        // return the result
        // this should be the matrix of the outputlayer
        return new ForwardOutput(input, layer.copy(), new Vector(getCorrectOutput().clone()));
    }

    /**
     * @param map
     */
    public void train(InputMap map) {

        getInputLayer().set(map.getInput());
        setCorrectOutput(map.getCorrectOutput());
        
        double t1 = System.nanoTime();

        ForwardOutput output = feedForward();
        BackOutput backOutput = backPropagate(output);

        double t2 = System.nanoTime();

        Iteration i = new Iteration(output, backOutput, getIterations().size(), (t2 - t1) / 1000000);
        i.print();
        getIterations().add(i);
    }

    /**
     * Call {@link #feedForward()} and then {@link #backPropagate()} a certain amount of iterations.
     * @param iterations the amount of iterations
     */
    public void train(int iterations) {
        for (int i = 0; i < iterations; i++) {
            train(getTrainingSet().get(currentSet));

            currentSet++;
            if (currentSet >= getTrainingSet().size()) currentSet = 0;
        }
    }

    /**
     * Print the layers of the network.
     */
    public void printLayers() {
        Layer l = getInputLayer();

        do {
            l.print();
            Weights weights = l.getOutputWeights();
            if (weights == null) break;
            weights.print();
            l = weights.getL1();
        }
        while (l != null);
        System.out.println();
    }


    /**
     * @return the input layer
     */
    public InputLayer getInputLayer() {
        return input;
    }

    /**
     * @return the output layer
     */
    public OutputLayer getOutputLayer() {
        return output;
    }

    /**
     * @return the layers
     */
    public Layer[] getLayers() {
        return layers;
    }

    /**
     * @return the iterations
     */
    public ArrayList<Iteration> getIterations() {
        return iterations;
    }

    /**
     * @param derivateActivationFunction the derivateActivationFunction to set
     */
    public void setDerivateActivationFunction(ActivationFunction derivateActivationFunction) {
        this.derivateActivationFunction = derivateActivationFunction;
    }

    /**
     * @return the derivateActivationFunction
     */
    public ActivationFunction getDerivateActivationFunction() {
        return derivateActivationFunction;
    }

    /**
     * @return the loss
     */
    public LossFunction getLossFunction() {
        return lossFunction;
    }

    /**
     * @param set the set to set
     */
    public void setTrainingSet(TrainingSet set) {
        this.trainingSet = set;
    }

    /**
     * @param m
     * @return
     */
    protected Matrix activationFunction(Matrix m) {
        return m.setAll(this::activationFunction);
    }

    /**
     * @param x
     * @return
     */
    protected double activationFunction(double x) {
        return getActivationFunction().apply(x);
    }

    /**
     * @param lossFunction the loss to set
     */
    public void setLossFunction(LossFunction lossFunction) {
        this.lossFunction = lossFunction;
    }

    /**
     * @param derivateLossFunction the derivateLossFunction to set
     */
    public void setDerivateLossFunction(LossFunction derivateLossFunction) {
        this.derivateLossFunction = derivateLossFunction;
    }

    /**
     * @param m
     * @return
     */
    public Matrix derivateActivationFunction(Matrix m) {
        return m.setAll(this::derivateActivationFunction);
    }

    /**
     * @param x
     * @return
     */
    public double derivateActivationFunction(double x) {
        return derivateActivationFunction(x);
    }

    /**
     * @return the derivateLossFunction
     */
    public LossFunction getDerivateLossFunction() {
        return derivateLossFunction;
    }

    /**
     * @return the correctOutput
     */
    public double[] getCorrectOutput() {
        return correctOutput;
    }
    
    /**
     * @return the set
     */
    public TrainingSet getTrainingSet() {
        return trainingSet;
    }


    /**
     * @param correctOutput the correctOutput to set
     */
    public void setCorrectOutput(double[] correctOutput) {
        this.correctOutput = correctOutput;
    }

    /**
     * @param activationFunction the activationFunction to set
     */
    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }
    /**
     * @return the activationFunction
     */
    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

}
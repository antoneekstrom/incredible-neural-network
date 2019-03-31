package network;

import math.Matrix;
import math.Vector;

import static math.MatrixMath.*;
import static math.Functions.*;

// TODO produce documentation of considerably sublime quality

/**
 * A neural network.
 * 
 * @see NetworkBuilder
 */
public class Network {

    private final InputLayer input;
    private final OutputLayer output;

    private final Layer[] layers;

    /**
     * Construct a neural network.
     * 
     * @param input the input layer
     * @param output the output layer
     * @param layers the middle layers
     */
    public Network(InputLayer input, OutputLayer output, Layer[] layers) {
        this.input = input;
        this.output = output;
        this.layers = layers;
    }

    /**
     * Connect all the layers with random weights.
     */
    public void connectLayers() {
        input.connect(layers[0]);

        for (int i = 0; i < layers.length - 1; i++) {
            layers[i].connect(layers[i + 1]);
        }

        output.connect(layers[layers.length - 1]);
    }

    /**
     * 
     */
    public void randomizeWeights() {

        for (Layer l : getLayers()) {
            l.getInputWeights().randomize();
            l.getOutputWeights().randomize();
        }
    }

    /**
     * TODO
     */
    public void backPropagate() {
    }
    
    /**
     * TODO
     */
    public Matrix feedForward() {

        // Current layer and weights
        Layer layer = getInputLayer();
        Weights outputWeights;

        Matrix z = layer.getVector();

        // Every layer except the last will have output weights which will carry the input all the way to the end

        while ((outputWeights = layer.getOutputWeights()) != null) {

            z = layer.getVector().rotate();

            z = sigmoid(z.dot(outputWeights.getMatrix()));

            // Get the next layer which is connected to the weights and check it's weights if there are any
            layer = outputWeights.getL2();
        }

        return z;

    }


    /**
     * @return the input
     */
    public InputLayer getInputLayer() {
        return input;
    }

    /**
     * @return the layers
     */
    public Layer[] getLayers() {
        return layers;
    }

    /**
     * @return the output
     */
    public OutputLayer getOutputLayer() {
        return output;
    }

}
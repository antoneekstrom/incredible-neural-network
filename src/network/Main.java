package network;

import static network.Layers.*;

import math.Matrix;

public class Main {

    public static void main(String[] args) {
        
        // For constructing the neural network
        NetworkBuilder builder = new NetworkBuilder();

        // Set the size of the input layer
        builder.setInput(new InputLayer(3));

        // The amount / size of hidden(?) layers
        builder.setLayers(createLayers(3));

        // The output layer
        builder.setOutput(new OutputLayer(1));

        // Build the network
        Network network = builder.create();

        // Connect all the layers with random weights
        network.connectLayers();
        
        network.getInputLayer().set(Math.random(), 0);

        Matrix result = network.feedForward();
        System.out.println(result.toString());
    }
    
}
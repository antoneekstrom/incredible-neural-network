package network;

import static network.Layers.*;

public class Main {

    public static void main(String[] args) {
        
        // For constructing the neural network
        NetworkBuilder builder = new NetworkBuilder();

        // Set the size of the input layer
        builder.setInput(new InputLayer(3));

        // The amount / size of hidden(?) layers
        builder.setLayers(createLayers(4));

        // The output layer
        builder.setOutput(new OutputLayer(1));

        // Build the network
        Network network = builder.create();

        // Connect all the layers with random weights
        network.connectLayers();
    }
    
}
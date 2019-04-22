package network;

import static network.Layers.*;

public class Main {

    public static void main(String[] args) {
        
        // For constructing the neural network
        NetworkBuilder builder = new NetworkBuilder();

        // Set the size of the input layer
        builder.setInput(new InputLayer(3));

        // The amount / size of hidden(?) layers
        // create 1 layer of length 3
        // also randomize the layer
        builder.setLayers(createLayers(3));

        // The output layer
        builder.setOutput(new OutputLayer(1));

        // specify the desired output
        builder.setCorrectOutput(new double[]{1});

        // Build the network
        NeuralNetwork network = builder.build();

        // Connect all the layers with random weights
        network.connectLayers();
        
        network.getInputLayer().randomize(1);

        network.printLayers();

        network.setTrainingSet(new network.TrainingSet(new InputMap[] {
            map(new double[] {0, 0, 1}, new double[] {0}),
            map(new double[] {0, 1, 1}, new double[] {1}),
            map(new double[] {1, 0, 1}, new double[] {1}),
            map(new double[] {1, 1, 1}, new double[] {0})
        }));

        // train the network
        network.train(1000);

        network.printLayers();
    }

    public static InputMap map(double[] input, double[] output) {
        return new InputMap(input, output);
    }



    // System.out.println("weights 1:");
    // System.out.println(network.getInputLayer().getOutputWeights().toString());

    // System.out.println("weights 2:");
    // System.out.println(network.getInputLayer().getOutputWeights().getL2().getOutputWeights().toString());
    
}
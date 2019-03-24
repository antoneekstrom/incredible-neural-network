package network;

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
    }

    /**
     * 
     */
    public void backPropagate() {
    }
    
    /**
     * 
     */
    public void feedForward() {
    }



    /**
     * @return the input
     */
    public InputLayer getInput() {
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
    public OutputLayer getOutput() {
        return output;
    }

}
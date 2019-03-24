package network;

/**
 * Builds networks.
 * 
 * @see Network
 */
public class NetworkBuilder {

    private InputLayer input;
    private OutputLayer output;
    private Layer[] layers;

    public Network create() {
        Network n = new Network(input, output, layers);
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

    
    
}
package network;

/**
 * InputLayer
 */
public class InputLayer extends Layer {

    private static final long serialVersionUID = 8116775535565757109L;

    /**
     * @param nodes the amount of nodes in the layer
     */
    public InputLayer(int nodes) {
        super(nodes, "InputLayer");
    }
}
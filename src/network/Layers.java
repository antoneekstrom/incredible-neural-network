package network;

/**
 * Convenience methods.
 */
public class Layers {

    /**
     * A layer of the specified size will be created for each argument.
     * Layers will be randomized.
     * 
     * @param layerSizes the sizes of the layers to create
     * 
     * @return the layers
     */
    public static Layer[] createLayers(int... layerSizes) {
        return createLayers(true, layerSizes);
    }

    /**
     * A layer of the specified size will be created for each argument.
     * 
     * @param randomize if the values of the layers should be randomized
     * @param layerSizes the sizes of the layers to create
     * 
     * @return the layers
     */
    public static Layer[] createLayers(boolean randomize, int... layerSizes) {
        Layer[] layers = new Layer[layerSizes.length];

        for (int i = 0; i < layerSizes.length; i++) {
            layers[i] = new Layer(layerSizes[i], "Layer " + (i + 1));
            if (randomize) layers[i].randomize(1);
        }

        return layers;
    }
    
}
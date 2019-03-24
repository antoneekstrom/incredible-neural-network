package network;

/**
 * Convenience methods.
 */
public class Layers {

    /**
     * @param layerSizes
     * 
     * @return
     */
    public static Layer[] createLayers(int... layerSizes) {
        Layer[] layers = new Layer[layerSizes.length];

        for (int i = 0; i < layerSizes.length; i++) {
            layers[i] = new Layer(layerSizes[i]);
        }

        return layers;
    }
    
}
package network.functions;

/**
 * ActivationFunction TODO
 */
@FunctionalInterface
public interface ActivationFunction {

    /**
     * @param x
     * @return
     */
    public double apply(double x);
    
}
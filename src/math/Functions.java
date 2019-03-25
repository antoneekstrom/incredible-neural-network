package math;

import static java.lang.Math.*;

/**
 * Math functions.
 */
public class Functions {

    /**
     * A sigmoid function. Also called logistic or soft-step, apparently.
     * 
     * @param x the input
     * @return y the output
     */
    public static double sigmoid(double x) {
        return 1 / (1 + pow(E, -x));
    }

    public static double sigmoid(double[] values) {
        return null;
    }
    
}
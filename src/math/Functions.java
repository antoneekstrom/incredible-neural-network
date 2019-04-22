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

    /**
     * A sigmoid function. Also called logistic or soft-step, apparently.
     * 
     * @param m the matrix
     * @return y the output
     */
    public static Matrix sigmoid(Matrix m) {
        return m.sigmoid();
    }

    /**
     * The derivate of a sigmoid function.
     * 
     * @param x the input
     * @return y the output
     */
    public static double sigmoidDerivate(double x) {
        return x * (1 - x);
    }

    /**
     * The derivate of a sigmoid function.
     * 
     * @param m the matrix
     * @return y the output
     */
    public static Matrix sigmoidDerivate(Matrix m) {
        return m.setAll(Functions::sigmoidDerivate);
    }

    /**
     * Square a value.
     * 
     * @param value the value
     * @return the square of the value
     */
    public static double square(double value) {
        return value * value;
    }
    
}
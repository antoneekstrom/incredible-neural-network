package math;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A two dimensional matrix consisting of doubles.
 */
public class Matrix2d {

    private int m, n;

    private double[][] matrix;

    /**
     * @param m the size of the horizontal axis (x)
     * @param n the size of the vertical axis (y)
     */
    public Matrix2d(int m, int n) {
        setMatrix(new double[m][n]);
    }

    /**
     * @param matrix the matrix to use
     */
    public Matrix2d(double[][] matrix) {
        setMatrix(matrix);
    }

    /**
     * Crucially radical.
     */
    private void evaluate() {
        m = matrix.length;
        n = matrix[0].length;
    }


    /**
     * @param d
     * @return this matrix
     */
    public Matrix2d power(double d) {
        return setAll(v -> Math.pow(v, d));
    }

    /**
     * @param d
     * @return this matrix
     */
    public Matrix2d add(double d) {
        return setAll(v -> v + d);
    }

    /**
     * @param d
     * @return this matrix
     */
    public Matrix2d multiply(double d) {
        return setAll(v -> v * d);
    }

    /**
     * @param d
     * @return this matrix
     */
    public Matrix2d divide(double d) {
        return setAll(v -> v / d);
    }

    /**
     * @param values
     * @return this matrix
     */
    public Matrix2d sigmoid(double[][] values) {
        return setAll(Functions::sigmoid);
    }


    /**
     * Get a value in the matrix.
     * 
     * @param m the position on the horizontal axis (x)
     * @param n the position on the vertical axis (y)
     * 
     * @return the value
     */
    public double get(int m, int n) {
        return matrix[m][n];
    }

    /**
     * Set the value of a position in the matrix.
     * 
     * @param m the position on the horizontal axis (x)
     * @param n the position on the vertical axis (y)
     * @param value the new value to set
     * 
     * @return this matrix
     */
    public Matrix2d set(int m, int n, double value) {
        matrix[m][n] = value;

        return this;
    }

    /**
     * Use {@link #set(int, int, double)} on every value of the matrix.
     * 
     * @param func The function to set every position to. The function takes in the value of the position.
     * 
     * @return this matrix
     */
    public Matrix2d setAll(Function<Double, Double> func) {
        iterate((m, n) -> set(m, n, func.apply(get(m, n))));

        return this;
    }

    /**
     * Use {@link #set(int, int, double)} on every value of the matrix.
     * 
     * @param func The function to set every position to. The function takes in the m position and the n position.
     * 
     * @return this matrix
     */
    public Matrix2d setAll(BiFunction<Integer, Integer, Double> func) {
        iterate((m, n) -> set(m, n, func.apply(m, n)));

        return this;
    }

    /**
     * @param consumer the function to apply to all members of the matrix
     * 
     * @return this matrix
     */
    public Matrix2d iterate(BiConsumer<Integer, Integer> consumer) {
        MatrixHelper.iterate(this.matrix, consumer);

        return this;
    }

    /**
     * Set the matrix.
     * @param matrix the two-dimensional array of doubles to use
     * 
     * @return this matrix
     */
    public Matrix2d setMatrix(double[][] matrix) {
        this.matrix = matrix;
        evaluate();

        return this;
    }

    /**
     * @return the horizontal length/size
     */
    public int m() {
        return m;
    }

    /**
     * @return the vertical length/size
     */
    public int n() {
        return n;
    }
    
}
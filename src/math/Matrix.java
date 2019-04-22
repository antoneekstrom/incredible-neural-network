package math;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A matrix consisting of doubles.
 * <p>
 * <ul>
 *    <li> <b>m</b> is the length of the horizontal axis, the amount of <b>rows</b></li>
 *    <li> <b>n</b> is the length of the vertical axis, the amount of <b>columns</b></li>
 * </ul>
 * 
 * Very Emotional.
 */
public class Matrix {

    private int m, n;

    private double[][] matrix;

    /**
     * Create an identity matrix with the same dimensions as another matrix.
     * @param m the matrix
     * @return a new matrix set to identity
     */
    public static Matrix identity(Matrix m) {
        return new Matrix(m.m(), m.n());
    }

    /**
     * @param m the length of the horizontal axis (rows)
     * @param n the length of the vertical axis (columns)
     */
    public Matrix(int m, int n) {
        setMatrix(new double[m][n]);
        identity();
    }

    /**
     * @param matrix the matrix to use
     */
    public Matrix(double[][] matrix) {
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
     * This modifies the current matrix.
     * 
     * @param d a scalar
     * @return this matrix
     */
    public Matrix power(double d) {
        return setAll(v -> Math.pow(v, d));
    }

    /**
     * This modifies the current matrix
     * 
     * @param d a scalar
     * @return this matrix
     */
    public Matrix add(double d) {
        return setAll(v -> v + d);
    }

    /**
     * This modifies the current matrix
     * 
     * @param matrix another matrix
     * @return this matrix
     */
    public Matrix add(Matrix matrix) {
        return setAll((m, n) -> {
            return get(m,n) + matrix.get(m, n);
        });
    }

    /**
     * This modifies the current matrix
     * 
     * @param matrix another matrix
     * @return this matrix
     */
    public Matrix subtract(Matrix matrix) {
        return setAll((m, n) -> {
            return get(m,n) - matrix.get(m, n);
        });
    }

    /**
     * This modifies the current matrix
     * 
     * @param d a scalar
     * @return this matrix
     */
    public Matrix divide(double d) {
        return setAll(v -> v / d);
    }

    /**
     * This modifies the current matrix
     * 
     * @param d a scalar
     * @return this matrix
     */
    public Matrix multiply(double d) {
        return setAll(v -> v * d);
    }

    /**
     * Multiply this matrix with another.
     * <p>This creates a new matrix.
     * 
     * @param matrix the other matrix
     * @return a new matrix that is the product of A x B
     */
    public Matrix multiply(Matrix b) {
        return new Matrix(MatrixMath.multiply(this.matrix, b.matrix));
    }

    /**
     * This creates a new matrix.
     * 
     * @param b another matrix
     * @return this matrix
     */
    public Matrix dot(Matrix b) {
        return new Matrix(MatrixMath.dot(this.matrix, b.matrix));
    }

    /**
     * Create a new matrix which is the transpose of this matrix.
     * @return the transpose
     */
    public Matrix transpose() {
        return new Matrix(MatrixMath.transpose(this.matrix));
    }

    /**
     * Do the simgoid.
     * 
     * @return this matrix
     */
    public Matrix sigmoid() {
        return setAll(Functions::sigmoid);
    }

    /**
     * Random all the values in the matrix. The random value will be range from 0-1 and be multiplied by the argument.
     * @param mul the value to multiply by
     * @return this matrix
     */
    public Matrix randomize(double mul) {
        return this.setAll(v -> Math.random() * mul);
    }

    /**
     * @return a copy of this array
     */
    public Matrix copy() {
        return new Matrix(Arrays.stream(this.matrix).map(double[]::clone).toArray(double[][]::new));
    }

    /**
     * Set this matrix to the identity.
     * 
     * @return this matrix
     */
    public Matrix identity() {

        for (int i = 0; i < m(); i++) {
            double[] row = new double[n()];
            
            for (int k = 0; k < n(); k++) {
                row[k] = k == i ? 1 : 0;
            }

            setRow(i, row);
        }

        return this;
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
     * Get a row.
     * 
     * @param m the index of the row
     * @return the row
     */
    public double[] getRow(int m) {
        return MatrixMath.getRow(this.matrix, m);
    }

    /**
     * Get a column.
     * 
     * @param n the index of the column
     * @return the column
     */
    public double[] getColumn(int n) {
        return MatrixMath.getColumn(this.matrix, n);
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
    public Matrix set(int m, int n, double value) {
        matrix[m][n] = value;

        return this;
    }

    /**
     * 
     * @param m
     * @param row
     * @return
     */
    public Matrix setRow(int m, double[] row) {
        this.matrix[m] = row;
        return this;
    }

    /**
     * Set a column.
     * 
     * @param n the index of the column
     * @param column the column
     * @return
     */
    public Matrix setColumn(int n, double[] column) {
        if (column.length != m()) throw new RuntimeException("Illegal column length.");

        for (int i = 0; i < this.matrix.length; i++) {
            this.matrix[i][n] = column[i];
        }

        return this;
    }

    /**
     * Use {@link #set(int, int, double)} on every value of the matrix.
     * 
     * @param func (old value) -> new value
     * 
     * @return this matrix
     */
    public Matrix setAll(Function<Double, Double> func) {
        iterate((m, n) -> set(m, n, func.apply(get(m, n))));

        return this;
    }

    /**
     * Use {@link #set(int, int, double)} on every value of the matrix.
     * 
     * @param func (m, n) -> new value
     * 
     * @return this matrix
     */
    public Matrix setAll(BiFunction<Integer, Integer, Double> func) {
        iterate((m, n) -> set(m, n, func.apply(m, n)));

        return this;
    }

    /**
     * @param func the function (m, n) -> void
     * 
     * @return this matrix
     */
    public Matrix iterate(BiConsumer<Integer, Integer> func) {
        MatrixMath.iterate(this.matrix, func);

        return this;
    }

    /**
     * Set the matrix.
     * @param matrix the two-dimensional array of doubles to use
     * 
     * @return this matrix
     */
    public Matrix setMatrix(double[][] matrix) {
        this.matrix = matrix;
        evaluate();

        return this;
    }

    /**
     * Format a string representing the matrix.
     * @return the string
     */
    @Override
    public String toString() {
        return MatrixMath.toString(this.matrix);
    }

    /**
     * Format the matrix to a readable form and print it to {@code System.out}.
     */
    public void print() {
        System.out.printf("---%s---%n%s%n", dimensions(), toString());
        System.out.println();
    }

    /**
     * Format the dimensions as a string.
     * @return the string
     */
    public String dimensions() {
        return String.format("%sx%s", m(), n());
    }

    /**
     * @return the matrix
     */
    public double[][] matrix() {
        return matrix;
    }

    /**
     * @return the amount of rows (horizontal axis)
     */
    public int m() {
        return m;
    }

    /**
     * @return the amount of columns (vertical axis)
     */
    public int n() {
        return n;
    }
    
}
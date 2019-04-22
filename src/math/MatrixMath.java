package math;

import java.util.function.BiConsumer;

/**
 * Helper methods.
 */
public class MatrixMath {

    public static void main(String[] args) {

        // matrix multiplication test

        Matrix a = new Matrix(new double[][] {
            {1, 2, 3},
            {4, 5, 6}
        });
        System.out.println("a:\n" + a.toString());

        Matrix b = new Matrix(new double[][] {
            {7, 8},
            {9, 10},
            {11, 12},
        });
        System.out.println("b:\n" + b.toString());

        Matrix z = a.multiply(b);
        System.out.println("result:\n" + z.toString());

        z =  new Matrix(8, 8);

        System.out.println("8x8 identity:\n" + z.toString());
    }

    /**
     * @param a
     * @return
     */
    public static int m(double[][] a) {
        return a.length;
    }

    /**
     * @param a
     * @return
     */
    public static int n(double[][] a) {
        return a[0].length;
    }

    /**
     * Multiply two matrices element-wise. This returns a matrix the same size as the two factors.
     * 
     * @param matrix the other matrix
     * @return a new matrix that is the product of {@code a * b}
     */
    public static double[][] multiply(double[][] a, double[][] b) {

        int m1 = m(a);
        int n1 = n(a);
        int m2 = m(b);
        int n2 = n(b);

        if (m1 != m2 || n1 != n2) throw new RuntimeException("Invalid matrix dimensions.");

        double[][] z = new double[m1][n1];

        for (int m = 0; m < m1; m++) {
            for (int n = 0; n < n1; n++) {
                z[m][n] = a[m][n] * b[m][n];
            }
        }

        return z;
    }

    /**
     * Calculate the dot product of two matrices.
     * 
     * @param a matrix a
     * @param b matrix b
     * @return the new matrix
     */
    public static double[][] dot(double[][] a, double[][] b) {

        int m1 = m(a);
        int n1 = n(a);
        int n2 = n(b);
        int m2 = m(b);

        if (n1 != m2) throw new RuntimeException("Invalid matrix dimensions.");

        // I copied this bit (sorry)

        double[][] z = new double[m1][n2];

        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n2; j++) {
                for (int k = 0; k < n1; k++) {
                    z[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return z;
    }

    /**
     * Iterate over matrix.
     * 
     * @param matrix the matrix
     * @param consumer the function (m, n) -> void
     * 
     */
    public static <T> void iterate(T[][] matrix, BiConsumer<Integer, Integer> consumer) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                consumer.accept(x, y);
            }
        }
    }

    /**
     * Iterate over matrix.
     * 
     * @param matrix the matrix
     * @param consumer the function (m, n) -> void
     * 
     */
    public static void iterate(double[][] matrix, BiConsumer<Integer, Integer> consumer) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                consumer.accept(x, y);
            }
        }
    }

    /**
     * Get a value from a matrix.
     * 
     * @param matrix the matrix
     * @param x the x position
     * @param y the y position
     * 
     * @return the value
     */
    public static <T> T getFrom(T[][] matrix, int x, int y) {
        return matrix[x][y];
    }

    /**
     * 
     * @param matrix
     * @param m
     * @return
     */
    public static double[] getRow(double[][] matrix, int m) {
        return matrix[m];
    }

    /**
     * 
     * @param matrix
     * @param n
     * @return
     */
    public static double[] getColumn(double[][] matrix, int n) {
        double[] column = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][n];
        }

        return column;
    }

    /**
     * Calculate the transpose of a matrix.
     * 
     * @param matrix the matrix
     * @return the tranpose
     */
    public static double[][] transpose(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] z = new double[n][m];

        // flip the matrix by switching x and y
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                z[j][i] = a[i][j];
            }
        }

        return z;
    }

    /**
     * Format a string representing the matrix.
     * 
     * @param matrix the matrix
     * @return the string
     */
    public static String toString(double[][] matrix) {
        StringBuilder b = new StringBuilder();

        for (int m = 0; m < matrix.length; m++) {

            b.append("[");

            for (int n = 0; n < matrix[m].length; n++) {

                // this if-clause is very un-phenomenal
                if (n != matrix[m].length - 1) {
                    b.append(String.format("%.2f, ", matrix[m][n]));
                }
                else {
                    b.append(String.format("%.2f", matrix[m][n]));
                }
            }

            b.append(String.format("]" + (m < matrix.length - 1 ? "\n" : "")));
        }

        return b.toString().replace(",", ".");
    }
    
}
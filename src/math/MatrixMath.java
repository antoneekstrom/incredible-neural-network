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
     * Multiply this matrix with another.
     * 
     * @param matrix the other matrix
     * @return a new matrix that is the product of A x B
     */
    public static double[][] multiply(double[][] a, double[][] b) {

        if (!validateMatrices(a, b)) throw new RuntimeException("Invalid matrix dimensions.");

        double[][] z = new double[a.length][b[0].length];

        for (int m = 0; m < z.length; m++) {
            for (int n = 0; n < z[0].length; n++) {
                z[m][n] = VectorHelper.dot(a[m], getColumn(b, n));
            }
        }

        return z;
    }

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static double[][] dot(double[][] a, double[][] b) {

        if (!validateMatrices(a, b)) throw new RuntimeException("Invalid matrix dimensions.");


        // I copied this bit (sorry)

        int m1 = a.length;
        int n1 = a[0].length;
        int n2 = b[0].length;

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
     * @param consumer the function
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
     * @param consumer the function
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
     * Check if two matrices are able to be multiplied.
     * 
     * @param a matrix a
     * @param b matrix b
     * @return if matrix dimensions are legal
     */
    public static boolean validateMatrices(double[][] a, double[][] b) {
        return a.length == b[0].length;
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

            b.append(String.format("]%n"));
        }

        return b.toString().replace(",", ".");
    }
    
}
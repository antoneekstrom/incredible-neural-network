package util;

import java.util.function.BiConsumer;

/**
 * Helper methods.
 */
public class MatrixHelper {

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

    
}
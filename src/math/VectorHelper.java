package math;

/**
 * Helper methods.
 */
public class VectorHelper {

    /**
     * Calculate the dot product from two vectors.
     * @param a vector a
     * @param b vector b
     * @return the dot product of a x b
     */
    public static double dot(double[] a, double[] b) {

        if (a.length != b.length) throw new RuntimeException("Invalid vector dimensions.");

        double y = 0;

        for (int i = 0; i < a.length; i++) {
            y += a[i] * b[i];
        }

        return y;
    }
    
}
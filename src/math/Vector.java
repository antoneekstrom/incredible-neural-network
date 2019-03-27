package math;

/**
 * A {@link Matrix} with one row.
 */
public class Vector extends Matrix {

    /**
     * @param length the length of the vector
     */
    public Vector(int length) {
        super(length, 1);
    }

    /**
     * @return this vector as an array
     */
    public double[] array() {
        return getColumn(0);
    }

    /**
     * Multiply this vector by another.
     * 
     * @param v the other vector
     * @return this vector
     */
    public double dot(Vector v) {
        return VectorHelper.dot(array(), v.array());
    }
    
}
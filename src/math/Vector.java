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

    public Vector(double[] v) {
        super(v.length, 1);

        for (int i = 0; i < v.length; i++) {
            setRow(i, new double[] {v[i]});
        }
    }

    /**
     * @return this vector as an array
     */
    public double[] array() {
        return getColumn(0);
    }

    /**
     * Rotate the vector, switch the columns and rows.
     * @return a new rotated vector
     */
    public Vector rotate() {
        double[] v = new double[n()];

        for (int i = 0; i < n(); i++) {
            v[i] = getRow(i)[0];
        }

        return new Vector(v);
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
package math;

/**
 * A {@link Matrix} with one row.
 */
public class Vector extends Matrix {

    /**
     * @param length the length of the vector (amount of columns in the matrix)
     */
    public Vector(int length) {
        super(length, 1);
    }

    /**
     * @param v
     */
    public Vector(double[] v) {
        super(v.length, 1);
        set(v);
    }

    /**
     * @param m
     */
    public Vector(Matrix m) {
        super(m.m(), m.n());
        if (m.n() != 1) throw new RuntimeException("Illegal vector dimensions.");
    }

    /**
     * Set the values of the vector.
     * 
     * @param values the values
     */
    public void set(double[] values) {
        if (values.length != m()) return;

        for (int i = 0; i < values.length; i++) {
            set(i, 0, values[i]);
        }
    }

    /**
     * @return
     */
    public double[] getValues() {
        double[] values = new double[m()];

        for (int i = 0; i < m(); i++) {
            values[i] = get(i);
        }

        return values;
    }

    /**
     * @param m
     * @return
     */
    public double get(int m) {
        return get(m, 0);
    }

    /**
     * @return this vector as an array
     */
    public double[] array() {
        return getColumn(0);
    }

    @Override
    public Vector copy() {
        return new Vector(getColumn(0).clone());
    }

    /**
     * Set a value in the vector.
     * 
     * @param m the index of the value (row)
     * @param val the value
     */
    public void set(int m, double val) {
        set(m, 0, val);
    }

    /**
     * @return the length of the vector (amount of columns in the matrix)
     */
    public int length() {
        return m();
    }

    /**
     * Multiply this vector by another.
     * Does not modify the vectors.
     * 
     * @param v the other vector
     * @return this vector
     */
    public double dot(Vector v) {
        return VectorHelper.dot(array(), v.array());
    }
    
}
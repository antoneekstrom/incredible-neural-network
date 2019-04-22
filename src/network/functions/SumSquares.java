package network.functions;

import math.Functions;

/**
 * A loss function that uses the sum of the squares of the difference of the values. hell yeah
 */
public class SumSquares implements LossFunction {

    @Override
    public double apply(double[] actualValues, double[] predictedValues) {

        double loss = 0;

        for (int i = 0; i < actualValues.length; i++) {
            loss += Functions.square(actualValues[i] - predictedValues[i]);
        }

        return loss;
    }
    
}
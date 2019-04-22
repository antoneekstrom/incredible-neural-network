package network.functions;

/**
 * @see SumSquares
 */
public class SumSquaresDerivate implements LossFunction {

    @Override
    public double apply(double[] actualValues, double[] predictedValues) {
        
        double loss = 0;

        for (int i = 0; i < actualValues.length; i++) {
            loss += 2 * (actualValues[i] - predictedValues[i]);
        }

        return loss;
    }

    
}
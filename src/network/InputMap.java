package network;

/**
 * Maps a certain input with the correct output.
 */
public class InputMap {

    double[] input;
    double[] correctOutput;

    /**
     * @param input
     * @param correctOutput
     */
    public InputMap(double[] input, double[] correctOutput) {
        this.input = input;
        this.correctOutput = correctOutput;
    }

    /**
     * @return the correctInput
     */
    public double[] getCorrectOutput() {
        return correctOutput;
    }

    /**
     * @return the input
     */
    public double[] getInput() {
        return input;
    }
    
}
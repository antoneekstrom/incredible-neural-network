package network;

/**
 * TrainingSet
 */
public class TrainingSet {

    /**
     * 
     */
    InputMap[] inputs;

    /**
     * @param inputs
     */
    public TrainingSet(InputMap[] inputs) {
        this.inputs = inputs;
    }

    /**
     * @param index
     * @return
     */
    public InputMap get(int index) {
        return inputs[index];
    }

    /**
     * @return the inputs
     */
    public InputMap[] getInputs() {
        return inputs;
    }

    /**
     * @return
     */
    public int size() {
        return inputs.length;
    }
    
}
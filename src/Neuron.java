public class Neuron {
    int index;
    double bias = 0.5;
    double learningRate = 1;

    public Neuron(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    // Sum all the inputs:weights and bias
    private double summation(Input[] inputs) {
        double summation = 0;

        for (int i=0; i<inputs.length; i++) {
            summation += (inputs[i].getInputValue() * inputs[i].getWeight(index));
        }

        summation += bias;

        return summation;
    }

    private int thresholdFunction(double summation) {
        if (summation > 0)
            return 1;
        return 0;
    }

    public double getError(Input[] inputs, double expectedOutput) {
        return expectedOutput - thresholdFunction(summation(inputs));
    }

    public double[] calculateNewWeights(Input[] inputs, double expectedOutput, int numNeurons) {
        double[] newWeights = new double[numNeurons*inputs.length];

        // Loop through the inputs to calculate their new weights
        for (int i=0; i<inputs.length; i++) {
            // Get the value and current weight of each
            double inputValue = inputs[i].getInputValue();
            double weight = inputs[i].getWeight(index);
            double error = getError(inputs, expectedOutput);

            // Calculate new weight
            double newWeight = weight + (learningRate * error * inputValue);
            // Add weight to array to return
            newWeights[i] = newWeight;
        }

        // Calculate the new weight for the neuron bias
        this.bias = this.bias + (learningRate * getError(inputs, expectedOutput));

        return newWeights;
    }

    public double checkInput(Input[] inputs, double a, double b) {
        inputs[0].setInputValue(a);
        inputs[1].setInputValue(b);

        return thresholdFunction(summation(inputs));
    }
}

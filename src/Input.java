public class Input {
    double inputValue;
    double[] weights;

    public Input(int numWeights) {
        double[] weights = new double[numWeights];
        for (int i=0; i<numWeights; i++)
            weights[i] = 0.5;

        this.weights = weights;
    }

    public double getInputValue() {
        return this.inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public double[] getWeights() {
        return this.weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double getWeight(int index) {
        return this.weights[index];
    }

    public void setWeight(int index, double weight) {
        this.weights[index] = weight;
    }
}

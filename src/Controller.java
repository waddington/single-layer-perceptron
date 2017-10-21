public class Controller {
    int[][][] trainingData = {{{0,0},{1}},{{0,1},{0}},{{1,0},{0}},{{1,1},{0}}};
    int numberOfNeurons = 1; // This is a single-layer-perceptron
    Neuron[] neurons = new Neuron[numberOfNeurons];
    Input[] inputs = new Input[trainingData[0].length];

    private void init() {
        System.out.println("Kai's single layer perceptron.");

        createInputsAndNeurons();

        startTraining();

        test(0,0);
    }

    private void createInputsAndNeurons() {
        // Create neurons
        for (int i=0; i<this.numberOfNeurons; i++)
            neurons[i] = new Neuron(i);

        // Create inputs
        for (int i=0; i<trainingData[0].length; i++)
            inputs[i] = new Input(this.numberOfNeurons);
    }

    private void startTraining() {
        boolean errorFlag = true;
        System.out.println("Starting training...");

        while (errorFlag) {
            // set the flag to false
            errorFlag = false;

            // Loop through all training data
            for (int i=0; i<trainingData.length; i++) {
                // Set the value of the inputs
                for (int j=0; j<inputs.length; j++)
                    inputs[j].setInputValue(trainingData[i][0][j]);

                // For each neuron get the error then decide what to do
                for (int j=0; j<neurons.length; j++) {
                    // get the error for the neuron
                    double neuronError = neurons[j].getError(inputs, trainingData[i][1][0]);

                    // If there is an error set the flag to true and update weights
                    if (neuronError != 0) {
                        errorFlag = true;
                        double[] newWeights = neurons[j].calculateNewWeights(inputs, trainingData[i][1][0], numberOfNeurons);
                        // Apply the weights to each input
                        for (int k=0; k<inputs.length; k++)
                            inputs[k].setWeight(neurons[j].getIndex(), newWeights[k]);
                    }
                }
            }
        }

        System.out.println("Training complete.");
    }

    private void test(int a, int b) {
        System.out.println();
        System.out.println("Testing:");
        System.out.println("Inputs: "+a+", "+b);
        System.out.println("Output: "+(int)neurons[0].checkInput(inputs, a,b));
    }

    public static void main(String[] args) {
        Controller c = new Controller();
        c.init();
    }
}

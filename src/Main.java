import java.util.Scanner;

public class Main {

    private int[] X1;
    private int[] X2;
    private int[] Y;
    private double W0;
    private double W1;
    private double W2;
    private double n;

    private void init() {
        // Set initial values
        // Training data
        this.X1 = new int[] {0,0,1,1};
        this.X2 = new int[] {0,1,0,1};
        this.Y = new int[] {1,0,0,0};
        // Initial weights
        this.W0 = 0.5;
        this.W1 = 0.5;
        this.W2 = 0.5;
        // Learning parameter
        this.n = 0.1;

        train();
        check();
    }

    private void train() {
        System.out.println("Training...");

        boolean weightChanged = true;

        while (weightChanged) {
            weightChanged = false;
            for (int i=0; i<4; i++) {
                // Calculate output after threshold function
                double o = threshold(W0 + (X1[i] * W1) + (X2[i] * W2));

                // Check if output is the same as expected output
                if (o != Y[i]) {
                    // Update the weights
                    W1 = W1 + (n * (Y[i] - o) * X1[i]);
                    W2 = W2 + (n * (Y[i] - o) * X2[i]);
                    W0 = W0 + (n * (Y[i] - o) * n);

                    weightChanged = true;
                }
            }
        }

        System.out.println("Finished Training.");
    }

    private int threshold(double s) {
        // If s > 0 then 1 else 0
        if (s > 0)
            return 1;

        return 0;
    }

    private void check() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lets check...");
        System.out.print("Enter a value for X1: ");
        int x1 = scanner.nextInt();
        System.out.print("Enter a value for X2: ");
        int x2 = scanner.nextInt();

        scanner.close();

        int y = threshold(W0 + (W1 * x1) + (W2 * x2));

        System.out.print("y = "+y);
    }

    public static void main(String[] args) {
	    Main main = new Main();
        main.init();
    }
}

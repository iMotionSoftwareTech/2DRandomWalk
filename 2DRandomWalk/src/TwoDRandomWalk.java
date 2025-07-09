//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TwoDRandomWalk {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java RandomWalker <n> [numTrials]");
            System.out.println("<n>: Defines the boundary size (2n x 2n square).");
            System.out.println("The walker hits the boundary when |x| == n or |y| == n.");
            System.out.println("[numTrials]: Optional. The number of simulations to run for averaging. Default is 10000.");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int numTrials = 10000; // Default number of trials

        if (args.length == 2) {
            numTrials = Integer.parseInt(args[1]);
        }

        // Input validation for n
        if (n < 0) {
            System.out.println("Error: n must be a non-negative integer.");
            return;
        }

        // If n is 0, the walker starts on the boundary, so 0 steps are needed.
        if (n == 0) {
            System.out.println("For n = 0, the walker starts on the boundary. Average steps: 0.0");
            return;
        }

        // Input validation for numTrials
        if (numTrials <= 0) {
            System.out.println("Error: Number of trials must be a positive integer.");
            return;
        }

        // Variable to accumulate the total steps across all trials
        long totalSteps = 0;

        // 2. Run multiple simulations (trials)
        System.out.println("Simulating 2D random walk for a " + (2 * n) + "x" + (2 * n) + " square (n=" + n + ") over " + numTrials + " trials...");

        for (int t = 0; t < numTrials; t++) {
            int x = 0; // Current x-coordinate of the walker, starts at center
            int y = 0; // Current y-coordinate of the walker, starts at center
            int steps = 0; // Steps taken in the current walk

            // Simulate a single random walk until the boundary is hit
            while (Math.abs(x) < n && Math.abs(y) < n) {
                // Generate a random number to decide direction (0: North, 1: South, 2: East, 3: West)
                int direction = (int) (Math.random() * 4); // Generates 0, 1, 2, or 3

                switch (direction) {
                    case 0: // North
                        y++;
                        break;
                    case 1: // South
                        y--;
                        break;
                    case 2: // East
                        x++;
                        break;
                    case 3: // West
                        x--;
                        break;
                }
                steps++; // Increment step count for the current walk
            }
            totalSteps += steps; // Add steps from this trial to the total
        }

        // 3. Calculate and print the average steps
        double averageSteps = (double) totalSteps / numTrials;
        System.out.println("Estimated average steps to hit boundary for n=" + n + ": " + averageSteps);
    }
}
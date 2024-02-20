import java.util.Arrays;

public class EngineBuildingTimeCalculator {

  public static int minTimeToBuildEngines(int[] engines, int splitCost) {
    // Sort the array of engines in ascending order
    Arrays.sort(engines);

    // Initialize the total time to 0
    int totalTime = 0;

    // Continue until there is only one engine left
    while (engines.length > 1) {
      // Take the two engines that require the least time to build
      int time1 = engines[0];
      int time2 = engines[1];

      // Calculate the total time required to build these two engines and split one
      // engineer
      int combinedTime = time1 + time2 + splitCost;
      // Add the total time to the total time count
      totalTime += combinedTime;

      // Create a new array to store the remaining engines
      int[] newEngines = new int[engines.length - 1];
      // Copy the remaining engines to the new array
      for (int i = 2; i < engines.length; i++) {
        newEngines[i - 2] = engines[i];
      }
      // Set the new array of engines
      engines = newEngines;

      // Add the combined time to the new array of engines
      engines[0] = combinedTime;

      // Sort the array of engines again
      Arrays.sort(engines);
    }

    // The remaining time in the array of engines is the total time required to
    // build all engines
    totalTime += engines[0];

    return totalTime;
  }

  public static void main(String[] args) {
    int[] engines = { 3, 4, 5, 2 };
    int splitCost = 2;
    System.out.println("Minimum time to build all engines: " + minTimeToBuildEngines(engines, splitCost));
  }
}

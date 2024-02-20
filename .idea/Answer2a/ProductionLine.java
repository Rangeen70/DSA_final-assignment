public class DressProductionManager {

  public static int minMovesToEqualize(int[] machines) {
    int totalDresses = 0;
    int n = machines.length;

    // Calculate the total number of dresses
    for (int dresses : machines) {
      totalDresses += dresses;
    }

    // Calculate the target number of dresses for each sewing machine
    int target = totalDresses / n;

    // If the total number of dresses cannot be evenly distributed among the sewing
    // machines,
    // return -1 as it's not possible to equalize the number of dresses.
    if (totalDresses % n != 0) {
      return -1;
    }

    int moves = 0;
    int balance = 0;

    // Iterate through each sewing machine
    for (int dresses : machines) {
      // Calculate the imbalance of dresses in the current sewing machine
      int diff = dresses - target;
      // Accumulate the imbalance to calculate the total balance
      balance += diff;
      // Update the moves required based on the absolute value of the balance
      moves = Math.max(moves, Math.abs(balance));
    }

    return moves;
  }

  public static void main(String[] args) {
    int[] machines = { 2, 1, 3, 0, 2 };
    System.out.println("Minimum moves to equalize: " + minMovesToEqualize(machines));
  }
}

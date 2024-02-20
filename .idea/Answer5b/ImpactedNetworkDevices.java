import java.util.*;

public class NetworkDeviceImpacts {

  /**
   * Finds the list of devices impacted by a given target device in a network.
   *
   * @param network      The network represented as an array of connections
   *                     between devices.
   * @param targetDevice The target device for which the impacted devices need to
   *                     be found.
   * @return The list of devices impacted by the target device.
   */
  public static List<Integer> findImpactedDevices(int[][] network, int targetDevice) {
    // Build the network graph
    Map<Integer, Set<Integer>> networkMap = buildNetworkMap(network);

    // Set to store the impacted devices
    Set<Integer> impactedDevices = new HashSet<>();

    // Perform depth-first search to find impacted devices
    depthFirstSearch(networkMap, targetDevice, impactedDevices);

    // Remove the target device itself from the impacted devices list
    impactedDevices.remove(targetDevice);

    // Convert the impacted devices set to a list and return
    return new ArrayList<>(impactedDevices);
  }

  /**
   * Builds the network graph from the given network array.
   *
   * @param network The network represented as an array of connections between
   *                devices.
   * @return The network graph represented as a map of device IDs to their
   *         connected devices.
   */
  private static Map<Integer, Set<Integer>> buildNetworkMap(int[][] network) {
    Map<Integer, Set<Integer>> networkGraph = new HashMap<>();

    // Populate the network graph with connections
    for (int[] connection : network) {
      int device1 = connection[0];
      int device2 = connection[1];

      networkGraph.computeIfAbsent(device1, k -> new HashSet<>()).add(device2);
      networkGraph.computeIfAbsent(device2, k -> new HashSet<>()).add(device1);
    }

    return networkGraph;
  }

  /**
   * Performs depth-first search to find all devices impacted by the target
   * device.
   *
   * @param networkMap      The network graph representing connections between
   *                        devices.
   * @param currentDevice   The current device being explored during depth-first
   *                        search.
   * @param impactedDevices Set to store the impacted devices.
   */
  private static void depthFirstSearch(Map<Integer, Set<Integer>> networkMap, int currentDevice,
      Set<Integer> impactedDevices) {
    Stack<Integer> stack = new Stack<>();
    stack.push(currentDevice);

    while (!stack.isEmpty()) {
      int current = stack.pop();
      impactedDevices.add(current);

      // Get the neighbors of the current device from the network graph
      Set<Integer> neighbors = networkMap.getOrDefault(current, Collections.emptySet());

      // Explore each neighbor and add to stack if not already impacted
      for (int neighbor : neighbors) {
        if (!impactedDevices.contains(neighbor)) {
          stack.push(neighbor);
        }
      }
    }
  }

  public static void main(String[] args) {
    // Example network represented as an array of connections
    int[][] network = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 6 }, { 2, 4 }, { 4, 6 }, { 4, 5 }, { 5, 7 } };
    int targetDevice = 4;

    // Find impacted devices and print the result
    List<Integer> impactedDevices = findImpactedDevices(network, targetDevice);
    System.out.println("Impacted Device List: " + impactedDevices);
  }
}

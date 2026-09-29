
import java.util.*;

public class CampusGraph {

    private final Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new LinkedHashMap<>();
    }

    public boolean addLocation(String location) {

        if (location == null || location.trim().isEmpty()) {
            return false;
        }

        location = location.trim();

        if (adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.put(location, new ArrayList<>());

        return true;
    }

    public boolean removeLocation(String location) {

        if (location == null) {
            return false;
        }

        location = location.trim();

        if (!adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.remove(location);

        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }

        return true;
    }

    public boolean addConnection(
            String location1,
            String location2) {

        if (location1 == null || location2 == null) {
            return false;
        }

        location1 = location1.trim();
        location2 = location2.trim();

        if (location1.isEmpty() || location2.isEmpty()) {
            return false;
        }

        if (location1.equalsIgnoreCase(location2)) {
            return false;
        }

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {
            return false;
        }

        if (adjacencyList.get(location1).contains(location2)) {
            return false;
        }

        adjacencyList.get(location1).add(location2);
        adjacencyList.get(location2).add(location1);

        return true;
    }

    public boolean removeConnection(
            String location1,
            String location2) {

        if (location1 == null || location2 == null) {
            return false;
        }

        location1 = location1.trim();
        location2 = location2.trim();

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {
            return false;
        }

        boolean removed1 =
                adjacencyList.get(location1).remove(location2);

        boolean removed2 =
                adjacencyList.get(location2).remove(location1);

        return removed1 || removed2;
    }

    public boolean locationExists(String location) {

        if (location == null) {
            return false;
        }

        return adjacencyList.containsKey(location.trim());
    }

    public void displayGraph() {

        if (adjacencyList.isEmpty()) {

            System.out.println(
                    "No campus locations found."
            );

            return;
        }

        System.out.println(
                "\nCampus Network (Adjacency List):"
        );

        for (Map.Entry<String, List<String>> entry
                : adjacencyList.entrySet()) {

            System.out.print(
                    entry.getKey() + " -> "
            );

            List<String> neighbours = entry.getValue();

            if (neighbours.isEmpty()) {

                System.out.println("No connections");

            } else {

                for (int i = 0; i < neighbours.size(); i++) {

                    System.out.print(neighbours.get(i));

                    if (i < neighbours.size() - 1) {
                        System.out.print(", ");
                    }
                }

                System.out.println();
            }
        }
    }

    public void bfs(String start) {

        if (!locationExists(start)) {

            System.out.println(
                    "Starting location not found!"
            );

            return;
        }

        Queue<String> queue = new LinkedList<>();

        Set<String> visited = new LinkedHashSet<>();

        start = start.trim();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.print(current + " ");

            for (String neighbour : adjacencyList.get(current)) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        System.out.println();
    }

    public void dfs(String start) {

        if (!locationExists(start)) {

            System.out.println(
                    "Starting location not found!"
            );

            return;
        }

        Set<String> visited = new LinkedHashSet<>();

        dfsRecursive(start.trim(), visited);

        System.out.println();
    }

    private void dfsRecursive(
            String current,
            Set<String> visited) {

        visited.add(current);

        System.out.print(current + " ");

        for (String neighbour : adjacencyList.get(current)) {

            if (!visited.contains(neighbour)) {

                dfsRecursive(neighbour, visited);
            }
        }
    }
}
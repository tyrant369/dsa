
class Pair {
    int node;
    int distance;

    public Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class PrimsAlgorithm {
    public int spanningTree(ArrayList<ArrayList<Pair>> adj) {
        int v = adj.size(); // Fix: Use size() method

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance); // Comparator to prioritize distance

        int[] visited = new int[v];

        pq.offer(new Pair(0, 0)); // Start with node 0, distance 0

        int sum = 0;

        while (!pq.isEmpty()) {
            Pair nearestNode = pq.poll();
            int node = nearestNode.node;
            int dis = nearestNode.distance;

            if (visited[node] == 1) continue; // If already visited, skip

            visited[node] = 1; // Mark the node as visited

            sum += dis; // Add the distance to the result sum

            // Visit all adjacent nodes
            for (Pair edge : adj.get(node)) {
                int edgWt = edge.distance;
                int adjNode = edge.node;
                if (visited[adjNode] == 0) {
                    pq.offer(new Pair(adjNode, edgWt)); // Add to the priority queue
                }
            }
        }

        return sum; // Return the total weight of the minimum spanning tree
    }
}

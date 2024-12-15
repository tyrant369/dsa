/**
 *  https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implementing-dijkstra-set-1-adjacency-matrix
 */

public class Dijkstra {

    // Represents a node and its associated distance
    private static class NodeDistancePair {
        int node;
        int distance;

        public NodeDistancePair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public ArrayList<Integer> findShortestPath(ArrayList<ArrayList<NodeDistancePair>> adj, int src) {
        int v = adj.size(); // Number of nodes in the graph

        // Min-Heap (Priority Queue) to get the node with the smallest distance
        PriorityQueue<NodeDistancePair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);

        // Distance array to store shortest distance to each node
        ArrayList<Integer> dist = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            dist.add(Integer.MAX_VALUE); // Initialize all distances to infinity
        }

        dist.set(src, 0); // Distance to source is 0
        pq.offer(new NodeDistancePair(src, 0)); // Push the source into the priority queue

        while (!pq.isEmpty()) {
            NodeDistancePair nearestNode = pq.poll(); // Get the node with the smallest distance

            int node = nearestNode.node;
            int dis = nearestNode.distance;

            // Explore all adjacent nodes
            for (NodeDistancePair edge : adj.get(node)) {
                int edgeWeight = edge.distance; // Weight of the edge
                int adjNode = edge.node; // Adjacent node

                int totalDistance = dis + edgeWeight;

                // If a shorter distance is found
                if (totalDistance < dist.get(adjNode)) {
                    dist.set(adjNode, totalDistance); // Update the distance
                    pq.offer(new NodeDistancePair(adjNode, totalDistance)); // Push the adjacent node into the queue
                }
            }
        }

        return dist; // Return the shortest distances from the source
    }
}

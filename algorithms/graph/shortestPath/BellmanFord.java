/**
 *  https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=distance-from-the-source-bellman-ford-algorithm
 */


/**
 * Bellman-Ford Algorithm to find the shortest path in a weighted graph
 * from a given source vertex. Detects negative weight cycles if present.
 *
 * Time Complexity: O(V * E)
 * Space Complexity: O(V)
 */


public class BellmanFord {
    static int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dis = new int[V];

        // Step 1: Initialize distances as infinite except the source vertex
        for (int i = 0; i < V; i++) {
            dis[i] = (int) 1e9; // Represents "infinity"
        }
        dis[src] = 0; // Distance to the source vertex is 0

        // Step 2: Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0]; // Start vertex of the edge
                int v = edge[1]; // End vertex of the edge
                int weight = edge[2]; // Weight of the edge

                // Relax the edge if a shorter path is found
                if (dis[u] != (int) 1e9 && dis[u] + weight < dis[v]) {
                    dis[v] = dis[u] + weight;
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            // If relaxation is still possible, a negative weight cycle exists
            if (dis[u] != (int) 1e9 && dis[u] + weight < dis[v]) {
                return new int[]{-1}; // Negative weight cycle detected
            }
        }

        return dis; // Return shortest distances
    }
}

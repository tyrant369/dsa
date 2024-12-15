/**
 *  https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implementing-floyd-warshall
 */


/**
 * Implementation of the Floyd-Warshall algorithm for finding shortest paths
 * in a graph with edge weights, including handling of unreachable nodes and negative weights.
 */
public class FloydWarshall {
    /**
     * Computes the shortest distances between all pairs of nodes in a weighted graph.
     *
     * @param mat The adjacency matrix representing the graph. A value of -1 indicates no direct edge between nodes.
     *            The matrix is modified in place to store the shortest distances.
     *            If a pair of nodes is unreachable, the distance will remain -1.
     */
    public void shortestDistance(int[][] mat) {
        if (mat == null || mat.length == 0 || mat.length != mat[0].length) return;

        int n = mat.length;
        final int INF = (int) 1e9; // A large value to represent infinity

        // Initialize the matrix: Replace -1 with INF and set diagonal to 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == -1) {
                    mat[i][j] = INF;
                }
                if (i == j) {
                    mat[i][j] = 0;
                }
            }
        }

        // Floyd-Warshall algorithm: Update distances using intermediate nodes
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][k] < INF && mat[k][j] < INF) { // Avoid overflow
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }

        // Restore unreachable paths to -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == INF) {
                    mat[i][j] = -1;
                }
            }
        }
    }
}
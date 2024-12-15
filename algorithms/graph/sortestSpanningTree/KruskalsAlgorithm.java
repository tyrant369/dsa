/**
 *  https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=minimum-spanning-tree
 */

public class KruskalsAlgorithm {
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        ArrayList<Edge> edges = new ArrayList<Edge>();

        // Add edges to the list
        for (int i = 0; i < V; i++) {
            for (ArrayList<Integer> neighbor : adj.get(i)) {
                int adjNode = neighbor.get(0);
                int wt = neighbor.get(1);
                edges.add(new Edge(i, adjNode, wt)); // Correct way to add edge
            }
        }

        // Create Disjoint Set
        DisjointSet ds = new DisjointSet(V);

        // Sort edges by their weights
        Collections.sort(edges);

        int mstWt = 0;

        // Process each edge in increasing order of weights
        for (Edge edge : edges) {
            int wt = edge.weight;
            int u = edge.src;
            int v = edge.dest;

            // If u and v are not in the same set, include this edge in the MST
            if (ds.findUPar(u) != ds.findUPar(v)) {
                ds.unionByRank(u, v);
                mstWt += wt;
            }
        }

        return mstWt; // Return the weight of the MST
    }
}

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    // Constructor for the edge
    Edge(int _src, int _dest, int _wt) {
        this.src = _src;
        this.dest = _dest;
        this.weight = _wt;
    }

    // Comparator for sorting edges based on weight
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    // Constructor to initialize the disjoint set data structure
    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    // Find the representative of the set to which 'node' belongs
    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        // Path compression
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    // Union by rank: Attach smaller rank tree under root of deeper tree
    public void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;

        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        } else if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        } else {
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU + 1);
        }
    }

    // Union by size: Attach smaller tree under root of larger tree
    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;

        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

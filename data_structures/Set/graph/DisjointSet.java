public class DisjointSet {
    ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<Integer> parent = new ArrayList<>();

    // Constructor to initialize the disjoint set with 'n' elements
    public DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            rank.add(0); // Initialize rank of all nodes as 0
            parent.add(i); // Initialize each node as its own parent
        }
    }

    // Find the ultimate parent of a node (with path compression)
    public int findUPar(int node) {
        if (node == parent.get(node)) return node; // Base case: node is its own parent

        // Recursive call with path compression
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp); // Update the parent to the ultimate parent
        return ulp;
    }

    // Union of two sets by rank
    public void unionByRank(int u, int v) {
        int uPar_u = findUPar(u); // Find ultimate parent of u
        int uPar_v = findUPar(v); // Find ultimate parent of v

        if (uPar_u == uPar_v) return; // If already in the same set, do nothing

        // Attach smaller rank tree under the larger rank tree
        if (rank.get(uPar_u) > rank.get(uPar_v)) {
            parent.set(uPar_v, uPar_u);
        } else if (rank.get(uPar_u) < rank.get(uPar_v)) {
            parent.set(uPar_u, uPar_v);
        } else {
            // If ranks are equal, attach one tree to the other and increase its rank
            parent.set(uPar_v, uPar_u);
            rank.set(uPar_u, rank.get(uPar_u) + 1);
        }
    }
}

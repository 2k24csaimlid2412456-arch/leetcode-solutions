import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] newParent;
    private int[] lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.newParent = Arrays.copyOf(parent, n);
        this.lastSeen = new int[26];
        Arrays.fill(this.lastSeen, -1);

        // 1. Build the initial tree
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        // 2. First DFS to determine the closest matching ancestor for every node
        findNewParents(0);

        // 3. Rebuild the tree using the updated parents
        for (int i = 0; i < n; i++) {
            adj[i].clear();
        }
        for (int i = 1; i < n; i++) {
            adj[newParent[i]].add(i);
        }

        // 4. Second DFS to compute subtree sizes in the final tree
        int[] answer = new int[n];
        calculateSubtreeSizes(0, answer);

        return answer;
    }

    private void findNewParents(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        // If a matching ancestor exists on the current path, update parent
        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        // Mark current node as the closest ancestor for this character
        lastSeen[charIdx] = u;

        for (int v : adj[u]) {
            findNewParents(v);
        }

        // Backtrack: restore the state before leaving this subtree
        lastSeen[charIdx] = prevAncestor;
    }

    private int calculateSubtreeSizes(int u, int[] answer) {
        int size = 1;
        for (int v : adj[u]) {
            size += calculateSubtreeSizes(v, answer);
        }
        answer[u] = size;
        return size;
    }
}

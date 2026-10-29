
import java.util.*;

class Solution {

    List<Integer>[] adj;
    int[][] up;
    int[] depth, tin, tout, parent;
    int[] rootMask, iter;
    int timer;
    Fenwick bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        depth = new int[n];
        parent = new int[n];
        rootMask = new int[n];
        tin = new int[n];
        tout = new int[n];
        iter = new int[n];

        Arrays.fill(parent, -1);

        int[] queue = new int[n];
        int head = 0, tail = 0;

        queue[tail++] = 0;
        parent[0] = 0;
        rootMask[0] = mask(s.charAt(0));

        while (head < tail) {
            int u = queue[head++];

            up[0][u] = parent[u];

            for (int v : adj[u]) {
                if (parent[v] != -1) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                rootMask[v] = rootMask[u] ^ mask(s.charAt(v));
                queue[tail++] = v;
            }
        }

        for (int k = 1; k < LOG; k++) {
            for (int v = 0; v < n; v++) {
                up[k][v] = up[k - 1][up[k - 1][v]];
            }
        }

        int[] stack = new int[n];
        int top = 0;

        stack[0] = 0;
        timer = 0;
        tin[0] = timer++;

        while (top >= 0) {
            int u = stack[top];

            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);

                if (v == parent[u]) {
                    continue;
                }

                tin[v] = timer++;
                stack[++top] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        bit = new Fenwick(n + 1);

        StringBuilder chars = new StringBuilder(s);
        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int delta = mask(chars.charAt(u)) ^ mask(c);

                if (delta != 0) {
                    bit.rangeXor(tin[u] + 1, tout[u] + 1, delta);
                }

                chars.setCharAt(u, c);

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);

                int maskU = rootMask[u] ^ bit.query(tin[u] + 1);
                int maskV = rootMask[v] ^ bit.query(tin[v] + 1);

                int pathMask =
                        maskU ^ maskV ^ mask(chars.charAt(lca));

                result.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return result;
    }

    private int mask(char c) {
        return 1 << (c - 'a');
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < up.length; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int index, int value) {
            while (index <= n) {
                tree[index] ^= value;
                index += index & -index;
            }
        }

        int query(int index) {
            int result = 0;

            while (index > 0) {
                result ^= tree[index];
                index -= index & -index;
            }

            return result;
        }

        void rangeXor(int left, int right, int value) {
            update(left, value);
            update(right + 1, value);
        }
    }
}

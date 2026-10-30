import java.util.*;

public class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build the adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
        }

        // dist[u][c] stores the minimum weight to reach node u 
        // with c consecutive identical characters ending at u.
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Priority Queue stores elements as: {weight, node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Initialize for node 0
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currWeight = curr[0];
            int u = curr[1];
            int count = curr[2];

            // If we found a shorter path to this state already, skip it
            if (currWeight > dist[u][count]) {
                continue;
            }

            // If we reached the target node, since it's Dijkstra, this is the optimal weight
            if (u == n - 1) {
                return currWeight;
            }

            // Traverse all outgoing edges
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;

                // Only proceed if the consecutive count constraint is satisfied
                if (nextCount <= k) {
                    if (currWeight + w < dist[v][nextCount]) {
                        dist[v][nextCount] = currWeight + w;
                        pq.offer(new int[]{dist[v][nextCount], v, nextCount});
                    }
                }
            }
        }

        // Find the minimum weight among all valid consecutive counts for node n - 1
        int minWeight = Integer.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            minWeight = Math.min(minWeight, dist[n - 1][c]);
        }

        return minWeight == Integer.MAX_VALUE ? -1 : minWeight;
    }
}

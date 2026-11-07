import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int qLen = queries.length;
        int[][] sortedQueries = new int[qLen][2];
        for (int i = 0; i < qLen; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[qLen];
        Map<Integer, Integer> serverCounts = new HashMap<>();
        
        int left = 0;
        int right = 0;

        for (int i = 0; i < qLen; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            int windowStart = queryTime - x;

            while (right < logs.length && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                serverCounts.put(serverId, serverCounts.getOrDefault(serverId, 0) + 1);
                right++;
            }

            while (left < logs.length && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                if (serverCounts.containsKey(serverId)) {
                    serverCounts.put(serverId, serverCounts.get(serverId) - 1);
                    if (serverCounts.get(serverId) == 0) {
                        serverCounts.remove(serverId);
                    }
                }
                left++;
            }

            result[originalIndex] = n - serverCounts.size();
        }

        return result;
    }
}

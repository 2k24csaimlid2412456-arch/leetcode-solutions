import java.util.*;

public class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestCount = new int[k];
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int duration = load[i];
            
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime) {
                int[] freed = busyServers.poll();
                availableServers.add(freed[1]);
            }
            
            if (availableServers.isEmpty()) {
                continue;
            }
            
            int preferredServer = i % k;
            Integer assignedServer = availableServers.ceiling(preferredServer);
            
            if (assignedServer == null) {
                assignedServer = availableServers.first();
            }
            
            requestCount[assignedServer]++;
            availableServers.remove(assignedServer);
            busyServers.offer(new int[]{arrivalTime + duration, assignedServer});
        }
        
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}

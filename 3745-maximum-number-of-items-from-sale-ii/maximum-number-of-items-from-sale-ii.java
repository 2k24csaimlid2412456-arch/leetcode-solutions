import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] <= budget && item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        if (minPrice == Long.MAX_VALUE) {
            return 0;
        }

        long[] freq = new long[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // mult[f] = total number of items with factor divisible by f
        long[] mult = new long[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int m = f; m <= maxFactor; m += f) {
                mult[f] += freq[m];
            }
        }

        // Sort items by price ascending
        Arrays.sort(items, Comparator.comparingInt(a -> a[1]));

        long totalItems = 0;
        long remainingBudget = budget;

        for (int[] item : items) {
            int f = item[0];
            long price = item[1];
            long maxFreeCopies = Math.max(0L, mult[f] - 1L);

            if (price > remainingBudget) {
                break;
            }

            // Each copy bought (up to maxFreeCopies) yields 2 items (1 bought + 1 free) for 'price'.
            // This beats buying single items at 'minPrice' if price < 2 * minPrice.
            if (maxFreeCopies > 0 && price < 2L * minPrice) {
                long canBuy = Math.min(maxFreeCopies, remainingBudget / price);
                totalItems += canBuy * 2L;
                remainingBudget -= canBuy * price;
            }
        }

        // Spend all remaining budget on the cheapest item (1 item per copy)
        totalItems += remainingBudget / minPrice;

        return (int) totalItems;
    }
}

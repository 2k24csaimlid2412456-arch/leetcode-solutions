class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        // Option 1: Buy all items separately (k = 0)
        long option1 = n1 * c1 + n2 * c2;

        // Option 2: Buy type 3 for the overlapping needs, then buy the rest separately
        long common = Math.min(n1, n2);
        long option2 = common * cb + (n1 - common) * c1 + (n2 - common) * c2;

        // Option 3: Buy type 3 to cover the larger need completely
        long maxNeed = Math.max(n1, n2);
        long option3 = maxNeed * cb;

        return Math.min(option1, Math.min(option2, option3));
    }
}

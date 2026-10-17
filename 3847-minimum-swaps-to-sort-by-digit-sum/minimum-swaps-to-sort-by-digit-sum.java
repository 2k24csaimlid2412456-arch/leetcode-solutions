import java.util.Arrays;

public class Solution {

    static class Element implements Comparable<Element> {
        int val;
        int originalIdx;
        int digitSum;

        public Element(int val, int originalIdx) {
            this.val = val;
            this.originalIdx = originalIdx;
            this.digitSum = getDigitSum(val);
        }

        private int getDigitSum(int n) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            return sum;
        }

        @Override
        public int compareTo(Element other) {
            if (this.digitSum != other.digitSum) {
                return Integer.compare(this.digitSum, other.digitSum);
            }
            return Integer.compare(this.val, other.val);
        }
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Element[] elements = new Element[n];

        for (int i = 0; i < n; i++) {
            elements[i] = new Element(nums[i], i);
        }

        Arrays.sort(elements);

        boolean[] visited = new boolean[n];
        int totalSwaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || elements[i].originalIdx == i) {
                continue;
            }

            int cycleSize = 0;
            int currIndex = i;

            while (!visited[currIndex]) {
                visited[currIndex] = true;
                currIndex = elements[currIndex].originalIdx;
                cycleSize++;
            }

            if (cycleSize > 1) {
                totalSwaps += (cycleSize - 1);
            }
        }

        return totalSwaps;
    }
}

package 贪心算法.IPO;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 1. 输入: 正数数组costs、正数数组profits、正数K、正数M
 2. capital[i]表示i号项目的花费
 3. profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 4. K表示你只能串行的最多做k个项目
 5. W表示你初始的资金
 6. 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 7. 输出：你最后获得的最大钱数。
 */
public class IPO {
    public static int findMaximizedCapital(int K, int W, int[] profits, int[] capital) {
        PriorityQueue<Program> minCostQueue = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.cost - o2.cost;
            }
        });
        PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o2.profit - o1.profit;
            }
        });
        for (int i = 0; i < profits.length; i++) {
            minCostQueue.add(new Program(capital[i], profits[i]));
        }
        int ans = W;
        for (int i = 0; i < K; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= ans) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            if (maxProfitQueue.isEmpty()) {
                break;
            }
            ans += maxProfitQueue.poll().profit;
        }
        return ans;
    }

    public static class Program {
        int cost;
        int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }
}

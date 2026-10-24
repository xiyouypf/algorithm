package 只出现一次的数字;

import java.util.HashMap;
import java.util.HashSet;

public class EvenTimesOddTimes {
    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次
     */
    public int singleNumber(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        return eor;
    }

    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次
     */
    public int[] singleNumber2(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        int rightOne = eor & -eor; // 提取出最右的1
        int onlyOne = 0;
        for (int num : nums) {
            if ((num & rightOne) != 0) {
                onlyOne ^= num;
            }
        }
        return new int[]{onlyOne, eor ^ onlyOne};
    }

    /**
     * 一个数组中有一种数出现K次，其他数都出现了M次，M > 1,  K < M
     * 找到，出现了K次的数，
     */
    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (help[i] % m != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}

package 二分查找;

import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 找满足>=value的最左位置
    public static int nearLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int t = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
                t = mid;
            } else {
                left = mid + 1;
            }
        }
        return t;
    }

    // 找满足>=value的最右位置
    public static int nearRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int t = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
                t = mid;
            } else {
                right = mid - 1;
            }
        }
        return t;
    }
}

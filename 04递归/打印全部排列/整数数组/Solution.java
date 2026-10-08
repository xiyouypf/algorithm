package 打印全部排列.整数数组;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }
        process(ans, arr, 0);
        return ans;
    }

    private static void process(List<List<Integer>> ans, List<Integer> nums, int idx) {
        if (idx == nums.size()) {
            ans.add(new ArrayList<>(nums));
        } else {
            for (int i = idx; i < nums.size(); i++) {
                swap(nums, i, idx);
                process(ans, nums, idx + 1);
                swap(nums, i, idx);
            }
        }
    }



    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }
        process2(ans, arr, 0);
        return ans;
    }

    private static void process2(List<List<Integer>> ans, List<Integer> nums, int idx) {
        if (idx == nums.size()) {
            ans.add(new ArrayList<>(nums));
        } else {
            boolean[] visit = new boolean[20];
            for (int i = idx; i < nums.size(); i++) {
                if (!visit[nums.get(i) + 10]) {
                    visit[nums.get(i) + 10] = true;
                    swap(nums, idx, i);
                    process2(ans, nums, idx + 1);
                    swap(nums, idx, i);
                }
            }
        }
    }

    private static void swap(List<Integer> nums, int i, int j) {
        Integer temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> permute = permute(nums);
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
        System.out.println("------------------------");
        List<List<Integer>> lists = permuteUnique(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}

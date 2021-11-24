package quests.arrays.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vyacheslav Doroshenko
 */
public class TwoSum {

    public static int[] twoSumSimple(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int num = i + 1;
            for (int j = num; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }


    public static int[] twoSumMidle(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int num = i + 1;
            for (int j = num; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static int[] twoSumMax(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}

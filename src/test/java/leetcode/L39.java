package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class L39 {
    private interface Solution {
        List<List<Integer>> combinationSum(int[] candidates, int target);
    }

    @Test
    public void test() {
        Solution solution = new Solution2();
        assertEquals(equaleA(Arrays.asList(Arrays.asList(7), Arrays.asList(2, 2, 3)), solution.combinationSum(new int[]{2, 3, 6, 7}, 7)),true);
        assertEquals(equaleA(Arrays.asList(Arrays.asList(3, 5), Arrays.asList(2, 3, 3),Arrays.asList(2, 2, 2, 2) ), solution.combinationSum(new int[]{2, 3, 5}, 8)),true);
    }
    static <E> boolean equaleA(List<List<E>> a,List<List<E>> b){
        if (a.size() != b.size()) {
            return false;
        }
        Set<List<E>> set = new HashSet<>();
        for (List<E> es : a) {
            set.add(es);
        }
        for (List<E> es : b) {
            if (!set.remove(es)) {
                return false;
            }
        }
        return set.isEmpty();

    }

    private static class Solution1 implements Solution {

        @Override
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<>(), ans);
            return ans;
        }

        void dfs(int[] candidates, int target, int i, ArrayList<Integer> temp, List<List<Integer>> ans) {
            if (target == 0) {
                ans.add((List<Integer>) temp.clone());
                return;
            }
            if (i >= candidates.length) return;

            int sum = 0;
            int back = temp.size();
            while (sum <= target) {
                dfs(candidates, target - sum, i + 1, temp, ans);
                sum += candidates[i];
                temp.add(candidates[i]);
            }
//            for (int size = temp.size()-1; size >= back; size--) {
//                temp.remove(size);
//            }
            if (temp.size() > back) {
                temp.subList(back, temp.size()).clear();
            }


        }
    }
    private static class Solution2 implements Solution {

        @Override
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<>(), ans);
            return ans;
        }

        void dfs(int[] candidates, int target, int i, ArrayList<Integer> temp, List<List<Integer>> ans) {
            if (target == 0) {
                ans.add(new ArrayList<>(temp));
                return;
            }
            for (int j = i; j < candidates.length; j++) {

                if (candidates[j]>target)break;
                temp.add(candidates[j]);
                dfs(candidates,target-candidates[j],j,temp,ans);
                temp.remove(temp.size()-1);
            }


        }
    }
}
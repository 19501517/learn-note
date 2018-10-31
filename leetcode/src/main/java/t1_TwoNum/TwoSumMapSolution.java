package t1_TwoNum;

import java.util.HashMap;
import java.util.Map;

/**
 * map集合法：
 * 把目标和减去当前数字，然后检索map里面是否存在这个结果
 * 如果这个结果存在，则把结果的value发出来，和当前数字的索引组成数字返回
 * 否则把当前数字作为key，当前数字的数组索引作为value存进map，继续下一个数字的检索
 */
class TwoSumMapSolution implements ITwoSumSolution {

    @Override
    public int[] getTwoSumResult(int[] nums, int target) {
        Map<Integer, Integer> numsPosition = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int otherNum = target - num;
            if (numsPosition.containsKey(otherNum)) {
//                return new int[]{numsPosition.get(otherNum), i};
                return new int[]{otherNum, num};
            } else {
                numsPosition.put(num, i);
            }
        }

        return null;
    }
}

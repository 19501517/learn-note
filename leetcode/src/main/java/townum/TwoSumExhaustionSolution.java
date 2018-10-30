package townum;

/**
 * 穷举法
 */
class TwoSumExhaustionSolution implements ITwoSumSolution {

    @Override
    public int[] getTwoSumResult(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 0; j < nums.length; j++) {
                int otherNum = nums[j];
                if (num + otherNum == target) {
//                    return new int[]{i, j};
                    return new int[]{num, otherNum};
                }
            }
        }

        return null;
    }
}

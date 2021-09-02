package t5_LongestPalindromicSubstring;

import com.test.base.Solution;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 一开始想的是翻转字符串后，查找两个字符串的最长公共子串，但是好像效率不行，而且空间效率也不行。
 * 中心扩散法，轮训查询当前点的回文串。
 */
public class CentrolExpand {

    @Solution
    public String findLongest(String input) {
        if (input == null) {
            return null;
        }
        if (input.length() < 1) {
            return input;
        }

        String max = input.charAt(0) + "";
        for (int i = 0; i < input.length() - 1; i++) {
            max = expandSearch(max, input, i, i);

            if (input.charAt(i) == input.charAt(i + 1)) {
                max = expandSearch(max, input, i, i + 1);
            }
        }
        return max;
    }

    private String expandSearch(String curMax, String input, int lp, int rp) {
        String max = curMax;

        if (max.length() < rp - lp + 1) {
            max = input.substring(lp, rp + 1);
        }

        while (lp > 0 && rp < input.length() - 1) {
            lp--;
            rp++;

            if (input.charAt(lp) == input.charAt(rp)) {
                if (max.length() < rp - lp + 1) {
                    max = input.substring(lp, rp + 1);
                }
            } else {
                break;
            }
        }
        return max;
    }
}

package t3_LongestSubstringWithoutRepeatingCharacters;

import java.util.BitSet;

/**
 * 通过BitSet和活动窗口判断最长序列
 * 这个是自己想出来的方法
 */
public class LongestSubstringBitSetSolution implements ILongestSubstringSolution {

    @Override
    public int lengthOfLongestSubstring(String s) {
        BitSet set = new BitSet();
        int leftWindow = 0;
        int rightWindow = 0;

        int maxLength = 0;
        while (leftWindow < s.length() && rightWindow < s.length()) {
            char curChar = s.charAt(rightWindow);
            if (set.get(curChar)) {
                // 现在的最长序列包含了当前这个字符，则减去最前面的那个字符
                char deleteChar = s.charAt(leftWindow);
                set.set(deleteChar, false);
                leftWindow++;
            } else {
                // 把当前字符加进当前最长序列
                set.set(curChar);
                maxLength = Math.max(maxLength, rightWindow - leftWindow + 1);
                rightWindow++;
            }

        }
        return maxLength;
    }
}

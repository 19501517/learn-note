package t557_ReverseWordsInAString3;

import com.base.Solution;
import commonmodel.TreeNode;

/**
 * 新建数组，循环交换
 */
public class ReverseWordsInAStringArrayListSolution {

    private TreeNode result;

    @Solution
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();

        int wordStart = 0;
        for (int i = 0; i < chars.length; i++) {
            // 如果不是空格，且不是最后一格，那就继续循环
            if (chars[i] != ' ' && i != chars.length - 1) {
                continue;
            }

            int wordEnd = chars[i] != ' ' ? i : i - 1;
            while (wordStart < wordEnd) {
                char temp = chars[wordStart];
                chars[wordStart] = chars[wordEnd];
                chars[wordEnd] = temp;
                wordEnd--;
                wordStart++;
            }

            // 避免有多个空格
            if (i + 1 < chars.length && chars[i + 1] != ' ') {
                wordStart = i + 1;
            }
        }
        return new String(chars);
    }
}

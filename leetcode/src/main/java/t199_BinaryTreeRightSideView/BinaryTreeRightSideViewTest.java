package t199_BinaryTreeRightSideView;


import com.base.ProxyRunner;
import commonmodel.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
 *
 * 示例 1:
 * 输入:[1,2,3,null,5,null,4]
 * 输出:[1,3,4]
 * 示例 2:
 * 输入:[1,null,3]
 * 输出:[1,3]
 * 示例 3:
 * 输入:[]
 * 输出:[]
 * 
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100<= Node.val <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeRightSideViewTest {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(arguments1(), Arrays.asList(1, 3, 4))
        );
    }

    private static TreeNode arguments1() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node5;
        node3.right = node4;
        return node1;
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void postorderTraversalTest(TreeNode given, List<Integer> expected) {
        ProxyRunner.run(given, expected, new BinaryTreeRightSideViewSolution());
    }
}
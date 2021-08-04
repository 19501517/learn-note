package t145_BinaryTreePostorderTaversal;


import base.ProxyRunner;
import commonmodel.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Given the root of binary tree, return the postorder traversal of its nodes' values.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class BinaryTreePostorderTraversalSolutionTest {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(arguments1(), Arrays.asList(3, 2, 1))
        );
    }

    private static TreeNode arguments1() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.right = node2;
        node2.left = node3;
        return root;
    }

    @ParameterizedTest
    @MethodSource("getParams")
    void postorderTraversalTest(TreeNode given, List<Integer> expected) {
        ProxyRunner.run(given, expected, new BinaryTreePostorderTraversalSolution());
    }
}
package t25_ResultPathOfTree;

import com.test.base.ProxyRunner;
import com.test.module.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 输入一颗二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 */
public class ResultPathOfTree {

    private static Stream<?> getParams() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node6;
        node2.right = node7;
        node3.left = node5;
        node3.right = node4;
        return Stream.of(
                Arguments.of(node1, 8, 1),
                Arguments.of(node1, 9, 2),
                Arguments.of(node1, 3, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void test(TreeNode root, int result, int expect) {
        ProxyRunner.run(new Object[]{root, result}, expect, new ResultPathOfTreeSolution());
    }
}

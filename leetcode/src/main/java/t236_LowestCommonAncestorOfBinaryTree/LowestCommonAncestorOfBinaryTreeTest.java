package t236_LowestCommonAncestorOfBinaryTree;


import com.base.ProxyRunner;
import commonmodel.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 */
public class LowestCommonAncestorOfBinaryTreeTest {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(arguments1())
        );
    }

    private static Object[] arguments1() {
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(5);
        TreeNode node7 = new TreeNode(5);
        TreeNode node8 = new TreeNode(5);
        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;
        return new Object[]{node3, node5, node1, node3};
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void lowestCommonAncestorOfBinaryTreeTest(TreeNode root, TreeNode p, TreeNode q, TreeNode expect) {
        ProxyRunner.run(new Object[]{root, p, q}, expect, new LowestCommonAncestorOfBinaryTreeSolution());
    }
}
package t236_LowestCommonAncestorOfBinaryTree;

import base.Solution;
import commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 适用于TreeNode中val唯一的情况下
 */
public class LowestCommonAncestorOfBinaryTreeSolution {

    private TreeNode result;

    @Solution
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        dfs(root, p, q);
        return result;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lFlag = dfs(root.left, p, q);
        boolean rFlag = dfs(root.right, p, q);

        // 最近祖先为q点或者p点
        if ((lFlag || rFlag) && (root.val == p.val || root.val == q.val)) {
            result = root;
            return true;
        }

        // 左右各有一个满足的，也就是说这个就是最近公共祖先
        if (lFlag && rFlag) {
            result = root;
            return true;
        }
        // 判断当前子树有且只有一个p或者q点
        return (lFlag || rFlag) || root.val == p.val || root.val == q.val;
    }
}

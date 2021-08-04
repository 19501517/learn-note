package t145_BinaryTreePostorderTaversal;

import commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BinaryTreePostorderTraversalSolution implements IBinaryTreePostorderTraversalSolution {

    @Override
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode lastPrint = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (root.right == null || root.right == lastPrint) {
                result.add(root.val);
                lastPrint = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return result;
    }
}

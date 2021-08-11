package t199_BinaryTreeRightSideView;

import base.Solution;
import commonmodel.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeRightSideViewSolution {

    @Solution
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.addLast(root);
        while (!nodes.isEmpty()) {
            int curLevelNodesSize = nodes.size();
            TreeNode lastNode = null;
            for (int i = 0; i < curLevelNodesSize; i++) {
                lastNode = nodes.removeFirst();
                if (lastNode.left != null) {
                    nodes.addLast(lastNode.left);
                }
                if (lastNode.right != null) {
                    nodes.addLast(lastNode.right);
                }
            }
            result.add(lastNode.val);
        }
        return result;
    }
}

package t25_ResultPathOfTree;

import com.test.base.Solution;
import com.test.module.TreeNode;

import java.util.*;

public class ResultPathOfTreeSolution {

    @Solution
    public int judge(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }

        Set<List<Integer>> result = new HashSet<>();
        calPath(result, new LinkedList<>(), root, target, 0);
        return result.size();
    }

    public void calPath(Set<List<Integer>> result, LinkedList<Integer> path, TreeNode curRoot, int target, int curAddResult) {
        path.addLast(curRoot.val);
        curAddResult += curRoot.val;

        if (curAddResult == target) {
            result.add(new ArrayList<>(path));
        }

        if (curRoot.left != null) {
            calPath(result, path, curRoot.left, target, curAddResult);
        }
        if (curRoot.right != null) {
            calPath(result, path, curRoot.right, target, curAddResult);
        }
        curAddResult = curAddResult - curRoot.val;
        path.removeLast();
    }
}

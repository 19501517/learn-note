package utils;

import com.test.module.TreeNode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {
        TreeNode n4_1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        TreeNode n4_2 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n4_1.left = n2;
        n4_1.right = n3;
        n2.left = n4_2;
        n2.right = n5;
        n4_2.left = n8;
        n4_2.right = n9;
        n3.left = n6;
        n3.right = n7;

        TreeNode tmp4 = new TreeNode(4);
        TreeNode tmp8 = new TreeNode(2);
        TreeNode tmp9 = new TreeNode(3);
        tmp4.left = tmp8;
        tmp4.right = tmp9;
        System.out.println(isSubStructure(n4_1, tmp4));
    }

    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return findEqualRoot(A, B);
    }

    private static boolean findEqualRoot(TreeNode A, TreeNode B) {
        if (A == null) {
            return false;
        }
        if (A.val == B.val && compareTree(A, B)) {
            return true;
        }

        boolean tmpLeft = findEqualRoot(A.left, B);
        if (tmpLeft) {
            return true;
        }
        return findEqualRoot(A.right, B);
    }

    private static boolean compareTree(TreeNode A, TreeNode B) {
        Queue<TreeNode> ANodes = new LinkedList<>();
        Queue<TreeNode> BNodes = new LinkedList<>();
        ANodes.offer(A);
        BNodes.offer(B);

        while (!BNodes.isEmpty()) {
            TreeNode tmpA = ANodes.poll();
            TreeNode tmpB = BNodes.poll();

            if (tmpA.val != tmpB.val) {
                return false;
            }
            if (tmpB.left != null) {
                if (tmpA.left == null || tmpA.left.val != tmpB.left.val) {
                    return false;
                }
            }
            if (tmpB.right != null) {
                if (tmpA.right == null || tmpA.right.val != tmpB.right.val) {
                    return false;
                }
            }

            if (tmpB.left != null) {
                ANodes.offer(tmpA.left);
                BNodes.offer(tmpB.left);
            }
            if (tmpB.right != null) {
                ANodes.offer(tmpA.right);
                BNodes.offer(tmpB.right);
            }
        }
        return true;
    }
}
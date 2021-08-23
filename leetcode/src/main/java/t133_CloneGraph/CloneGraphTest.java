package t133_CloneGraph;


import com.test.base.ProxyRunner;
import com.test.module.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Given the root of binary tree, return the postorder traversal of its nodes' values.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CloneGraphTest {

    private static Stream<?> getParams() {
        return Stream.of(
                Arguments.of(arguments1(), arguments1())
        );
    }

    private static Node arguments1() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        return node1;
    }

    @ParameterizedTest
    @MethodSource("getParams")
    public void postorderTraversalTest(Node node, Node expected) {
        ProxyRunner.run(node, expected, new CloneGraphSolution());
    }
}
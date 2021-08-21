package t133_CloneGraph;

import com.base.Solution;
import commonmodel.Node;

import java.util.*;

public class CloneGraphSolution {

    private Map<Node, Node> newNodes = new HashMap<>();

    @Solution
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        LinkedList<Node> nodes = new LinkedList<>();
        Set<Node> handledSets = new HashSet<>();
        nodes.add(node);
        Node newNode = getNewNode(node);
        newNodes.put(node, newNode);
        while (!nodes.isEmpty()) {
            Node n = nodes.removeFirst();
            if (!handledSets.add(n)) {
                continue;
            }

            Node newN = getNewNode(n);
            for (Node neighbor : n.neighbors) {
                Node newNeighbor = getNewNode(neighbor);
                newN.neighbors.add(newNeighbor);
                nodes.add(neighbor);
            }
        }
        return newNode;
    }

    public Node getNewNode(Node node) {
        return newNodes.computeIfAbsent(node, key -> new Node(key.val));
    }
}

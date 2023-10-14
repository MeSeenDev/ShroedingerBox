package nods;

import java.util.List;

public class Nods {

    void setParents(List<Node> nodes) { // FIXME: 13.10.2023 false
        for (Node node : nodes) {
            Node left = node.getLeft();
            Node right = node.getRight();
            if (left != null) {
                left.setParent(node);
            } else if (right != null) {
                right.setParent(node);
            }
        }
    }

    interface Node {
        Node getLeft();

        Node getRight();

        Node getParent();

        void setParent(Node parent);
    }
}

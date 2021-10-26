package quests;

import quests.support.Node;

import java.util.*;

public class Nodes {

    Node merge(Node head1, Node head2) {
        LinkedList<Node> list = new LinkedList<>();
        putToList(list, head1);
        putToList(list, head2);
        list.sort((Comparator.comparingInt(o -> o.data)));

        Deque<Node> deque = new ArrayDeque<>(list);
        Node root = deque.peekFirst();
        for (int i = list.size(); i > 0; i--) {
            Node node = deque.pollFirst();
            Node next = deque.pollFirst();
            node.setNext(next);
            if (next != null)
                deque.addFirst(next);
        }
        return root;
    }

    private void putToList(List<Node> list, Object node) {
        if (node instanceof Node) {
            Node current = (Node) node;
            list.add((Node) node);
            while ((current = current.getNext()) != null) {
                list.add(current);
            }
        }

    }
}

package quests.nodes;

public class ReverseNode {

    public static <T> LineNode<T> iterJavaReverseNode(LineNode<T> head) {
        LineNode<T> prev = null, next;
        while (head != null) {
            next = head.getNext();
            head.setNext(prev);

            prev = head;
            head = next;
        }
        return prev;
    }

    public static <T> LineNode<T> recJavaReverseNode(LineNode<T> prev, LineNode<T> head) {
        if (head == null) return prev;
        LineNode<T> next = head.getNext();
        head.setNext(prev);
        return recJavaReverseNode(head, next);
    }
}

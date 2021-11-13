package quests.nodes;

public class ReverseNode {

    public static LineNode iterJavaReverseNode(LineNode head) {
        LineNode prev = null, next;
        while (head != null) {
            next = head.getNext();
            head.setNext(prev);

            prev = head;
            head = next;
        }
        return prev;
    }

    public static LineNode recJavaReverseNode(LineNode prev, LineNode head) {
        if (head == null) return prev;
        LineNode next = head.getNext();
        head.setNext(prev);
        return recJavaReverseNode(head, next);
    }
}

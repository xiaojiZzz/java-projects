package hsp_java.collection_.list_;

public class LinkedList_ {
    public static void main(String[] args) {
        // 模拟一个双向链表
        Node jack = new Node("jack");
        Node lucy = new Node("lucy");
        Node mike = new Node("mike");

        jack.next = lucy;
        lucy.next = mike;

        mike.pre = lucy;
        lucy.pre = jack;

        Node first = jack;
        Node last = mike;

        while (true) {
            if (first == null) {
                break;
            }
            System.out.println(first);
            first = first.next;
        }

        Node smith = new Node("smith");
        lucy.next = smith;
        smith.pre = lucy;
        mike.pre = smith;
        smith.next = mike;

        System.out.println("------加入后-------");

        first = jack;
        while (true) {
            if (first == null) {
                break;
            }
            System.out.println(first);
            first = first.next;
        }

    }
}


@SuppressWarnings({"all"})
class Node {
    public Object item;
    public Node pre;
    public Node next;

    public Node(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Node name = " + item;
    }
}
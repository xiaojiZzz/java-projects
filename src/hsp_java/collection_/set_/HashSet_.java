package hsp_java.collection_.set_;

@SuppressWarnings({"all"})
public class HashSet_ {
    public static void main(String[] args) {
        Node[] table = new Node[16];
        Node john = new Node("john", null);
        table[2] = john;
        Node jack = new Node("jack", null);
        john.next = jack;
        Node rose = new Node("rose", null);
        jack.next = rose;
        Node lucy = new Node("lucy", null);
        table[3] = lucy;
//        TreeSet treeSet = new TreeSet();
//        treeSet.add(new Person());
//        HashSet hashSet = new HashSet();
//        hashSet.add(new Person());
//        TreeMap treeMap = new TreeMap();
//        treeMap.put(new Person(), new Person());
    }
}


@SuppressWarnings({"all"})
class Node {
    public Object item;
    public Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }

    public Node(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Node name = " + item;
    }
}

class Person implements Comparable {

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
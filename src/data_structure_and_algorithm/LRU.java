package data_structure_and_algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({"all"})
// Least Recently Used（最近最少使用），一种淘汰策略
public class LRU {
    class Node {
         int key;
         int value;
         Node prev;
         Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoubleList {
        // 头尾虚节点
         Node head;
         Node tail;
        // 链表元素个数
         int size;

        public DoubleList() {
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.size = 0;
        }

        // 在链表尾添加节点 x
        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        // 删除链表的第一个节点，并返回
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node firstNode = head.next;
            remove(firstNode);
            return firstNode;
        }

        // 从链表中删除x节点
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        // 返回链表长度
        public int size() {
            return size;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hashMap;
    private DoubleList doubleList;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        this.doubleList = new DoubleList();
    }

    public int get(int key) {
        if (!hashMap.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return hashMap.get(key).value;
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            Node x = hashMap.get(key);
            doubleList.remove(x);
            addRecently(key, value);
            return;
        }
        if (capacity == doubleList.size()) {
            removeLeastRecently();
        }
        addRecently(key, value);
    }

    // 将key成为最近使用的元素
    private void makeRecently(int key) {
        Node x = hashMap.get(key);
        doubleList.remove(x);
        doubleList.addLast(x);
    }

    // 添加最近使用的元素
    private void addRecently(int key, int value) {
        Node x = new Node(key, value);
        doubleList.addLast(x);
        hashMap.put(key, x);
    }

    // 删除最近最少使用的元素
    private void removeLeastRecently() {
        Node x = doubleList.removeFirst();
        hashMap.remove(x.key);
    }
}

@SuppressWarnings({"all"})
// 利用api
class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        // capacity：容量，0.75F：加载因子，
        // true：accessOrder参数，true时按照访问顺序（最近访问的元素放在最后）迭代；false（默认值），则按照插入顺序迭代
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    // 用于当元素总数超过容量时移除最近最少使用的元素
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

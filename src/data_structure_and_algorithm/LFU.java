package data_structure_and_algorithm;

import java.util.HashMap;
import java.util.Map;

// Least Frequently Used（最不经常使用）一种淘汰策略
class LFUCache {

    class Node {
        int key, value, freq;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head, tail;
        int size;

        public DoublyLinkedList() {
            head = new Node(0, 0);  // Dummy head
            tail = new Node(0, 0);  // Dummy tail
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addLast(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node removeFirst() {
            if (size == 0) return null;
            Node first = head.next;
            remove(first);
            return first;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> keyNodeMap;
    private final Map<Integer, DoublyLinkedList> freqListMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyNodeMap = new HashMap<>();
        freqListMap = new HashMap<>();
    }

    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) {
            return -1;
        }
        Node node = keyNodeMap.get(key);
        update(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyNodeMap.containsKey(key)) {
            Node node = keyNodeMap.get(key);
            node.value = value;
            update(node);
        } else {
            if (keyNodeMap.size() == capacity) {
                DoublyLinkedList minList = freqListMap.get(minFreq);
                Node toRemove = minList.removeFirst();
                keyNodeMap.remove(toRemove.key);
            }
            Node newNode = new Node(key, value);
            keyNodeMap.put(key, newNode);
            minFreq = 1;
            freqListMap.computeIfAbsent(1, k -> new DoublyLinkedList()).addLast(newNode);
        }
    }

    private void update(Node node) {
        int freq = node.freq;
        DoublyLinkedList oldList = freqListMap.get(freq);
        oldList.remove(node);
        if (freq == minFreq && oldList.size == 0) {
            minFreq++;
        }
        node.freq++;
        freqListMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addLast(node);
    }
}
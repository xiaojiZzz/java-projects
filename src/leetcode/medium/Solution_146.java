package leetcode.medium;

import java.util.HashMap;


/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
 * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 示例：
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 */
public class Solution_146 {

    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    class DoubleList {
        // 头尾虚节点
        private Node head, tail;
        // 链表元素数
        private int size;

        public DoubleList() {
            // 初始化双向链表的数据
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // 在链表尾部添加节点 x，时间 O(1)
        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        // 删除链表中第一个节点，并返回该节点，时间 O(1)
        public Node removeFirst() {
            if (head.next == tail)
                return null;
            Node first = head.next;
            remove(first);
            return first;
        }

        // 返回链表长度，时间 O(1)
        public int size() {
            return size;
        }

    }

    class LRUCache {
        // key -> Node(key, val)
        private HashMap<Integer, Node> map;
        // Node(k1, v1) <-> Node(k2, v2)...
        private DoubleList cache;
        // 最大容量
        private int cap;

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 将该数据提升为最近使用的
            makeRecently(key);
            return map.get(key).val;
        }

        public void put(int key, int val) {
            if (map.containsKey(key)) {
                // 删除旧的数据
                deleteKey(key);
                // 新插入的数据为最近使用的数据
                addRecently(key, val);
                return;
            }

            if (cap == cache.size()) {
                // 删除最久未使用的元素
                removeLeastRecently();
            }
            // 添加为最近使用的元素
            addRecently(key, val);
        }

        /* 将某个 key 提升为最近使用的 */
        private void makeRecently(int key) {
            Node x = map.get(key);
            // 先从链表中删除这个节点
            cache.remove(x);
            // 重新插到队尾
            cache.addLast(x);
        }

        /* 添加最近使用的元素 */
        private void addRecently(int key, int val) {
            Node x = new Node(key, val);
            // 链表尾部就是最近使用的元素
            cache.addLast(x);
            // 别忘了在 map 中添加 key 的映射
            map.put(key, x);
        }

        /* 删除某一个 key */
        private void deleteKey(int key) {
            Node x = map.get(key);
            // 从链表中删除
            cache.remove(x);
            // 从 map 中删除
            map.remove(key);
        }

        /* 删除最久未使用的元素 */
        private void removeLeastRecently() {
            // 链表头部的第一个元素就是最久未使用的
            Node deletedNode = cache.removeFirst();
            // 同时别忘了从 map 中删除它的 key
            int deletedKey = deletedNode.key;
            map.remove(deletedKey);
        }
    }
}

/*
public class LRUCache {
    private static class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int capacity;
    private final Node dummy = new Node(0, 0); // 哨兵节点
    private final Map<Integer, Node> keyToNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public int get(int key) {
        Node node = getNode(key);
        return node != null ? node.value : -1;
    }

    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) { // 有这本书
            node.value = value; // 更新 value
            return;
        }
        node = new Node(key, value); // 新书
        keyToNode.put(key, node);
        pushFront(node); // 放在最上面
        if (keyToNode.size() > capacity) { // 书太多了
            Node backNode = dummy.prev;
            keyToNode.remove(backNode.key);
            remove(backNode); // 去掉最后一本书
        }
    }

    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) { // 没有这本书
            return null;
        }
        Node node = keyToNode.get(key); // 有这本书
        remove(node); // 把这本书抽出来
        pushFront(node); // 放在最上面
        return node;
    }

    // 删除一个节点（抽出一本书）
    private void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    // 在链表头添加一个节点（把一本书放在最上面）
    private void pushFront(Node x) {
        x.prev = dummy;
        x.next = dummy.next;
        x.prev.next = x;
        x.next.prev = x;
    }
}
*/

/*
class LRUCache {
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            // 修改 key 的值
            cache.put(key, val);
            // 将 key 变为最近使用
            makeRecently(key);
            return;
        }

        if (cache.size() >= this.cap) {
            // 链表头部就是最久未使用的 key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的 key 添加链表尾部
        cache.put(key, val);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }
}
*/
/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/

public class LRUCache {
    private class Node{
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {     // 经常用的放在前面，用的少的放在后面
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        if( !hs.containsKey(key)) {
            return -1;
        }

        // remove current
        Node current = hs.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;

        // move current to tail
        move_to_tail(current);

        return hs.get(key).value;
    }

    public void set(int key, int value) {
        if( get(key) != -1) {
            hs.get(key).value = value;
            return;
        }

        if (hs.size() == capacity) {
            hs.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);
        hs.put(key, insert);
        move_to_tail(insert);
    }

    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
}

/*
这是一道非常综合的题目，主要应用在操作系统的资源管理中
按照题目要求，要实现get和set功能，为了满足随机存储的需求我们首先想到的一般是用数组，如果用链表会有O(n)的访问时间
然而他又有另一个条件就是要维护least used的队列，也就是说经常用的放在前面，用的少的放在后面。这样当资源超过cache的容积的时候就可以把用得最少的资源删掉
这就要求我们要对节点有好的删除和插入操作，这个要求又会让我们想到用链表，因为数组的删除和插入是O(n)复杂度的
那么我们能不能维护一个数据结构使得访问操作和插入删除操作都是O(1)复杂度的呢？答案是肯定的。这个数据结构比较复杂，是用一个hash表加上一个双向链表来实现
基本思路是这样的，用一个hash表来维护结点的位置关系，也就是hash表的key就是资源本身的key，value是资源的结点（包含key和value的信息）
然后把结点维护成一个双向链表构成的队列，这样子如果我们要访问某一个结点，那么可以通过hash表和key来找到结点，从而取到相应的value
而当我们想要删除或者插入结点时，我们还是通过hash表找到结点，然后通过双向链表和队列的尾结点把自己删除同时插入到队尾
通过hash表访问结点我们可以认为是O(1)的操作（假设hash函数足够好），然后双向链表的插入删除操作也是O(1)的操作。如此我们便实现了用O(1)时间来完成所有LRU cache的操作
空间上就是对于每一个资源有一个hash表的的项以及一个对应的结点（包含前后指针和资源的<key, value>）

实现的时候还是有很多细节的，因为我们不经常使用双向链表，插入删除操作要维护前后指针，并且同时要维护成队列，增加了许多注意点
这道题是一道很实际的题目，思路和数据结构都是很适合面试的题目，但是代码量有些偏大，所以一般只在onsite的时候有可能遇到，可能也不会让你完整地写出全部代码，主要还是讲出维护的数据结构和各种操作复杂度的分析

Reference:
http://www.ninechapter.com/solutions/lru-cache
http://blog.csdn.net/linhuanmars/article/details/21310633
*/
/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class CopyListWithRandomPointer { 		// hash map version
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy, newNode;
        while (head != null) {
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            pre.next = newNode;

            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }
            pre = newNode;
            head = head.next;
        }
        return dummy.next;
    }
}

/*
这是一道链表操作的题目，要求复制一个链表，不过链表的每个结点带有一个随机指针，指向某一个结点
我们先介绍一种比较直接的算法，思路是先按照复制一个正常链表的方式复制，复制的时候把复制的结点做一个HashMap，以旧结点为key，新节点为value
这么做的目的是为了第二遍扫描的时候我们按照这个哈希表把结点的随机指针接上。这个算法是比较容易想到的，总共要进行两次扫描，所以时间复杂度是O(2*n)=O(n)
空间上需要一个哈希表来做结点的映射，所以空间复杂度也是O(n)
那么有没有办法可以不用额外空间来完成这个任务呢？还是有的，前面我们需要一个哈希表的原因是当我们访问一个结点时可能它的随机指针指向的结点还没有访问过，结点还没有创建，所以我们需要线性的额外空间
想避免使用额外空间，我们只能通过利用链表原来的数据结构来存储结点。基本思路是这样的，对链表进行三次扫描，第一次扫描对每个结点进行复制，然后把复制出来的新节点接在原结点的next，也就是让链表变成一个重复链表，就是新旧更替
第二次扫描中我们把旧结点的随机指针赋给新节点的随机指针，因为新结点都跟在旧结点的下一个，所以赋值比较简单，就是node.next.random = node.random.next，其中node.next就是新结点，因为第一次扫描我们就是把新结点接在旧结点后面
现在我们把结点的随机指针都接好了，最后一次扫描我们把链表拆成两个，第一个还原原链表，而第二个就是我们要求的复制链表。因为现在链表是旧新更替，只要把每隔两个结点分别相连，对链表进行分割即可
这个方法总共进行三次线性扫描，所以时间复杂度是O(n)。而这里并不需要额外空间，所以空间复杂度是O(1)。比起上面的方法，这里多做一次线性扫描，但是不需要额外空间，还是比较值的
上面介绍了两种方法来解决这个问题，第二种方法利用了原来的链表省去了额外空间，虽然多进行一次扫描，不过对时间复杂度量级没有影响，还是对算法有提高的
这个题目算是比较有难度的链表题目，既有基本操作，也需要一些算法思想

方法一：
用一个hashmap，把新旧node一一对应存起来，HashMap的key存原始pointer，value存新的pointer
第一遍只copy node，先不copy random的值，只copy数值建立好新的链表。并把新旧pointer存在HashMap中
第二遍从表中查，遍历旧表，复制random的值，因为第一遍已经把链表复制好了并且也存在HashMap里了，所以只需从HashMap中，把当前旧的node.random作为key值，得到新的value的值，并把其赋给新node.random就好
public RandomListNode copyRandomList(RandomListNode head) {
    if(head == null)
        return head;
    HashMap<RandomListNode,RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
    RandomListNode newHead = new RandomListNode(head.label);
    map.put(head,newHead);
    RandomListNode pre = newHead;
    RandomListNode node = head.next;
    while(node!=null)
    {
        RandomListNode newNode = new RandomListNode(node.label);
        map.put(node,newNode);
        pre.next = newNode;
        pre = newNode;
        node = node.next;
    }
    node = head;
    RandomListNode copyNode = newHead;
    while(node!=null)
    {
        copyNode.random = map.get(node.random);
        copyNode = copyNode.next;
        node = node.next;
    }
    return newHead;
}

方法二：
不使用额外空间，把每一个新建出来的节点都接到对应的旧节点的后面。即list长度*2。链接完了之后，再把新list分离出来
这种方法不使用HashMap来做，使空间复杂度降为O(1)，不过需要3次遍历list，时间复杂度为O(3n)=O(n)
第一遍，对每个node进行复制，并插入其原始node的后面，新旧交替，变成重复链表。如：原始：1->2->3->null，复制后：1->1->2->2->3->3->null
第二遍，遍历每个旧node，把旧node的random的复制给新node的random，因为链表已经是新旧交替的。所以复制方法为：node.next.random = node.random.next
前面是说旧node的next的random，就是新node的random，后面是旧node的random的next，正好是新node，是从旧random复制来的
第三遍，则是把新旧两个表拆开，返回新的表即可。
public RandomListNode copyRandomList(RandomListNode head) {
    if(head == null)
        return head;
    RandomListNode node = head;
    while(node!=null)
    {
        RandomListNode newNode = new RandomListNode(node.label);
        newNode.next = node.next;
        node.next = newNode;
        node = newNode.next;
    }
    node = head;
    while(node!=null)
    {
        if(node.random != null)
            node.next.random = node.random.next;
        node = node.next.next;
    }
    RandomListNode newHead = head.next;
    node = head;
    while(node != null)
    {
        RandomListNode newNode = node.next;
        node.next = newNode.next;
        if(newNode.next!=null)
            newNode.next = newNode.next.next;
        node = node.next;
    }
    return newHead;
}

//No HashMap version
public class Solution {
    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.random = head.random;
            newNode.next = head.next;
            head.next = newNode;
            head = head.next.next;
        }
    }

    private void copyRandom(RandomListNode head) {
        while (head != null) {
            if (head.next.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }

    private RandomListNode splitList(RandomListNode head) {
        RandomListNode newHead = head.next;
        while (head != null) {
            RandomListNode temp = head.next;
            head.next = temp.next;
            head = head.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }
        return newHead;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }
}

Reference:
http://www.ninechapter.com/solutions/copy-list-with-random-pointer/
http://blog.csdn.net/linhuanmars/article/details/22463599
https://googleentrance.wordpress.com/2014/10/06/copy-list-with-random-pointer/
https://yusun2015.wordpress.com/2015/01/14/copy-list-with-random-pointer/
http://www.cnblogs.com/springfor/p/3864457.html
*/
/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(List<ListNode> lists) {
      if(lists.size()==0) return null;
      ListNode head=new ListNode(0);
      ListNode pre=head;
       PriorityQueue<ListNode> q=new PriorityQueue<ListNode>(
           new Comparator<ListNode>(){
               public int compare(ListNode n1,ListNode n2){
                   if(n1.val>n2.val) return 1;
                   if(n1.val==n2.val) return 0;
                   return -1;
               }
           }
        );
       for(ListNode node:lists){
           if(node!=null) q.add(node);
       }
       while(!q.isEmpty()){
           ListNode temp=q.remove();
           pre.next=temp;
           pre=temp;
           if(temp.next!=null) q.add(temp.next);
       }
       return head.next;
    }
}

/*
Analysis:
- use a heap and put all the nodes in the heap;
- insert the node on the top of the head to the list and put the node.next into the heap;
- If we have total n nodes and k sorted list, then the complexity if O(long(k)).

就是merge sort那个n-way merge，轮流出牌。这个题的重点是要用heap，这样每次新加一个元素的时侯能很快的找到新的min
还有，这是第一次真的implement priority queue，需要自己写comparator（小于号说明是MinHeap）

这道题目在分布式系统中非常常见，来自不同client的sorted list要在central server上面merge起来
这个题目一般有两种做法，第一种做法比较容易想到，就是有点类似于MergeSort的思路,就是分治法，是一个比较经典的O(nlogn)的排序算法
思路是先分成两个子任务，然后递归求子任务，最后回溯回来。这个题目也是这样，先把k个list分成两半，然后继续划分，知道剩下两个list就合并起来，合并时会用到Merge Two Sorted Lists这道题
public ListNode mergeKLists(ArrayList<ListNode> lists) {
    if(lists==null || lists.size()==0)
        return null;
    return helper(lists,0,lists.size()-1);
}
private ListNode helper(ArrayList<ListNode> lists, int l, int r)
{
    if(l<r)
    {
        int m = (l+r)/2;
        return merge(helper(lists,l,m),helper(lists,m+1,r));
    }
    return lists.get(l);
}
private ListNode merge(ListNode l1, ListNode l2)
{ 
    ListNode dummy = new ListNode(0);
    dummy.next = l1;
    ListNode cur = dummy;
    while(l1!=null && l2!=null)
    {
        if(l1.val<l2.val)
        {
            l1 = l1.next;
        }
        else
        {
            ListNode next = l2.next;
            cur.next = l2;
            l2.next = l1;
            l2 = next;
        }
        cur = cur.next;
    }
    if(l2!=null)
        cur.next = l2;
    return dummy.next;
}
我们来分析一下上述算法的时间复杂度。假设总共有k个list，每个list的最大长度是n，那么运行时间满足递推式T(k) = 2T(k/2)+O(n*k)
根据主定理(http://en.wikipedia.org/wiki/Master_theorem)，可以算出算法的总复杂度是O(nklogk)。空间复杂度的话是递归栈的大小O(logk)
接下来我们来看第二种方法。这种方法用到了堆的数据结构，思路比较难想到，但是其实原理比较简单。维护一个大小为k的堆，每次取堆顶的最小元素放到结果中，然后读取该元素的下一个元素放入堆中，重新维护好
因为每个链表是有序的，每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中
这个算法每个元素要读取一次，即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，所以总时间复杂度是O(nklogk)。空间复杂度是堆的大小，即为O(k)
public ListNode mergeKLists(ArrayList<ListNode> lists) {
    PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10,new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1, ListNode n2)
            {
                return n1.val-n2.val;
            }
        });
    for(int i=0;i<lists.size();i++)
    {
        ListNode node = lists.get(i); 
        if(node!=null)
        {
            heap.offer(node);
        }
    }
    ListNode head = null;
    ListNode pre = head;
    while(heap.size()>0)
    {
        ListNode cur = heap.poll();
        if(head == null)
        {
            head = cur;
            pre = head;
        }
        else
        {
            pre.next = cur;
        }
        pre = cur;
        if(cur.next!=null)
            heap.offer(cur.next);
    }
    return head;
}

Reference:
https://leetcodenotes.wordpress.com/2013/08/01/merge-k-sorted-lists/
http://www.ninechapter.com/solutions/merge-k-sorted-lists/
http://blog.csdn.net/linhuanmars/article/details/19899259
https://yusun2015.wordpress.com/2015/01/16/merge-k-sorted-lists/
*/
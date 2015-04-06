/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeRightSideView { // Divide and conquer: solving the right tree side view and left tree side view ,and then merge the results
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root==null){
            return result;
        }
        result.add(root.val);
        List<Integer> rightTree = rightSideView(root.right);
        List<Integer> leftTree = rightSideView(root.left);
        for(int i=0; i<Math.max(leftTree.size(), rightTree.size()); i++){
            if(i >= rightTree.size()) {
            	result.add(leftTree.get(i));
            } else {
            	result.add(rightTree.get(i));
            }          
        }    
        return result;
    }
}

/*
// level order traversal solution
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {     // reverse level traversal
        List<Integer> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        if (root == null) return result;
        
        queue.offer(root);
        while (queue.size() != 0) {
            int size = queue.size(); 	// level number
            for (int i=0; i<size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) result.add(cur.val);
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left != null) queue.offer(cur.left);
            }
        }
        return result;
    }
}

Reference:
https://leetcode.com/discuss/30445/java-solution-using-divide-and-conquer
https://leetcode.com/discuss/30464/reverse-level-order-traversal-java
https://leetcode.com/discuss/30451/share-java-iterative-solution-based-level-order-traversal
https://leetcode.com/discuss/30433/java-solution-accepted
https://leetcode.com/discuss/30500/o-n-java-solution-using-level-order-traversal
*/
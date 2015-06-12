/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
Ensure that numbers within the set are sorted in ascending order.

Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        return combinationSum3Int(k, n, 9, new ArrayList<>(), new LinkedList<Integer>());
    }

    private List<List<Integer>> combinationSum3Int(int k, int n, int max, List<List<Integer>> result, LinkedList<Integer> list){
        if(k <= 0 || n <= 0){
            if(k == 0 && n == 0)
                result.add(new ArrayList<>(list));
            return result;
        }
        for(int i = max; i >= 1; i--){
            list.addFirst(i);
            combinationSum3Int(k - 1, n - i, i - 1, result, list);
            list.removeFirst();
        }
        return result;
    }
}

/*
Reference:
https://leetcode.com/discuss/37125/java-ac-solution-seeking-further-optimization
https://leetcode.com/discuss/37335/share-my-java-solution-which-is-easy-to-understand
https://leetcode.com/discuss/37387/share-my-simple-java-solution-with-backtracking
https://leetcode.com/discuss/37667/java-backtracking-solution-based-combination-understand
https://leetcode.com/discuss/37809/accepted-recursive-java-solution-easy-to-understand
https://leetcode.com/discuss/37036/my-java-solution-basic-recursion-with-backtracking
https://leetcode.com/discuss/37070/200-ms-java-ac-recursive-solution
*/
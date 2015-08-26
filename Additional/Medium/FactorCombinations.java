/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4

Write a function that takes an integer n and return all possible combinations of its factors.

Note:
Each combination’s factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].

You may assume that n is always positive.

Factors should be greater than 1 and less than n.

Examples:
input: 1
output:
[]

input: 37
output:
[]

input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]

input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/

public class FactorCombinations {
   public List<List<Integer>> getFactors(int n) {
        Set<List<Integer>> result = new HashSet<>();
        int dist = (int) Math.sqrt(n);
        for (int i = 2; i <= dist; i++) {
            if (n % i == 0) {
                List<List<Integer>> tmp = helper(n / i);
                for (List<Integer> l : tmp) {
                    l.add(i);
                    Collections.sort(l);
                    result.add(l);
                }
            }
        }
        return new ArrayList<>(result);
    }

    public List<List<Integer>> helper(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        t.add(n);
        result.add(t);
        int dist = (int) Math.sqrt(n);
        for (int i = 2; i <= dist; i++) {
            if (n % i == 0) {
                List<List<Integer>> tmp = helper(n / i);
                for (List<Integer> l : tmp) {
                    l.add(i);
                    result.add(l);
                }
            }
        }
        return result;
    }
}

/*
http://sbzhouhao.net/LeetCode/LeetCode-Factor-Combinations.html
*/
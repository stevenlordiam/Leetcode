/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

public class StrobogrammaticNumberIII {
    private char[] validNumbers = new char[]{'0', '1', '6', '8', '9'};
    private char[] singleable = new char[]{'0', '1', '8'};

    public int strobogrammaticInRange(String low, String high) {
        assert low != null && high != null;
        int ll = low.length();
        int hl = high.length();
        int result = 0;
        if(ll > hl || (ll == hl&&low.compareTo(high) > 0)) {
            return 0;
        }
        List<String> list = findStrobogrammatic(ll);
        if (ll == hl) {
            for (String s : list) {
                if (s.compareTo(low) >= 0 && s.compareTo(high) <= 0) {
                    result++;
                }
                if (s.compareTo(high) > 0) {
                    break;
                }
            }
        } else {
            for (int i = list.size() - 1; i >= 0; i--) {
                String s = list.get(i);
                if (s.compareTo(low) >= 0) {
                    result++;
                }
                if (s.compareTo(low) < 0) {
                    break;
                }
            }
            list = findStrobogrammatic(hl);
            for (String s : list) {
                if (s.compareTo(high) <= 0) {
                    result++;
                }
                if (s.compareTo(high) > 0) {
                    break;
                }
            }
            for (int i = ll + 1; i < hl; i++) {
                result += findStrobogrammatic(i).size();
            }
        }
        return result;
    }

    public List<String> findStrobogrammatic(int n) {
        assert n > 0;
        List<String> result = new ArrayList<>();
        if (n == 1) {
            for (char c : singleable) {
                result.add(String.valueOf(c));
            }
            return result;
        }
        if (n % 2 == 0) {
            helper(n, new StringBuilder(), result);
        } else {
            helper(n - 1, new StringBuilder(), result);
            List<String> tmp = new ArrayList<>();
            for (String s : result) {
                for (char c : singleable) {
                    tmp.add(new StringBuilder(s).insert(s.length() / 2, c).toString());
                }
            }
            result = tmp;
        }
        return result;
    }

    private void helper(int n, StringBuilder sb, List<String> result) {
        if (sb.length() > n) return;
        if (sb.length() == n) {
            if (sb.length() > 0 && sb.charAt(0) != '0') {
                result.add(sb.toString());
            }
            return;
        }
        for (char c : validNumbers) {
            StringBuilder tmp = new StringBuilder(sb);
            String s = "" + c + findMatch(c);
            tmp.insert(tmp.length() / 2, s);
            helper(n, tmp, result);
        }
    }

    private char findMatch(char c) {
        switch (c) {
            case '1':
                return '1';
            case '6':
                return '9';
            case '9':
                return '6';
            case '8':
                return '8';
            case '0':
                return '0';
            default:
                return 0;
        }
    }
}

/*
Reference:
http://sbzhouhao.net/LeetCode/LeetCode-Strobogrammatic-Number-III.html
*/
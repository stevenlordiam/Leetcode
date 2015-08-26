/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].
*/

public class StrobogrammaticNumberII {
    private char[] validNumbers = new char[]{'0', '1', '6', '8', '9'};
    private char[] singleable = new char[]{'0', '1', '8'};

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
            for(String s : result) {
                for(char c : singleable) {
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
http://sbzhouhao.net/LeetCode/LeetCode-Strobogrammatic-Number-II.html
*/
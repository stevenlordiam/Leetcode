/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<Character,Integer> table = new HashMap<Character,Integer>();
        table.put('A',0);
        table.put('C',1);
        table.put('G',2);
        table.put('T',3);
        List<String> list = new LinkedList<>();
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<s.length()-9;i++) {
            String temp = s.substring(i,i+10);
            int v = str2int(temp,table);
            if(map.containsKey(v)) {
                if(map.get(v)==1) {  // meet repeated substrings
                    list.add(temp); // if meet more than once, put it in the result list
                    // no need to check for substrings repeated more than 2 times, because the substring is already in the result list
                    // so we can just use map.put(v,2) to mark the substrings that repeat more than once
                    map.put(v,2);   
                } 
            }
            else map.put(v,1);     // first time to meet substrings
        }
        return list;
    }
    public int str2int(String s,HashMap<Character,Integer> table) {  // String is too long, use this hash fuction to convert string to integers
        int res=0;
        for(int i=0;i<s.length();i++) {
            res=table.get(s.charAt(i))&3|res<<2;    // hash
        }
        return res;
    }
}

/*
Brute force is too time consuming, so we can use a hashmap to store them and use a hash function to convert the string to integers

Reference:
https://yusun2015.wordpress.com/2015/02/07/repeated-dna-sequences/
http://www.cnblogs.com/hzhesi/p/4285793.html
http://blog.csdn.net/coderhuhy/article/details/43647731
http://blog.csdn.net/u011108221/article/details/43581889
https://changhaz.wordpress.com/2015/02/05/leetcode-repeated-dna-sequences/
http://www.jkeabc.com/p/450909.html
*/
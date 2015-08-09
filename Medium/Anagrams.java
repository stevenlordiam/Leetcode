/*
Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.
*/

public class Anagrams {
    public List<String> anagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<String,List<String>>();
        for(int i=0; i<strs.length; i++) {
            char[] c=strs[i].toCharArray();			// first turn string to char array
            Arrays.sort(c);							// second sort the char array
            String s = new String(c);
            if(map.keySet().contains(s)) {          // third store them in a hashmap
                map.get(s).add(strs[i]);	
            } else{
                List<String> list=new LinkedList<>();
                list.add(strs[i]);
                map.put(s,list);
            }
        }
        List<String> res=new LinkedList<>();
        for(List<String> list:map.values()){
           if(list.size()>1) res.addAll(list);		// return all groups of strings that are anagrams
        }
        return res;
    }
}

/*
注意熟悉hashmap和list的常用方法操作！！！

char[] c = string.toCharArray() - return a newly allocated character array

For strs[i], turn it into char array and sort it. 
If char array already in the hash map add it the list which corresponds to the array.

这是一道很经典的面试题了，在cc150里面也有，就是把一个数组按照易位构词分类。易位构词其实也很好理解，就是两个单词所包含的字符和数量都是一样的，只是顺序不同
这个题简单的版本是判断两个单词是不是anagram，一般来说有两种方法。第一种方法是用hashmap，key是字符，value是出现的次数，如果两个单词构成的hashmap相同，那么就是anagram。
实现起来就是用一个构建hashmap，然后另一个在前面的hashmap中逐个去除，最后如果hashmap为空，即返回true。这个方法时间复杂度是O(m+n)，m，n分别是两个单词的长度。而空间复杂度是O(字符集的大小)。
第二种方法是将两个单词排序，如果排序之后结果相同，就说明两个单词是anagram。这种方法的时间复杂度取决于排序算法，一般排序算法是O(nlogn)，如果字符集够小，也可以用线性的排序算法。
不过总体来说，如果是判断两个单词的，第一种方法要直接简单一些。接下来我们看看这道题，是在很多字符串里面按照anagram分类，如果用hashmap的方法，然后两两匹配，在分组会比较麻烦。
而如果用排序的方法则有一个很大的优势，就是排序后的字符串可以作为一个key，也就是某一个class的id，如此只要对每一个字符串排序，然后建立一个hashmap，key是排序后的串，而value是所有属于这个key类的字符串，这样就可以比较简单的进行分类。
假设我们有n个字符串，字符串最大长度是k，那么该算法的时间复杂度是O(nklogk)，其中O(klogk)是对每一个字符串排序（如果用线性算法也可以提高）。空间复杂度则是O(nk)，即hashmap的大小

Reference:
https://yusun2015.wordpress.com/2015/01/15/anagrams/
http://blog.csdn.net/linhuanmars/article/details/21664747
http://www.ninechapter.com/solutions/anagrams/
*/
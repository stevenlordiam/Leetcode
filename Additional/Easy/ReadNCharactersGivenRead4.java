/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function will only be called once for each test case.
*/

public class ReadNCharactersGivenRead4 extends Reader4 {
	public int read(char[] buf, int n) {
    	char[] buffer = new char[4];
    	int readBytes = 0;
    	boolean eof = false;
    	while (!eof && readBytes < n) {
        	int sz = read4(buffer);
        	if (sz < 4) eof = true;
        	int bytes = Math.min(n - readBytes, sz); 
        	System.arraycopy(buffer /* src */, 0 /* srcPos */, buf /* dest */, readBytes /* destPos */, bytes /* length */);
    		readBytes += bytes;
	}
    	return readBytes;
	}
}

/*
[分析]
每次只能读4个字符，但是n不一定是n的整数倍。直接一个while循环，然后在里面判断读出来的buff长度是不是小于4。另外，文件的字符数可能少于n。所以需要有eof变量在循环体中做判断

[注意事项]
最后一次读出来的buff长度有可能等于4，所以在while循环的判断条件中，需要加上 readBytes < n

Reference:
http://www.cnblogs.com/EdwardLiu/p/4233533.html
http://www.danielbit.com/blog/puzzle/leetcode/leetcode-read-n-characters-given-read4
http://shanjiaxin.blogspot.com/2015/01/read-n-characters-given-read4-leetcode.html
https://leetcode.com/discuss/28554/accepted-java-solution
https://leetcode.com/discuss/19573/accepted-clean-java-solution
*/
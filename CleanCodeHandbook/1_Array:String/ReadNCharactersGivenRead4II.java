/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function may be called multiple times.
*/

public class ReadNCharactersGivenRead4II extends Reader4 {
   private char [] buffer = new char[4];
   int offset = 0, bufsize = 0; 

   public int read(char[] buf, int n) {
      int readBytes = 0;
      boolean eof = false;
      while (!eof && readBytes < n) {
          int sz = (bufsize>0)? bufsize : read4(buffer);
          if (bufsie==0 && sz < 4) eof = true;
          int bytes = Math.min(n - readBytes, sz); 
          System.arraycopy(buffer /* src */, offset /* srcPos */, buf /* dest */, readBytes /* destPos */, bytes /* length */);
          offset = (offset + bytes) % 4;
          bufsize = sz - bytes;
          readBytes += bytes;
    }
      return readBytes;
   }
}

/*
Solution:
Use two private variable to keep track of which portion of the buffer is leftover after one read operation
Use buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data received in previous calls
In the while loop, if buffPtr reaches current buffCnt, it will be set as zero to be ready to read new data

public class Solution {
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0;
        }
        return ptr;
    }
}

[分析]
跟之前的一道题目比，这一题要复杂不少。主要是因为read函数可以调用多次以后，有可能文件中的部分内容被读出来，但是暂时没有用到。因此需要额外的空间来缓存读出来的字符

[注意事项]
对于几个变量的解读：
－ buffer 存储从文件中读出来的字符
－ offset 上一次读取之后buffer中剩下字符的偏移量
－ bufsize buffer中剩下字符的个数

Reference:
http://www.danielbit.com/blog/puzzle/leetcode/leetcode-read-n-characters-given-read4-ii
http://shanjiaxin.blogspot.com/2015/01/read-n-characters-given-read4-ii-call.html
https://changhaz.wordpress.com/2014/11/24/leetcode-read-n-characters-given-read4-ii-call-multiple-times/
http://leetcode.tgic.me/read-n-characters-given-read4-ii-call-multiple-times/index.html
https://leetcode.com/discuss/21219/a-simple-java-code
https://leetcode.com/discuss/22982/java-solution-with-clean-comments
https://leetcode.com/discuss/19581/clean-accepted-java-solution
*/
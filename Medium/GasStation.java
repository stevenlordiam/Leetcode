/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
            return -1;
        }

        int sum = 0;
        int total = 0;
        int index = -1;

        for(int i = 0; i<gas.length; i++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if(sum < 0) {
                index = i;
                sum = 0;
            }
        }
        return total < 0 ? -1 : index + 1;
        // index should be updated here for cases ([5], [4]);
        // total < 0 is for case [2], [2]
    }
}

/*
Greedy

这个题要用反证法来理解。算法：
- 从i开始，j是当前station的指针，sum += gas[j] – cost[j] （从j站加了油，再算上从i开始走到j剩的油，走到j+1站还能剩下多少油）
- 如果sum < 0，说明从i开始是不行的。那能不能从i..j中间的某个位置开始呢？假设能从k (i <=k<=j)走，那么i..j < 0，若k..j >=0，说明i..k – 1更是<0，那从k处就早该断开了，根本轮不到j。
- 所以一旦sum<0，i就赋成j + 1，sum归零。
- 最后total表示能不能走一圈。
- 这个题算法简单。注意千万不能出现while (i < 0) { i++, A[i]}这种先++然后取值的情况，必须越界
public int canCompleteCircuit(int[] gas, int[] cost) {
    int i = 0, j = 0;
    int sum = 0;
    int total = 0;
    while (j < gas.length) {
        int diff = gas[j] - cost[j];
        if (sum + diff < 0) {
            i = j + 1;
            sum = 0;
        } else {
            sum += diff;
        }
        j++;
        total += diff;
    }
    return total >= 0 ? i : -1;
}

这是一道具体问题的题目，brute force的方法比较容易想到，就是从每一个站开始，一直走一圈，累加过程中的净余的油量，看它是不是有出现负的，如果有则失败，从下一个站开始重新再走一圈
如果没有负的出现，则这个站可以作为起始点，成功。可以看出每次需要扫描一圈，对每个站都要做一次扫描，所以时间复杂度是O(n^2)
接下来说说如何提高这个算法。方法主要思想是把这个圈划分成一个个的负序列，以及一个正序列（如果存在的话）。从任意一个站出发，我们可以累加油的净余量，如果出现负的，序列结束，开启一个新的，并且证明旧的这个序列的起点不能作为起点，因为会出现负油量，不能继续前进
下面我们证明: 不仅这个负序列的起点不能作为起点，负序列中的任意一点都不能作为起点
证明：假设我们取定负序列中的一个站作为起点，因为一个序列一旦遇到负的净余量就会结束并且开启新的，那么说明在这个起点前的累加结果必然是正数（否则会结束这个序列，则前面不会是这个序列的一部分）
如此我们从当前序列出发必然会使走到序列终点时负的油量更大，本来已经是负的，所以不能去负序列的任意一个结点作为起点
根据上面的划分方式，我们会把圈分成一段段的序列，而且其中最多只有一个正序列，那就是绕一圈回到起点的那个序列（当然也有可能整个圈是一个正序列，就是油量一直为正，那么我们测的开始点就可以作为起点了）
接下来我们证明: 如果将全部油量累计起来，总量为正，那么一定能找到一个起点，使得可以走完一圈，也就是一定有解
证明：按照我们之前的划分，整个圈会被划分成有累积量为s1, s2, ..., sk 的负序列，以及一个正序列拥有油量sp（这里正序列一定存在因为全部累加和是正的，如果全是负序列那么结果不会是正的）
而且我们知道s1+s2+...+sk+sp>0，也就是说sp>-s1-s2-...-sk。换句话说，如果我们从sp对应的站的起点出发，在sp对应的序列会一直是正的，并且，当他走到负序列时，因为sp的正油量大于所有负油量的总和，所以累加油量会一直正，完整整个圈的行驶
这证明了只要累加油量是正的，一定能找到一个起点来完成任务。根据上面的两个命题，我们可以来实现代码，需要维护两个量，一个是总的累积油量total，另一个是当前序列的累计油量sum，如果出现负的，则切换起点，并且将sum置0
总共是需要扫描所有站一次，时间复杂度是O(n)。而只需要两个额外变量，空间复杂度是O(1)

Reference:
https://leetcodenotes.wordpress.com/2013/11/21/leetcode-gas-station-%E8%BD%AC%E5%9C%88%E7%9A%84%E5%8A%A0%E6%B2%B9%E7%AB%99%E7%9C%8B%E8%83%BD%E4%B8%8D%E8%83%BD%E8%B5%B0%E4%B8%80%E5%9C%88/
http://www.ninechapter.com/solutions/gas-station/
http://www.cnblogs.com/yuzhangcmu/p/4179228.html
http://blog.csdn.net/linhuanmars/article/details/22706553
https://yusun2015.wordpress.com/2015/01/12/gas-station/
*/
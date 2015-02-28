/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/

public class SortColors {
    public void sortColors(int[] a) {
        if(a == null || a.length <= 1)
            return;
        
        int pl = 0;					// front pointer
        int pr = a.length - 1;		// rear pointer
        int i = 0;					// counter
        while(i <= pr){				// 用两个指针夹逼的方法排序
            if(a[i] == 0){		
                swap(a, pl, i);
                pl++;
                i++;
            }else if(a[i] == 1){	// 1保持不变，遇到0换到前面，遇到2换到后面
                i++;
            }else{
                swap(a, pr, i);
                pr--;
            }
        }
    }
    
    private void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

/*
Analysis:
Use start as the boundary of 1 and 0 and end as the boundary of 2 and 1. 
Shift k, if A[k] is not 1, swap it with the element at corresponding boundary.

这道题也是数组操作的题目，其实就是要将数组排序，只是知道数组中只有三个元素0,1,2。
熟悉计数排序的朋友可能很快就发现这其实就是使用计数排序(http://en.wikipedia.org/wiki/Counting_sort)，
元素空间只需要三个元素即可。总共进行了三次扫描，其实最后一次只是把结果数组复制到原数组而已，如果不需要in-place的结果只需要两次扫描。
其实就算返回元素组也可以是两次扫描，这需要用到元素只有0,1,2的本质。我们知道helper[i]中是包含着0,1,2的元素数量，
我们只需要按照helper[0,1,2]的数量依次赋值过来即可（每层循环把helper[i]--，如果helper[i]到0就i++就可以了），
这种方法的时间复杂度是O(2*n)，空间是O(k)，k是颜色的数量，这里是3
上述方法需要两次扫描，我们考虑怎么用一次扫描来解决。其实还是利用了颜色是三种这一点，道理其实也简单，
就是搞两个指针，一个指在当前0的最后一个下标，另一个是指在当前1的最后一个下标（2不需要指针因为剩下的都是2了）。
进行一次扫描，如果遇到0就两个指针都前进一步并进行赋值，如果遇到1就后一个指针前进一步并赋值。
上述方法时间复杂度还是O(n)，只是只需要一次扫描，空间上是O(1)（如果颜色种类是已知的话）

Reference:
http://www.ninechapter.com/solutions/sort-colors/
https://yusun2015.wordpress.com/2015/01/07/sort-colors/
http://blog.csdn.net/linhuanmars/article/details/24286349
*/
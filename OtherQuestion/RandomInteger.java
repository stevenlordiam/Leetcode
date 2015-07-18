/*
Problem: given function of rand3() which return uniformly random int number of [1,2,3], write a random function rand4(), which return uniformly random integer of [1,2,3,4]

How to test it? As follow up, I was asked about how to test rand4() function, to verify it's truly random. My thought is to run rand4() for 1K times, and collect the frequency of [1,2,3,4], and then run rand4() 10K times and collect frequency, then run rand4() 100K time ... to see if the frequency of each number between [1,2,3,4] converge to 25%.

There is a scenario like 1,2,3,4,1,2,3,4,1,2,3,4 ... ... like round robin generation. it would pass the convergence test I mentioned above, but it's not uniformly random. So does any pattern that shows a deterministic pattern, like 1,2,3,4,4,3,2,1 ...

https://leetcode.com/discuss/44912/generate-uniform-random-integer
http://articles.leetcode.com/2010/11/rejection-sampling.html
*/

public class RandomInteger {
	public int rand10Imp() {
		int a, b, idx;
		while (true) {
		a = rand7();
		b = rand7();
		idx = b + (a-1)*7;
		if (idx <= 40)
		  return 1 + (idx-1)%10;
		a = idx-40;
		b = rand7();
		// get uniform dist from 1 - 63
		idx = b + (a-1)*7;
		if (idx <= 60)
		  return 1 + (idx-1)%10;
		a = idx-60;
		b = rand7();
		// get uniform dist from 1-21
		idx = b + (a-1)*7;
		if (idx <= 20)
		  return 1 + (idx-1)%10;
		}
	}
}
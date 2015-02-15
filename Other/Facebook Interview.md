1. given an array of size N which every nightumber betwn 1 & N, determine dups

-1: ask questions: any detail need? restricts?
input [1 2 3]-> true
more questions:
can I destroy/modify array?

-2: get a base sol:what's best way to solve?
discuss w/ interviewer

1: a[j]==a[j-1] -> o(N^2)
2: a.sort(); first o(n*logn)
3:  boolean[] seen = new boolean[a.length]
	array.fill(seen, false) 
	int num = a[i]-1;
	for(){
	if(seen[num]) return true;
	seen[num] = true;
	}
	return false;
	o(n)
4. sum of 1 to N, then compare the sum to (1+N)/2.(THIS IS GOOD!)






2. an atm can only withdraw $1, $5, 20, 50. return the number of unique ways that a $ amount of X can be tendered.
 input:4, output:1
 input:6, output:3

Answer:
Dynamic Perogramming



3. given the root of a binary tree, return a new data syricture which is an collection of linked list to a shared root

    5 				[3    1   2]
  7   2 	- >   	   7
3   1 					   5     

















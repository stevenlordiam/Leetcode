/*
Determine whether an integer is a palindrome. Do this without extra space.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/
public class PalindromeNumber  {
    public boolean isPalindrome(int x) {
      if(x<0) return false;     // discuss with interviewer if negative integers are palindromes
      int z=x;
      long y=0;
      while(z!=0){		// reverse an integer
          y=10*y+z%10;
          z=z/10;
      }
      return x==y;
    }
}

/*
Solution 1:
use long to avoid overflow
% - get the last digit
/ - get the first digit

Ways to reverse an int:

while(x!=0){		// y is the reversed int
	y=10*y+x%10;
	x=x/10;
}

*/
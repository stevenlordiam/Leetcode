/*
Description:
Count the number of prime numbers less than a non-negative number, n

References:
How Many Primes Are There? (https://primes.utm.edu/howmany.html)
Sieve of Eratosthenes (http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes)
*/

public class CountPrimes {
    public int countPrimes(int n) {
        int count=0;
        boolean[] nums = new boolean[n];    // default is false
        for(int i=2; i<nums.length; i++){
            if(!nums[i]){
                count++;
                for(int j=2*i; j<nums.length; j=j+i){
                        nums[j] = true;
                }
            }
        }
        return count;
    }
}

/*
This solution uses the Sieve of Eratosthenes (http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) :
- if n<3 then the number of primes is 0 (0 and 1 are not primes)
- create an array of boolean of length n
- initialize the count of primes to n-2 (initially we consider all primes except for 0 and 1 that we already said are not primes)
- starting from int i=2 take this number as the smallest prime
- mark all multiples of i smaller than n as not prime. Every time an element is marked as true (that indicates is not a prime), decrement the prime count (count--).
- take the next i in the sequence that is not marked and repeat the process from point 5.
- at the end we will have in the count variable the number of primes (the number of elements that are not marked in the inner loop).

Reference:
https://leetcode.com/discuss/33725/o-n-log-n-java-solution
https://leetcode.com/discuss/33694/my-easy-one-round-c-code
https://leetcode.com/discuss/33600/java-eratosthenes-sieve-o-n-lg-lg-n-solution-with-o-n-space
https://leetcode.com/discuss/33531/simple-solution
https://leetcode.com/discuss/33528/accepted-java-code-with-bitset
*/
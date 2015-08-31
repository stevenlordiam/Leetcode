/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Hint:
Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
*/

public class IntegerToEnglishWords {
    public String[] digits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	public String[] teens = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	public String[] tens = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	public String[] bigs = {"", "Thousand", "Million", "Billion"};
	
	public String numberToWords(int number) {
		if (number == 0) {
			return "Zero";
		}
		
		if (number < 0) {
			return "Negative " + numberToWords(-1 * number);
		}
		int count = 0;
		String str = "";
		
		while (number > 0) {
			if (number % 1000 != 0) {
				str = numToString100(number % 1000) + bigs[count] + " " + str;
			}
			number /= 1000;
			count++;
		}
		
		return str.trim();
	}
	
	private String numToString100(int number) {	
		String str = "";
		
		/* Convert hundreds place */
		if (number >= 100) {
			str += digits[number / 100 - 1] + " Hundred ";
			number %= 100;
		}
		
		/* Convert tens place */
		if (number >= 11 && number <= 19) {
			return str + teens[number - 11] + " ";
		} else if (number == 10 || number >= 20) {
			str += tens[number / 10 - 1] + " ";
			number %= 10;
		}
		
		/* Convert ones place */
		if (number >= 1 && number <= 9) {
			str += digits[number - 1] + " ";
		}
		
		return str;
	}
}

/*
CC150 17-7

Reference:
https://leetcode.com/discuss/55273/my-java-solution
https://leetcode.com/discuss/55276/my-accepted-java-solution-248ms
https://leetcode.com/discuss/55349/if-you-know-how-to-read-numbers-you-can-make-it
http://www.cnblogs.com/jcliBlogger/p/4774090.html
*/
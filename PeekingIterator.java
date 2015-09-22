/*
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:
- Think of "looking ahead". You want to cache the next element
- Is one variable sufficient? Why or why not?
- Test your design with call order of peek() before next() vs next() before peek()
- For a clean implementation, check out Google's guava library source code (https://github.com/google/guava/blob/703ef758b8621cfbab16814f01ddcc5324bdea33/guava-gwt/src-super/com/google/common/collect/super/com/google/common/collect/Iterators.java#L1125)

Follow up: How would you extend your design to be generic and work with all types, not just integer?
*/

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    Integer n = null;
    private Iterator<Integer> iterator = null;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if (n == null && iterator.hasNext()){
            n = iterator.next();
        }
        return n;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (n!=null){
            int temp = n;
            n = null;
            return temp;
        }
        return iterator.next();
	}

	@Override
	public boolean hasNext() {
	    if (n!=null){
            return true;
        }
        return iterator.hasNext();
	}
}

/*
Reference:
http://www.fgdsb.com/2015/01/25/peek-iterator/
http://www.1point3acres.com/bbs/thread-42043-1-1.html
https://leetcode.com/discuss/59327/my-java-solution
https://leetcode.com/discuss/59381/solution-which-could-types-work-when-this-element-collection
https://leetcode.com/discuss/59368/concise-java-solution
http://www.cnblogs.com/grandyang/p/4825068.html
http://yuanhsh.iteye.com/blog/2228680
*/
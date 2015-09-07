/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.

Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
*/

class ImplementStackUsingQueues {
    
    private Queue<Integer> queue = new LinkedList<>();
    
    // Push element x onto stack.
    public void push(int x) { 		// make the queue reverse order to implement a stack
        queue.add(x);
        for (int i=0; i<queue.size()-1; i++) {  // 除了第一个element之外，每加一个element都将queue poll然后再add 相当于把queue reverse order
            queue.add(queue.poll());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        queue.poll(); // remove and return the head of the queue
    }

    // Get the top element.
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}

/*
Reference:
https://leetcode.com/discuss/39799/two-possible-solutions
https://leetcode.com/discuss/39814/concise-1-queue-java-c-python
https://leetcode.com/discuss/40202/only-push-others-using-queue-combination-shared-solutions
*/
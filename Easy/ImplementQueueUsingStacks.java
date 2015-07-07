/*
Implement the following operations of a queue using stacks

push(x) -- Push element x to the back of queue
pop() -- Removes the element from in front of queue
peek() -- Get the front element
empty() -- Return whether the queue is empty

Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue)
*/

public class ImplementQueueUsingStacks {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    
    public void stack2ToStack1() {
		while (! stack2.empty()) {
			stack1.push(stack2.peek());
			stack2.pop();
		}
	}

    public ImplementQueueUsingStacks() {
       stack1 = new Stack<Integer>();
       stack2 = new Stack<Integer>();
    }

    public void push(int number) {
        stack2.push(number);
    }

    public int pop() {
        if (stack1.empty() == true) {
			this.stack2ToStack1();
		}
        return stack1.pop();
    }

    public int peek() {
        if (stack1.empty() == true) {
			this.stack2ToStack1();
		}
        return stack1.peek();
    }
    
    public boolean empty() {
        if(stack1.empty() == true && stack2.empty() == true) {
            return true;
        }
        return false;
    }
}
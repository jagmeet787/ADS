package ADS.DataStructures;

class Stack {
    int[] arr;
    int top, n;
    
    public Stack(int capacity) {
        this.n = capacity;
        arr = new int[n];
        this.top = 0;
    }
    
    public boolean isEmpty() {
        return top == 0;
    }
    
    public boolean isFull() {
        return top == n;
    }
    
    public int size() {
        return top;
    }
    
    public void push(int element) {
        if (isFull())
            throw new RuntimeException("Overflow: Stack is full.");
        arr[top++] = element;
    }
    
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("Underflow: Stack is empty.");
        return arr[--top];
    }
    
    public int peek() {
        return arr[top - 1];
    }
    
    @Override public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("[");
        for (int i = 0; i < top; i++) {
            out.append(" " + arr[i]);
        }
        out.append(" ]");
        return out.toString();
    }
}
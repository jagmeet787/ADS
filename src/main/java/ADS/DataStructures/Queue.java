// package ADS.DataStructures;

class Queue {
    int[] arr;
    int front, back, n;
    
    public Queue() {
        this(1000_000);
    }

    public Queue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity should be greater than 0.");
        this.n = capacity + 1;
        arr = new int[n];
        this.front = this.back = 0;
    }
    
    public boolean isEmpty() {
        return front == back;
    }
    
    public boolean isFull() {
        return (back + 1) % n == front;
    }
    
    public int size() {
        if (back < front) return back + n - front;
        return back - front;
    }
    
    public void enqueue(int element) {
        if (isFull())
            throw new RuntimeException("Overflow: Queue is full.");
        back = (++back) % n;
        arr[back] = element;
    }
    
    public int dequeue() {
        if (isEmpty())
            throw new RuntimeException("Underflow: Queue is empty.");
        front = (++front) % n;
        return arr[front];
    }
    
    public int peek() {
        return arr[(front + 1) % n];
    }
    
    @Override public String toString() {
        int i = front;
        StringBuilder out = new StringBuilder();
        out.append("[");
        while (i != back) {
            i = (i + 1) % n;
            out.append(" " + arr[i]);
        }
        out.append(" ]");
        return out.toString();
    }
}
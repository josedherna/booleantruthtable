class Node {
    public char data;
    public Node next;
    Node(char info) {
        this.data = info;
        this.next = null;
    }
}
public class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return (this.top == null);
    }

    public void push(char info) {
        if (isEmpty()) {
            Node temp = new Node(info);
            this.top = temp;
        }
        else {
            Node temp = new Node(info);
            temp.next = this.top;
            this.top = temp;
        }
    }

    public char pop() {
        if (!isEmpty()) {
            char removedData = this.peek();
            this.top = top.next;
            return removedData;
        }
        return 0;
    }

    public char peek() {
        if (!isEmpty()) {
            return top.data;
        }
        return 0;
    }
}

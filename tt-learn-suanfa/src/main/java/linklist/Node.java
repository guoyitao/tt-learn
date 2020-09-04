package linklist;

public class Node<T> {

    public Node(T element, Node<T> pre, Node<T> next) {
        this.element = element;
        this.pre = pre;
        this.next = next;
    }

    T element;
    Node<T> pre;
    Node<T> next;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(element + "回收");
    }
}

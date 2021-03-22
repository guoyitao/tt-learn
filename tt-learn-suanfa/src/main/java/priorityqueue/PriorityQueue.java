package priorityqueue;


public interface PriorityQueue<T> {
    void insert(T t);
    T getMax();
    T delMax();


}

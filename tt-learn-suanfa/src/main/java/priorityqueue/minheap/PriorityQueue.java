package priorityqueue.minheap;

import java.util.List;

public interface PriorityQueue<T> {
    void insert(T item);

    T remove();

    boolean isEmpty();

    List<T> sort();
}

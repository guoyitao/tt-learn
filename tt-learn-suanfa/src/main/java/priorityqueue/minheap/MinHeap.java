package priorityqueue.minheap;

import java.util.List;

public interface MinHeap<T> {
    void insert(T item);

    T remove();

    boolean isEmpty();

    void heapify();

    List<T> sort();
}

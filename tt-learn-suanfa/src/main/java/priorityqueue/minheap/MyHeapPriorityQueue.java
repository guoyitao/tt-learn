package priorityqueue.minheap;

import java.util.List;

public class MyHeapPriorityQueue <T extends Comparable<T> > implements PriorityQueue<T>{

    private MinHeap<T> minHeap;

    public MyHeapPriorityQueue(int capacity) {
        minHeap = new MyMinHeap<>(capacity);
    }

    @Override
    public void insert(T item) {
        minHeap.insert(item);
    }
    @Override
    public T remove() {
        return minHeap.remove();
    }

    @Override
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    @Override
    public List<T> sort() {
        return minHeap.sort();
    }
}

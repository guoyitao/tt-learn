package priorityqueue.minheap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyMinHeap<T extends Comparable<T> > implements MinHeap<T> {
    private T[] heap;
    private int size;


    public MyMinHeap(int capacity) {
        heap =  (T[]) new Comparable[capacity + 1];
        size = 0;
    }

    /**
     * add a item to the heap
     * @param item
     */
    @Override
    public void insert(T item) {
        heap[++size] = item;
        swim(size);
    }

    /**
     * remove data
     * @return data has been removed from the heap
     */
    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = heap[1];
        swap(1, size--);
        sink(1);
        heap[size + 1] = null;
        return min;
    }

    /**
     * @return is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Repeat the sink operation to organize the elements of an unordered array into an array that conforms to the nature of the heap
     */
    @Override
    public void heapify() {
        for (int i = size / 2; i >= 1; i--) {
            sink(i);
        }
    }

    /**
     * @return list has sorted
     */
    @Override
    public List<T> sort() {
        List<T> sortedList = new ArrayList<>();
        // Build a new heap to avoid affecting the original heap
        MyMinHeap<T> heapCopy = new MyMinHeap<>(size + 1);
        for (int i = 1; i <= size; i++) {
            heapCopy.insert(heap[i]);
        }
        heapCopy.heapify();

        // Remove the smallest element from the heap repeatedly until the heap is empty
        while (!heapCopy.isEmpty()) {
            sortedList.add(heapCopy.remove());
        }
        return sortedList;
    }

    /**
     * When a new element is inserted into the heap, a swim operation is required to float the element up to the correct position in order to preserve the nature of the heap
     * @param i
     */
    private void swim(int i) {
        while (i > 1 && greater(i / 2, i)) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    /**
     * Sink the i element to where it should be to satisfy the heap nature
     * @param i
     */
    private void sink(int i) {
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && greater(j, j + 1)) {
                j++;
            }
            if (!greater(i, j)) {
                break;
            }
            swap(i, j);
            i = j;
        }
    }

    /**
     * @param i heap[j]
     * @param j heap[j]
     * @return is smaller heap[i] than heap[j]
     */
    private boolean greater(int i, int j) {
        return heap[i].compareTo(heap[j]) > 0;
    }

    /**
     * swap data
     * @param i heap[j]
     * @param j heap[j]
     */
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

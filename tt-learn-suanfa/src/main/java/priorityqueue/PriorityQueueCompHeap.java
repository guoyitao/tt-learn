package priorityqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 优先队列
 * <p>
 * <p>
 * 逻辑上是完全二叉树
 * 物理上是向量
 * <p>
 * 任何节点都小于他的父节点
 * 父节点--》 i-1 >> 1
 * 左节点--》 1 + ( (i) << 1)
 * 右节点--》 (1+ (i)) << 1
 *
 * @author guoyitao
 */
public class PriorityQueueCompHeap<T extends Comparable<T>> implements PriorityQueue<T> {

    List<T> list = new ArrayList<>();

    @Override
    public void insert(T t) {
        this.list.add(t);
        this.percolateUp(list.size() - 1);
    }

    /**
     * 在最后插入，如果跟父亲逆序，就一直往上交换
     * O(3logn) 因为最差的时候每次都需要交换 交换需要3次赋值
     * TODO 上滤优化.jpg  O(logn)
     *
     * @param i 需要上滤的index
     * @return void
     */
    public void percolateUp(int i) {

        while (parentValid(i)) {
            int parent = parent(i);
            if (list.get(i).compareTo(list.get(parent)) < 0) {
                break;
            }
            T tmp1 = list.get(i);
            list.set(i, list.get(parent));
            list.set(parent, tmp1);
            i = parent;
        }
    }

    /**
     * 对规模为size的元素i进行下滤
     *
     * @param size
     * @param i
     * @return void
     */
    public void percolateDown(int size, int i) {
        int j = findBigChildIndex(size, i);
        while (i != j) {

            T tmp1 = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp1);
            i = j;

            j = findBigChildIndex(size, i);
            if (j==-1) {
                return;
            }
        }
    }

    private int findBigChildIndex(int size, int i) {
        int lIndex = 1 + ((i) << 1);
        int rIndex = (1 + (i)) << 1;
        T l = null;
        T r = null;
        if (lIndex <= (size - 1) ) {
            l = list.get(lIndex);
        }
        if (rIndex <= (size - 1) ) {
            r = list.get(rIndex);
        }

        if (l == null && r == null) {
          return -1;
        }
        if (l == null || r == null) {
            return l != null ?lIndex:rIndex;
        }
        return l.compareTo(r) > 0 ? lIndex : rIndex;
    }

    private boolean parentValid(int i) {
        return (i - 1 >> 1) >= 0;
    }

    private int parent(int i) {
        return i - 1 >> 1;
    }


    @Override
    public T getMax() {
        return list.get(0);
    }

    @Override
    public T delMax() {
        T max = list.get(0);
        T remove = list.remove(list.size() - 1);
        list.set(0, remove);
        this.percolateDown(list.size(), 0);

        return max;
    }

    @Override
    public String toString() {
        return "PriorityQueueCompHeap{" +
                "list=" + list +
                '}';
    }


    @Test
    public void test1() {

        PriorityQueueCompHeap<Integer> priorityQueueCompHeap = new PriorityQueueCompHeap<>();
//        priorityQueueCompHeap.list.add(4);
//        priorityQueueCompHeap.list.add(3);
//        priorityQueueCompHeap.list.add(0);
//        priorityQueueCompHeap.list.add(1);
//        priorityQueueCompHeap.list.add(2);
//
//        priorityQueueCompHeap.insert(5);

        priorityQueueCompHeap.list.add(5);
        priorityQueueCompHeap.list.add(4);
        priorityQueueCompHeap.list.add(3);
        priorityQueueCompHeap.list.add(2);
        priorityQueueCompHeap.list.add(0);
        priorityQueueCompHeap.list.add(1);
        priorityQueueCompHeap.delMax();

        System.out.println(priorityQueueCompHeap);
    }
}

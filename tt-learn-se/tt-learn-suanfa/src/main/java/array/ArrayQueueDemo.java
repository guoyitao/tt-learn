package array;

import java.math.BigInteger;

/**
 * description: 数组队列
 * author: Guo Yitao
 * create: 2020-07-21 23:14
 **/
public class ArrayQueueDemo {

    //环形队列
    private static class ArrayQueue2{
        private int maxSize; // 表示数组的最大容量
        //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
        //front 的初始值 = 0
        private int front;
        //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
        //rear 的初始值 = 0
        private int rear; // 队列尾
        private int[] arr; // 该数据用于存放数据, 模拟队列

        // 创建队列的构造器
        public ArrayQueue2(int arrMaxSize) {
            maxSize = arrMaxSize +1;
            arr = new int[maxSize];

        }

        // 判断队列是否满
        public boolean isFull() {
            return  (rear  + 1) % maxSize == front;
        }

        // 判断队列是否为空
        public boolean isEmpty() {
            return rear == front;
        }

        // 添加数据到队列
        public void addQueue(int n) {
            // 判断队列是否满
            if (isFull()) {
                System.out.println("队列满，不能加入数据~");
                return;
            }
            arr[rear] = n;
            rear = (rear+1) % maxSize;
        }

        // 获取队列的数据, 出队列
        public int getQueue() {
            // 判断队列是否空
            if (isEmpty()) {
                // 通过抛出异常
                throw new RuntimeException("队列空，不能取数据");
            }
            // 这里需要分析出 front是指向队列的第一个元素
            // 1. 先把 front 对应的值保留到一个临时变量
            // 2. 将 front 后移, 考虑取模
            // 3. 将临时保存的变量返回
            int value = arr[front];
            arr[front] = 0;
            front = (front + 1) % maxSize;
            return value;

        }

        // 显示队列的所有数据
        public void showQueue() {
            // 遍历
            if (isEmpty()) {
                System.out.println("队列空的，没有数据~~");
                return;
            }
            for (int i = front; i < front + getSize() ; i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }

        public int getSize(){
            /*
            * rear=1 front=1 maxsize=3
            * */
            return  (rear + maxSize - front) % maxSize;
        }


        // 显示队列的头数据， 注意不是取出数据
        public int headQueue() {
            // 判断
            if (isEmpty()) {
                throw new RuntimeException("队列空的，没有数据~~");
            }
            return arr[front];
        }

    }

    //非环形队列
    private static class ArrayQueue{
        private int maxSize; // 表示数组的最大容量
        private int front; // 队列头
        private int rear; // 队列尾
        private int[] arr; // 该数据用于存放数据, 模拟队列

        // 创建队列的构造器
        public ArrayQueue(int arrMaxSize) {
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = -1; // 指向队列头部，分析出front是指向队列头的前一个位置.
            rear = -1; // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
        }

        // 判断队列是否满
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        // 判断队列是否为空
        public boolean isEmpty() {
            return rear == front;
        }

        // 添加数据到队列
        public void addQueue(int n) {
            // 判断队列是否满
            if (isFull()) {
                System.out.println("队列满，不能加入数据~");
                return;
            }
            rear++; // 让rear 后移
            arr[rear] = n;
        }

        // 获取队列的数据, 出队列
        public int getQueue() {
            // 判断队列是否空
            if (isEmpty()) {
                // 通过抛出异常
                throw new RuntimeException("队列空，不能取数据");
            }
            front++; // front后移
            int tmp = arr[front];
            arr[front] = 0;
            return tmp;

        }

        // 显示队列的所有数据
        public void showQueue() {
            // 遍历
            if (isEmpty()) {
                System.out.println("队列空的，没有数据~~");
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }

        // 显示队列的头数据， 注意不是取出数据
        public int headQueue() {
            // 判断
            if (isEmpty()) {
                throw new RuntimeException("队列空的，没有数据~~");
            }
            return arr[front + 1];
        }

    }

    public static void main(String[] args) {
//        ArrayQueue arrayQueue = new ArrayQueue(3);
        ArrayQueue2 arrayQueue = new ArrayQueue2(3);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);

        System.out.println("is full "+arrayQueue.isFull());
        while (!arrayQueue.isEmpty()) {
            System.out.println("出队：" + arrayQueue.getQueue());
            arrayQueue.showQueue();
        }
        arrayQueue.addQueue(6);
        while (!arrayQueue.isEmpty()) {
            System.out.println("出队：" + arrayQueue.getQueue());
            arrayQueue.showQueue();
        }
    }
}

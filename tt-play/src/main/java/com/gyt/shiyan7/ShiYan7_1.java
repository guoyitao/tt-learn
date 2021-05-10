package com.gyt.shiyan7;

//模拟ArrayList扩容
public class ShiYan7_1<T> {
    private Object[] arr;
    private int index = 0;
    private int maxSize = 10;

    public int maxSize() {
        return this.maxSize;
    }

    public int index(){
        return this.index;
    }


    public void add(T n){

        if (index >= maxSize){
            System.out.printf("扩容 arr:[%s] index:[%d]  size:[%d]\n",this.toString(),index, maxSize);
            //扩容
            maxSize = maxSize * 2;
            Object[] newArray = new Object[maxSize];
            System.arraycopy(arr, 0, newArray, 0, arr.length);
            arr = newArray;
        }
        arr[index++] = n;
    }

    public ShiYan7_1(T[] array) {
        this.arr = new Object[maxSize];
        for (T i : array) {
            this.add(i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < index; i++) {
            sb.append(arr[i]);
            if (i != index -1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] array = {5,8,23,45,89,136};
        ShiYan7_1<Integer> shiYan7_1 = new ShiYan7_1<>(array);

        shiYan7_1.add(56);
        System.out.printf("第一次添加56 arr:[%s] index:[%d]  size:[%d]\n",shiYan7_1.toString(),shiYan7_1.index(), shiYan7_1.maxSize());

        for (int i = 0; i < 100; i++) {
            shiYan7_1.add(i);
        }

        System.out.println(shiYan7_1);
    }
}

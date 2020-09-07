package nio.buffer;

import java.nio.IntBuffer;

public class BascisBuffer {
    public static void main(String[] args) {
        IntBuffer allocate = IntBuffer.allocate(5);
        allocate.put(1);
        allocate.put(2);
        allocate.put(3);
        allocate.put(4);
        allocate.put(9);

        allocate.flip(); //翻转

        while (allocate.hasRemaining()){
            System.out.println(allocate.get());
        }



    }
}

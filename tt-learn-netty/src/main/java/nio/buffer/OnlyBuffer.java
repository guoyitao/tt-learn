package nio.buffer;

import java.nio.ByteBuffer;

public class OnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        System.out.println(buffer.getClass());
        System.out.println(readOnlyBuffer.getClass());
        readOnlyBuffer.put("as".getBytes());
    }
}

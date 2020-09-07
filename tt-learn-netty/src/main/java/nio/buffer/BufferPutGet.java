package nio.buffer;

import java.nio.ByteBuffer;

public class BufferPutGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(121);
        buffer.putLong(111112312331212L);
        buffer.putDouble(123321d);
        buffer.putChar('啊');
        buffer.putShort((short)1);

        buffer.flip();
        //顺序不能颠倒
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}

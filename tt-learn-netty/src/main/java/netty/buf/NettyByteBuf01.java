package netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * description:
 * date: 2020/9/10 13:08
 * created by: guo
 */
public class NettyByteBuf01 {
    @Test
    public void test1(){
        ByteBuf buf = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            buf.writeByte(i);
        }

        for (int i = 0; i < buf.capacity(); i++) {
            System.out.println(buf.getByte(i));
        }
    }

    @Test
    public void test2(){
        ByteBuf buf = Unpooled.copiedBuffer("哈哈", CharsetUtil.UTF_8);
        if (buf.hasArray()){
            byte[] array = buf.array();
            System.out.println(new String(array, Charset.forName("utf-8")));

            System.out.println(buf.arrayOffset());
            System.out.println(buf.readerIndex());
            System.out.println(buf.writerIndex());
            System.out.println(buf.capacity());
            System.out.println(buf.readableBytes());
            System.out.println(buf.getCharSequence(0, 3, CharsetUtil.UTF_8));
        }
    }
}

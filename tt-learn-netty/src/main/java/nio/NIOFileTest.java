package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileTest {

    //写
    @Test
    public  void test3()  {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("noiotest3.txt");
            FileChannel channel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            byte[] msg = "hello nio !!!!".getBytes();

            for (int i = 0; i < msg.length; i++) {
                byteBuffer.put(msg[i]);
            }
//            将缓存字节数组的指针设置为数组的开始序列即数组下标0
            byteBuffer.flip();

            channel.write(byteBuffer);
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //读
    @Test
    public void test2()  {
        try {
            FileInputStream fileInputStream = new FileInputStream("noiotest3.txt");
            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            channel.read(byteBuffer);

            byteBuffer.flip();

            while (byteBuffer.hasRemaining()){
                byte b = byteBuffer.get();
                System.out.println("byte :  "+(char)b);
            }

            fileInputStream.close();
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //复制
    @Test
    public  void test4() throws Exception{
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(2);

        while (true){
            //重置position =0 limit=capacity
            //主要是为了下一次循环起作用
            buffer.clear();
            //这里里面会调用buffer.put()
            int len = inputChannel.read(buffer);
            System.out.println(len);
            if (-1 == len){
                break;
            }
            //让position等于0然后好开始写
            buffer.flip();
            //这里会让limit和position一起移动
            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();

    }

    //复制2
    @Test
    public void test5() throws IOException {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        outputChannel.transferFrom(inputChannel,0,inputChannel.size());

        inputChannel.close();
        outputChannel.close();
        inputChannel.close();
        outputChannel.close();
    }
}

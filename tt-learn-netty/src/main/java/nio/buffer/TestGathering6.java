package nio.buffer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 *  关于buffer的Scattering和Gathering
 *  {
 *      Scattering{
 *          把来自一个channel的数据读到多个buffer
 *          按照顺序，读满第一个再第二个。。。。。。。
 *      }
 *
 *      Gathering{
 *          相对
 *      }
 *  }
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/3/18
 * @UpdateUser:
 */
public class TestGathering6 {

    public static void main(String[] args)throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messagelen = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true){

            int bytesRead = 0;

            while (bytesRead < messagelen){
                long read = socketChannel.read(buffers);
                bytesRead += read;

                System.out.println("bytesread: " + bytesRead);

                Arrays.asList(buffers).stream().map(
                        buffer -> "position:" +buffer.position() + ",limit: " + buffer.limit()).
                        forEach(System.out::println);

            }

            Arrays.asList(buffers).forEach(byteBuffer -> {
                byteBuffer.flip();
            });

            long bytesWritten = 0;

            while (bytesWritten < messagelen){
                long l = socketChannel.write(buffers);
                bytesWritten += l;
            }

            Arrays.asList(buffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });

            System.out.println("bytesRead: "+ bytesRead + ",bytesWritten " + bytesWritten + "，messageLength" + messagelen);
        }

    }
}

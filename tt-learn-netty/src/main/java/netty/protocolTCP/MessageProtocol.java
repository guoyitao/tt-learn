package netty.protocolTCP;

/**
 * 协议
 * user: guoyitao
 * date: 2020/9/19 20:25
 * version:1.0
 */
public class MessageProtocol {
    private int len;
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}

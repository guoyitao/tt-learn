package bio;

public enum MessageType {

    AcceptOk("接受成功"),

    SendChatRoom("发送到聊天室"),
    ExitChatRoom("退出聊天室"),
    JoinChatRoom("加入聊天室"),

    SendChatOne("发送消息给一个人"),
    ExitChatOne("离开单聊"),
    JoinChatOne("加入单聊"),

    ForgetPassword("忘记密码"),
    Login("登录"),
    Register("注册");

    private final String name;
    MessageType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name();
    }
}

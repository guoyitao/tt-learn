import com.chat.client.service.ClientUserService;

/**
 * @description://
 * @author: Luck_chen
 * @date: 2022/11/8 10:42
 * @Version 1.0.0.0
 */
public class TestUS {
    public static void main(String[] args) {
        ClientUserService clientUserService = new ClientUserService();
//        userService.register();
        clientUserService.login();
    }
}

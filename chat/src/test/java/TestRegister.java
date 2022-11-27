import com.chat.domain.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @description://TODO
 * @author: Luck_chen
 * @date: 2022/11/13 21:25
 * @Version 1.0.0.0
 */
public class TestRegister {
    public static ArrayList<User> userList = new ArrayList<>();
    public static void main(String[] args) {
        TestRegister testRegister = new TestRegister();
        testRegister.register();

    }
    //注册
    public boolean register() {
        User user = new User();
        Scanner sc = new Scanner(System.in);

        /**
         * 验证用户名
         * while循环控制直到输入合法为止
         */
        System.out.println("请输入用户名(用户名由数字、字母、下划线任意组合,长度为3~10)：");
        String uname = sc.next();
        while(!checkName(uname)) {
            System.out.println("用户名不合法，请重新输入：");
            uname=sc.next();
        }
        user.setUsername(uname);

        /**
         * 验证密码
         * while循环控制直到输入合法为止
         */
        System.out.println("请输入密码(密码由数字、字母、下划线任意组合,长度为6~20)：");
        String upassword = sc.next();
        while(!checkPwd(upassword)) {
            System.out.println("密码不合法，请重新输入：");
            upassword=sc.next();
        }
        user.setPassword(upassword);

        /**
         * 验证邮箱
         * while循环控制直到输入合法为止
         */
        System.out.println("请输入邮箱：");
        String uemail = sc.next();
        while(!checkEmail(uemail)) {
            System.out.println("邮箱不合法，请重新输入：");
            uemail=sc.next();
        }
        user.setEmail(uemail);
        //添加到数据库
        userList.add(user);
        System.out.println("注册成功！");
        return true;
    }
    //检查邮箱
    private boolean checkEmail(String uemail) {
        if ((uemail != null) && (!uemail.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", uemail);
        }
        return false;
    }
    //检查密码
    private boolean checkPwd(String upassword) {
        String regExp = "^[\\w_]{6,20}$";
        if(upassword.matches(regExp)) {
            return true;
        }
        return false;
    }

    //检查用户名 todo 中文
    private boolean checkName(String uname) {
        String regExp = "^[^0-9][\\w_]{2,10}$";
        if(uname.matches(regExp)) {
            return true;
        }else {
            return false;
        }
    }

}

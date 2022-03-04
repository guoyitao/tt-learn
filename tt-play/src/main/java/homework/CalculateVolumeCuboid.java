package homework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateVolumeCuboid {
    public static String classAndName = "asd";
    public static void main(String[] args) {
        JFrame frame=new JFrame("确认个人信息");    //创建Frame窗口
        JPanel jp=new JPanel();    //创建面板
        JLabel label1=new JLabel("班级学号姓名");    //创建标签
        JLabel label2=new JLabel();
        label2.setText(classAndName);
        JButton btn1=new JButton("submit");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame2=new JFrame("计算");
                JPanel jp2=new JPanel();

                JTextField txtfield1=new JTextField(30);    //创建文本框
                txtfield1.setText("");    //设置文本框的内容
                JButton btn1=new JButton("submit");
                btn1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text = txtfield1.getText();
                        if ("c".equals(text)){
                            frame2.dispose();
                            changfangti();
                        }else if ("q".equals(text)){
                            frame2.dispose();
                            qiu();
                        }
                    }
                });


                jp2.add(txtfield1);
                jp2.add(btn1);
                frame2.add(jp2);
                frame2.setBounds(300,200,400,200);
                frame2.setVisible(true);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        //创建既含有文本又含有图标的JLabel对象
        jp.add(label1);    //添加标签到面板
        jp.add(label2);
        jp.add(btn1);
        frame.add(jp);
        frame.setBounds(300,200,400,100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void changfangti(){
        JFrame frame=new JFrame("计算长方体体积");    //创建Frame窗口
        JPanel jp=new JPanel();    //创建面板
        JLabel labela=new JLabel("长：");
        JTextField a=new JTextField(10);    //创建文本框
        JLabel labelb=new JLabel("宽");
        JTextField b=new JTextField(10);    //创建文本框
        JLabel labelc=new JLabel("高：");
        JTextField c=new JTextField(10);    //创建文本框

        JLabel tiji=new JLabel("体积：");

        JButton submit=new JButton("submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double aDouble = Double.valueOf(a.getText());
                Double bDouble = Double.valueOf(b.getText());
                Double cDouble = Double.valueOf(c.getText());
                tiji.setText("体积：" + aDouble*bDouble*cDouble);

            }
        });

        jp.add(labela);
        jp.add(a);
        jp.add(labelb);
        jp.add(b);
        jp.add(labelc);
        jp.add(c);
        jp.add(submit);
        jp.add(tiji);

        frame.add(jp);
        frame.setBounds(300,200,500,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void qiu(){
//        4/3πr^3
        JFrame frame=new JFrame("计算球体体积");    //创建Frame窗口
        JPanel jp=new JPanel();    //创建面板
        JLabel labela=new JLabel("r：");
        JTextField a=new JTextField(10);    //创建文本框

        JLabel tiji=new JLabel("体积：");

        JButton submit=new JButton("submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double aDouble = Double.valueOf(a.getText());
                tiji.setText("体积：" + 4/3.0 * Math.PI * Math.pow(aDouble,3));

            }
        });

        jp.add(labela);
        jp.add(a);
        jp.add(submit);
        jp.add(tiji);

        frame.add(jp);
        frame.setBounds(300,200,500,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

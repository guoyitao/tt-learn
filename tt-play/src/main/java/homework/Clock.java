package homework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Clock {

    public static String classAndName = "asd";

    public static ExecutorService jobExecutor = Executors.newSingleThreadExecutor();
    public static volatile boolean isRunning = false;
    static class Job implements Runnable {
        private Double millis;
        private JLabel label2;

        public Job(Double millis, JLabel label2) {
            this.millis = millis;
            this.label2 = label2;
        }

        @Override
        public void run() {
            while (isRunning){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
                millis -= 100;
                if (millis <=0 ){
                    label2.setText("剩余：" + 0 + "秒");
                    JOptionPane.showConfirmDialog(label2,"时间到啦","时间到啦",0);
                    break;
                }
                label2.setText("剩余：" + millis/1000 + "秒");
            }
        }
    }

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
                run();
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

    private static void run() {
        JFrame frame2=new JFrame("哈喽呀！请设置你的闹钟!");
        JPanel jp=new JPanel();    //创建面板
        JTextField a=new JTextField(10);    //创建文本框
        JLabel showTime=new JLabel();
        JButton btn1=new JButton("submit");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning){
                    isRunning = false;
                    jobExecutor.shutdownNow();
                    jobExecutor = Executors.newSingleThreadExecutor();
                }
                isRunning = true;
                double millis = 0;
                String time = a.getText();
                String time2 = time.substring(0,time.length()-1);
                if (time.endsWith("s")) {
                    millis = Double.parseDouble(time2);
                    millis *= 1000;
                }else if (time.endsWith("m")){
                    millis = Double.parseDouble(time2);
                    millis *= (1000 * 60);
                }

                jobExecutor.submit(new Job(millis,showTime));
            }
        });

        jp.add(a);    //添加标签到面板
        jp.add(btn1);
        jp.add(showTime);
        frame2.add(jp);
        frame2.setBounds(300,200,600,200);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

package homework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestAVG {

    static class ActionClick implements ActionListener{

        JTextField from;
        JTextField to;

        int dfrom = 0;
        int dto = 0;

        public ActionClick(JTextField a, JTextField b) {
            this.from =a;
            this.to = b;
            change();
        }

        private void change() {
            String text = from.getText();
            dfrom = Integer.parseInt(text);

            String dto = to.getText();
            this.dto = Integer.parseInt(dto);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            change();
            int sum = getSum(dfrom,dto);
            String title = dfrom +  "到"+dto+"的平均数";
            JFrame frame=new JFrame(title);
            JPanel jp=new JPanel();    //创建面板
            double avg = sum / (dto*1.0);
            String result = buildGongShi(dfrom,dto);
            JLabel label2= new JLabel(result);
            jp.add(label2);

            String s = dfrom +  "到"+dto+"的平均数";
            JLabel label1=new JLabel(s + avg);
            jp.add(label1);
            frame.add(jp);
            frame.setBounds(300,200,1100,100);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    private static String buildGongShi(int dfrom, int dto) {
        StringBuilder result = new StringBuilder();
        for (int i = dfrom; i <= dto; i++) {
            result.append(i).append("+");
        }
        result = new StringBuilder("(" + result );

        int end = result.length() - 1;
        int start = 0;
        String substring = result.substring(start, end);
        String s1 = ")" + "/" + dto;
        String s = substring + s1;
        return s;
    }

    private static int getSum(int a, int b) {
        int sum = 0;

        for (int i = a; i <= b ; i++) {
            sum += i;
        }

        return sum;
    }

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        JFrame frame=new JFrame("个人信息");    //创建Frame窗口
        JPanel jp=new JPanel();    //创建面板
        buildContext(jp);
        frame.add(jp);
        frame.setBounds(300,200,400,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void buildContext(JPanel jp) {
        JLabel clazz=new JLabel("班级:");    //创建标签
        String clazzItem = "电自191";
        JLabel clazz2=new JLabel(clazzItem);

        JLabel name1=new JLabel("姓名:");    //创建标签
        String nameStr = "王智";
        JLabel name2=new JLabel(nameStr);

        JLabel id=new JLabel("学号:");    //创建标签
        String idCode = "2019301040129";
        JLabel id2=new JLabel(idCode);

        JTextField a=new JTextField(10);
        a.setText("1");
        JTextField b=new JTextField(10);
        b.setText("50");

        JButton btn1=new JButton("submit");
        btn1.addActionListener(new ActionClick(a,b));

        jp.add(clazz);
        jp.add(clazz2);

        jp.add(name1);
        jp.add(name2);

        jp.add(id);
        jp.add(id2);

        jp.add(btn1);


        jp.add(a);
        jp.add(b);
    }

}

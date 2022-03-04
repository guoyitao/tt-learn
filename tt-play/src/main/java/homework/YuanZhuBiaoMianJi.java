package homework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YuanZhuBiaoMianJi {
    static class ActionClick implements ActionListener {

        JTextField r;
        JTextField h;

        double dr = 0;
        double dh = 0;

        public JTextField getR() {
            return r;
        }

        public void setR(JTextField r) {
            this.r = r;
        }

        public JTextField getH() {
            return h;
        }

        public void setH(JTextField h) {
            this.h = h;
        }

        public double getDr() {
            return dr;
        }

        public void setDr(double dr) {
            this.dr = dr;
        }

        public double getDh() {
            return dh;
        }

        public void setDh(double dh) {
            this.dh = dh;
        }

        public ActionClick(JTextField a, JTextField b) {
            this.r =a;
            this.h = b;
            change();
        }

        private void change() {
            String text = r.getText();
            this.dr = Double.parseDouble(text);

            String dto = h.getText();
            this.dh = Double.parseDouble(dto);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            change();
            String title = "圆柱的表面积";
            JFrame frame=new JFrame(title);
            JPanel jp=new JPanel();    //创建面板
            double s = (Math.PI * dr*dr *2) + (2*Math.PI* dr * dh);

            JLabel label2= new JLabel(String.format("r:%f h:%f 的圆柱体积为：", dr,dh));
            jp.add(label2);
            String strS = String.valueOf(s);
            JLabel label3= new JLabel(strS);
            jp.add(label3);

            frame.add(jp);
            frame.setBounds(300,200,400,150);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }



    public static void main(String[] args) {
        init();
    }

    private static void init() {
        JFrame frame=new JFrame("个人信息");
        JPanel jp=new JPanel();
        buildContext(jp);
        frame.add(jp);
        frame.setBounds(300,200,400,150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void buildContext(JPanel jp) {
        JLabel clazz=new JLabel("班级:");    //创建标签
        String clazzItem = "电自191";
        JLabel clazz2=new JLabel(clazzItem);

        JLabel name1=new JLabel("姓名:");    //创建标签
        String nameStr = "靳博然";
        JLabel name2=new JLabel(nameStr);

        JLabel id=new JLabel("学号:");    //创建标签
        String idCode = "2019301040114";
        JLabel id2=new JLabel(idCode);

        JLabel r=new JLabel("半径:");
        JTextField a=new JTextField(10);
        a.setText("5");

        JLabel h=new JLabel("高:");
        JTextField b=new JTextField(10);
        b.setText("5");

        JButton btn1=new JButton("submit");
        btn1.addActionListener(new YuanZhuBiaoMianJi.ActionClick(a,b));

        jp.add(clazz);
        jp.add(clazz2);

        jp.add(name1);
        jp.add(name2);

        jp.add(id);
        jp.add(id2);

        jp.add(btn1);

        jp.add(r);
        jp.add(a);
        jp.add(h);
        jp.add(b);
    }
}

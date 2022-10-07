package chx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Computer {
    public static void main(String[] args) {
        new MyComputer();
    }

}

class MyComputer extends JFrame implements ActionListener  {
    JTextField textField;
    public MyComputer() {
        initJM();
    }

    //初始化界面
    public void initJM() {


        ButtonArrayExample();
        //设置可见
        this.setVisible(true);

    }

    public void ButtonArrayExample() {

        BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
        borderLayout.setHgap(20);
        borderLayout.setVgap(10);
        // 设置窗体的标题
        setTitle("计算器 ");
        // 设置窗体的显示位置及大小
        setBounds(100, 100, 500, 488);
        // 设置窗体关闭按钮的动作为退出
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout());
        getContentPane().add(jPanel,BorderLayout.NORTH);
        textField = new JTextField("0");
        Font textFont = new Font("宋体", Font.BOLD, 30);
        textField.setFont(textFont);
        JButton CE = new JButton("CE");
        textField.setHorizontalAlignment(SwingConstants.LEADING);
        textField.setPreferredSize(new Dimension(12, 50));
        textField.setColumns(10);
        jPanel.add(textField);
        jPanel.add(CE);
        CE.addActionListener(this);

        // 创建网格布局管理器对象
        final GridLayout gridLayout = new GridLayout(4, 0);
        // 设置组件的水平间距
        gridLayout.setHgap(5);
        // 设置组件的垂直间距
        gridLayout.setVgap(5);
        // 获得容器对象
        JPanel panel = new JPanel();
        // 设置容器采用网格布局管理器
        panel.setLayout(gridLayout);
        getContentPane().add(panel, BorderLayout.CENTER);
        String[][] names = {{"7", "8", "9", "/"}, {"4", "5", "6", "*"},
                {"1", "2", "3", "-"}, {".", "0", "=", "+"}};
        JButton[][] buttons = new JButton[4][4];

        for (int row = 0; row < names.length; row++) {
            for (int col = 0; col < names.length; col++) {
                // 创建按钮对象
                buttons[row][col] = new JButton(names[row][col]);
                buttons[row][col].setFont(textFont);
                buttons[row][col].addActionListener(this);
                // 将按钮添加到面板中
                panel.add(buttons[row][col]);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] comands = {"\\/", "\\*", "\\-", "\\+"};
        if (Objects.equals(e.getActionCommand(), "=")){
            String text = textField.getText();
            for (String comand : comands) {
                if (text.contains(comand.substring(1, comand.length()))) {
                    String[] split = text.split(comand);
                    if (split!= null && split.length > 1){
                        switch (comand){
                            case "\\/":
                                textField.setText(String.valueOf(Double.parseDouble(split[0]) / Double.parseDouble(split[1])));
                                break;
                            case "\\-":
                                textField.setText(String.valueOf(Double.parseDouble(split[0]) - Double.parseDouble(split[1])));
                                break;
                            case "\\*":
                                textField.setText(String.valueOf(Double.parseDouble(split[0]) * Double.parseDouble(split[1])));
                                break;
                            case "\\+":
                                textField.setText(String.valueOf(Double.parseDouble(split[0]) + Double.parseDouble(split[1])));
                                break;
                        }

                    }
                    break;
                }
            }
        }else if ("CE".equals(e.getActionCommand())){
            textField.setText("0");
        }else{
            if ("0".equals(textField.getText())){
                textField.setText(e.getActionCommand());
            }else{
                textField.setText(textField.getText() + e.getActionCommand());
            }
        }

    }
}
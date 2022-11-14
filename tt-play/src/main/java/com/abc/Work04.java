package com.abc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Work04 extends JFrame {

    public static void main(String[] args) {
        Frame frame = new Frame("标题ABC");

        // 设置边界
        frame.setBounds(30, 30, 450, 300);
        frame.setLayout(null);
        //文本框
        TextField inputTextField = new TextField(30);
        frame.add(inputTextField);
        inputTextField.setBounds(100, 50, 200, 50);

        JLabel l = new JLabel("我是标签");
        frame.add(l);
        l.setBounds(100, 100, 200, 50);

        JButton button = new JButton("我是按钮");
        frame.add(button);
        button.setBounds(100, 200, 200, 50);
        //单击
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l.setText(inputTextField.getText());
            }
        });

        // 显示窗口
        frame.setVisible(true);
    }

}

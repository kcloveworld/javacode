package com.itheima.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.util.Random;

public class MyJFrame extends JFrame implements ActionListener{

    JButton jbt1 = new JButton("点击我");
    JButton jbt2 = new JButton("再点击我啊");

    public MyJFrame() {
        //设置界面的宽和高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("事件演示");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);

        // 设置按钮的位置和大小
        jbt1.setBounds(0, 0, 100, 50);
        jbt1.addActionListener(this);// 注册监听器
        jbt2.setBounds(100,0,100,50);
        jbt2.addActionListener(this);// 注册监听器


        /*jbt1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("按钮被点击了");
                    }
                }
        );*/
        // 将按钮添加到窗口内容面板
        this.getContentPane().add(jbt1);
        this.getContentPane().add(jbt2);
        // 设置窗口可见
        this.setVisible(true);
    }
    // 处理按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();// 获取事件源
        if (source == jbt1){    //如果事件源是 jbt1
            jbt1.setSize(200,200);
        } else if (source == jbt2) {  // 如果事件源是 jbt2
            Random r = new Random();
            jbt2.setLocation(r.nextInt(500),r.nextInt(500));
        }


    }
}




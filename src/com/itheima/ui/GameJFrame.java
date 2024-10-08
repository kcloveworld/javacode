package com.itheima.ui;

import javax.swing.*;

public class GameJFrame extends JFrame{
    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化图片
        initImage();

        this.setVisible(true);
    }

    private void initImage() {
        int number = 1;
        for (int j = 0; j < 4; j++) {
            //外循环---把内循环重复了4次
            for (int i = 0; i < 4; i++) {
                //内循环----表示在1行添加4涨图片
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon("E:\\code\\java\\puzzlegame\\image\\animal\\animal3\\" + number +".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * i,105 * j,105,105);
                //获取窗体，设置布局为null，即取消默认居中设置
                this.getContentPane().add(jLabel);
                //添加一次后，number需要自增，表示下一次添加后一张图片
                number++;
            }
        }

    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem accountItem = new JMenuItem("公众号");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);
        this.setTitle("拼图单机版 v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中放置
        this.setLayout(null);
    }
}

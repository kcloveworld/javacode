package com.itheima.ui;

import javax.swing.*;
import java.util.Random;

public class GameJFrame extends JFrame{
    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据
        initData();
        //初始化图片
        initImage();

        this.setVisible(true);
    }

    //创建一个二维数组
    int[][] data = new int[4][4];
    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        //遍历数组，得到每一个元素，拿着每一个元素跟随即索引上的数据进行交换
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //解法1
        //遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组当中
        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    private void initImage() {
        for (int j = 0; j < 4; j++) {
            //外循环---把内循环重复了4次
            for (int i = 0; i < 4; i++) {
                int num = data[i][j];
                //内循环----表示在1行添加4涨图片
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon("E:\\code\\java\\puzzlegame\\image\\animal\\animal3\\" + num +".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * i,105 * j,105,105);
                //获取窗体，设置布局为null，即取消默认居中设置
                this.getContentPane().add(jLabel);
                //添加一次后，number需要自增，表示下一次添加后一张图片
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

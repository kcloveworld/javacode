package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener {
    //记录空白方块在二维数组的位置
    int x = 0 ;
    int y = 0 ;

    int[][] data = new int[4][4];

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
            if (tempArr[i] == 0){
                x = i / 4 ;
                y = i % 4;
            }
            else
                data[i / 4][i % 4] = tempArr[i];
        }
    }

    private void initImage() {
        //清空已经出现的所有图片
        this.getContentPane().removeAll();

        for (int i = 0; i < 4; i++) {
            //外循环---把内循环重复了4次
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //内循环----表示在1行添加4涨图片
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon("image\\animal\\animal3\\" + num +".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * j + 83,105 * i + 134,105,105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //获取窗体，设置布局为null，即取消默认居中设置
                this.getContentPane().add(jLabel);
                //添加一次后，number需要自增，表示下一次添加后一张图片

            }
        }
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40,40,508,560);
        //把背景图片添加到当前的界面当中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();


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
        //添加键盘监听器
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 37){
            System.out.println("向左移动");

            if (y < 3) {
                System.out.println("向右移动");
                data[x][y] = data[x][y + 1];
                data[x][y +1] = 0;
                y++;
                initImage();
            }
        } else if (keyCode == 38) {
            if (x < 3) {
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                initImage();
            }else
                System.out.println("超出范围");
        }else if (keyCode == 39) {
            System.out.println("向右移动");
            if (y > 0) {
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                initImage();
            }
        }else if (keyCode == 40){
            if (x > 0) {
                System.out.println("向下移动");
                data[x][y] = data[x - 1][y];
                data[x -1][y] = 0;
                x--;
                initImage();
            }
        }


    }
}

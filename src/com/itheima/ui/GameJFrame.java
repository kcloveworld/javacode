package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //记录空白方块在二维数组的位置
    int x = 0 ;
    int y = 0 ;
    String path = "D:\\code\\puzzlegame\\image\\animal\\animal3\\";
    int step = 0;


    int[][] data = new int[4][4];
    int[][] win = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");

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

    //初始化数据（打乱）
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
                data[i / 4][i % 4] = tempArr[i];
            }
            else
                data[i / 4][i % 4] = tempArr[i];
        }
    }
    //初始化图片
    private void initImage() {
        //清空已经出现的所有图片
        this.getContentPane().removeAll();
        if (victory()) {
            //显示胜利的图标
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        //添加计步器
        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            //外循环---把内循环重复了4次
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //内循环----表示在1行添加4涨图片
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path + num +".jpg"));
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

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //条目添加监听器
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

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
        int keyCode = e.getKeyCode();
        if (keyCode == 65){
            //把界面上所有的图片都删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //添加背景图片
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40,40,508,560);
            //把背景图片添加到当前的界面当中
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利
        //如果胜利，此方法需要直接结束，不执行下面的移动代码
        int keyCode = e.getKeyCode();
        if (victory()){
            //结束方法
            return;
        }
        if (keyCode == 37){
            System.out.println("向左移动");

            if (y < 3) {
                System.out.println("向右移动");
                data[x][y] = data[x][y + 1];
                data[x][y +1] = 0;
                y++;
                step++;
                initImage();
            }
        } else if (keyCode == 38) {
            if (x < 3) {
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                step++;
                initImage();
            }else
                System.out.println("超出范围");
        }else if (keyCode == 39) {
            System.out.println("向右移动");
            if (y > 0) {
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                step++;
                initImage();
            }
        }else if (keyCode == 40){
            if (x > 0) {
                System.out.println("向下移动");
                data[x][y] = data[x - 1][y];
                data[x -1][y] = 0;
                x--;
                step++;
                initImage();
            }
        }else if (keyCode == 65){
            initImage();
        } else if (keyCode == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    //判断data数组中的数据是否和win数据相同
    //不同返回falase，相同返回true
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == replayItem){
            //记计步器清零
            step = 0 ;
            initData();
            //初始化图片
            initImage();
        } else if (source == reLoginItem){
            //隐藏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        }else if (source == closeItem){
            //关闭虚拟机
            System.exit(0);
        }else if (source == accountItem){
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/about.png"));
            //设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            //弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //让弹窗显示出来
            jDialog.setVisible(true);
        }
    }
}

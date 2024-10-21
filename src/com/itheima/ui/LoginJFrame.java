package com.itheima.ui;

import com.itheima.domain.User;
import com.itheima.util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {

    //静态初始化块
    static ArrayList<User> allUsers = new ArrayList<>();
    static {
        User zhangsan = new User("zhangsan", "123");
        User lisi = new User("lisi", "456");
        allUsers.add(zhangsan);
        allUsers.add(lisi);
    }

    JButton login = new JButton();
    JButton register = new JButton();
    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JTextField codeText = new JTextField();
    JLabel rightCode = new JLabel();

    public LoginJFrame(){
        initJFrame();
        initView();

        this.setVisible(true);
    }

    public void initView(){
        //创建用户名文字容器
        JLabel username = new JLabel(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\用户名.png"));
        username.setBounds(116, 135, 47, 17);
        this.getContentPane().add(username);
        //创建用户名输入框
        usernameText.setBounds(195, 134, 200, 30);
        this.getContentPane().add(usernameText);
        //创建密码文字容器
        JLabel password = new JLabel(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\密码.png"));
        password.setBounds(130, 195, 32, 16);
        this.getContentPane().add(password);
        //创建密码输入框

        passwordText.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passwordText);
        //创建验证码文字容器
        JLabel code = new JLabel(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\验证码.png"));
        code.setBounds(133, 256, 50, 30);
        this.getContentPane().add(code);
        //创建验证码输入框

        codeText.setBounds(195, 256, 100, 30);
        this.getContentPane().add(codeText);

        String str = CodeUtil.getCode();
        rightCode.setText(str);
        rightCode.setBounds(300, 256, 50, 30);
        this.getContentPane().add(rightCode);
        //添加登录按钮
        login.setIcon(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\登录按钮.png"));
        login.setBounds(123, 310, 128, 47);
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);
        //添加注册按钮
        register.setIcon(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\注册按钮.png"));
        register.setBounds(256, 310, 128, 47);
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);


        //创建背景图片容器
        JLabel background = new JLabel(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

        //登录按钮和注册按钮添加鼠标监听器
        login.addMouseListener(this);
        register.addMouseListener(this);
    }

    public void initJFrame(){
        this.setSize(488,430); //设置宽高
        this.setTitle("拼图游戏 V1.0登录"); //设置标题
        this.setAlwaysOnTop(true);  //设置置顶
        this.setLocationRelativeTo(null); //设置剧中
        this.setLayout(null);//取消内部默认布局
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
    }

    public void showJDialog(String content){
        //创建一个弹窗对象
        JDialog jDialog = new JDialog();
        //设置弹窗宽高
        jDialog.setSize(200, 150);
        //设置弹窗置顶
        jDialog.setAlwaysOnTop(true);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);
        //设置弹窗居中
        jDialog.setLocationRelativeTo(null);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);

    }

    //判断用户在集合中是否存在
    public boolean contains(User userInput){
        for (int i = 0; i < allUsers.size(); i++) {
            User rightUser = allUsers.get(i);
            if (userInput.getUsername().equals(rightUser.getUsername()) && userInput.getPassword().equals(rightUser.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
        if (e.getSource() == login){
            //获取用户输入框内容
            String usernameInput = usernameText.getText();
            String passwordInput = passwordText.getText();
            //获取用户输入的验证码
            String codeInput = codeText.getText();

            //创建一个User对象
            User userInfo = new User(usernameInput, passwordInput);
            System.out.println("用户输入的用户名为" + usernameInput);
            System.out.println("用户输入的密码为" + passwordInput);

            if (codeInput.length() == 0){
                showJDialog("验证码不能为空");
            } else if (usernameInput.length() == 0 || passwordInput.length() == 0){
                //调用showJDialog方法并展示弹框
                showJDialog("用户名或密码不能为空");
            } else if(!codeInput.equalsIgnoreCase(rightCode.getText())){
                showJDialog("验证码错误");
            } else if (contains(userInfo)) {
                System.out.println("用户名和密码正确可以开始玩游戏了");
                this.setVisible(false);
                new GameJFrame();
            }else {
                showJDialog("用户名或密码错误");
            }
        } else if (e.getSource() == register) {
            System.out.println("点击了注册按钮");
        } else if (e.getSource() == rightCode) {
            System.out.println("更换验证码");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        if (e.getSource() == login){
            login.setIcon(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\注册按下.png"));

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("release");
        if (e.getSource() == login){
            login.setIcon(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\登录按钮.png"));
        }else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("D:\\code\\puzzlegame\\image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exited");
    }
}

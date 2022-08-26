package cn.myh001.ui;

import cn.myh001.component.BackGroundPanel;
import cn.myh001.pojo.Admin;
import cn.myh001.service.AdminService;
import cn.myh001.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainInterface {
    JFrame loginWindow = new JFrame("水果超市登录");
    int WIDTH = 816;
    int HEIGHT = 439;

    public void init() throws IOException {
        //设置窗口在屏幕中间显示
        loginWindow.setBounds(
                (ScreenUtils.getScreenWidth() - WIDTH) / 2,
                (ScreenUtils.getScreenHeight() - HEIGHT) / 2,
                WIDTH, HEIGHT);

        JPanel backgroundPanel = new BackGroundPanel(ImageIO.read(
                new File("D:\\Java_Project\\fruit_store_swing\\images\\back_img.png")
        ));

        JPanel holePanel = new JPanel(new GridLayout(1, 2, 0, 0));

        backgroundPanel.setBounds(0, 0, 400, 400);

        JPanel loginForm = new JPanel(new GridLayout(4, 1));


        /*
         *title:
         *userPanel:
         *passwordPanel:
         *text1:
         *text2:
         * username:
         * password:
         */

        JLabel title = new JLabel("LOGIN", JLabel.CENTER);
        title.setFont(new Font("", Font.BOLD, 20));
        JPanel userPanel = new JPanel(new FlowLayout());
        JPanel passwordPanel = new JPanel(new FlowLayout());
        JLabel text1 = new JLabel("用户名:");
        JLabel text2 = new JLabel("密 码 :");
        JTextField username = new JTextField(15);
        JTextField password = new JPasswordField(15);

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton loginBtn = new JButton("登录");
        JButton registerBtn = new JButton("去注册");


        //给登录按钮添加事件监听器
        loginBtn.addActionListener(e -> {
            //获取用户输入的用户名和密码
            String userName = username.getText().trim();
            String passWord = password.getText().trim();
            //查询用户
            AdminService adminService = new AdminService();
            Admin admin = adminService.login(userName, passWord);

            if (admin != null) {//如果用户存在，跳转到登录界面
                JOptionPane.showMessageDialog(
                        loginWindow,
                        "登录成功！",
                        "登录成功",
                        JOptionPane.INFORMATION_MESSAGE,
                        null);
                loginWindow.dispose();

                    new ManageWindow(userName);

            } else {
                JOptionPane.showMessageDialog(
                        loginWindow,
                        "登录失败！",
                        "登录失败",
                        JOptionPane.INFORMATION_MESSAGE,
                        null);
            }
        });

        registerBtn.addActionListener(e -> {
            loginWindow.dispose();

                new RegisterWindow();



        });
        //组装用户名
        userPanel.add(text1);
        userPanel.add(username);
        //组装密码
        passwordPanel.add(text2);
        passwordPanel.add(password);
        //组装按钮
        btnPanel.add(loginBtn);
        btnPanel.add(registerBtn);
        //把组装好的panel添加至大的panel
        loginForm.add(title);
        loginForm.add(userPanel);
        loginForm.add(passwordPanel);
        loginForm.add(btnPanel);

        holePanel.add(backgroundPanel);
        holePanel.add(loginForm);

        loginWindow.setIconImage(ImageIO.read(new File("D:\\Java_Project\\fruit_store_swing\\images\\img.png")));
        loginWindow.add(holePanel);
        loginWindow.setResizable(false);
        loginWindow.setVisible(true);
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        new MainInterface().init();
    }
}
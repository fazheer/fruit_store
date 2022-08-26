package cn.myh001.ui;

import cn.myh001.component.BackGroundPanel;
import cn.myh001.component.VerifyImgLabel;
import cn.myh001.pojo.Admin;
import cn.myh001.service.AdminService;
import cn.myh001.util.GetVerifyCodeImageDataUtil;
import cn.myh001.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;


public class RegisterWindow extends JFrame {

    int WIDTH = 816;
    int HEIGHT = 439;
    JPanel userPanel;
    JPanel passwordPanel;
    JPanel confirmPasswordPanel;
    JPanel verifyPanel;
    JPanel btnPanel;
    JLabel title;
    JLabel usernameText = new JLabel("用户名:");
    JLabel passwordText = new JLabel("密 码:   ");
    JLabel confirmPasswordText = new JLabel("确认密码:");
    JLabel verifyCodeText = new JLabel("验证码:");

    JTextField usernameFiled;
    JTextField passwordFiled;
    JTextField confirmPasswordFiled;
    JTextField verifyCodeFiled;
    String verifyCodeGen;
    JButton registerBtn;
    JButton returnBtn;
    byte[] imageData;
    VerifyImgLabel verifyCodeImage;

    private JPanel backgroundPanel;

    JPanel registerForm;

    public RegisterWindow() {
        try {
            this.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.addComponent();
        this.addListener();
    }
    public void init() throws IOException {

        //左侧背景
        backgroundPanel = new BackGroundPanel(
                ImageIO.read(new File("D:\\Java_Project\\fruit_store_swing\\images\\back_img.png"))
        );
        //标题
        title = new JLabel("REGISTER", JLabel.CENTER);
        title.setFont(new Font("", Font.BOLD, 20));
        //用户名
        userPanel = new JPanel(new FlowLayout());
        usernameText = new JLabel("用户名:");
        usernameFiled = new JTextField(15);
        userPanel.add(usernameText);
        userPanel.add(usernameFiled);
        //密码
        passwordPanel = new JPanel(new FlowLayout());
        passwordText = new JLabel("密 码:   ");
        passwordFiled = new JPasswordField(15);
        passwordPanel.add(passwordText);
        passwordPanel.add(passwordFiled);
        //确认密码
        confirmPasswordPanel = new JPanel(new FlowLayout());
        confirmPasswordText = new JLabel("确认密码:");
        confirmPasswordFiled = new JPasswordField(15);
        confirmPasswordPanel.add(confirmPasswordText);
        confirmPasswordPanel.add(confirmPasswordFiled);
        //验证码
        verifyPanel = new JPanel(new FlowLayout());
        verifyCodeText = new JLabel("验证码:");
        verifyCodeFiled = new JTextField(4);

        imageData = GetVerifyCodeImageDataUtil.imageData();
        verifyCodeGen=GetVerifyCodeImageDataUtil.verifyCodeGen;

        verifyCodeImage = new VerifyImgLabel(imageData);
        verifyPanel.add(verifyCodeText);
        verifyPanel.add(verifyCodeFiled);
        verifyPanel.add(verifyCodeImage);
        //按钮
        btnPanel = new JPanel(new FlowLayout());
        registerBtn = new JButton("注册");
        returnBtn = new JButton("返回登录");
        btnPanel.add(registerBtn);
        btnPanel.add(returnBtn);


        this.setTitle("注册");
        this.setBounds(
                (ScreenUtils.getScreenWidth() - WIDTH) / 2,
                (ScreenUtils.getScreenHeight() - HEIGHT) / 2,
                WIDTH, HEIGHT);
        this.setIconImage(ImageIO.read(new File("D:\\Java_Project\\fruit_store_swing\\images\\img.png")));
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void addComponent() {
        registerForm = new JPanel(new GridLayout(6, 1));
        registerForm.add(title);
        registerForm.add(userPanel);
        registerForm.add(passwordPanel);
        registerForm.add(confirmPasswordPanel);
        registerForm.add(verifyPanel);
        registerForm.add(btnPanel);
        this.setLayout(new GridLayout(1,2,0,0));
        this.add(backgroundPanel);
        this.add(registerForm);
    }

    public void addListener() {
        registerBtn.addActionListener(e -> {
            String username = usernameFiled.getText().trim();
            String password1 = passwordFiled.getText().trim();
            String password2 = confirmPasswordFiled.getText().trim();
            String verifyCodeCheck = verifyCodeFiled.getText().toLowerCase().trim();

            if ("".equals(username)) {
                JOptionPane.showMessageDialog(
                        this,
                        "请输入用户名",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            }
            if ("".equals(password1)) {
                JOptionPane.showMessageDialog(
                        this,
                        "请输入密码",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            } else if (!password1.equals(password2)) {
                JOptionPane.showMessageDialog(
                        this,
                        "两次密码不一致！",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            } else if ("".equals(verifyCodeCheck)) {
                JOptionPane.showMessageDialog(
                        this,
                        "请输入验证码！",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            } else if (verifyCodeGen.equals(verifyCodeCheck)) {
                AdminService adminService = new AdminService();
                Admin admin = adminService.selectByName(username);
                if (admin == null) {
                    boolean flag = adminService.register(new Admin(username, password1));
                    if (flag) {
                        JOptionPane.showMessageDialog(
                                this,
                                "注册成功！请登录",
                                "",
                                JOptionPane.INFORMATION_MESSAGE,
                                null);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "用户名已存在",
                            "",
                            JOptionPane.WARNING_MESSAGE,
                            null);
                }
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "验证码错误",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            }
        });

        returnBtn.addActionListener(e -> {
            this.dispose();
            try {
                new MainInterface().init();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        verifyCodeImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    verifyCodeImage.refreshVerifyImg();
                    verifyCodeGen=GetVerifyCodeImageDataUtil.verifyCodeGen;
                }
            }
        }
        );

    }
}

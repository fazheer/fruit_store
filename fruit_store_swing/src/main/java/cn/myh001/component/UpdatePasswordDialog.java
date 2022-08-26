package cn.myh001.component;

import cn.myh001.pojo.Admin;
import cn.myh001.service.AdminService;
import cn.myh001.util.DialogPositUtil;
import cn.myh001.util.GetVerifyCodeImageDataUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdatePasswordDialog extends JDialog {
    JLabel title ;
    JPanel passwordPanel ;
    JPanel confirmPasswordPanel ;
    JPanel verifyCodePanel;
    JPanel btnPanel;
    JLabel passwordText;
    JLabel confirmPasswordText;
    JLabel verifyCodeText;
    JTextField passwordFiled;
    JTextField confirmPasswordFiled;
    JTextField verifyCodeFiled;
    JButton confirmChangeBtn ;
    JButton cancelBtn ;

    String verifyCodeGen;
    VerifyImgLabel verifyCodeImage;
    byte[] imageData;
    String username;

    public UpdatePasswordDialog(JFrame frame,String username) {
        super(frame,"修改密码",true);
        this.username=username;
        this.init();
        this.addComponent();
        this.addListener();
        this.setBounds(DialogPositUtil.getDialogX(frame),DialogPositUtil.getDialogY(frame),400,400);
        this.setVisible(true);
    }

    public void init() {

        //标题
        title = new JLabel("修改密码", JLabel.CENTER);
        title.setFont(new Font("",Font.BOLD, 20));
        //密码
        passwordPanel = new JPanel(new FlowLayout());
        passwordText = new JLabel("新密码:");
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
        verifyCodePanel = new JPanel(new FlowLayout());
        verifyCodeText = new JLabel("验证码:");
        verifyCodeFiled = new JTextField(5);
        imageData = GetVerifyCodeImageDataUtil.imageData();
        verifyCodeImage = new VerifyImgLabel(imageData);
        verifyCodeGen = GetVerifyCodeImageDataUtil.verifyCodeGen;

        verifyCodePanel.add(verifyCodeText);
        verifyCodePanel.add(verifyCodeFiled);
        verifyCodePanel.add(verifyCodeImage);
        //确认按钮
        btnPanel = new JPanel(new FlowLayout());
        confirmChangeBtn = new JButton("确认修改");
        cancelBtn = new JButton("取消修改");
        btnPanel.add(confirmChangeBtn);
        btnPanel.add(cancelBtn);


    }
    public void addComponent() {
        //4行一列的网格布局
        this.setLayout(new GridLayout(5,1,0,0));
        this.add(title);
        this.add(passwordPanel);
        this.add(confirmPasswordPanel);
        this.add(verifyCodePanel);
        this.add(btnPanel);

    }

    public void addListener() {
        confirmChangeBtn.addActionListener(e -> {
            String password = passwordFiled.getText().trim();
            String confirmPassword = confirmPasswordFiled.getText().trim();
            String verifyCodeCheck = verifyCodeFiled.getText().trim();
            if ("".equals(password)) {
                JOptionPane.showMessageDialog(
                        UpdatePasswordDialog.this,
                        "请输入密码",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(
                        UpdatePasswordDialog.this,
                        "两次密码不一致！",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            }else if ("".equals(verifyCodeCheck)) {
                JOptionPane.showMessageDialog(
                        UpdatePasswordDialog.this,
                        "请输入验证码！",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            } else if (verifyCodeGen.equals(verifyCodeCheck)) {
                AdminService adminService = new AdminService();
                adminService.update(new Admin(username, password));
                JOptionPane.showMessageDialog(
                        UpdatePasswordDialog.this,
                        "修改成功！",
                        "",
                        JOptionPane.INFORMATION_MESSAGE,
                        null);
            }else{
                JOptionPane.showMessageDialog(
                        UpdatePasswordDialog.this,
                        "验证码错误",
                        "",
                        JOptionPane.WARNING_MESSAGE,
                        null);
            }

        });
        cancelBtn.addActionListener(e->UpdatePasswordDialog.this.dispose());

        verifyCodeImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    verifyCodeImage.refreshVerifyImg();
                    verifyCodeGen = GetVerifyCodeImageDataUtil.verifyCodeGen;


                }
            }
        });
    }


}

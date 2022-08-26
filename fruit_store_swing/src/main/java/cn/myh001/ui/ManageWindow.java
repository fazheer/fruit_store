package cn.myh001.ui;

import cn.myh001.component.AddFruitDialog;
import cn.myh001.component.UpdateFruitDialog;
import cn.myh001.component.UpdatePasswordDialog;
import cn.myh001.listener.TableMouseListener;
import cn.myh001.model.MyTableModel;
import cn.myh001.service.FruitService;
import cn.myh001.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ManageWindow extends JFrame{


    //宽高
    int WIDTH = ScreenUtils.getScreenWidth() / 2;
    int HEIGHT = ScreenUtils.getScreenHeight() / 2;
    //顶部显示的用户名
    String username;

    FruitService fruitService = new FruitService();

    JTable table = new JTable(new MyTableModel());

    JPanel topPanel;
    JLabel welcomeText;
    JLabel tableTitle;
    JButton addFruitBtn;
    JButton refreshBtn;
    JMenuItem updateFruitBtn;
    JMenuItem deleteFruitBtn;
    JPopupMenu updateFruitMenu;
    JButton exitBtn;
    JButton updateAdminBtn;
    JScrollPane tablePanel;


    public ManageWindow(String username) {

        this.username = username;

        this.init();
        this.addComponent();
        this.addListener();
        this.setBounds(
                (ScreenUtils.getScreenWidth() - WIDTH) / 2,
                (ScreenUtils.getScreenHeight() - HEIGHT) / 2,
                WIDTH,
                HEIGHT
        );
        try {
            this.setIconImage(ImageIO.read(
                    new File("D:\\Java_Project\\fruit_store_swing\\images\\img.png")
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void init() {
        //组装上部分 管理员信息，以及对水果新增按钮
        topPanel = new JPanel(new FlowLayout());
        //新增按钮，点击后弹出添加水果对话框
        addFruitBtn = new JButton("新增");
        //刷新按钮，点击后刷新表格内容
        refreshBtn = new JButton("刷新");
        //欢迎
        welcomeText = new JLabel(username + "欢迎您！");
        //点击后返回登录界面
        exitBtn = new JButton("退出登录");
        //点击后弹出修改密码对话框
        updateAdminBtn = new JButton("修改密码");
        //组装
        topPanel.add(addFruitBtn);
        topPanel.add(refreshBtn);
        //间隔
        topPanel.add(Box.createHorizontalStrut(WIDTH - 480));
        topPanel.add(welcomeText);
        topPanel.add(exitBtn);
        topPanel.add(updateAdminBtn);
        //表格的右键菜单
        updateFruitMenu = new JPopupMenu();
        //点击后弹出修改水果的对话框，修改后自动刷新表格内容
        updateFruitBtn = new JMenuItem("修改");
        //点击后删除，并刷新表格内容
        deleteFruitBtn = new JMenuItem("删除");
        updateFruitMenu.add(updateFruitBtn);
        updateFruitMenu.add(deleteFruitBtn);

        tableTitle = new JLabel("水果信息", JLabel.CENTER);
        tableTitle.setFont(new Font("", Font.BOLD, 30));
        table.setModel(new MyTableModel());
        //设置表格的行高
        table.setRowHeight(40);
        table.setFont(new Font("", Font.BOLD,14));
        //装表格的scrollPane
        tablePanel = new JScrollPane(table);
    }
    public void addComponent() {

        this.add(topPanel,BorderLayout.NORTH);
       Box tableBox = Box.createVerticalBox();
       Box titleBox = Box.createHorizontalBox();
       titleBox.add(tableTitle);
       tableBox.add(titleBox);
       tableBox.add(Box.createVerticalStrut(50));
       tableBox.add(tablePanel);
        this.add(tableBox);
    }
    public void addListener() {

        exitBtn.addActionListener(e -> {
            this.dispose();
            try {
                new MainInterface().init();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        refreshBtn.addActionListener(e -> freshTable());
        updateAdminBtn.addActionListener(e -> new UpdatePasswordDialog(this,username));

        addFruitBtn.addActionListener(e -> {
            new AddFruitDialog(this);
            freshTable();
        });


        updateFruitBtn.addActionListener(e -> {
            new UpdateFruitDialog(this,TableMouseListener.fruitId);
            freshTable();

        });
        deleteFruitBtn.addActionListener(e -> {
            fruitService.deleteById(TableMouseListener.fruitId);
            freshTable();
        });
        table.addMouseListener(new TableMouseListener(table, updateFruitMenu));
    }

    public void freshTable() {
        MyTableModel myTableModel = new MyTableModel();
        table.setModel(myTableModel);
    }

  

}

package cn.myh001.component;

import cn.myh001.pojo.Fruit;
import cn.myh001.service.FruitService;
import cn.myh001.util.DialogPositUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 添加水果的弹窗对话
 *
 */
public class AddFruitDialog extends JDialog {

    JLabel title;
    JPanel namePanel ;
    JPanel pricePanel ;
    JPanel unitPanel;

    JLabel fruitNameText;
    JLabel fruitPriceText;
    JLabel saleUnitText;

    JTextField fruitNameFiled;
    JTextField fruitPriceFiled;
    JTextField saleUnitFiled;

    JPanel btnPanel ;
    JButton addFruitBtn ;
    JButton cancelBtn ;
    FruitService fruitService = new FruitService();
    JFrame fatherFrame;
    int WIDTH = 400;
    int HEIGHT =400;


    public AddFruitDialog(JFrame fatherFrame) {
        super(fatherFrame,"添加水果",true);
        this.fatherFrame =fatherFrame;
        this.init();
        this.addComponent();
        this.addListener();
        this.setBounds(DialogPositUtil.getDialogX(fatherFrame),DialogPositUtil.getDialogY(fatherFrame),WIDTH,HEIGHT);
        this.setVisible(true);
    }
    public void init() {

        //初始化标题，设置标题字体
        title = new JLabel("添加水果", JLabel.CENTER);
        title.setFont(new Font("",Font.BOLD, 20));
        //水果名称
        namePanel = new JPanel(new FlowLayout());
        fruitNameText = new JLabel("水果名称:");
        fruitNameFiled = new JTextField(15);
        namePanel.add(fruitNameText);
        namePanel.add(fruitNameFiled);
        //水果价格
        pricePanel = new JPanel(new FlowLayout());
        fruitPriceText = new JLabel("水果价格:");
        fruitPriceFiled = new JTextField(15);
        pricePanel.add(fruitPriceText);
        pricePanel.add(fruitPriceFiled);
        //计价单位,流式布局
        unitPanel = new JPanel(new FlowLayout());
        saleUnitText = new JLabel("计价单位:");
        saleUnitFiled = new JTextField(15);
        unitPanel.add(saleUnitText);
        unitPanel.add(saleUnitFiled);
        //添加水果的按钮，和取消添加的按钮
        btnPanel = new JPanel(new FlowLayout());
        addFruitBtn = new JButton("添加水果");
        cancelBtn = new JButton("取消添加");
        btnPanel.add(addFruitBtn);
        btnPanel.add(cancelBtn);
    }
    public void addComponent() {
        //设置为网格布局
        this.setLayout(new GridLayout(5,1,0,0));
        //把组装好的面板添加至对话框
        this.add(title);
        this.add(namePanel);
        this.add(pricePanel);
        this.add(unitPanel);
        this.add(btnPanel);
    }
    public void addListener() {
        //给对话框中的两个按钮分别添加事件监听
        addFruitBtn.addActionListener(e -> {
            String fruitName = fruitNameFiled.getText().trim();
            Double fruitPrice = Double.valueOf(fruitPriceFiled.getText().trim());
            String saleUnit = saleUnitFiled.getText().trim();
            Fruit fruit = new Fruit();
            fruit.setFruitName(fruitName);
            fruit.setFruitPrice(fruitPrice);
            fruit.setSaleUnit(saleUnit);
            fruitService.add(fruit);
            JOptionPane.showMessageDialog(
                    AddFruitDialog.this,
                    "添加成功！"
                    ,"添加成功",
                    JOptionPane.INFORMATION_MESSAGE
            );
            AddFruitDialog.this.dispose();
        });

        cancelBtn.addActionListener(e -> AddFruitDialog.this.dispose());
    }


}

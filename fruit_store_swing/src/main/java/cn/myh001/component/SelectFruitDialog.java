package cn.myh001.component;

import cn.myh001.listener.TableMouseListener;
import cn.myh001.model.MyTableModel;
import cn.myh001.pojo.Fruit;
import cn.myh001.service.FruitService;
import cn.myh001.util.DialogPositUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SelectFruitDialog extends JDialog {

    int WIDTH = 600;
    int HEIGHT = 600;
    JLabel title;
    JTable table;
    JScrollPane tablePane;

    String inputText;

    JPopupMenu updateFruitMenu;
    JMenuItem updateFruitBtn;
    JMenuItem deleteFruitBtn;

    FruitService fruitService = new FruitService();
    List<Fruit> fruits;

    JFrame fatherFrame;

    public SelectFruitDialog(JFrame fatherFrame,String inputText) {
        this.inputText = inputText;
        this.fatherFrame=fatherFrame;
        this.init();
        this.addComponent();
        this.addListener();
        this.setBounds(
                DialogPositUtil.getDialogX(fatherFrame),
                DialogPositUtil.getDialogY(fatherFrame),
                WIDTH,HEIGHT
        );
        this.setModal(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }
    public void init() {

        fruits = fruitService.selectByName(inputText);

        title = new JLabel("'"+inputText+"'"+"的查询结果如下",SwingConstants.CENTER);
        title.setFont(new Font("", Font.BOLD, 30));

        table = new JTable(new MyTableModel(fruits));
        tablePane = new JScrollPane(table);

        updateFruitMenu = new JPopupMenu();
        //点击后弹出修改水果的对话框，修改后自动刷新表格内容
        updateFruitBtn = new JMenuItem("修改");
        //点击后删除，并刷新表格内容
        deleteFruitBtn = new JMenuItem("删除");
        updateFruitMenu.add(updateFruitBtn);
        updateFruitMenu.add(deleteFruitBtn);


    }
    public void addComponent() {

        Box verticalBox = Box.createVerticalBox();
        Box titleBox = Box.createHorizontalBox();
        titleBox.add(title);
        verticalBox.add(titleBox);
        verticalBox.add(Box.createVerticalStrut(30));
        verticalBox.add(tablePane);
        this.add(verticalBox);

    }
    public void addListener() {
        updateFruitBtn.addActionListener(e->{
            new UpdateFruitDialog(this.fatherFrame,TableMouseListener.fruitId);
            freshTable();
        });
        deleteFruitBtn.addActionListener(e->{
            fruitService.deleteById(TableMouseListener.fruitId);
            freshTable();
        });
        table.addMouseListener(new TableMouseListener(table,updateFruitMenu));
    }
    public void freshTable() {
        fruits = fruitService.selectByName(inputText);
        MyTableModel myTableModel = new MyTableModel(fruits);
        table.setModel(myTableModel);
    }
}

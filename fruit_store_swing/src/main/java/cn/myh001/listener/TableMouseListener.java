package cn.myh001.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableMouseListener extends MouseAdapter {

    JTable table;
    JPopupMenu jPopupMenu;
   public static int fruitId;
    public TableMouseListener(JTable table, JPopupMenu jPopupMenu) {
            this.table = table;
            this.jPopupMenu = jPopupMenu;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) { //获取鼠标右键点击事件
            jPopupMenu.show(table, e.getX(), e.getY());
            int rowIndex = table.rowAtPoint(e.getPoint());
            table.setRowSelectionInterval(rowIndex, rowIndex);
            fruitId = (int) table.getModel().getValueAt(rowIndex, 0);
            System.out.println(fruitId);
        }
    }
}

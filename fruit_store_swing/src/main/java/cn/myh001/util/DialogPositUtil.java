package cn.myh001.util;

import javax.swing.*;

public class DialogPositUtil {
    //该工具类用以得到对话框显示的X，Y坐标
    public static int getDialogX(JFrame fatherFrame) {
        return fatherFrame.getX()+300;
    }
    public static int getDialogY(JFrame fatherFrame) {
        return fatherFrame.getY()+150;
    }
}

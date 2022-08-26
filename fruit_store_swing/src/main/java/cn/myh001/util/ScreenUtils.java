package cn.myh001.util;

import java.awt.*;

public class ScreenUtils {

    //返回当前屏幕的宽度
    public static int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    //返回当前屏幕的高度
    public static int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}

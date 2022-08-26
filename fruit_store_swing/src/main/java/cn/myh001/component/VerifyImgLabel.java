package cn.myh001.component;
import cn.myh001.util.GetVerifyCodeImageDataUtil;

import javax.swing.*;
import java.awt.*;

public class VerifyImgLabel extends JLabel {


    byte[] imgData;
    public VerifyImgLabel(byte[] imgData) {
        super(new ImageIcon(imgData));

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    public void refreshVerifyImg() {
        //刷新图片
        this.imgData= GetVerifyCodeImageDataUtil.imageData();
        this.setIcon(new ImageIcon(imgData));
        this.repaint();
    }

}

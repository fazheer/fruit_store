package cn.myh001.component;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {

    Image bgImage;

    public BackGroundPanel(Image backgroundImage) {
        this.bgImage = backgroundImage;
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}

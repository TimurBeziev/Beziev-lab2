package BWidgets;

import java.awt.*;
import javax.swing.*;

public class BButton extends JButton {

    JButton button;
    public BButton(int x, int y, String imageSource) {
//        buttonImage = new ImageIcon(imageSource).getImage();
        button = new JButton("hello");
//        button.setBounds(x, y, buttonImage.getWidth(null), buttonImage.getHeight(null));
//        button.setPreferredSize(new Dimension(300, 300));
        button.setBounds(x, y, 500,500);
    }

}

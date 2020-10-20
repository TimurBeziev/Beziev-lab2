package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddElementsPanel extends CreateGUIItems {
    public JPanel addElementBackground;
    public JPanel addElementItems;

    public AddElementsPanel() throws IOException {
        addElementBackground = CreateImage("src/img/addElementPannel.png");
        addElementItems = CreateAddElementItems();
    }


    private JPanel CreateAddElementItems() {
        JPanel backpackImage = new JPanel();
        backpackImage.add(new JLabel("geg"));
        backpackImage.setBounds(0, 0, 100, 300);
        backpackImage.setOpaque(true);
        return backpackImage;
    }
}

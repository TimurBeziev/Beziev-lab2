package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateGUIItems extends JPanel {
    final Color backgroundColor = new Color(215, 226, 238);
    final Color foregroundColor = new Color(227, 237, 247);
    final Color transparentColor = new Color(0, 0, 0, 0);
    final Font labelsInfoFont = new Font("Segoe UI", Font.PLAIN, 50);
    final Font labelsInBackpackFont = new Font("Segoe UI", Font.PLAIN, 45);

    static JButton addButton(String imgPath) throws IOException {
        BufferedImage buttonIcon = ImageIO.read(new File(imgPath));
        JButton button = new JButton(new ImageIcon(buttonIcon));
        button.setBounds(0, 0, buttonIcon.getWidth(), buttonIcon.getHeight());
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;
    }

    static JLabel addImage(String imgPath) throws IOException {
        BufferedImage img = ImageIO.read(new File(imgPath));
        ImageIcon icon = new ImageIcon(img);
        Image scaleImage = icon.getImage().getScaledInstance
                (icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT);
        ImageIcon icn = new ImageIcon(scaleImage);
        JLabel label = new JLabel(icn);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        return label;
    }

    JPanel CreateImage(String img_path) throws IOException {
        JPanel backpackImage = new JPanel();
        backpackImage.setBackground(backgroundColor);
        JLabel backpackImg = addImage(img_path);
        backpackImage.add(backpackImg);
        backpackImage.setBounds(0, 0, backpackImg.getWidth(), backpackImg.getHeight());
        backpackImage.setOpaque(true);
        return backpackImage;
    }
}

package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

public class GUI {

    private final Color backgroundColor = new Color(215, 226, 238);

    public static JButton addButton(String imgPath) throws IOException {
        BufferedImage buttonIcon = ImageIO.read(new File(imgPath));
        JButton button = new JButton(new ImageIcon(buttonIcon));
        button.setBounds(0, 0, buttonIcon.getWidth(), buttonIcon.getHeight());
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;
    }

    public static JLabel addImage(String imgPath) throws IOException {
        BufferedImage img = ImageIO.read(new File(imgPath));
        ImageIcon icon = new ImageIcon(img);
        Image scaleImage = icon.getImage().getScaledInstance
                (icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT);
        ImageIcon icn = new ImageIcon(scaleImage);
        JLabel label = new JLabel(icn);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        return label;
    }

    private JPanel CreateButtonsPanel() throws IOException {
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1));
        buttonsPanel.setBackground(backgroundColor);
        JButton addButton = addButton("src/img/ADD BUTTON.png");
        JButton deleteButton = addButton("src/img/DELETE BUTTON.png");
        JButton phdButton = addButton("src/img/PHD.png");
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(phdButton);
        buttonsPanel.setBorder(new EmptyBorder(200, 10, 200, 10));
        buttonsPanel.setOpaque(true);
        return buttonsPanel;
    }

    private JPanel CreateBackpackImage() throws IOException {
        JPanel backpackImage = new JPanel();
        backpackImage.setBackground(backgroundColor);
        JLabel backpackImg = addImage("src/img/BackpackImage.png");
        backpackImage.add(backpackImg);
        backpackImage.setBounds(0, 0, backpackImg.getWidth(), backpackImg.getHeight());
        backpackImage.setOpaque(true);
        return backpackImage;
    }

    public GUI() throws IOException {
        JFrame mainscene = new JFrame("Laba 2");
        JPanel window = new JPanel();
        window.setLayout(new BoxLayout(window, BoxLayout.X_AXIS));


        JPanel buttonsPanel = CreateButtonsPanel();
        JPanel backpackImage = CreateBackpackImage();
        window.add(backpackImage);
        window.add(buttonsPanel);
        window.setBounds(0, 0, 1920, 1080);


        JPanel secondLayer = CreateButtonsPanel();
        secondLayer.setBounds(1920 / 4, 1080 / 4, 200, 200);

        JLayeredPane layers = new JLayeredPane();
        layers.add(window, 0, 0);
        layers.add(secondLayer, 1, 1);
        layers.setVisible(true);

        mainscene.add(layers);
        mainscene.setSize(1920, 1080);
        mainscene.setMinimumSize(new Dimension(1920, 1080));
        mainscene.setMaximumSize(new Dimension(1920, 1080));
        mainscene.setVisible(true);
    }
}

package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import Controller.Controller;

public class GUI {
    Controller controller;
    private final Color backgroundColor = new Color(215, 226, 238);
    private final Color transparentColor = new Color(0, 0, 0, 0);
    private final Font labelsInfoFont = new Font("Segoe UI", Font.PLAIN, 50);

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

    private JPanel CreateBackpackInfo() throws IOException {
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));

        String[][] data = {
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"},
                {"Parallelepiped", "4031.290"}
        };

        // Column Names
        String[] columnNames = {"Item", "Volume"};

        JTable backpackInfo = new JTable(data, columnNames);
        backpackInfo.setBackground(backgroundColor);

        backpackInfo.setColumnSelectionAllowed(false);
        backpackInfo.getTableHeader().setBackground(backgroundColor);
        backpackInfo.setRowHeight(70);
        backpackInfo.setFont(labelsInfoFont);
        backpackInfo.setShowGrid(false);

        JScrollPane backpackScroll = new JScrollPane(backpackInfo);
        backpackScroll.setBackground(backgroundColor);
        backpackScroll.getVerticalScrollBar().setBackground(backgroundColor);

        info.add(backpackScroll);
        info.setBackground(transparentColor);

        return info;
    }

    public GUI() throws IOException {
        JFrame mainscene = new JFrame("Laba 2");
        JPanel firstLayer = new JPanel();
        firstLayer.setLayout(new BoxLayout(firstLayer, BoxLayout.X_AXIS));
        controller = new Controller();


        JPanel buttonsPanel = CreateButtonsPanel();
        JPanel backpackImage = CreateBackpackImage();
        firstLayer.add(backpackImage);
        firstLayer.add(buttonsPanel);
        firstLayer.setBounds(0, 0, 1920, 1080);


        JPanel secondLayer = CreateBackpackInfo();
        secondLayer.setBounds(220, 350, 820, 450);

        JLayeredPane layers = new JLayeredPane();
        // backpack image and button
        layers.add(firstLayer, 0, 0);
        // backpack info
        layers.add(secondLayer, 1, 1);
        layers.setVisible(true);

        mainscene.add(layers);
        mainscene.setSize(1920, 1080);
        mainscene.setMinimumSize(new Dimension(1920, 1080));
        mainscene.setMaximumSize(new Dimension(1920, 1080));
        mainscene.setVisible(true);
    }
}

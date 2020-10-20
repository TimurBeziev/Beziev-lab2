package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import GUI.AddElementsPanel;


public class GUI extends CreateGUIItems {
    JLayeredPane layers;
    JButton addButton;
    JButton deleteButton;
    JButton phdButton;
    DefaultTableModel model;
    String[][] data = {};

    public GUI() throws IOException {
        JFrame mainscene = new JFrame("Laba 2");
        JPanel firstLayer = new JPanel();

        firstLayer.setLayout(new BoxLayout(firstLayer, BoxLayout.X_AXIS));
        JPanel buttonsPanel = CreateButtonsPanel();
        JPanel backpackImage = CreateImage("src/img/BackpackImage.png");
        firstLayer.add(backpackImage);
        firstLayer.add(buttonsPanel);
        firstLayer.setBounds(0, 0, 1920, 1080);

        JPanel secondLayer = CreateBackpackInfo();
        secondLayer.setBounds(220, 350, 820, 450);


        layers = new JLayeredPane();
        // backpack image and button
        AddPanelToLayers(firstLayer, 0, 0);
        // backpack info
        AddPanelToLayers(secondLayer, 1, 1);
        layers.setVisible(true);

        mainscene.add(layers);
        mainscene.setSize(1920, 1080);
        mainscene.setMinimumSize(new Dimension(1920, 1080));
        mainscene.setMaximumSize(new Dimension(1920, 1080));
        mainscene.setVisible(true);
    }

    private JPanel CreateButtonsPanel() throws IOException {
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1));
        buttonsPanel.setBackground(backgroundColor);
        addButton = addButton("src/img/ADD BUTTON.png");
        deleteButton = addButton("src/img/DELETE BUTTON.png");
        phdButton = addButton("src/img/PHD.png");

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(phdButton);
        buttonsPanel.setBorder(new EmptyBorder(300, 10, 300, 10));
        buttonsPanel.setOpaque(true);
        return buttonsPanel;
    }

    private JPanel CreateBackpackInfo() throws IOException {
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));

        // Column Names
//        String[] columnNames = {"Item", "Volume"};
        JTable backpackInfo = new JTable();
        model = new DefaultTableModel();
        backpackInfo.setModel(model);
        model.addColumn("Item");
        model.addColumn("Volume");

//        JTable backpackInfo = new JTable(data, columnNames);
        backpackInfo.setBackground(backgroundColor);

        backpackInfo.setEnabled(false);
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

    public void SetAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void AddPanelToLayers(JPanel panel, int constraints, int index) {
        layers.add(panel, constraints, index);
    }

    public void AddBackpackInfo(String backpackItemName,String volume) {
        model.addRow(new Object[]{backpackItemName, volume});
    }

}
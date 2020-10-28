package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import Data.Shape;
import GUI.AddElementsPanel;


public class GUI extends CreateGUIItems {
    private final JLayeredPane layers;
    private JLabel backpackCapacity;
    private JTable backpackInfo;
    private JButton addButton;
    private JButton deleteButton;
    private JButton phdButton;
    private DefaultTableModel model;
    static JMenuBar menuBar;
    static JMenu menu;
    static JMenuItem loadFromFile, readToFile;
    private double vol;

    public GUI() throws IOException {
        JFrame mainscene = new JFrame("Laba 3");
        JPanel firstLayer = new JPanel();

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        loadFromFile = new JMenuItem("Load From File");
        readToFile = new JMenuItem("Save To File");

        menu.add(loadFromFile);
        menu.add(readToFile);

        menuBar.add(menu);

        mainscene.setJMenuBar(menuBar);


        firstLayer.setLayout(new BoxLayout(firstLayer, BoxLayout.X_AXIS));
        JPanel buttonsPanel = CreateButtonsPanel();
        JPanel backpackImage = CreateImage("src/img/BackpackImage.png");
        firstLayer.add(backpackImage);
        firstLayer.add(buttonsPanel);
        firstLayer.setBounds(0, 0, 1920, 1080);

        JPanel secondLayer = CreateBackpackInfo();
        secondLayer.setBounds(220, 350, 820, 450);
        secondLayer.setBackground(Color.yellow);

        JPanel thirdLayer = new JPanel();
        backpackCapacity = new JLabel();
        backpackCapacity.setFont(labelsInfoFont);
        backpackCapacity.setForeground(textFont);
        thirdLayer.setBackground(foregroundColor);
        thirdLayer.add(backpackCapacity);
        thirdLayer.setBounds(650, 194, 200, 70);


        layers = new JLayeredPane();
        // backpack image and button
        AddPanelToLayers(firstLayer, 0, 0);
        // backpack info
        AddPanelToLayers(secondLayer, 1, 1);
        AddPanelToLayers(thirdLayer, 2, 2);
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


        backpackInfo = new JTable();
        backpackInfo.setDefaultEditor(Object.class, null);

        model = new DefaultTableModel();
        model.addColumn("Item");
        model.addColumn("Volume");

        backpackInfo.setModel(model);

        backpackInfo.setBackground(backgroundColor);

//        backpackInfo.setEnabled(fals
//        backpackInfo.setColumnSelectionAllowed(false);
        backpackInfo.getTableHeader().setBackground(backgroundColor);
        backpackInfo.setRowHeight(70);
        backpackInfo.setFont(labelsInfoFont);
        backpackInfo.setShowGrid(false);

        JScrollPane backpackScroll = new JScrollPane(backpackInfo);
        backpackScroll.setBackground(backgroundColor);
        backpackScroll.getVerticalScrollBar().setBackground(backgroundColor);

        info.add(backpackScroll);
        info.setBackground(backgroundColor);

        return info;
    }

    public int GetSelectedElement() {
        return backpackInfo.getSelectedRow();
    }

    public void SetAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void SetDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void SetPHDButtonListener(ActionListener listener) {
        phdButton.addActionListener(listener);
    }

    public void SetLoadFromFileActionListener(ActionListener listener) {
        loadFromFile.addActionListener(listener);
    }

    public void SetReadToFileActionListener(ActionListener listener) {
        readToFile.addActionListener(listener);
    }

    public void AddPanelToLayers(JPanel panel, int constraints, int index) {
        layers.add(panel, constraints, index);
    }

    public void SetBackpackVolume(String volume) {
        backpackCapacity.setText(volume);
    }

    public void UpdateBackpackInfo(DefaultTableModel model1, String backpackVolume) {
        ClearAll();
        model = model1;
        backpackInfo.setModel(model);
        backpackCapacity.setText(backpackVolume);
    }

    private void ClearAll() {
        for (int i = 0; i < model.getRowCount(); i++) {
            RemoveElement(i);
        }
    }

    public void RemoveElement(int index) {
        model.removeRow(index);
    }

}
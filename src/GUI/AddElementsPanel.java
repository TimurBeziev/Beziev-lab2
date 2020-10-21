package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddElementsPanel extends CreateGUIItems {
    public JPanel addElementBackground;
    public JPanel addElementItems;
    public JTextField parameter;
    public JComboBox backpackItems;
    public JButton addElementButtonSmall;
    public JButton cancelElementButtonSmall;

    public AddElementsPanel() throws IOException {
        addElementBackground = CreateImage("src/img/addElementPannel.png");
        addElementBackground.setBounds(1300, 170, addElementBackground.getWidth(), addElementBackground.getHeight());
        addElementItems = CreateAddElementItems();
    }

    private JPanel CreateAddElementItems() throws IOException {
        JPanel addButtonItems = new JPanel(new GridLayout(6, 1));

        String[] items = {
                "Cube",
                "Cylinder",
                "Parallelepiped",
                "Sphere"
        };

        backpackItems = new JComboBox(items);
        backpackItems.setFont(labelsInBackpackFont);
        backpackItems.setBackground(foregroundColor);


        parameter = new JTextField();
        parameter.setFont(labelsInfoFont);
        parameter.setBackground(foregroundColor);
        parameter.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(c >= '0' && c <= '9')) {
                    e.consume();
                }
            }
        });

        JPanel buttons = new JPanel(new GridLayout(1, 2));
        addElementButtonSmall = addButton("src/img/ADD Small.png");
        cancelElementButtonSmall = addButton("src/img/CANCEL Small.png");
        buttons.add(addElementButtonSmall);
        buttons.add(cancelElementButtonSmall);
        buttons.setBackground(foregroundColor);


        addButtonItems.add(backpackItems);
        addButtonItems.add(new JLabel());
        addButtonItems.add(new JLabel());
        addButtonItems.add(parameter);
        addButtonItems.add(new JLabel());
        addButtonItems.add(buttons);


        addButtonItems.setBounds(1360, 250, addElementBackground.getWidth() * 3 / 4, 600);
        addButtonItems.setBackground(foregroundColor);
        addButtonItems.setOpaque(true);
        return addButtonItems;
    }

    public void SetCancelButtonListener(ActionListener listener) {
        cancelElementButtonSmall.addActionListener(listener);
    }

    public void SetAddElementButtonListener(ActionListener listener) {
        addElementButtonSmall.addActionListener(listener);
    }
}

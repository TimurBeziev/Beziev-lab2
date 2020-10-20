package Controller;

import GUI.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import GUI.AddElementsPanel;

import javax.swing.*;

public class Controller {
    GUI gui;
    JPanel addElementBackground;

    public Controller() throws IOException {
        gui = new GUI();
        AddElementsPanel addElementsPanel = new AddElementsPanel();
        addElementBackground = addElementsPanel.addElementBackground;

        ActionListener addButtonListener = new AddButtonListener();
        gui.SetAddButtonListener(addButtonListener);


//        gui.SetBackpackInfo(data);
    }

    public class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.AddPanelToLayers(addElementBackground, 2,2);
            String[][] data = {
                    {"Parallelepiped", "4031.290"},
                    {"Parallelepiped", "000.8"}
            };
            gui.AddBackpackInfo("keke", "jej");
        }
    }

}



package Controller;

import Backpack.Backpack;
import GUI.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import GUI.AddElementsPanel;

import javax.swing.*;

public class Controller {
    GUI gui;
    Backpack backpack;
    JPanel addElementBackground;
    JPanel getAddElementItems;
    boolean isAnyButtonEnabled = true;

    public Controller() throws IOException {
        gui = new GUI();
        backpack = new Backpack(500);
        AddElementsPanel addElementsPanel = new AddElementsPanel();
        ActionListener cancelButtonListener = new CancelButtonListener();
        addElementsPanel.SetCancelButtonListener(cancelButtonListener);

        addElementBackground = addElementsPanel.addElementBackground;
        getAddElementItems = addElementsPanel.addElementItems;

        getAddElementItems.setVisible(false);
        addElementBackground.setVisible(false);

        gui.AddPanelToLayers(addElementBackground, 2, 2);
        gui.AddPanelToLayers(getAddElementItems, 3, 3);

        ActionListener addButtonListener = new AddButtonListener();
        gui.SetAddButtonListener(addButtonListener);

    }

    public class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (isAnyButtonEnabled) {
                getAddElementItems.setVisible(true);
                addElementBackground.setVisible(true);
//                gui.AddPanelToLayers(addElementBackground, 2, 2);
//                gui.AddPanelToLayers(getAddElementItems, 3, 3);
                gui.AddBackpackInfo("keke", "jej");
                isAnyButtonEnabled = false;
            }
        }
    }
    public class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("je");
            getAddElementItems.setVisible(false);
            addElementBackground.setVisible(false);
            isAnyButtonEnabled = true;
        }
    }

}



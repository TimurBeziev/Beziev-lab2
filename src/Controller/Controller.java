package Controller;

import Backpack.Backpack;
import Data.Cube;
import Data.Cylinder;
import Data.Parallelepiped;
import Data.Sphere;
import GUI.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

import GUI.AddElementsPanel;
import GUI.PHDPannel;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Controller {
    GUI gui;
    Backpack backpack;
    JPanel addDialogBackground;
    JPanel addDialogItems;
    JPanel phdPannel;
    PHDPannel pannel;
    AddElementsPanel addElementsPanel;
    boolean isAnyButtonEnabled = true;

    public Controller() throws Exception {
        gui = new GUI();
        backpack = new Backpack(50000);

        String volume = String.format("%.1f", backpack.GetBackpackVolume());
        gui.SetBackpackVolume(volume);

        pannel = new PHDPannel();
        phdPannel = pannel.GetPGDPannel();
        phdPannel.setVisible(false);

        // Создаю панельку с добавлением элементов и связываю кнопочки
        addElementsPanel = new AddElementsPanel();
        ActionListener cancelButtonListener = new CancelButtonListener();
        ActionListener addElementButtonListner = new AddElementButtonListener();

        addElementsPanel.SetCancelButtonListener(cancelButtonListener);
        addElementsPanel.SetAddElementButtonListener(addElementButtonListner);

        addDialogBackground = addElementsPanel.addElementBackground;
        addDialogItems = addElementsPanel.addElementItems;

        addDialogItems.setVisible(false);
        addDialogBackground.setVisible(false);

        gui.AddPanelToLayers(addDialogBackground, 2, 2);
        gui.AddPanelToLayers(addDialogItems, 3, 3);
        gui.AddPanelToLayers(phdPannel, 4, 4);

        ActionListener openAddDialogListener = new AddButtonListener();
        gui.SetAddButtonListener(openAddDialogListener);

        ActionListener deleteElementListner = new DeleteButtonListener();
        gui.SetDeleteButtonListener(deleteElementListner);

        ActionListener phdButtonListner = new PHDButtonListener();
        gui.SetPHDButtonListener(phdButtonListner);

        ActionListener phdImageButtonListner = new PHDImageButtonListener();
        pannel.SetCancelButtonListener(phdImageButtonListner);

        ActionListener loadFromFileListener = new LoadFromFileListener();
        gui.SetLoadFromFileActionListener(loadFromFileListener);

        ActionListener readToFileListener = new ReadToFileListener();
        gui.SetReadToFileActionListener(readToFileListener);

    }

    public class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (isAnyButtonEnabled) {
                addDialogItems.setVisible(true);
                addDialogBackground.setVisible(true);
                isAnyButtonEnabled = false;
            }
        }
    }

    public class PHDButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (isAnyButtonEnabled) {
                phdPannel.setVisible(true);
                isAnyButtonEnabled = false;
            }

        }
    }

    public class PHDImageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            phdPannel.setVisible(false);
            isAnyButtonEnabled = true;
        }
    }

    public class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (isAnyButtonEnabled) {
                int selectedIndex = gui.GetSelectedElement();
                if (selectedIndex >= 0) {
                    backpack.DeleteElement(selectedIndex);
                }

                String volume = String.format("%.1f", backpack.GetBackpackVolume());
                try {
                    gui.UpdateBackpackInfo(backpack.getShapesInfo(), volume);
                } catch (Exception parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                }
            }
        }
    }

    public class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            addDialogItems.setVisible(false);
            addDialogBackground.setVisible(false);
            isAnyButtonEnabled = true;
        }
    }

    public class AddElementButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String shapeName = (String) addElementsPanel.backpackItems.getSelectedItem();
            String shapeParameterString = addElementsPanel.parameter.getText();
            double shapeParameter = 0.0;
            if (!shapeParameterString.equals("")) {
                shapeParameter = Double.parseDouble(addElementsPanel.parameter.getText());
            }

            switch (Objects.requireNonNull(shapeName)) {
                case ("Cube"):
                    try {
                        backpack.addShape(new Cube(shapeParameter));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;

                case ("Cylinder"):
                    try {
                        backpack.addShape(new Cylinder(shapeParameter, shapeParameter));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;

                case ("Parallelepiped"):
//                    не спрашивайте меня в чем разница параллелепипеда и куба :)
//                    так надо!
                    try {
                        backpack.addShape(new Parallelepiped(shapeParameter, shapeParameter, shapeParameter));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;

                case ("Sphere"):
                    try {
                        backpack.addShape(new Sphere(shapeParameter));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
            }
//            сделал немного очень через... ноги. Ну да ладно
            String volume = String.format("%.1f", backpack.GetBackpackVolume());
            try {
                gui.UpdateBackpackInfo(backpack.getShapesInfo(), volume);
            } catch (Exception parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            }
        }
    }

    public class LoadFromFileListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                try {
                    ParseXML(selectedFile.getAbsolutePath());
                    System.out.println(selectedFile.getAbsolutePath());
                } catch (Exception parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                }
            }
        }
    }

    public class ReadToFileListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                DefaultTableModel model = backpack.getShapesInfo();
                WriteXML(model);
//                System.out.println(data);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void WriteXML(DefaultTableModel model) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

//        Vector data = model.getDataVector();

        // root elements
        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("shapes");
        doc.appendChild(rootElement);

        // staff elements
        for (int i = 0; i < model.getRowCount(); i++) {
            Element staff = doc.createElement("shape");
            rootElement.appendChild(staff);
            Element firstname = doc.createElement("shapeName");
            firstname.appendChild(doc.createTextNode((String) model.getValueAt(i, 0)));
            staff.appendChild(firstname);

            Element lastname = doc.createElement("shapeVolume");
            lastname.appendChild(doc.createTextNode(String.valueOf(model.getValueAt(i, 1))));
            staff.appendChild(lastname);
        }


        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/res/shapes2.xml"));

        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);

        transformer.transform(source, result);

        System.out.println("File saved!");
    }

    private void ParseXML(String strPath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(strPath));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all employees
        NodeList nList = document.getElementsByTagName("shape");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            System.out.println("");    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String volume = eElement.getElementsByTagName("shapeVolume").item(0).getTextContent();
                volume = volume.replace(',', '.');
                AddShapeWithVolumeFromFile(eElement.getElementsByTagName("shapeName").item(0).getTextContent(), Double.parseDouble(volume));
            }
        }
        String volume = String.format("%.1f", backpack.GetBackpackVolume());
        try {
            gui.UpdateBackpackInfo(backpack.getShapesInfo(), volume);
        } catch (Exception parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        }
    }

    void AddShapeWithVolumeFromFile(String shapeName, double shapeVolume) throws Exception {
        // дубликация кода, понимаю, но слишком торопился и не смог бы починить по-быстрому
        switch (Objects.requireNonNull(shapeName)) {
            case ("Cube"):
                Cube cube = new Cube(0);
                cube.setVolume(shapeVolume);
                backpack.addShape(cube);
                break;

            case ("Cylinder"):
                Cylinder cylinder = new Cylinder(0, 0);
                cylinder.setVolume(shapeVolume);
                backpack.addShape(cylinder);
                break;

            case ("Parallelepiped"):
                Parallelepiped parallelepiped = new Parallelepiped(0, 0, 0);
                parallelepiped.setVolume(shapeVolume);
                backpack.addShape(parallelepiped);
                break;

            case ("Sphere"):
                Sphere sphere = new Sphere(0);
                sphere.setVolume(shapeVolume);
                backpack.addShape(sphere);
                break;
        }
    }

}



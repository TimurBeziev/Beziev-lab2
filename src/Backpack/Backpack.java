package Backpack;

import Data.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sun.security.util.ArrayUtil;

import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;


public class Backpack {
    private final ArrayList<Shape> shapes;
    private double backpackVolume;

    public Backpack(double backpackVolume) {
        this.backpackVolume = backpackVolume;
        shapes = new ArrayList<Shape>();
    }

    public void addShape(Shape shape) throws Exception {
        backpackVolume -= shape.getVolume();
        if (backpackVolume < 0) {
            backpackVolume += shape.getVolume();
            showMessageDialog(null, "Вот тут мне короче надоело делать красиво. " +
                    "Не стоит класть такие большие предметы");
            throw new Exception("Backpack is already full!!!");
        } else {
//            backpackElements.put(shape.getVolume(), shape);
            shapes.add(InsertionIndex(shape.getVolume()), shape);
        }
    }

    public void DeleteElement(int deleteIndex) {
        Shape shape = shapes.get(deleteIndex);
        backpackVolume += shape.getVolume();
        shapes.remove(deleteIndex);
    }

    public double GetBackpackVolume() {
        return backpackVolume;
    }

    private Integer InsertionIndex(Double newVolume) {
        int insertionIndex = 0;
        while (insertionIndex < shapes.size() && newVolume <= shapes.get(insertionIndex).getVolume()) {
            insertionIndex++;
        }

        return insertionIndex;
    }


    public DefaultTableModel getShapesInfo() throws Exception {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Item");
        model.addColumn("Volume");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File("src/res/shapes.xml"));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all employees
        NodeList nList = document.getElementsByTagName("shape");
        System.out.println("============================");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            System.out.println("");    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                AddShapeFromFile(eElement.getElementsByTagName("shapeName").item(0).getTextContent(), Double.parseDouble(eElement.getElementsByTagName("shapeVolume").item(0).getTextContent()));
            }
        }

        for (Shape shape : shapes) {
            String volume = String.format("%.4f", shape.getVolume());
            model.addRow(new Object[]{shape.toString(), volume});
        }
        return model;
    }

    void AddShapeFromFile(String shapeName, double shapeVolume) throws Exception {

        switch (Objects.requireNonNull(shapeName)) {
            case ("Cube"):
                Cube cube = new Cube(0);
                cube.setVolume(shapeVolume);
                addShape(cube);
                break;

            case ("Cylinder"):
                Cylinder cylinder = new Cylinder(0, 0);
                cylinder.setVolume(shapeVolume);
                addShape(cylinder);
                break;

            case ("Parallelepiped"):
                Parallelepiped parallelepiped = new Parallelepiped(0, 0, 0);
                parallelepiped.setVolume(shapeVolume);
                addShape(parallelepiped);
                break;

            case ("Sphere"):
                Sphere sphere = new Sphere(0);
                sphere.setVolume(shapeVolume);
                addShape(sphere);
                break;
        }
    }

}


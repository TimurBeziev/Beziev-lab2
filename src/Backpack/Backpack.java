package Backpack;

import Data.Parallelepiped;
import Data.Shape;

import java.util.ArrayList;
import java.util.HashMap;

public class Backpack {
    private ArrayList<Shape> shapes;
    private double backpackVolume;

    public Backpack(double backpackVolume) {
        this.backpackVolume = backpackVolume;
        shapes = new ArrayList<Shape>();
    }

    public void addShape(Shape shape) throws Exception {
        backpackVolume -= shape.getVolume();
        if (backpackVolume < 0) {
            throw new Exception("Backpack is already full!!!");
        } else {
            shapes.add(shape);
        }
    }

    public void showShapes() {
        for (int i = 0; i < shapes.size(); i++) {
            System.out.println(shapes.get(i).toString());
        }
    }

    public HashMap<String, Integer> getShapesInfo() {
        HashMap<String, Integer> tempShapesArray = new HashMap<>();

        for (Shape shape : shapes) {
            String tempShape = shape.toString();
            if (!tempShapesArray.containsKey(tempShape)) {
                tempShapesArray.put(tempShape, 1);
            } else {
                tempShapesArray.put(tempShape, tempShapesArray.get(tempShape) + 1);
            }
        }

        return tempShapesArray;
    }
}

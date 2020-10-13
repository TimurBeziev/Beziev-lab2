package com.vozhicks;

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
import javax.xml.soap.SOAPPart;

import Backpack.Backpack;
import Data.Cube;
import Data.Cylinder;
import Data.Parallelepiped;
import Data.Sphere;
import GUI.GUI;

public class Main  {
    private static Backpack backpack;
    private static JFrame mainScene;

    public static JButton addButton(int x, int y, String imgPath) throws IOException {
        BufferedImage buttonIcon = ImageIO.read(new File(imgPath));
        JButton button = new JButton(new ImageIcon(buttonIcon));
        button.setBounds(x, y, buttonIcon.getWidth(), buttonIcon.getHeight());
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        return button;
    }

    public static JLabel addImage(int x, int y, String imgPath) throws IOException {
        BufferedImage img = ImageIO.read(new File(imgPath));
//        img.getScaledInstance(1000,1000, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(img);
        Image scaleImage = icon.getImage().getScaledInstance(280, 280,Image.SCALE_DEFAULT);
        ImageIcon icn = new ImageIcon(scaleImage);
        JLabel label = new JLabel(icn);
        label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        return label;
    }

    public static JLabel addLabel(int x, int y, String shape, String count) throws IOException {
        String resutText = shape + "           " + count;
        Font font = new Font("Segoe UI", Font.PLAIN, 50);
        JLabel label = new JLabel(resutText);
        label.setFont(font);

        label.setBounds(x, y, resutText.length() * 20, 65);
        label.setVisible(true);
        return label;
    }


    public static void showInfo(JFrame mainScene) throws IOException {
        HashMap<String, Integer> backbackContent = backpack.getShapesInfo();
//        for (int i = 0; i < backbackContent.size(); i++) {
//            mainScene.add(addLabel(279, 400 + 100 * (i), backbackContent.toString(), "ji"), 1);
//        }
        System.out.println(backbackContent);
        int i = 0;
        for (HashMap.Entry<String, Integer> item : backbackContent.entrySet()) {
            mainScene.add(addLabel(279, 400 + 100 * (i), item.getKey(), String.valueOf(item.getValue())), 1);
            i++;
        }

    }

    public static void main(String[] args) throws Exception {
//        mainScene = new JFrame("example");
//
//        Color backgroundColor = new Color(215, 226, 238);
//        mainScene.getContentPane().setBackground(backgroundColor);
//
//        JButton addElButton = addButton(1300, 270, "src/img/ADD BUTTON.png");
//        JButton deleteButton = addButton(1300, 400, "src/img/DELETE BUTTON.png");
//        JButton phdButton = addButton(1300, 530, "src/img/PHD.png");
//
//        phdButton.addActionListener(e -> phdButton.setVisible(false));
//        addElButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    showInfo(mainScene);
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
//            }
//        } );
//
//
//        mainScene.add(addElButton);
//        mainScene.add(deleteButton);
//        mainScene.add(phdButton);
////        mainScene.add(addImage(10, 10, "src/img/BackpackImage.png"));
//
//
//        backpack = new Backpack(160);
//        Cube cube1 = new Cube(2);
//        Cylinder c = new Cylinder(1, 2);
//        Sphere s = new Sphere(1);
//        Parallelepiped p = new Parallelepiped(1, 2, 3);
//        backpack.addShape(cube1);
//        backpack.addShape(cube1);
//        backpack.addShape(cube1);
//        backpack.addShape(c);
//        backpack.addShape(s);
//        backpack.addShape(p);
////        showInfo(mainScene);
//
////        JTextArea jt = new JTextArea("please write something ", 10, 10);
////        JPanel panel = new JPanel();
////        panel.setBounds(0,0,1000,1000);
////        panel.add(jt);
////        panel.add(addLabel(100,100,"100","100"));
//////        panel.add(addLabel(100,120,"100","100"));
////        panel.add(addLabel(100,200,"100","100"));
////        panel.setLocation(150, 150);
////        mainScene.add(panel);
//
//
//
////        mainScene.add(addLabel(1500,100, "hello", "ji"));
//
//        mainScene.setSize(1920, 1080);
//        mainScene.setLayout(null);
//        mainScene.setVisible(true);
        GUI gui = new GUI();
    }
}

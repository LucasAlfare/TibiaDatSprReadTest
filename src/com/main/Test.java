package com.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Test extends JFrame {

    private int genPos;
    private DatItem[] items;
    private DatItem currItem;

    private Test() throws IOException {
        setSize(150, 120);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Random r = new Random();

        SprReader p =
                new SprReader(
                        Files.readAllBytes(
                                Paths.get(
                                        "src/assets/tibia-8.6.spr")));

        DatReader datReader =
                new DatReader(
                        new JBinaryReader(
                                Files.readAllBytes(
                                        Paths.get("src/assets/tibia-8.6.dat"))));

        items = datReader.getGroundItems();
        JButton b = new JButton("<html>Desenhar outra sprite</html>");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genPos = r.nextInt(items.length);
                currItem = items[genPos];
                System.out.println(currItem);
                repaint();
            }
        });

        add(b, BorderLayout.SOUTH);

        add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(p.spriteImg(currItem.spriteList.get(0)), 0, 0, null);
                    g.setColor(Color.BLACK);
                    g.drawString("Dat id: " + currItem.id, 40, 32);
                } catch (Exception e){
                    //pass tratamento de exceção
                    g.setColor(Color.MAGENTA);
                    g.fillRect(0, 0, 32, 32);
                    g.setColor(Color.BLACK);
                    g.drawString("Dat id: " + "null", 40, 32);
                }
            }
        }, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Test();
    }
}

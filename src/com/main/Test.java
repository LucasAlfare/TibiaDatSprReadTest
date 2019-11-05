package com.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Timer;

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

        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(20, 15));

        items = datReader.getItems();
        JButton b = new JButton("Draw");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genPos = field.getText().isEmpty() ? r.nextInt(items.length) : Integer.parseInt(field.getText());
                currItem = items[genPos];
                repaint();
                //System.out.println(currItem);
            }
        });

        add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.setColor(Color.BLACK);
                    g.drawString("Dat id: " + currItem.id, 40, 32);
                    ArrayList<Integer> spritesIDs = currItem.spriteList;
                    g.drawImage(p.spriteImg(spritesIDs.get(0)), 0, 0, null);
                } catch (Exception e){
                    //pass tratamento de exceção
                    g.setColor(Color.MAGENTA);
                    g.fillRect(0, 0, 32, 32);
                    g.setColor(Color.BLACK);
                    g.drawString("Dat id: " + "null", 40, 32);
                }
            }
        }, BorderLayout.CENTER);
        add(b, BorderLayout.SOUTH);
        add(field, BorderLayout.LINE_END);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Test();

        /*
        ESCREVENDO:

        - Converter a informação que quer para UM array de bytes;
            - A informação pode ser qualquer tipo;
        - Escrever cada informação (cada array) no arquivo;
         */

//        File f = new File("src/com/main/teste.buaa");
//        FileOutputStream stream = new FileOutputStream(f);
//
//        stream.write(toBytes(181094));
//        stream.write(toBytes(1234));
//        stream.write(toBytes(5678));
//        stream.write(toBytes(181094));
//        stream.close();

        /*
        LENDO:

        - Converter arqivo para array de bytes;
        - Realizar leitura de acordo como foi escrito;
            - Se foi adicionado um array de bytes que contém 2
            elementos, então deverão ser lidos 2 elementos aqui
            e depois esses elementos deverão ser convertidos de
            volta para o tipo do qual vieram para que a informação
            possa fazer sentido;
         */
//        byte[] arr2 = Files.readAllBytes(Paths.get("src/com/main/teste.bua"));
//        JBinaryReader reader = new JBinaryReader(arr2);
//        System.out.println(reader.readInt32());
//        System.out.println(reader.readInt32());
//        System.out.println(reader.readInt32());
//        System.out.println(reader.readInt32());
    }

    private static byte[] toBytes(int i) {
        byte[] aBuffer = new byte[4];

        aBuffer[0] = (byte) (i >> 24);
        aBuffer[1] = (byte) (i >> 16);
        aBuffer[2] = (byte) (i >> 8);
        aBuffer[3] = (byte) (i /*>> 0*/);

        return aBuffer;
    }
}
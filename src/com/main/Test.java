package com.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
 * Essa classe aqui fiz só pra testar
 *
 * Basicamente a gente escreve um ID e no componente da esquerda
 * deve ser mostrado o item correspondente...
 *
 * Eu testei com alguns IDs desse link e aparentemente tudo certo:
 *
 * https://tibia.fandom.com/wiki/Item_IDs
 */
public class Test extends JFrame {

    private int genPos;
    private DatItem[] items;
    private DatItem currItem;

    private Test() throws IOException {
        setSize(200, 120);
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
                        Files.readAllBytes(
                                Paths.get("src/assets/tibia-8.6.dat")));

        JOptionPane.showMessageDialog(null, "Foram carregados [" + datReader.getItems().length + "] items do DAT 8.60. Era pra ter mais itens?");

        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(50, 15));

        items = datReader.getItems();
        JButton b = new JButton("Desenhar!");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genPos = field.getText().isEmpty() ? r.nextInt(items.length) : Integer.parseInt(field.getText());
                currItem = items[genPos];
                repaint();
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
    }

    @Deprecated
    private static byte[] toBytes(int i) {
        byte[] aBuffer = new byte[4];

        aBuffer[0] = (byte) (i >> 24);
        aBuffer[1] = (byte) (i >> 16);
        aBuffer[2] = (byte) (i >> 8);
        aBuffer[3] = (byte) (i /*>> 0*/);

        return aBuffer;
    }
}
package com.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SprReader {

    //bytes do arquivo tibia.spr
    private byte[] sprBytes;

    //leitor estilo C# que fiz, tem repositório dele
    private JBinaryReader reader;

    public SprReader(byte[] bytes) {
        sprBytes = bytes;
        reader = new JBinaryReader(sprBytes);
    }

    //spriteID refere-se ao "endereço" do desenho
    //dentro do arquivo spr
    public BufferedImage spriteImg(int spriteID) {
        BufferedImage img = rawImage(32, 32);

        int pixelAtual = 0;
        long offsetDesejado;

        reader.seek(6 + (spriteID - 1) * 4, 8);
        reader.seek((int) (reader.readUInt32() + 3), 8);

        offsetDesejado = reader.getPosition() + reader.readUInt16();

        while (reader.getPosition() < offsetDesejado){
            int pixelsTransparentes = reader.readUInt16();
            int pixelsColoridos = reader.readUInt16();
            pixelAtual += pixelsTransparentes;
            for (int i = 0; i < pixelsColoridos; i++) {
                img.setRGB(
                        pixelAtual % 32,
                        pixelAtual / 32,
                        new Color(
                                rgbToArgb(
                                        reader.readByte(),
                                        reader.readByte(),
                                        reader.readByte()
                                )
                        ).getRGB());
                pixelAtual++;
            }
        }

        return img;
    }

    //achei esse código na net...
    private static int rgbToArgb(int red, int green, int blue) {
        //Shift red 16-bits and mask out other stuff
        red = (red << 16) & 0x00FF0000;

        //Shift green 8-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00;

        //Mask out anything not blue.
        blue = blue & 0x000000FF;

        //0xFF000000 for 100% Alpha. Bitwise OR everything together.
        return 0xFF000000 | red | green | blue;
    }

    private static BufferedImage rawImage(int w, int h) {
        return GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(
                        w,
                        h,
                        BufferedImage.TYPE_INT_ARGB
                );
    }
}

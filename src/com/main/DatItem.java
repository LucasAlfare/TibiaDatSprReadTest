package com.main;

import java.util.ArrayList;

//fields for 8.6 dat structure
@SuppressWarnings("ALL")
public class DatItem {

    public enum ItemType {
        Ground, //chao
        Container, //conteiner
        Fluid, //fluido
        Splash; //splash
    }

    public int id;
    public ItemType type;
    public int groundSpeed;
    public boolean alwaysOnTop;
    public int alwaysOnTopOrder;
    public boolean hasUseWith;
    public boolean isReadable;
    public int maxReadWriteChars;
    public int maxReadChars;
    public boolean blockObject;
    public boolean isMoveable;
    public boolean blockProjectile;
    public boolean blockPathFind;
    public boolean isPickupable;
    public boolean isHangable;
    public boolean isHorizontal;
    public boolean isVertical;
    public boolean isRotatable;
    public int lightLevel;
    public int lightColor;
    public boolean hasHeight;
    public int minimapColor;
    public boolean lookThrough;
    public int width;
    public int height;
    public int frames;
    public int xdiv;
    public int ydiv;
    public int zdiv;
    public int animationLength;
    public boolean isStackable;
    public int numSprites;
    public ArrayList<Integer> spriteList;

    @Override
    public String toString() {
        String r = "Item " + id + " atributes:\n";
        r += "- Item type: " + type + ";\n";
        r += "- Ground speed: " + groundSpeed + ";\n";
        r += "- Always on top: " + alwaysOnTop + ";\n";
        r += "- Always on top order: " + alwaysOnTopOrder + ";\n";
        r += "- Has use with: " + hasUseWith+ ";\n";
        r += "- Is readable: " + isReadable + ";\n";
        r += "- Max reader max chars: " + maxReadWriteChars + ";\n";
        r += "- Max read chars: " + maxReadChars + ";\n";
        r += "- Blocks object: " + blockObject + ";\n";
        r += "- Is moveable: " + isMoveable + ";\n";
        r += "- Blocks projectile: " + blockPathFind + ";\n";
        r += "- Block pathfinding: " + blockPathFind + ";\n";
        r += "- Is pickupable: " + isPickupable + ";\n";
        r += "- Is hangable: " + isHangable + ";\n";
        r += "- Is horizontal: " + isHorizontal + ";\n";
        r += "- Is vertical: " + isVertical + ";\n";
        r += "- Is rotatable: " + isRotatable + ";\n";
        r += "- Light level: " + lightLevel + ";\n";
        r += "- Light color: " + lightColor + ";\n";
        r += "- Has heigth: " + hasHeight + ";\n";
        r += "- Minimap color: " + minimapColor + ";\n";
        r += "- Look through: " + lookThrough + ";\n";
        r += "- Width: " + width + ";\n";
        r += "- Heigth: " + height + ";\n";
        r += "- Frames: " + frames + ";\n";
        r += "- X div: " + xdiv + ";\n";
        r += "- Y div: " + ydiv + ";\n";
        r += "- Z div: " + zdiv + ";\n";
        r += "- Animation length: " + animationLength + ";\n";
        r += "- Is stackable: " + isStackable + ";\n";
        r += "- Number of sprites: " + numSprites + ";\n";
        r += "- Sprite list (sprite addresses values): \n\t" + spriteList + ".\n";

        return r;
    }
}
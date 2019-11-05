package com.main;

import java.util.ArrayList;

public class DatReader {

    private JBinaryReader reader;
    private DatItem[] items;

    public DatReader(JBinaryReader reader){
        this.reader = reader;
        load();
    }

    private void load(){
        long signature = reader.readUInt32();
        System.out.println(String.format("Current dat file signature: %s.", signature));

        int itemCount = reader.readUInt16();
        int creatureCount = reader.readUInt16();
        int effectCount = reader.readUInt16();
        int distanceCount = reader.readUInt16();

        int minClientID = 100;
        int maxClientID = itemCount;

        items = new DatItem[
                minClientID
                + itemCount
                + creatureCount
                + effectCount
                + distanceCount
                ];

        int id = minClientID;
        while (id <= maxClientID) {
            DatItem item = new DatItem();
            item.id = id;

            int atributo;

            do {
                atributo = reader.readByte();

                switch (atributo) {
                    case 0x00: //groundtile
                        item.groundSpeed = reader.readUInt16();
                        item.type = DatItem.ItemType.Ground;
                        break;

                    case 0x01: //all OnTop
                        item.alwaysOnTop = true;
                        item.alwaysOnTopOrder = 1;
                        break;

                    case 0x02: //can walk trough (open doors, arces, bug pen fence)
                        item.alwaysOnTop = true;
                        item.alwaysOnTopOrder = 2;
                        break;

                    case 0x03: //can walk trough (arces)
                        item.alwaysOnTop = true;
                        item.alwaysOnTopOrder = 3;
                        break;

                    case 0x04: //container
                        item.type = DatItem.ItemType.Container;
                        break;

                    case 0x05: //stackable
                        item.isStackable = true;
                        break;

                    case 0x06: //informação desconhecida
                        break;

                    case 0x07: //useable
                        item.hasUseWith = true;
                        break;

                    case 0x08: //read/write-able
                        item.isReadable = true;
                        //item.isWriteable = true;
                        item.maxReadWriteChars = reader.readUInt16();
                        break;

                    case 0x09: //readable
                        item.isReadable = true;
                        item.maxReadChars = reader.readUInt16();
                        break;

                    case 0x0A: //fluid containers
                        item.type = DatItem.ItemType.Fluid;
                        break;

                    case 0x0B: //splashes
                        item.type = DatItem.ItemType.Splash;
                        break;

                    case 0x0C: //blocks solid objects (creatures, walls etc)
                        item.blockObject = true;
                        break;

                    case 0x0D: //not moveable
                        item.isMoveable = false;
                        break;

                    case 0x0E: //blocks missiles (walls, magic wall etc)
                        item.blockProjectile = true;
                        break;

                    case 0x0F: //blocks pathfind algorithms (monsters)
                        item.blockPathFind = true;
                        break;

                    case 0x10: //blocks monster movement (flowers, parcels etc)
                        item.isPickupable = true;
                        break;

                    case 0x11: //hangable objects (wallpaper etc)
                        item.isHangable = true;
                        break;

                    case 0x12: //horizontal wall
                        item.isHorizontal = true;
                        break;

                    case 0x13: //vertical wall
                        item.isVertical = true;
                        break;

                    case 0x14: //rotatable
                        item.isRotatable = true;
                        break;

                    case 0x15: //light info
                        item.lightLevel = reader.readUInt16();
                        item.lightColor = reader.readUInt16();
                        break;

                    case 0x16: //informação desconhecida
                        break;

                    case 0x17: //changes floor
                        break;

                    case 0x18: //informação desconhecida

                        reader.readUInt16();
                        reader.readUInt16();
                        break;

                    case 0x19:
                        item.hasHeight = true;
                        int height = reader.readUInt16();
                        break;

                    case 0x1A: //informação desconhecida
                        break;

                    case 0x1B: //informação desconhecida
                        break;

                    case 0x1C: //minimap color
                        item.minimapColor = reader.readUInt16();
                        break;

                    case 0x1D: //in-game help info
                        int opt = reader.readUInt16();
                        if (opt == 1112) {
                            item.isReadable = true;
                        }
                        break;

                    case 0x1E: //informação desconhecida
                        break;

                    case 0x1F: //look through (borders)
                        item.lookThrough = true;
                        break;

                    case 0x20: //informação desconhecida
                        break;

                    case 0xFF: //end of attributes
                        break;

                    default:
                        System.err.println("DEU ERROP");
                        return;
                }
            } while (atributo != 0xFF);

            item.width = reader.readByte();
            item.height = reader.readByte();
            if ((item.width > 1) || (item.height > 1)) {
                reader.setPosition(reader.getPosition() + 1);
            }

            item.frames = reader.readByte();
            item.xdiv = reader.readByte();
            item.ydiv = reader.readByte();
            item.zdiv = reader.readByte();
            item.animationLength = reader.readByte();

            item.numSprites = item.width * item.height * item.frames * item.xdiv * item.ydiv * item.zdiv * item.animationLength;

            // Read the sprite ids
            item.spriteList = new ArrayList<>();
            for (int i = 0; i < item.numSprites; ++i) {
                int spriteId = reader.readUInt16();
                item.spriteList.add(spriteId);
            }

            items[id] = item;
            id++;
        }
    }

    public DatItem[] getItems() {
        if (items == null){
            load();
        }
        return items;
    }

    public DatItem[] getGroundItems(){
        ArrayList<DatItem> groundsList = new ArrayList<>();
        for (DatItem item : getItems()){
            if (item != null && item.type == DatItem.ItemType.Ground){
                groundsList.add(item);
            }
        }

        return groundsList.toArray(new DatItem[groundsList.size()]);
    }

    public DatItem[] getContainerItems(){
        ArrayList<DatItem> containersList = new ArrayList<>();
        for (DatItem item : getItems()){
            if (item != null && item.type == DatItem.ItemType.Container){
                containersList.add(item);
            }
        }
        return containersList.toArray(new DatItem[containersList.size()]);
    }

    public DatItem[] getFluidItems(){
        ArrayList<DatItem> fluidList = new ArrayList<>();
        for (DatItem item : getItems()){
            if (item != null && item.type == DatItem.ItemType.Fluid){
                fluidList.add(item);
            }
        }
        return fluidList.toArray(new DatItem[fluidList.size()]);
    }

    public DatItem[] getSplashItems(){
        ArrayList<DatItem> splashList = new ArrayList<>();
        for (DatItem item : getItems()){
            if (item != null && item.type == DatItem.ItemType.Splash){
                splashList.add(item);
            }
        }

        return splashList.toArray(new DatItem[splashList.size()]);
    }
}

package com.main;

import java.util.ArrayList;

/**
 * Esses campos parecem respeitar a estrutura
 * do tibia 8.60
 *
 *
 */
@SuppressWarnings("ALL")
public class DatItem {

    public static final class ItemAtributesFlags {


        /**
         * Esses números representam coisas que um
         * item pode ter. Esses números poderão ser
         * encontrados dentro do OFFSET de um item
         * que esteja dentro de um item, logo, caso
         * o número apareça, significará que o item
         * terá atribudos relacionados a ele.
         *
         * Algumas flags indicam que um ou mais valores
         * seguintes também deverão ser lidos para
         * a devida construção de um campo do objeto
         * item em questão.
         */
        public static final int

                /**
                 * é seguido por 2 bytes, indicando
                 * a velocidade que tal chão implica
                 */
                GROUND_TILE = 0X00,
        ALWAYS_ON_TOP = 0x01,
        CAN_WALK_THROUGH_DOORS = 0x02,
        CAN_WALK_THROUGH_ARCS = 0X03,
        CONTAINER = 0x04,
        STACKABLE = 0x05,
        UNKNOWN_0X06 = 0x06,
        USABLE = 0X07,

        /**
         * indica se um item pode ser
         * "lido" e "escrevido" ao mesmo tempo.
         * É seguido por 2 bytes que indicam a
         * quantidade máxima de caracteres para
         * leitura e escrita. Ex.: carta ou selo
         * de correio.
         */
        WRITABLE = 0X08, //?

        /**
         * indica se um item pode ser
         * "lido".
         * É seguido por 2 bytes que indicam a
         * quantidade máxima de caracteres para
         * leitura. Ex.: carta ou selo de correio
         * especiais.
         */
        READABLE = 0X09, //?
                FLUID_CONTAINER = 0X0A,
                SPLASH = 0X0B,
                BLOCKS_OBJECT = 0X0C,
                NOT_MOVEABLE = 0X0D,
                BLOCKS_MISSILE = 0X0E,
                BLOCKS_PATHFIND_ALGORITHM = 0X0F,
                BLOCKS_MONSTER_MOVEMENT = 0X10,
                HANGABLE_OBJECT = 0X11,
                HORIZONTAL_WALL = 0X12,
                VERTICAL_WALL = 0X13,
                ROTATABLE = 0X14,

        /**
         * Responsável para marcar onde deverá
         * ser lida a informação de luz do item.
         * <p>
         * É seguido por 4 bytes, onde os dois
         * primeiros indicam a intensidade da luz
         * e os dois seguintes a cor da luz.
         */
        LIGHT_INFO = 0X15,
                UNKNOWN_0X16 = 0X16,
                CHANGES_FLOOR = 0X17,

        /**
         * O prpósito desse valor é desconhecido.
         * <p>
         * Esse valor é seguido pro 4 bytes,
         * os quais não se sabe para que servem.
         */
        UNKNOWN_0X18 = 0X18,

        /**
         * Indica se o item terá "peso" (massa).
         * <p>
         * É seguindo por 2 bytes, os quais são
         * traduzidos na respectiva massa.
         */
        HEIGHT = 0X19,
                UNKNOWN_0X1A = 0X1A,
                UNKNOWN_0X1B = 0X1B,

        /**
         * Indica a cor que o item terá dentro
         * do minimapa.
         * <p>
         * É seguido por 2 bytes que guardam a
         * cor.
         */
        MINIMAP_COLOR = 0X1C,

        /**
         * Indica se o item poderá dar "ajuda"
         * no jogo.
         * <p>
         * É seguido por 2 bytes e, caso esses
         * bytes sejam lidos como valendo o decimal
         * "1112", indicará, também, que o item pode
         * ser lido.
         */
        INFORMATION_IN_GAME = 0X1D,
                UNKNOWN_0X1E = 0X1E,
                LOOK_THROUGH = 0X1F,
                UNKNOWN_0X20 = 0X20,
                END_FLAG = 0XFF;
    }

    public enum ItemType {
        Ground, //chao
        Container, //conteiner
        Fluid, //fluido
        Splash, //splash
        Wall; //paredes
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

    /**
     * Sobescrevi o toString pra debug...
     *
     * @return lalalala
     */
    @Override
    public String toString() {
        String r = "Item " + id + " atributes:\n";
        r += "- Item type: " + type + ";\n";
        r += "- Ground speed: " + groundSpeed + ";\n";
        r += "- Always on top: " + alwaysOnTop + ";\n";
        r += "- Always on top order: " + alwaysOnTopOrder + ";\n";
        r += "- Has use with: " + hasUseWith + ";\n";
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

/**
 * Project Furniture
 * Created by Shibkov Konstantin on 19.12.2018.
 */
package Furniture;

public class Furniture {
    protected short legs;
    protected short height;
    protected String materials;
    protected float price;

    Furniture(short legs, short height, String materials, float price) {
        this.legs = legs;
        this.height = height;
        this.materials = materials;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public short getHeight() {
        return height;
    }

    public short getLegs() {
        return legs;
    }

    public String getMaterials() {
        return materials;
    }
}

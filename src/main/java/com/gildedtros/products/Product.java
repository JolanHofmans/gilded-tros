package com.gildedtros.products;

import java.util.Objects;

public class Product {
    private final String name;
    private int sellIn;
    private int quality;

    public Product(String name, int sellIn, int quality) {
        if (quality < 0 || quality > 50) {
            throw new IllegalArgumentException("Quality must be between 0 and 50");
        }
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void increaseQuality(int value) {
        quality = Math.min(50, quality + value);
    }

    public void decreaseQuality(int value) {
        quality = Math.max(0, quality - value);
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void decreaseSellIn() {
        sellIn--;
    }

    public int getQuality() {
        return quality;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.name, that.name) &&
                this.sellIn == that.sellIn &&
                this.quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIn, quality);
    }

    @Override
    public String toString() {
        return "Product[" +
                "name=" + name + ", " +
                "sellIn=" + sellIn + ", " +
                "quality=" + quality + ']';
    }

}

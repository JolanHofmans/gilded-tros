package com.gildedtros.products;

import com.gildedtros.products.strategies.QualityUpdatingStrategy;

public class Product {
    private final String name;
    private int sellIn;
    private int quality;
    private final QualityUpdatingStrategy qualityUpdatingStrategy;

    private Product(String name, int sellIn, int quality, QualityUpdatingStrategy qualityUpdatingStrategy) {
        if (qualityUpdatingStrategy == null) {
            throw new IllegalArgumentException("QualityUpdatingStrategy must not be null");
        }
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.qualityUpdatingStrategy = qualityUpdatingStrategy;
    }

    public static Product createProduct(String name, int sellIn, int quality, QualityUpdatingStrategy qualityUpdatingStrategy) {
        if (quality < 0 || quality > 50) {
            throw new IllegalArgumentException("Quality must be between 0 and 50");
        }
        return new Product(name, sellIn, quality, qualityUpdatingStrategy);
    }

    public static Product createProduct(String name, int sellIn, QualityUpdatingStrategy qualityUpdatingStrategy) {
        return new Product(name, sellIn, 80, qualityUpdatingStrategy);
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

    public QualityUpdatingStrategy getQualityUpdatingStrategy() {
        return qualityUpdatingStrategy;
    }

    public void updateQuality() {
        qualityUpdatingStrategy.updateQuality(this);
    }
}

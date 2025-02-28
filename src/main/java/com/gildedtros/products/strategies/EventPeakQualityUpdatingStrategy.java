package com.gildedtros.products.strategies;

import com.gildedtros.products.Product;

public class EventPeakQualityUpdatingStrategy implements QualityUpdatingStrategy{
    @Override
    public void updateQuality(Product product) {
        if (product.getSellIn() <= 0) {
            product.setQuality(0);
        } else if (product.getSellIn() <= 5) {
            product.increaseQuality(3);
        } else if (product.getSellIn() <= 10) {
            product.increaseQuality(2);
        } else {
            product.increaseQuality(1);
        }

        product.decreaseSellIn();
    }
}

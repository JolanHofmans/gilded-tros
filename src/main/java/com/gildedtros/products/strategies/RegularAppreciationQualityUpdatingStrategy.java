package com.gildedtros.products.strategies;

import com.gildedtros.products.Product;

public class RegularAppreciationQualityUpdatingStrategy implements QualityUpdatingStrategy {
    @Override
    public void updateQuality(Product product) {
        if (product.getSellIn() > 0) {
            product.increaseQuality(1);
        } else {
            product.increaseQuality(2);
        }
        product.decreaseSellIn();
    }
}

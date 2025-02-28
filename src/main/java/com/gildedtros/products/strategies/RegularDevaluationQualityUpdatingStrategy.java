package com.gildedtros.products.strategies;

import com.gildedtros.products.Product;

public class RegularDevaluationQualityUpdatingStrategy implements QualityUpdatingStrategy{
    @Override
    public void updateQuality(Product product) {
        if (product.getSellIn() > 0) {
            product.decreaseQuality(1);
        } else {
            product.decreaseQuality(2);
        }
        product.decreaseSellIn();
    }
}

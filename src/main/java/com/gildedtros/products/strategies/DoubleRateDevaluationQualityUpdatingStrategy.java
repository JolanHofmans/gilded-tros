package com.gildedtros.products.strategies;

import com.gildedtros.products.Product;

public class DoubleRateDevaluationQualityUpdatingStrategy implements QualityUpdatingStrategy{
    @Override
    public void updateQuality(Product product) {
        if (product.getSellIn() > 0) {
            product.decreaseQuality(2);
        } else {
            product.decreaseQuality(4);
        }
        product.decreaseSellIn();
    }
}

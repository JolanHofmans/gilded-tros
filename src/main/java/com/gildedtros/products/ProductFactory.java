package com.gildedtros.products;

import com.gildedtros.Item;
import com.gildedtros.products.strategies.ConstantQualityUpdatingStrategy;
import com.gildedtros.products.strategies.EventPeakQualityUpdatingStrategy;
import com.gildedtros.products.strategies.RegularAppreciationQualityUpdatingStrategy;
import com.gildedtros.products.strategies.RegularDevaluationQualityUpdatingStrategy;

public class ProductFactory {

    private static final RegularDevaluationQualityUpdatingStrategy REGULAR_DEVALUATION = new RegularDevaluationQualityUpdatingStrategy();
    private static final RegularAppreciationQualityUpdatingStrategy REGULAR_APPRECIATION = new RegularAppreciationQualityUpdatingStrategy();
    private static final ConstantQualityUpdatingStrategy CONSTANT_QUALITY = new ConstantQualityUpdatingStrategy();
    private static final EventPeakQualityUpdatingStrategy EVENT_PEAK = new EventPeakQualityUpdatingStrategy();

    private ProductFactory() {
    }

    public static Product createProduct(Item item) {
        return switch (item.name) {
            case "Good Wine" ->
                    new Product(item.name, item.sellIn, item.quality, REGULAR_APPRECIATION);
            case "B-DAWG Keychain" ->
                    new Product(item.name, item.sellIn, item.quality, CONSTANT_QUALITY);
            case "Backstage passes for Re:Factor", "Backstage passes for HAXX" ->
                    new Product(item.name, item.sellIn, item.quality, EVENT_PEAK);
            default ->
                    new Product(item.name, item.sellIn, item.quality, REGULAR_DEVALUATION);
        };
    }
}
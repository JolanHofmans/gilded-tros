package com.gildedtros.products;

import com.gildedtros.Item;
import com.gildedtros.products.strategies.*;

public class ProductFactory {

    private static final RegularDevaluationQualityUpdatingStrategy REGULAR_DEVALUATION = new RegularDevaluationQualityUpdatingStrategy();
    private static final RegularAppreciationQualityUpdatingStrategy REGULAR_APPRECIATION = new RegularAppreciationQualityUpdatingStrategy();
    private static final ConstantQualityUpdatingStrategy CONSTANT_QUALITY = new ConstantQualityUpdatingStrategy();
    private static final EventPeakQualityUpdatingStrategy EVENT_PEAK = new EventPeakQualityUpdatingStrategy();
    private static final DoubleRateDevaluationQualityUpdatingStrategy DOUBLE_RATE_DEVALUATION = new DoubleRateDevaluationQualityUpdatingStrategy();

    private ProductFactory() {
    }

    public static Product createProduct(Item item) {
        return switch (item.name) {
            case "Good Wine" -> Product.createProduct(item.name, item.sellIn, item.quality, REGULAR_APPRECIATION);
            case "B-DAWG Keychain" -> Product.createProduct(item.name, item.sellIn, CONSTANT_QUALITY);
            case "Backstage passes for Re:Factor", "Backstage passes for HAXX" -> Product.createProduct(item.name, item.sellIn, item.quality, EVENT_PEAK);
            case "Duplicate Code", "Long Methods", "Ugly Variable Names" -> Product.createProduct(item.name, item.sellIn, item.quality, DOUBLE_RATE_DEVALUATION);
            default -> Product.createProduct(item.name, item.sellIn, item.quality, REGULAR_DEVALUATION);
        };
    }
}
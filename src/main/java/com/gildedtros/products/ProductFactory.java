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
        QualityUpdatingStrategy strategy = switch (item.name) {
            case "Good Wine" -> REGULAR_APPRECIATION;
            case "B-DAWG Keychain" -> CONSTANT_QUALITY;
            case "Backstage passes for Re:Factor", "Backstage passes for HAXX" -> EVENT_PEAK;
            case "Duplicate Code", "Long Methods", "Ugly Variable Names" -> DOUBLE_RATE_DEVALUATION;
            default -> REGULAR_DEVALUATION;
        };
        return new Product(item.name, item.sellIn, item.quality, strategy);
    }
}
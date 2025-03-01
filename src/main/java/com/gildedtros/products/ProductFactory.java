package com.gildedtros.products;

import com.gildedtros.Item;
import com.gildedtros.products.strategies.*;

import java.util.List;
import java.util.Map;

public class ProductFactory {

    private static final RegularDevaluationQualityUpdatingStrategy REGULAR_DEVALUATION = new RegularDevaluationQualityUpdatingStrategy();
    private static final RegularAppreciationQualityUpdatingStrategy REGULAR_APPRECIATION = new RegularAppreciationQualityUpdatingStrategy();
    private static final ConstantQualityUpdatingStrategy CONSTANT_QUALITY = new ConstantQualityUpdatingStrategy();
    private static final EventPeakQualityUpdatingStrategy EVENT_PEAK = new EventPeakQualityUpdatingStrategy();
    private static final DoubleRateDevaluationQualityUpdatingStrategy DOUBLE_RATE_DEVALUATION = new DoubleRateDevaluationQualityUpdatingStrategy();

    private static final Map<ProductType, List<String>> productTypeToNames = Map.of(
            ProductType.GOOD_WINE, List.of("Good Wine"),
            ProductType.RARE, List.of("B-DAWG Keychain"),
            ProductType.BACKSTAGE_PASSES, List.of("Backstage passes for Re:Factor", "Backstage passes for HAXX"),
            ProductType.SMELLY, List.of("Duplicate Code", "Long Methods", "Ugly Variable Names")
    );

    private ProductFactory() {
    }

    public static Product createProduct(Item item) {
        return switch (getProductType(item.name)) {
            case GOOD_WINE -> Product.createProduct(item.name, item.sellIn, item.quality, REGULAR_APPRECIATION);
            case RARE -> Product.createProduct(item.name, item.sellIn, CONSTANT_QUALITY);
            case BACKSTAGE_PASSES -> Product.createProduct(item.name, item.sellIn, item.quality, EVENT_PEAK);
            case SMELLY -> Product.createProduct(item.name, item.sellIn, item.quality, DOUBLE_RATE_DEVALUATION);
            case REGULAR -> Product.createProduct(item.name, item.sellIn, item.quality, REGULAR_DEVALUATION);
        };
    }

    private static ProductType getProductType(String name) {
        return productTypeToNames.entrySet().stream()
                .filter(entry -> entry.getValue().contains(name))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(ProductType.REGULAR);
    }

    enum ProductType {
        REGULAR, GOOD_WINE, RARE, BACKSTAGE_PASSES, SMELLY
    }
}
package com.gildedtros.products;

import com.gildedtros.Item;
import com.gildedtros.products.strategies.ConstantQualityUpdatingStrategy;
import com.gildedtros.products.strategies.EventPeakQualityUpdatingStrategy;
import com.gildedtros.products.strategies.RegularAppreciationQualityUpdatingStrategy;
import com.gildedtros.products.strategies.RegularDevaluationQualityUpdatingStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductFactoryTest {

    @Test
    void regularItemShouldHaveRegularDevaluationStrategy() {
        Item item = new Item("Regular Item", 10, 10);

        Product product = ProductFactory.createProduct(item);

        assertThat(product.getQualityUpdatingStrategy()).isInstanceOf(RegularDevaluationQualityUpdatingStrategy.class);
    }

    @Test
    void goodWineShouldHaveRegularAppreciationStrategy() {
        Item item = new Item("Good Wine", 10, 10);

        Product product = ProductFactory.createProduct(item);

        assertThat(product.getQualityUpdatingStrategy()).isInstanceOf(RegularAppreciationQualityUpdatingStrategy.class);
    }

    @Test
    void bDawgKeychainShouldHaveRegularAppreciationStrategy() {
        Item item = new Item("B-DAWG Keychain", 10, 10);

        Product product = ProductFactory.createProduct(item);

        assertThat(product.getQualityUpdatingStrategy()).isInstanceOf(ConstantQualityUpdatingStrategy.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Backstage passes for Re:Factor", "Backstage passes for HAXX"})
    void bDawgKeychainShouldHaveRegularAppreciationStrategy(String name) {
        Item item = new Item(name, 10, 10);

        Product product = ProductFactory.createProduct(item);

        assertThat(product.getQualityUpdatingStrategy()).isInstanceOf(EventPeakQualityUpdatingStrategy.class);
    }
}
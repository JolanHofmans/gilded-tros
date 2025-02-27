package com.gildedtros.products;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductTest {

    @Test
    void ShouldIncreaseQuality() {
        Product product = new Product("Good Wine", 10, 10);
        product.increaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(11);
    }

    @Test
    void ShouldDecreaseQuality() {
        Product product = new Product("Normal Item", 10, 10);
        product.decreaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(9);
    }

    @Test
    void ShouldNotDecreaseQualityBelowZero() {
        Product product = new Product("Normal Item", 10, 0);
        product.decreaseQuality(1);
        assertThat(product.getQuality()).isZero();
    }

    @Test
    void ShouldNotIncreaseQualityAboveFifty() {
        Product product = new Product("Normal Item", 10, 50);
        product.increaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(50);
    }

    @Test
    void ShouldDecreaseSellIn() {
        Product product = new Product("Normal Item", 10, 10);
        product.decreaseSellIn();
        assertThat(product.getSellIn()).isEqualTo(9);
    }

}

package com.gildedtros.products;

import com.gildedtros.products.strategies.RegularDevaluationQualityUpdatingStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ProductTest {

    @Test
    void shouldThrowExceptionWhenQualityIsNegative() {
        assertThatThrownBy(() -> new Product("Normal Item", 10, -1, new RegularDevaluationQualityUpdatingStrategy()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quality must be between 0 and 50");
    }

    @Test
    void shouldThrowExceptionWhenQualityIsAboveFifty() {
        assertThatThrownBy(() -> new Product("Normal Item", 10, 51, new RegularDevaluationQualityUpdatingStrategy()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quality must be between 0 and 50");
    }

    @Test
    void shouldThrowExcpetionWhenQualityUpdatingStrategyIsNull() {
        assertThatThrownBy(() -> new Product("Normal Item", 10, 10, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("QualityUpdatingStrategy must not be null");
    }

    @Test
    void shouldIncreaseQuality() {
        Product product = new Product("Good Wine", 10, 10, new RegularDevaluationQualityUpdatingStrategy());
        product.increaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(11);
    }

    @Test
    void shouldDecreaseQuality() {
        Product product = new Product("Normal Item", 10, 10, new RegularDevaluationQualityUpdatingStrategy());
        product.decreaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(9);
    }

    @Test
    void shouldNotDecreaseQualityBelowZero() {
        Product product = new Product("Normal Item", 10, 0, new RegularDevaluationQualityUpdatingStrategy());
        product.decreaseQuality(1);
        assertThat(product.getQuality()).isZero();
    }

    @Test
    void shouldNotIncreaseQualityAboveFifty() {
        Product product = new Product("Normal Item", 10, 50, new RegularDevaluationQualityUpdatingStrategy());
        product.increaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(50);
    }

    @Test
    void shouldDecreaseSellIn() {
        Product product = new Product("Normal Item", 10, 10, new RegularDevaluationQualityUpdatingStrategy());
        product.decreaseSellIn();
        assertThat(product.getSellIn()).isEqualTo(9);
    }

}


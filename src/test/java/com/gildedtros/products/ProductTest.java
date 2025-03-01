package com.gildedtros.products;

import com.gildedtros.products.strategies.RegularDevaluationQualityUpdatingStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class ProductTest {

    @Test
    void shouldThrowExceptionWhenQualityIsNegative() {
        assertThatThrownBy(() -> Product.createProduct("Normal Item", 10, -1, new RegularDevaluationQualityUpdatingStrategy()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quality must be between 0 and 50");
    }

    @Test
    void shouldThrowExceptionWhenQualityIsAboveFifty() {
        assertThatThrownBy(() -> Product.createProduct("Normal Item", 10, 51, new RegularDevaluationQualityUpdatingStrategy()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quality must be between 0 and 50");
    }

    @Test
    void shouldNotThrowExceptionWhenQualityEighty() {
        assertThatCode(() -> Product.createProduct("Normal Item", 10, new RegularDevaluationQualityUpdatingStrategy()))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldThrowExcpetionWhenQualityUpdatingStrategyIsNull() {
        assertThatThrownBy(() -> Product.createProduct("Normal Item", 10, 10, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("QualityUpdatingStrategy must not be null");
    }

    @Test
    void shouldIncreaseQuality() {
        Product product = Product.createProduct("Good Wine", 10, 10, new RegularDevaluationQualityUpdatingStrategy());
        product.increaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(11);
    }

    @Test
    void shouldDecreaseQuality() {
        Product product = Product.createProduct("Normal Item", 10, 10, new RegularDevaluationQualityUpdatingStrategy());
        product.decreaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(9);
    }

    @Test
    void shouldNotDecreaseQualityBelowZero() {
        Product product = Product.createProduct("Normal Item", 10, 0, new RegularDevaluationQualityUpdatingStrategy());
        product.decreaseQuality(1);
        assertThat(product.getQuality()).isZero();
    }

    @Test
    void shouldNotIncreaseQualityAboveFifty() {
        Product product = Product.createProduct("Normal Item", 10, 50, new RegularDevaluationQualityUpdatingStrategy());
        product.increaseQuality(1);
        assertThat(product.getQuality()).isEqualTo(50);
    }

    @Test
    void shouldDecreaseSellIn() {
        Product product = Product.createProduct("Normal Item", 10, 10, new RegularDevaluationQualityUpdatingStrategy());
        product.decreaseSellIn();
        assertThat(product.getSellIn()).isEqualTo(9);
    }

}


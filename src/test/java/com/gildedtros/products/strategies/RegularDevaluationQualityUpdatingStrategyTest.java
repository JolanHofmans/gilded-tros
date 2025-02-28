package com.gildedtros.products.strategies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegularDevaluationQualityUpdatingStrategyTest extends QualityUpdatingStrategyTest {

    private final RegularDevaluationQualityUpdatingStrategy regularDevaluationQualityUpdatingStrategy = new RegularDevaluationQualityUpdatingStrategy();

    @ParameterizedTest
    @ValueSource(ints = {1, 4})
    void shouldDecreaseQualityByOneWhenSellInPositive(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        regularDevaluationQualityUpdatingStrategy.updateQuality(product);

        verify(product).decreaseQuality(1);
        verify(product).decreaseSellIn();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -4})
    void shouldDecreaseQualityByTwoWhenSellInZeroOrNegative(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        regularDevaluationQualityUpdatingStrategy.updateQuality(product);

        verify(product).decreaseQuality(2);
        verify(product).decreaseSellIn();
    }
}

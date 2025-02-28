package com.gildedtros.products.strategies;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DoubleRateDevaluationQualityUpdatingStrategyTest extends QualityUpdatingStrategyTest {

    private final DoubleRateDevaluationQualityUpdatingStrategy doubleRateDevaluationQualityUpdatingStrategy = new DoubleRateDevaluationQualityUpdatingStrategy();

    @ParameterizedTest
    @ValueSource(ints = {1, 4})
    void shouldDecreaseQualityBytwoWhenSellInPositive(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        doubleRateDevaluationQualityUpdatingStrategy.updateQuality(product);

        verify(product).decreaseQuality(2);
        verify(product).decreaseSellIn();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -4})
    void shouldDecreaseQualityByfourWhenSellInZeroOrNegative(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        doubleRateDevaluationQualityUpdatingStrategy.updateQuality(product);

        verify(product).decreaseQuality(4);
        verify(product).decreaseSellIn();
    }
}
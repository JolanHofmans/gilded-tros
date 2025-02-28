package com.gildedtros.products.strategies;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegularAppreciationQualityUpdatingStrategyTest extends QualityUpdatingStrategyTest {

    private final RegularAppreciationQualityUpdatingStrategy regularAppreciationQualityUpdatingStrategy = new RegularAppreciationQualityUpdatingStrategy();

    @ParameterizedTest
    @ValueSource(ints = {1, 4})
    void shouldIncreaseQualityByOneWhenSellInPositive(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        regularAppreciationQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(1);
        verify(product).decreaseSellIn();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -4})
    void shouldIncreaseQualityByTwoWhenSellInZeroOrNegative(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        regularAppreciationQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(2);
        verify(product).decreaseSellIn();
    }
}
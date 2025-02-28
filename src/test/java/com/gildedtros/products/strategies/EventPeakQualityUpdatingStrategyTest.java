package com.gildedtros.products.strategies;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventPeakQualityUpdatingStrategyTest extends QualityUpdatingStrategyTest {

    private final EventPeakQualityUpdatingStrategy eventPeakQualityUpdatingStrategy = new EventPeakQualityUpdatingStrategy();

    @ParameterizedTest
    @ValueSource(ints = {0, -4})
    void shouldHaveQualityZeroWhenSellInZeroOrNegative(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        eventPeakQualityUpdatingStrategy.updateQuality(product);

        verify(product).setQuality(0);
        verify(product).decreaseSellIn();
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 15})
    void shouldIncreaseQualityByOneWhenSellInGreaterThanTen(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        eventPeakQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(1);
        verify(product).decreaseSellIn();
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 6})
    void shouldIncreaseQualityByTwoWhenSellInGreaterThanFive(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        eventPeakQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(2);
        verify(product).decreaseSellIn();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5})
    void shouldIncreaseQualityByThreeWhenSellInGreaterThanZero(int sellIn) {
        when(product.getSellIn()).thenReturn(sellIn);

        eventPeakQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(3);
        verify(product).decreaseSellIn();
    }
}
package com.gildedtros.products.strategies;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventPeakQualityUpdatingStrategyTest extends QualityUpdatingStrategyTest {

    private final EventPeakQualityUpdatingStrategy eventPeakQualityUpdatingStrategy = new EventPeakQualityUpdatingStrategy();

    @Test
    void shouldHaveQualityZeroWhenSellInZeroOrNegative() {
        when(product.getSellIn()).thenReturn(0);

        eventPeakQualityUpdatingStrategy.updateQuality(product);

        verify(product).setQuality(0);
        verify(product).decreaseSellIn();
    }

    @Test
    void shouldIncreaseQualityByOneWhenSellInGreaterThanTen() {
        when(product.getSellIn()).thenReturn(11);

        eventPeakQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(1);
        verify(product).decreaseSellIn();
    }

    @Test
    void shouldIncreaseQualityByTwoWhenSellInGreaterThanFive() {
        when(product.getSellIn()).thenReturn(7);

        eventPeakQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(2);
        verify(product).decreaseSellIn();
    }

    @Test
    void shouldIncreaseQualityByThreeWhenSellInGreaterThanZero() {
        when(product.getSellIn()).thenReturn(3);

        eventPeakQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(3);
        verify(product).decreaseSellIn();
    }
}
package com.gildedtros.products.strategies;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegularAppreciationQualityUpdatingStrategyTest extends QualityUpdatingStrategyTest {

    private final RegularAppreciationQualityUpdatingStrategy regularAppreciationQualityUpdatingStrategy = new RegularAppreciationQualityUpdatingStrategy();

    @Test
    void shouldIncreaseQualityByOneWhenSellInPositive() {
        when(product.getSellIn()).thenReturn(10);

        regularAppreciationQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(1);
        verify(product).decreaseSellIn();
    }

    @Test
    void shouldIncreaseQualityByTwoWhenSellInZeroOrNegative() {
        when(product.getSellIn()).thenReturn(0);

        regularAppreciationQualityUpdatingStrategy.updateQuality(product);

        verify(product).increaseQuality(2);
        verify(product).decreaseSellIn();
    }
}
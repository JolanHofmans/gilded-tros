package com.gildedtros.products.strategies;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegularDevaluationQualityUpdatingStrategyTest extends QualityUpdatingStrategyTest {

    private final RegularDevaluationQualityUpdatingStrategy regularDevaluationQualityUpdatingStrategy = new RegularDevaluationQualityUpdatingStrategy();

    @Test
    void shouldDecreaseQualityByOneWhenSellInPositive() {
        when(product.getSellIn()).thenReturn(10);

        regularDevaluationQualityUpdatingStrategy.updateQuality(product);

        verify(product).decreaseQuality(1);
        verify(product).decreaseSellIn();
    }

    @Test
    void shouldDecreaseQualityByTwoWhenSellInZeroOrNegative() {
        when(product.getSellIn()).thenReturn(0);

        regularDevaluationQualityUpdatingStrategy.updateQuality(product);

        verify(product).decreaseQuality(2);
        verify(product).decreaseSellIn();
    }
}

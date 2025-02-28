package com.gildedtros.products.strategies;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verifyNoInteractions;

class ConstantQualityUpdatingStrategyTest extends QualityUpdatingStrategyTest {

    private final ConstantQualityUpdatingStrategy constantQualityUpdatingStrategy = new ConstantQualityUpdatingStrategy();

    @Test
    void shouldStayConstant() {
        constantQualityUpdatingStrategy.updateQuality(product);

        verifyNoInteractions(product);
    }
}
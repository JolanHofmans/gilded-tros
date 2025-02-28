package com.gildedtros.products.strategies;

import com.gildedtros.products.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public abstract class QualityUpdatingStrategyTest {

    @Mock
    protected Product product;

    @AfterEach
    void verifyNoMoreProductInteractions() {
        verifyNoMoreInteractions(product);
    }
}

package com.gildedtros;

import com.gildedtros.products.Product;
import com.gildedtros.products.ProductFactory;

import java.util.Arrays;
import java.util.List;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        List<Product> products = Arrays.stream(items)
                .map(ProductFactory::createProduct)
                .toList();

        products.forEach(Product::updateQuality);

        items = products.stream()
                .map(product -> new Item(product.getName(), product.getSellIn(), product.getQuality()))
                .toArray(Item[]::new);
    }
}
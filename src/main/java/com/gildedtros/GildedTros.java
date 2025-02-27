package com.gildedtros;

import com.gildedtros.products.Product;

import java.util.Arrays;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        items = Arrays.stream(items)
                .map(item -> new Product(item.name, item.sellIn, item.quality))
                .map(this::updateQualityOfProduct)
                .map(product -> new Item(product.getName(), product.getSellIn(), product.getQuality()))
                .toArray(Item[]::new);
    }

    private Product updateQualityOfProduct(Product product) {
        if (!product.getName().equals("Good Wine") && !product.getName().equals("Backstage passes for Re:Factor") && !product.getName().equals("Backstage passes for HAXX")) {
            if (!product.getName().equals("B-DAWG Keychain")) {
                product.decreaseQuality(1);
            }
        } else {
            product.increaseQuality(1);

            if (product.getName().equals("Backstage passes for Re:Factor") || product.getName().equals("Backstage passes for HAXX")) {
                if (product.getSellIn() < 11) {
                    product.increaseQuality(1);
                }

                if (product.getSellIn() < 6) {
                    product.increaseQuality(1);
                }
            }
        }

        if (!product.getName().equals("B-DAWG Keychain")) {
            product.decreaseSellIn();
        }

        if (product.getSellIn() < 0) {
            if (!product.getName().equals("Good Wine")) {
                if (!product.getName().equals("Backstage passes for Re:Factor") && !product.getName().equals("Backstage passes for HAXX")) {
                    if (!product.getName().equals("B-DAWG Keychain")) {
                        product.decreaseQuality(1);
                    }
                } else {
                    product.setQuality(0);
                }
            } else {
                product.increaseQuality(1);
            }
        }
        return product;
    }
}
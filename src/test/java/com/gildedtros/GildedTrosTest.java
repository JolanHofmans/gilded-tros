package com.gildedtros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedTrosTest {

    private static final String NORMAL_ITEM = "Normal Item";
    private static final String GOOD_WINE = "Good Wine";
    private static final String B_DAWG_KEYCHAIN = "B-DAWG Keychain";

    @Test
    void regularItemShouldDecreaseInQualityByOneEachDay() {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, 1, 4)};
        GildedTros app = new GildedTros(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(3);
        assertThat(app.items[0].sellIn).isZero();

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(1);
        assertThat(app.items[0].sellIn).isEqualTo(-1);

        app.updateQuality();

        assertThat(app.items[0].quality).isZero();
        assertThat(app.items[0].sellIn).isEqualTo(-2);
    }

    @Test
    void goodWineShouldIncreaseInQualityByOneEachDay() {
        Item[] items = new Item[]{new Item(GOOD_WINE, 1, 47)};
        GildedTros app = new GildedTros(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(48);
        assertThat(app.items[0].sellIn).isZero();

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(50);
        assertThat(app.items[0].sellIn).isEqualTo(-1);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(50);
        assertThat(app.items[0].sellIn).isEqualTo(-2);
    }

    @Test
    void bDawgShouldStayConstant() {
        Item[] items = new Item[]{new Item(B_DAWG_KEYCHAIN, 1, 80)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(80);
        assertThat(app.items[0].sellIn).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Backstage passes for Re:Factor",
            "Backstage passes for HAXX",
    })
    void backStagePassesShouldDecreaseByOneWhenWellInLongerThanTenDays(String backstagePass) {
        Item[] items = new Item[]{new Item(backstagePass, 15, 2)};
        GildedTros app = new GildedTros(items);

        IntStream.range(0, 5).forEach(i -> app.updateQuality());
        assertThat(app.items[0].quality).isEqualTo(7);
        assertThat(app.items[0].sellIn).isEqualTo(10);

        IntStream.range(0, 5).forEach(i -> app.updateQuality());
        assertThat(app.items[0].quality).isEqualTo(17);
        assertThat(app.items[0].sellIn).isEqualTo(5);

        IntStream.range(0, 5).forEach(i -> app.updateQuality());
        assertThat(app.items[0].quality).isEqualTo(32);
        assertThat(app.items[0].sellIn).isZero();

        app.updateQuality();
        assertThat(app.items[0].quality).isZero();
        assertThat(app.items[0].sellIn).isEqualTo(-1);


    }

    @ParameterizedTest
    @ValueSource(strings = {"Duplicate Code", "Long Methods", "Ugly Variable Names"})
    void smellyItemShouldDecreaseByTwoInQualityByOneEachDay(String name) {
        Item[] items = new Item[]{new Item(name, 1, 10)};
        GildedTros app = new GildedTros(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(8);
        assertThat(app.items[0].sellIn).isZero();

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(4);
        assertThat(app.items[0].sellIn).isEqualTo(-1);

        app.updateQuality();

        assertThat(app.items[0].quality).isZero();
        assertThat(app.items[0].sellIn).isEqualTo(-2);

        app.updateQuality();

        assertThat(app.items[0].quality).isZero();
        assertThat(app.items[0].sellIn).isEqualTo(-3);
    }

}

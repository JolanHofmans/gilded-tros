package com.gildedtros;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedTrosTest {

    private static final String NORMAL_ITEM = "Normal Item";
    private static final String GOOD_WINE = "Good Wine";
    private static final String B_DAWG_KEYCHAIN = "B-DAWG Keychain";

    @ParameterizedTest
    @ValueSource(ints = {10, 1})
    void regularItemShouldDecreaseInQualityByOneEachDay(int sellIn) {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(9);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 0, -1})
    void regularItemShouldNotDecreaseInQualityBelowZero(int sellIn) {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, sellIn, 0)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isZero();
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void regularItemShouldDecreaseInQualityByTwoAfterHittingSellInDay(int sellIn) {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(8);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 1})
    void goodWineShouldIncreaseInQualityByOneEachDay(int sellIn) {
        Item[] items = new Item[]{new Item(GOOD_WINE, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(11);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10})
    void goodWineShouldIncreaseInQualityByTwoAfterSellIn(int sellIn) {
        Item[] items = new Item[]{new Item(GOOD_WINE, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(12);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 0, -10})
    void bDawgShouldStayConstant(int sellIn) {
        Item[] items = new Item[]{new Item(B_DAWG_KEYCHAIN, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(10);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn);
    }

    @ParameterizedTest
    @CsvSource({
            "Backstage passes for Re:Factor, 11",
            "Backstage passes for HAXX, 11",
    })
    void backStagePassesShouldDecreaseByOneWhenWellInLongerThanTenDays(String backstagePass, int sellIn) {
        Item[] items = new Item[]{new Item(backstagePass, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(11);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

    @ParameterizedTest
    @CsvSource({
            "Backstage passes for Re:Factor, 10",
            "Backstage passes for Re:Factor, 6",
            "Backstage passes for HAXX, 10",
            "Backstage passes for HAXX, 6",
    })
    void backstagePassesForInterestingConfsShouldIncreaseByTwoWhenWellInLongerThanTenDays(String backstagePass, int sellIn) {
        Item[] items = new Item[]{new Item(backstagePass, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(12);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

    @ParameterizedTest
    @CsvSource({
            "Backstage passes for Re:Factor, 5",
            "Backstage passes for Re:Factor, 1",
            "Backstage passes for HAXX, 5",
            "Backstage passes for HAXX, 1",
    })
    void backstagePassesForInterestingConfsShouldIncreaseByThreeWhenWellInLongerThanTenDays(String backstagePass, int sellIn) {
        Item[] items = new Item[]{new Item(backstagePass, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(13);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

    @ParameterizedTest
    @CsvSource({
            "Backstage passes for Re:Factor, 0",
            "Backstage passes for Re:Factor, -1",
            "Backstage passes for HAXX, 0",
            "Backstage passes for HAXX, -1",
    })
    void backstagePassesForInterestingConfsShouldBeZeroQualityWhenHittingSellIn(String backstagePass, int sellIn) {
        Item[] items = new Item[]{new Item(backstagePass, sellIn, 10)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isZero();
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
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

    @ParameterizedTest
    @CsvSource({
            "Backstage passes for Re:Factor, 5, 48",
            "Backstage passes for Re:Factor, 10, 49",
            "Backstage passes for Re:Factor, 15, 50",
            "Backstage passes for HAXX, 5, 48",
            "Backstage passes for HAXX, 10, 49",
            "Backstage passes for HAXX, 15, 50",
            "Good Wine, 10, 49",
            "Good Wine, -10, 49",
    })
    void itemShouldNotIncreaseInQualityOverFifty(String itemName, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(itemName, sellIn, quality)};
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(50);
        assertThat(app.items[0].sellIn).isEqualTo(sellIn - 1);
    }

}

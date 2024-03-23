package org.DeliveryCostCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SimpleUnitTests {

    @Test
    @DisplayName("Test calculateDeliveryCost method")
    @Tag("delivery-cost")
    void testForBigSizeAndFragile() {
        int distance = 40;
        boolean isBigSize = true;
        boolean isFragile = true;
        double loadFactor = 1.5;
        int expectedCost = 1000;

        int actualCost = DeliveryCostCalculator.calculateDeliveryCost(distance, isBigSize, isFragile, loadFactor);

        assertEquals(expectedCost, actualCost, "неверная цена доставки");
    }

    @ParameterizedTest
    @CsvSource({
            "40, true, true, 1.5, 1100",
            "20, false, false, 1.0, 400",
            "5, true, false, 0.8, 400"
    })
    @DisplayName("Test calculateDeliveryCost method")
    @Tag("delivery-cost")
    void testCalculateDeliveryCostWithTags(int distance, boolean isBigSize, boolean isFragile, double loadFactor, int expectedCost) {
        int actualCost = DeliveryCostCalculator.calculateDeliveryCost(distance, isBigSize, isFragile, loadFactor);
        assertEquals(expectedCost, actualCost);
    }
}



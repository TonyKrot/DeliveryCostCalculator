package org.DeliveryCostCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SimpleUnitTests {

    @Test
    @Tag("unit")
    @DisplayName("Test calculateDeliveryCost method with regular parameters")
    void testCalculateDeliveryCostRegular() {
        int distance = 20;
        boolean isBigSize = false;
        boolean isFragile = false;
        double loadFactor = 1.0;

        int expectedCost = 400; // Base cost is 200 + 100 (small size) + 0 (load factor 1.0) = 300, but minimum cost is 400
        int actualCost = DeliveryCostCalculator.calculateDeliveryCost(distance, isBigSize, isFragile, loadFactor);

        assertEquals(expectedCost, actualCost);
    }

    @Test
    @Tag("unit")
    @DisplayName("Test calculateDeliveryCost method with large size and high load factor")
    void testCalculateDeliveryCostLargeHighLoadFactor() {
        int distance = 15;
        boolean isBigSize = true;
        boolean isFragile = false;
        double loadFactor = 1.4;

        int expectedCost = 560; // Base cost is 200 + 200 (large size) + 0 (load factor 1.4) = 560
        int actualCost = DeliveryCostCalculator.calculateDeliveryCost(distance, isBigSize, isFragile, loadFactor);

        assertEquals(expectedCost, actualCost);
    }

      @Test
      @Tag("unit")
    @DisplayName("Test calculateDeliveryCost method with fragile cargo and distance over 30 km")
    void testCalculateDeliveryCostFragileOver30km() {
        int distance = 35;
        boolean isBigSize = true;
        boolean isFragile = true;
        double loadFactor = 1.2;

          int expectedCost = -1; // Base cost is 300 + 200 (large size) + 300 (load factor 1.2) = 960,but Fragile cargo can't be delivered over 30 km
        int actualCost = DeliveryCostCalculator.calculateDeliveryCost(distance, isBigSize, isFragile, loadFactor);

        assertEquals(expectedCost, actualCost);
    }

    @ParameterizedTest
    @Tag("unit")
    @DisplayName("Parameterized test for calculateDeliveryCost method")
    @CsvSource({
            "20, false, false, 1.0, 400", // Regular parameters
            "35, true, true, 1.2, -1",   // Fragile cargo over 30 km
            "15, true, false, 1.5, 600"  // Large size and high load factor
    })

    void testCalculateDeliveryCost(int distance, boolean isBigSize, boolean isFragile, double loadFactor, int expectedCost) {
        int actualCost = DeliveryCostCalculator.calculateDeliveryCost(distance, isBigSize, isFragile, loadFactor);
        assertEquals(expectedCost, actualCost);
    }
}
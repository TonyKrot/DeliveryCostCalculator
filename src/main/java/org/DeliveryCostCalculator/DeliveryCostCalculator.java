package org.DeliveryCostCalculator;

public class DeliveryCostCalculator {
    public static int calculateDeliveryCost(int distance, boolean isBigSize, boolean isFragile, double loadFactor) {
        int baseCost = 0;

        if (distance > 30) {
            baseCost += 300;
        } else if (distance > 10) {
            baseCost += 200;
        } else if (distance > 2) {
            baseCost += 100;
        } else {
            baseCost += 50;
        }

        if (isBigSize) {
            baseCost += 200;
        } else {
            baseCost += 100;
        }

        if (isFragile && distance > 30) {
            baseCost += 300;
        }

        int totalCost = (int) (baseCost * loadFactor);

        if (totalCost < 400) {
            totalCost = 400;
        }

        return totalCost;
    }
}


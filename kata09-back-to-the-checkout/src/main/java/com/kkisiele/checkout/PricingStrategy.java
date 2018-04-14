package com.kkisiele.checkout;

public interface PricingStrategy {
    Calculation calculate(Items items);
}

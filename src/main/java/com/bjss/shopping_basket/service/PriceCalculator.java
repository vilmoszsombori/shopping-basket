package com.bjss.shopping_basket.service;

import java.util.Collection;
import java.util.Map;

public interface PriceCalculator {
    
    public double getInitialPrice(Basket basket);

    public Map<Offer, Double> getDiscounts(Basket basket);

    public Collection<Offer> getApplicableOffers(Basket basket);
    
}

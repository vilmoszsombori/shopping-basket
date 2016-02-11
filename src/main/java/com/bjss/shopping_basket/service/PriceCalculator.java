package com.bjss.shopping_basket.service;

import java.util.Collection;

public interface PriceCalculator {
    
    public double getInitialPrice(Basket basket);

    public double getDiscount(Basket basket);

    public Collection<Offer> getApplicableOffers(Basket basket);
    
}

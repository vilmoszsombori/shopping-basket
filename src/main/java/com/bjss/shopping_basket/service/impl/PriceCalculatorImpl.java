package com.bjss.shopping_basket.service.impl;

import com.bjss.shopping_basket.service.PriceCalculator;
import com.bjss.shopping_basket.model.Item;
import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PriceCalculatorImpl implements PriceCalculator {

    private Collection<Offer> offers = new HashSet<Offer>();

    public PriceCalculatorImpl(Offer... offers) {
        if (offers != null)
            for (Offer offer : offers)
                this.offers.add(offer);
    }

    @Override
    public double getInitialPrice(Basket basket) {
        double v = 0;
        if (basket != null && !basket.isEmpty())
            for (Item.Type itemType : Item.Type.values())
                v += basket.count(itemType) * Item.getInstance(itemType).getUnitPrice();
        return v;
    }

    @Override
    public Map<Offer, Double> getDiscounts(Basket basket) {
        Map<Offer, Double> discounts = new HashMap<>();
        for (Offer offer : offers)
            if (offer.isApplicable(basket))
                discounts.put(offer, offer.getDiscount(basket));
        return discounts;
    }

    @Override
    public Collection<Offer> getApplicableOffers(Basket basket) {
        Collection<Offer> applicableOffers = new HashSet<Offer>();
        for (Offer offer : offers)
            if (offer.isApplicable(basket))
                applicableOffers.add(offer);
        return applicableOffers;
    }

}


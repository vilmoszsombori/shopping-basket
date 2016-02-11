package com.bjss.shopping_basket.service.impl;

import com.bjss.shopping_basket.service.PriceCalculator;
import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;

import java.util.Collection;
import java.util.HashSet;

import com.bjss.shopping_basket.domain.Item;

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
    public double getDiscount(Basket basket) {
        double v = 0;
        for (Offer offer : offers)
            if (offer.isApplicable(basket))
                v += offer.getDiscount(basket);
        return v;
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


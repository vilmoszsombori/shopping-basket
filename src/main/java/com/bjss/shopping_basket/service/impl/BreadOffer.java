package com.bjss.shopping_basket.service.impl;

import com.bjss.shopping_basket.domain.Bread;
import com.bjss.shopping_basket.domain.Item;
import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;

public class BreadOffer implements Offer {

    public static final String DESCRIPTION = "Buy 2 soups, get bread half price";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public double getDiscount(Basket basket) {
        if (isApplicable(basket))
            return Math.min(Math.floor(basket.count(Item.Type.SOUP) / 2),
                    basket.count(Item.Type.BREAD)) * Bread.UNIT_PRICE * .5;
        else
            return 0;
    }

    @Override
    public boolean isApplicable(Basket basket) {
        if (basket == null || basket.isEmpty())
            return false;
        else
            return basket.count(Item.Type.BREAD) > 0 && basket.count(Item.Type.SOUP) > 1;
    }

}

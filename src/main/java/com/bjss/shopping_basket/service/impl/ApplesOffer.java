package com.bjss.shopping_basket.service.impl;

import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;
import com.bjss.shopping_basket.domain.Apples;
import com.bjss.shopping_basket.domain.Item;

public class ApplesOffer implements Offer {

    public static final String DESCRIPTION = "Apples 10% off";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public double getDiscount(Basket basket) {
        if (isApplicable(basket))
            return basket.count(Item.Type.APPLES) * (new Apples()).getUnitPrice() * .1;
        else
            return 0;
    }

    @Override
    public boolean isApplicable(Basket basket) {
        if (basket == null || basket.isEmpty())
            return false;
        else
            return basket.count(Item.Type.APPLES) > 0;
    }

}

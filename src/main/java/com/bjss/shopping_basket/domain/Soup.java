package com.bjss.shopping_basket.domain;

public class Soup extends Item {

    public static final double UNIT_PRICE = .65;

    @Override
    public Type getType() {
        return Item.Type.SOUP;
    }

    @Override
    public double getUnitPrice() {
        return UNIT_PRICE;
    }

}

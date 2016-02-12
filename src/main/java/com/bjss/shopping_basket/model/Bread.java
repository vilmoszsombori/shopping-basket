package com.bjss.shopping_basket.model;

public class Bread extends Item {

    public static final double UNIT_PRICE = .8;

    @Override
    public Type getType() {
        return Item.Type.BREAD;
    }

    @Override
    public double getUnitPrice() {
        return UNIT_PRICE;
    }

}


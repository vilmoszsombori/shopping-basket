package com.bjss.shopping_basket.model;

public class Apples extends Item {

    public static final double UNIT_PRICE = 1.;

    @Override
    public Type getType() {
        return Item.Type.APPLES;
    }

    @Override
    public double getUnitPrice() {
        return UNIT_PRICE;
    }

}

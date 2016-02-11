package com.bjss.shopping_basket.domain;

public class Milk extends Item {

    public static final double UNIT_PRICE = 1.3;

    @Override
    public Type getType() {
        return Item.Type.MILK;
    }

    @Override
    public double getUnitPrice() {
        return UNIT_PRICE;
    }

}

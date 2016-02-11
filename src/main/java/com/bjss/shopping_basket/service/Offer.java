package com.bjss.shopping_basket.service;

public interface Offer {

    public String getDescription();

    public double getDiscount(Basket basket);

    public boolean isApplicable(Basket basket);

}

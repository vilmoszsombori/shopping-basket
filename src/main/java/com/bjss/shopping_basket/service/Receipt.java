package com.bjss.shopping_basket.service;

public interface Receipt {

    public String generate(Basket basket, PriceCalculator priceCalculator);

}

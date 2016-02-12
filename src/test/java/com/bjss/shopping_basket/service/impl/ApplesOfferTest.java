package com.bjss.shopping_basket.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bjss.shopping_basket.model.Apples;
import com.bjss.shopping_basket.model.Bread;
import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;

public class ApplesOfferTest {
    
    private static final int APPLES_COUNT = 19;
    private static final double PRICE_TOLERANCE = .00001;

    private Offer offer;

    @Before
    public void setUp() throws Exception {
        offer = new ApplesOffer();
    }

    @Test
    public void testGetDiscountWithNullBasket() {
        assertEquals(0, offer.getDiscount(null), PRICE_TOLERANCE);
    }

    @Test
    public void testGetDiscountWithEmptyBasket() {
        assertEquals(0, offer.getDiscount(new BasketImpl()), PRICE_TOLERANCE);
    }

    @Test
    public void testGetDiscountWithApples() {
        Basket basket = new BasketImpl();
        for (int i = 0; i < APPLES_COUNT; i++)
            basket.add(new Apples());
        assertEquals(APPLES_COUNT * Apples.UNIT_PRICE * .1, offer.getDiscount(basket),
                PRICE_TOLERANCE);
    }

    @Test
    public void testGetDiscountWithNoApples() {
        Basket basket = new BasketImpl();
        for (int i = 0; i < APPLES_COUNT; i++)
            basket.add(new Bread());
        assertEquals(0, offer.getDiscount(basket), PRICE_TOLERANCE);
    }

    @Test
    public void testIsApplicableWithNullBasket() {
        assertFalse(offer.isApplicable(null));
    }

    @Test
    public void testIsApplicableWithEmptyBasket() {
        assertFalse(offer.isApplicable(new BasketImpl()));
    }

    @Test
    public void testIsApplicableWithNoApples() {
        Basket basket = new BasketImpl();
        for (int i = 0; i < APPLES_COUNT; i++)
            basket.add(new Bread());
        assertFalse(offer.isApplicable(basket));
    }

    @Test
    public void testIsApplicableWithApples() {
        Basket basket = new BasketImpl();
        for (int i = 0; i < APPLES_COUNT; i++)
            basket.add(new Apples());
        assertTrue(offer.isApplicable(basket));
    }

}

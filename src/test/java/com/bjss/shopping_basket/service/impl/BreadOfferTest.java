package com.bjss.shopping_basket.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bjss.shopping_basket.model.Bread;
import com.bjss.shopping_basket.model.Item;
import com.bjss.shopping_basket.model.Soup;
import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;

public class BreadOfferTest {

    private static final int BREAD_COUNT = 19;
    private static final int SOUP_COUNT = 17;
    private static final double PRICE_TOLERANCE = .00001;

    private Offer offer;

    @Before
    public void setUp() throws Exception {
        offer = new BreadOffer();
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
    public void testGetDiscountWithBreadButNoSoup() {
        Basket basket = new BasketImpl();
        for (int i = 0; i < BREAD_COUNT; i++)
            basket.add(new Bread());
        assertEquals(0, offer.getDiscount(basket), PRICE_TOLERANCE);
    }

    @Test
    public void testGetDiscountWithNoBread() {
        Basket basket = new BasketImpl();
        for (int i = 0; i < SOUP_COUNT; i++)
            basket.add(new Soup());
        assertEquals(0, offer.getDiscount(basket), PRICE_TOLERANCE);
    }

    @Test
    public void testGetDiscountWithBreadAndSoup() {
        Basket basket = new BasketImpl();
        basket.add(Item.Type.SOUP, SOUP_COUNT);
        basket.add(Item.Type.BREAD, BREAD_COUNT);
        assertEquals(Math.min(Math.floor(SOUP_COUNT / 2), BREAD_COUNT) * Bread.UNIT_PRICE * .5,
                offer.getDiscount(basket), PRICE_TOLERANCE);
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
    public void testIsApplicableWithBreadButNoSoup() {
        Basket basket = new BasketImpl();
        for (int i = 0; i < BREAD_COUNT; i++)
            basket.add(new Bread());
        assertFalse(offer.isApplicable(basket));
    }

    @Test
    public void testIsApplicableWithNoBread() {
        Basket basket = new BasketImpl();
        for (int i = 0; i < SOUP_COUNT; i++)
            basket.add(new Soup());
        assertFalse(offer.isApplicable(basket));
    }

    @Test
    public void testIsApplicableWithBreadAndSoup() {
        Basket basket = new BasketImpl();
        basket.add(Item.Type.SOUP, SOUP_COUNT);
        basket.add(Item.Type.BREAD, BREAD_COUNT);
        assertTrue(offer.isApplicable(basket));
    }

}

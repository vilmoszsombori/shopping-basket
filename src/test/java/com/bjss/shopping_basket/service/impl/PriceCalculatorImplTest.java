package com.bjss.shopping_basket.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.bjss.shopping_basket.model.Item;
import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;
import com.bjss.shopping_basket.service.PriceCalculator;

public class PriceCalculatorImplTest {

    private static final double PRICE_TOLERANCE = .00001;

    @SuppressWarnings("serial")
    public static final Map<Item.Type, Integer> content = new HashMap<Item.Type, Integer>() {
        {
            put(Item.Type.APPLES, 9);
            put(Item.Type.MILK, 11);
            put(Item.Type.BREAD, 17);
            put(Item.Type.SOUP, 13);
        }
    };

    Basket basket;

    @Before
    public void setUp() throws Exception {
        basket = new BasketImpl();
        for (Item.Type itemType : content.keySet())
            basket.add(itemType, content.get(itemType));
    }

    @Test
    public void testInitialPriceWithoutOffer() {
        double v = 0;
        for (Item.Type itemType : content.keySet())
            v += Item.getInstance(itemType).getUnitPrice() * content.get(itemType);

        PriceCalculator priceCalculator = new PriceCalculatorImpl();
        assertEquals(v, priceCalculator.getInitialPrice(basket), PRICE_TOLERANCE);
    }

    @Test
    public void testInitialPriceWithApplesOffer() {
        double v = 0;
        for (Item.Type itemType : content.keySet())
            v += Item.getInstance(itemType).getUnitPrice() * content.get(itemType);

        PriceCalculator priceCalculator = new PriceCalculatorImpl(new ApplesOffer());
        assertEquals(v, priceCalculator.getInitialPrice(basket), PRICE_TOLERANCE);
    }

    @Test
    public void testInitialPriceWithApplesAndBreadOffer() {
        double v = 0;
        for (Item.Type itemType : content.keySet())
            v += Item.getInstance(itemType).getUnitPrice() * content.get(itemType);

        PriceCalculator priceCalculator =
                new PriceCalculatorImpl(new ApplesOffer(), new BreadOffer());
        assertEquals(v, priceCalculator.getInitialPrice(basket), PRICE_TOLERANCE);
    }

    @Test
    public void testDiscountWithoutOffer() {
        PriceCalculator priceCalculator = new PriceCalculatorImpl();
        assertTrue(priceCalculator.getDiscounts(basket).isEmpty());
    }

    @Test
    public void testDiscountWithApplesOffer() {
        Offer applesOffer = new ApplesOffer();
        PriceCalculator priceCalculator = new PriceCalculatorImpl(new ApplesOffer());
        double discount = priceCalculator.getDiscounts(basket).entrySet().stream()
                .collect(Collectors.summingDouble(Entry::getValue));
        assertEquals(applesOffer.getDiscount(basket), discount, PRICE_TOLERANCE);
    }

    @Test
    public void testDiscountWithBreadOffer() {
        Offer breadOffer = new BreadOffer();
        PriceCalculator priceCalculator = new PriceCalculatorImpl(new BreadOffer());
        double discount = priceCalculator.getDiscounts(basket).entrySet().stream()
                .collect(Collectors.summingDouble(Entry::getValue));
        assertEquals(breadOffer.getDiscount(basket), discount, PRICE_TOLERANCE);
    }

    @Test
    public void testDiscountWithApplesAndBreadOffer() {
        Offer applesOffer = new ApplesOffer();
        Offer breadOffer = new BreadOffer();
        PriceCalculator priceCalculator =
                new PriceCalculatorImpl(new ApplesOffer(), new BreadOffer());
        double discount = priceCalculator.getDiscounts(basket).entrySet().stream()
                .collect(Collectors.summingDouble(Entry::getValue));
        assertEquals(applesOffer.getDiscount(basket) + breadOffer.getDiscount(basket), discount,
                PRICE_TOLERANCE);
    }

}

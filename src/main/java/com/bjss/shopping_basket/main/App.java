package com.bjss.shopping_basket.main;

import java.util.ArrayList;
import java.util.Collection;

import com.bjss.shopping_basket.model.Item;
import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.PriceCalculator;
import com.bjss.shopping_basket.service.Receipt;
import com.bjss.shopping_basket.service.impl.ApplesOffer;
import com.bjss.shopping_basket.service.impl.BasketImpl;
import com.bjss.shopping_basket.service.impl.PriceCalculatorImpl;
import com.bjss.shopping_basket.service.impl.ReceiptImpl;

public class App {
    
    public static void main(String[] args) {
        Collection<Item> items = new ArrayList<Item>();

        for (String arg : args) {
            try {
                items.add(Item.getInstance(Item.Type.valueOf(arg.toUpperCase())));
            } catch (IllegalArgumentException e) {
                System.err.println("Unrecognized item: " + arg);
            }
        }

        Basket basket = new BasketImpl();

        basket.add(items.toArray(new Item[] {}));

        // System.out.println(basket);

        // PriceCalculator priceCalculator = new PriceCalculatorImpl(new ApplesOffer(), new
        // BreadOffer());
        PriceCalculator priceCalculator = new PriceCalculatorImpl(new ApplesOffer());

        Receipt receipt = new ReceiptImpl();

        System.out.println(receipt.generate(basket, priceCalculator));
    }
    
}

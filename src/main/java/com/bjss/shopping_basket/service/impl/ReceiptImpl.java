package com.bjss.shopping_basket.service.impl;

import java.util.Collection;

import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;
import com.bjss.shopping_basket.service.PriceCalculator;
import com.bjss.shopping_basket.service.Receipt;

public class ReceiptImpl implements Receipt {

    @Override
    public String generate(Basket basket, PriceCalculator priceCalculator) {
        String offerString = "";

        Collection<Offer> offers = priceCalculator.getApplicableOffers(basket);
        double initialPrice = priceCalculator.getInitialPrice(basket);
        double discount = priceCalculator.getDiscount(basket);

        if (offers.isEmpty()) {
            offerString = "(No offers available)\n";
        } else {
            for (Offer offer : offers)
                offerString += offer.getDescription() + "; ";
            if (offerString.endsWith("; "))
                offerString = offerString.substring(0, offerString.length() - 2);
            offerString += String.format(": -£%.2f%n", discount);
        }

        String str = String.format("Subtotal: £%.2f%n" + offerString + "Total: £%.2f%n",
                initialPrice, initialPrice - discount);

        return str;
    }

}

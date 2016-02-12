package com.bjss.shopping_basket.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.service.Offer;
import com.bjss.shopping_basket.service.PriceCalculator;
import com.bjss.shopping_basket.service.Receipt;

public class ReceiptImpl implements Receipt {

    @Override
    public String generate(Basket basket, PriceCalculator priceCalculator) {
        if (basket.isEmpty()) {
            return "Basket is empty";
        }

        double initialPrice = priceCalculator.getInitialPrice(basket);
        double discount = 0;
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Subtotal: £%.2f", initialPrice));

        Collection<Offer> offers = priceCalculator.getApplicableOffers(basket);
        if (offers.isEmpty()) {
            builder.append(System.lineSeparator());
            builder.append("(No offers available)");
        } else {
            Map<Offer, Double> discounts = priceCalculator.getDiscounts(basket);
            discounts.forEach((k, v) -> {
                builder.append(System.lineSeparator());
                builder.append(String.format("%s: -£%.2f", k.getDescription(), v));
            });
            discount = discounts.entrySet().stream()
                    .collect(Collectors.summingDouble(Entry::getValue));
        }

        builder.append(System.lineSeparator());
        builder.append(String.format("Total: £%.2f", (initialPrice - discount)));

        return builder.toString();
    }

}

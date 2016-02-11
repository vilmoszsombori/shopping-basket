package com.bjss.shopping_basket.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.bjss.shopping_basket.service.Basket;
import com.bjss.shopping_basket.domain.Item;
import com.bjss.shopping_basket.domain.Item.Type;

public class BasketImpl implements Basket {

    private Map<Item.Type, Integer> items = new HashMap<Item.Type, Integer>();

    @Override
    public int count(Type itemType) {
        return items.containsKey(itemType) ? items.get(itemType) : 0;
    }

    @Override
    public boolean add(Item... items) {
        boolean r = false;
        for (Item item : items)
            r = add(item.getType(), 1) || r;
        return r;
    }

    @Override
    public boolean add(Item.Type itemType, int count) {
        if (count <= 0)
            return false;
        else {
            items.put(itemType, count(itemType) + count);
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public void reset() {
        items.clear();
    }

    @Override
    public String toString() {
        String s = "Basket[";
        for (Item.Type itemType : items.keySet())
            s += itemType + ": " + count(itemType) + ", ";
        if (s.endsWith(", "))
            s = s.substring(0, s.length() - 2);
        s += "]";
        return s;
    }
    
}

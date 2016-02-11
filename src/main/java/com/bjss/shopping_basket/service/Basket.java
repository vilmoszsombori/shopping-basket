package com.bjss.shopping_basket.service;

import com.bjss.shopping_basket.domain.Item;

public interface Basket {

    public boolean add(Item.Type itemType, int count);

    public boolean add(Item... items);

    public int count(Item.Type itemType);

    public boolean isEmpty();

    public void reset();

}

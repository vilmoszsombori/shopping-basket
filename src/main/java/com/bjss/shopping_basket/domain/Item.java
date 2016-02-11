package com.bjss.shopping_basket.domain;

public abstract class Item {

    public static enum Type {
        APPLES, BREAD, MILK, SOUP;
    }

    public static Item getInstance(Item.Type itemType) {
        switch (itemType) {
            case APPLES:
                return new Apples();
            case BREAD:
                return new Bread();
            case MILK:
                return new Milk();
            case SOUP:
                return new Soup();
            default:
                throw new IllegalArgumentException("Unrecognized item: " + itemType);
        }
    }

    public abstract Type getType();

    public abstract double getUnitPrice();

    public String toString() {
        return this.getType().toString();
    }
}

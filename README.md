# Pricing a basket

This is a brief command line Java application (programme and unit tests) that can price a basket of products taking into account some special offers. 

The goods that can be purchased, together with their normal prices are:

*   Soup: 65p per tin
*   Bread: 80p per loaf
*   Milk: £1.30 per bottle
*   Apples: £1.00 per bag

Current special offers are:

*   Apples have a 10% discount off their normal price this week
*   Buy 2 tins of soup and get a loaf of bread for half price

The programme accepts a list of items in the basket and outputs the subtotal, the special offer discounts and the total price. 

Input is taken on the command line in the form of PriceBasket item1 item2 item3 ...

For example:

```
PriceBasket Apples Milk Bread
```

Output will be on the console, for example:

```
Subtotal: £3.10
Apples 10% off: -10p
Total: £3.00
```

If no special offers are applicable the output is:

```
Subtotal: £1.30
(No offers available)
Total price: £1.30
```

The code is well structured, commented, has a limited degree of error handling and it has a few key unit tests. The design is sufficiently flexible to allow future changes to the product list and/or discounts applied. 

It is an Java 8 / Maven command line application. Use Maven to build it:

```
mvn clean package
```

To launch it:

```
java -cp target/shopping-basket-0.0.1.jar PriceBasket Apples Apples Milk Bread Soup Soup
```


## Design

### Package structure

![Package structure](https://raw.githubusercontent.com/vilmoszsombori/shopping-basket/master/doc/package-diagram.png "Package structure")

### Class diagram

![Class diagram](https://raw.githubusercontent.com/vilmoszsombori/shopping-basket/master/doc/class-diagram.png "Class diagram")

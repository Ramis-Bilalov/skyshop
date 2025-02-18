package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItemList;
    private double total;


    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
        total = 0;
        basketItemList.forEach(basketItem -> total = total + basketItem.getProduct().getProductPrice() * basketItem.getCount());
    }

    public double getTotal() {
        return total;
    }

    public List<BasketItem> getBasketItemList() {
        return basketItemList;
    }
}

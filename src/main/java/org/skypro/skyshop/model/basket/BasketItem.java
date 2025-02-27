package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {

    private final Product product;

    private Integer count = 0;


    public BasketItem(Product product) {
        this.product = product;
        count++;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}

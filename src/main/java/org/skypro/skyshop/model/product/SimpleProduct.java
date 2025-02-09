package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private int productPrice;
    private final UUID id;

    public SimpleProduct(String productName, int productPrice, UUID id) {
        super(productName);
        this.id = id;
        if (productPrice >= 1) {
            this.productPrice = productPrice;
        } else {
            throw new IllegalArgumentException("Цена не может быть отрицательной!");
        }
    }

    @Override
    public int getProductPrice() {
        return productPrice;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return productName + " : " + productPrice;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
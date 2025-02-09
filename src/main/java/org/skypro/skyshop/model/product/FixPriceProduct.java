package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final int FIX_PRICE = 50;
    private final UUID id;

    public FixPriceProduct(String productName, UUID id) {
        super(productName);
        this.id = id;
    }

    @Override
    public int getProductPrice() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return productName + " : (Fix price) " + FIX_PRICE;
    }

    @Override
    public UUID getId() {
        return id;
    }
}

package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {

    protected String productName;


    public Product(String productName) {
        if (productName != null && !productName.isBlank()) {
            this.productName = productName;
        } else throw new IllegalArgumentException("Неправильное имя");
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return productName;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (!productName.isBlank()) {
            this.productName = productName;
        }
    }

    public abstract int getProductPrice();

    public abstract boolean isSpecial();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }
}
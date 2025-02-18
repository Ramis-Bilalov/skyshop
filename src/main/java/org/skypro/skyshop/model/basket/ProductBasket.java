package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ProductBasket {

    private final Map<UUID, Integer> productsMap;


    public ProductBasket() {
        this.productsMap = new TreeMap<>();
    }

    public void addProduct(UUID id) {
        if (productsMap.containsKey(id)) {
            productsMap.put(id, productsMap.get(id) + 1);
        } else {
            productsMap.put(id, 1);
        }
    }

    public Map<UUID, Integer> getProductsMap() {
        Map<UUID, Integer> unmodifiableMap = Collections.unmodifiableMap(productsMap);
        return unmodifiableMap;
    }
}

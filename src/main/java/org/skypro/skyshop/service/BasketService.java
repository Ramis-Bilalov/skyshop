package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {


    private final ProductBasket productBasket;

    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }



    public void addProductToBasket(UUID uuid) {
        if (storageService.getProductById(uuid).isEmpty()) {
            throw new NoSuchProductException("Такого продукта не существует!");
        } else
            productBasket.addProduct(uuid);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> productsMap = productBasket.getProductsMap();
        List<BasketItem> basketItems = productsMap
                .keySet()
                .stream()
                .map(integer -> new BasketItem(storageService.getProductById(integer).orElseThrow(NoSuchProductException::new)))
                .toList();
        return new UserBasket(basketItems);
    }
}


package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    ProductBasket productBasket;
    @Mock
    StorageService storageService;
    @InjectMocks
    BasketService basketService;

    @Test
    public void givenRandomUUID_whenAddProductToBasket_thenException() { // 1. Добавление несуществующего товара в корзину приводит к выбросу исключения.
        UUID uuid = UUID.randomUUID();
        try {
            basketService.addProductToBasket(uuid);
        } catch (NoSuchProductException noSuchProductException) {
            Assertions.assertEquals("Такого продукта не существует!", noSuchProductException.getMessage());
        }

    }

    @Test
    public void givenUUID_whenAddProductToBasket_thenProductAdded() { // 2. Добавление существующего товара вызывает метод addProduct у мока ProductBasket
        SimpleProduct orange = new SimpleProduct("Orange", 115, UUID.randomUUID());
        DiscountedProduct apple = new DiscountedProduct("Apple", 80, 15, UUID.randomUUID());
        SimpleProduct mango = new SimpleProduct("Mango", 500, UUID.randomUUID());

        Optional<Product> optionalProduct = Optional.of(orange);

        Mockito.when(storageService.getProductById(orange.getUuid())).thenReturn(optionalProduct);

        basketService.addProductToBasket(orange.getUuid());

        Mockito.verify(productBasket).addProduct(orange.getUuid());
    }

    @Test
    public void whenGetUserBasket_thenReturnNull() {    // 3. Метод getUserBasket возвращает пустую корзину, если ProductBasket пуст.
        Mockito.when(productBasket.getProductsMap()).thenReturn(new HashMap<>());

        basketService.getUserBasket();

        Assertions.assertEquals("UserBasket{basketItemList=[], total=0.0}", basketService.getUserBasket().toString());
    }

    @Test
    public void whenGetUserBasket_thenReturnUserBasket() { // 4. Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары.
        SimpleProduct orange = new SimpleProduct("Orange", 115, UUID.randomUUID());
        DiscountedProduct apple = new DiscountedProduct("Apple", 80, 15, UUID.randomUUID());
        SimpleProduct mango = new SimpleProduct("Mango", 500, UUID.randomUUID());

        Map<UUID, Integer> uuidIntegerMap = new HashMap<>();
        uuidIntegerMap.put(orange.getUuid(), 1);

        Map<UUID, Integer> unmodifiableMap = Collections.unmodifiableMap(uuidIntegerMap);

        Optional<Product> optional = Optional.of(orange);

        Mockito.when(storageService.getProductById(orange.getUuid())).thenReturn(optional);

        Mockito.when(productBasket.getProductsMap()).thenReturn(unmodifiableMap);

        basketService.getUserBasket();

        Assertions.assertEquals("UserBasket{basketItemList=[BasketItem{product=Orange : 115, count=1}], total=115.0}", basketService.getUserBasket().toString());
    }
}


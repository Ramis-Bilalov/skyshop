package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> uuidProductMap;
    private final Map<UUID, Article> uuidArticleMap;

    public StorageService() {
        uuidProductMap = new HashMap<>();
        uuidArticleMap = new HashMap<>();
        fillTheMap();
    }

    private void fillTheMap() {
        SimpleProduct orange = new SimpleProduct("Orange", 115, UUID.randomUUID());        // создание продуктов
        DiscountedProduct apple = new DiscountedProduct("Apple", 80, 15, UUID.randomUUID());
        SimpleProduct mango = new SimpleProduct("Mango", 500, UUID.randomUUID());
        FixPriceProduct kiwi = new FixPriceProduct("Kiwi", UUID.randomUUID());
        SimpleProduct watermelon = new SimpleProduct("Watermelon", 50, UUID.randomUUID());
        SimpleProduct banana = new SimpleProduct("Banana", 130, UUID.randomUUID());
        uuidProductMap.put(UUID.randomUUID(), orange);
        uuidProductMap.put(UUID.randomUUID(), apple);
        uuidProductMap.put(UUID.randomUUID(), mango);
        uuidProductMap.put(UUID.randomUUID(), kiwi);
        uuidProductMap.put(UUID.randomUUID(), watermelon);
        uuidProductMap.put(UUID.randomUUID(), banana);
        Article koreaAutos = new Article("Korea Autos", "KIA, HYUNDAI, GENESIS, SSANGYONG", UUID.randomUUID());
        Article germanAutos = new Article("Germany Autos", "VW, AUDI, SKODA, BMW, MERCEDES", UUID.randomUUID());
        Article japanAutos = new Article("Japan Autos", "Toyota, Mazda, Lexus", UUID.randomUUID());
        uuidArticleMap.put(UUID.randomUUID(), koreaAutos);
        uuidArticleMap.put(UUID.randomUUID(), germanAutos);
        uuidArticleMap.put(UUID.randomUUID(), japanAutos);

    }


    public Collection<Product> getUuidProductMap() {
        Collection<Product> productCollection = new ArrayList<>();
        uuidProductMap.forEach((key, value)-> productCollection.add(value));
        return productCollection;
    }

    public Collection<Article> getUuidArticleMap() {
        Collection<Article> articleCollection = new ArrayList<>();
        uuidArticleMap.forEach((key, value)-> articleCollection.add(value));
        return articleCollection;
    }
}

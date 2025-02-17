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
        this.uuidProductMap = new HashMap<>();
        this.uuidArticleMap = new HashMap<>();
        fillTheMap();
    }

    private void fillTheMap() {
        SimpleProduct orange = new SimpleProduct("Orange", 115, UUID.randomUUID());        // создание продуктов
        DiscountedProduct apple = new DiscountedProduct("Apple", 80, 15, UUID.randomUUID());
        SimpleProduct mango = new SimpleProduct("Mango", 500, UUID.randomUUID());
        FixPriceProduct kiwi = new FixPriceProduct("Kiwi", UUID.randomUUID());
        SimpleProduct watermelon = new SimpleProduct("Watermelon", 50, UUID.randomUUID());
        SimpleProduct banana = new SimpleProduct("Banana", 130, UUID.randomUUID());
        uuidProductMap.put(orange.getUuid(), orange);
        uuidProductMap.put(apple.getUuid(), apple);
        uuidProductMap.put(mango.getUuid(), mango);
        uuidProductMap.put(kiwi.getUuid(), kiwi);
        uuidProductMap.put(watermelon.getUuid(), watermelon);
        uuidProductMap.put(banana.getUuid(), banana);
        Article koreaAutos = new Article("Korea Autos", "KIA, HYUNDAI, GENESIS, SSANGYONG", UUID.randomUUID());
        Article germanAutos = new Article("Germany Autos", "VW, AUDI, SKODA, BMW, MERCEDES", UUID.randomUUID());
        Article japanAutos = new Article("Japan Autos", "Toyota, Mazda, Lexus", UUID.randomUUID());
        uuidArticleMap.put(koreaAutos.getUuid(), koreaAutos);
        uuidArticleMap.put(germanAutos.getUuid(), germanAutos);
        uuidArticleMap.put(japanAutos.getUuid(), japanAutos);
        System.out.println("uuidProductMap при инициализации = " + uuidProductMap);

    }

    public Optional<Product> getProductById(UUID id) {
        Optional<Product> product = Optional.ofNullable(uuidProductMap.get(id));
        return product;
    }

    public Collection<Product> getUuidProductMap() {
        Collection<Product> productCollection = uuidProductMap.values();
        Collection<UUID> uuids = uuidProductMap.keySet();
        return productCollection;
    }


    public Collection<Article> getUuidArticleMap() {
        Collection<Article> articleCollection = new ArrayList<>();
        uuidArticleMap.forEach((key, value) -> articleCollection.add(value));
        return articleCollection;
    }
}

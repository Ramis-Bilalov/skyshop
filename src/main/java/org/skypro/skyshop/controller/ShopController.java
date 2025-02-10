package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopController {

    @Autowired
    StorageService storageService;
    @Autowired
    SearchService searchService;

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getUuidProductMap();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getUuidArticleMap();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {
        return searchService.getSearchResults(pattern);
    }
}

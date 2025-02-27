package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private SearchService searchService;

    @InjectMocks
    private StorageService storageService;

    @Test
    public void givenNonExistingUUID_whenGetProductById_thenNonReturnProduct() {      // 1. Поиск в случае отсутствия объектов в StorageService
        UUID uuid = UUID.randomUUID();

        Optional<Product> productById = storageService.getProductById(uuid);

        Assertions.assertFalse(productById.isPresent());
    }

    @Test
    public void givenNewUUID_whenGetProductById_thenNonReturnProductCauseByNonExisting() {      // 2. Поиск в случае, если объекты в StorageService есть, но нет подходящего.
        ArrayList<UUID> collect = storageService.getUuidProductMap().stream().map(Searchable::getUuid).collect(Collectors.toCollection(ArrayList::new));

        SimpleProduct cucumber = new SimpleProduct("Cucumber", 125, UUID.randomUUID());

        Optional<Product> productById = storageService.getProductById(cucumber.getUuid());

        Assertions.assertFalse(productById.isPresent());
    }

    @Test
    public void givenUUID_whenGetProductById_thenReturnProduct() {          // 3. Поиск, когда есть подходящий объект в StorageService
        ArrayList<UUID> collect = storageService.getUuidProductMap().stream().map(Searchable::getUuid).collect(Collectors.toCollection(ArrayList::new));

        UUID uuid = collect.get(0);

        Optional<Product> productById = storageService.getProductById(uuid);

        Assertions.assertTrue(productById.isPresent());
    }

}
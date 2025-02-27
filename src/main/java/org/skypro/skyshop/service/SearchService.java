package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<Searchable> getSearchableCollection() {
        Collection<Searchable> searchables = new ArrayList<>();
        searchables.addAll(storageService.getUuidProductMap());
        searchables.addAll(storageService.getUuidArticleMap());
        return searchables;
    }

    public Collection<SearchResult> getSearchResults(String searchTerm) {
        Collection<SearchResult> searchResultCollection = new ArrayList<>();
        List<Searchable> collect = getSearchableCollection().stream().filter(searchable -> searchable.searchTerm().contains(searchTerm)).toList();
        collect.forEach(searchable -> searchResultCollection.add(SearchResult.fromSearchable(searchable)));
        return searchResultCollection;
    }
}

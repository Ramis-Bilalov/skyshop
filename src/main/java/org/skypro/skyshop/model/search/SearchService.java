package org.skypro.skyshop.model.search;

import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    private final StorageService storageService;


    public Collection<Searchable> getSearchableCollection() {
        Collection<Searchable> searchables = new ArrayList<>();
        searchables.addAll(storageService.getUuidProductMap());
        searchables.addAll(storageService.getUuidArticleMap());
        return searchables;
    }

    public Collection<SearchResult> getSearchResults(String searchTerm) {
        System.out.println("searchTerm = " + searchTerm);
        Collection<SearchResult> searchResultCollection = new ArrayList<>();
        List<Searchable> collect = getSearchableCollection().stream().filter(searchable -> searchable.searchTerm().contains(searchTerm)).toList();
        collect.forEach(searchable -> searchResultCollection.add(SearchResult.fromSearchable(searchable)));
        return searchResultCollection;
    }
}

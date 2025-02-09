package org.skypro.skyshop.model.search;

import org.skypro.skyshop.model.service.StorageService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService() {
        storageService = new StorageService();
    }

    public Collection<Searchable> getSearchableCollection() {
        Collection<Searchable> searchables = new ArrayList<>();
        searchables.addAll(storageService.getUuidProductMap());
        searchables.addAll(storageService.getUuidArticleMap());
        return searchables;
    }

    public Collection<SearchResult> getSearchResults(String searchTerm) {
        System.out.println("searchTerm = " + searchTerm);
        Collection<SearchResult> searchResultCollection = new ArrayList<>();
        List<Searchable> collect = getSearchableCollection().stream().filter(searchable -> searchable.searchTerm().contains(searchTerm)).collect(Collectors.toList());
        collect.forEach(searchable -> searchResultCollection.add(SearchResult.fromSearchable(searchable)));
        return searchResultCollection;
    }
}

package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private String articleName;
    private String articleText;
    private final UUID id;


    public Article(String articleName, String articleText, UUID id) {
        this.articleName = articleName;
        this.articleText = articleText;
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    @Override
    public String toString() {
        return articleName + "\n" + articleText;
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return articleName + "\n" + articleText;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public UUID getUuid() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return articleName.equals(article.articleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleName);
    }
}

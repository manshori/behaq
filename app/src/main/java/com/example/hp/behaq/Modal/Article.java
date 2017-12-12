package com.example.hp.behaq.Modal;

/**
 * Created by hp on 07/12/2017.
 */

public class Article {
    private int id;
    private String query;
    private String name;
    private String url;
    private String snippet;
    private int count_verified;
    private int count_hoax;
    private int sentiment;
    private int label;
    private int content_id;
    private String space_id;

    public Article(int id, String query, String name, String url, String snippet, int count_verified, int count_hoax, int sentiment, int label, int content_id, String space_id) {
        this.id = id;
        this.query = query;
        this.name = name;
        this.url = url;
        this.snippet = snippet;
        this.count_verified = count_verified;
        this.count_hoax = count_hoax;
        this.sentiment = sentiment;
        this.label = label;
        this.content_id = content_id;
        this.space_id = space_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public int getCount_verified() {
        return count_verified;
    }

    public void setCount_verified(int count_verified) {
        this.count_verified = count_verified;
    }

    public int getCount_hoax() {
        return count_hoax;
    }

    public void setCount_hoax(int count_hoax) {
        this.count_hoax = count_hoax;
    }

    public int getSentiment() {
        return sentiment;
    }

    public void setSentiment(int sentiment) {
        this.sentiment = sentiment;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getSpace_id() {
        return space_id;
    }

    public void setSpace_id(String space_id) {
        this.space_id = space_id;
    }
}

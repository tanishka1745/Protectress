package com.example.protectress.Retrofit;

import java.util.ArrayList;

public class ModalClass {

    private String status;
    private String totalResults;
    private ArrayList<NewsModal> articles;

    public ModalClass(String status, String totalResults, ArrayList<NewsModal> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<NewsModal> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsModal> articles) {
        this.articles = articles;
    }
}

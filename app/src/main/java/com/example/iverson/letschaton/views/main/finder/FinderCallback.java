package com.example.iverson.letschaton.views.main.finder;

public interface FinderCallback {

    void error(String error);
    void success();
    void notFound();

}

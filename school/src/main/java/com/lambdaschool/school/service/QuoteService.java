package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Quote;
import com.lambdaschool.school.view.CountQuotes;

import java.util.ArrayList;
import java.util.List;

public interface QuoteService
{
    List<Quote> findAll();

    Quote findQuoteById(long id);

    List<Quote> findByUserName(String username);

    void delete(long id);

    Quote save(Quote quote);

    Quote update(Quote quote, long id);

    ArrayList<CountQuotes> getCountQuotes();
}

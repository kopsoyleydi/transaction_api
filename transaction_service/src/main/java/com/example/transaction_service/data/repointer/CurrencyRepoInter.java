package com.example.transaction_service.data.repointer;

import com.example.transaction_service.data.model.Currency;

import java.util.List;

public interface CurrencyRepoInter {

    Currency insert(Currency currency);

    Currency update(Currency currency);

    Currency getByCurrencyCode(String code);

    List<Currency> getAllCurrency();

    void insertAllCurrencies(List<Currency> currencyList);

    void deleteAll();
}

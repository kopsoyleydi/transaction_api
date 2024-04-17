package com.example.transaction_service.data;

import com.example.transaction_service.data.model.Currency;
import com.example.transaction_service.data.repointer.CurrencyRepoInter;
import com.example.transaction_service.data.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyRepoImpl implements CurrencyRepoInter {

    private final CurrencyRepository currencyRepository;

    @Override
    public Currency insert(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency update(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency getByCurrencyCode(String code) {
        return currencyRepository.findByCurrencyCode(code);
    }

    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }
}

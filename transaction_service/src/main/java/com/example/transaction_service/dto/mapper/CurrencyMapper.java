package com.example.transaction_service.dto.mapper;

import com.example.transaction_service.dto.CurrencyDto;
import com.example.transaction_service.data.model.Currency;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto toDto(Currency comment);

    Currency toModel(CurrencyDto comment);

    List<CurrencyDto> toDtoList(List<Currency> list);

    List<Currency> toModelList(List<CurrencyDto> list);
}

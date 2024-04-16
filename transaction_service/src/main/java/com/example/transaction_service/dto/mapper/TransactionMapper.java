package com.example.transaction_service.dto.mapper;

import com.example.transaction_service.dto.TransactionDto;
import com.example.transaction_service.data.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDto(Transaction comment);

    Transaction toModel(TransactionDto comment);

    List<TransactionDto> toDtoList(List<Transaction> list);

    List<Transaction> toModelList(List<TransactionDto> list);
}

package com.example.transaction_service.exceptions;

public class TransactionNotFoundException extends NullPointerException{

    public TransactionNotFoundException(){
        super("TransactionNotFoundException");
    }
}

package com.searchmetrics.currencyexchange.exception;

public class CurrencyRateUnavailableException extends RuntimeException {

    public CurrencyRateUnavailableException(String message) {
        super(message);
    }
}

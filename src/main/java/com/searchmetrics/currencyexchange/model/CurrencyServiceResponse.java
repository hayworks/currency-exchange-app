package com.searchmetrics.currencyexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
public class CurrencyServiceResponse {

    @JsonProperty("EUR_USD")
    private ValuePart valPart;

    @JsonIgnore
    public BigDecimal getRate() {

        if(valPart == null)
            return null;

        return valPart.getVal();
    }

    public CurrencyServiceResponse(BigDecimal rate) {
        valPart = new ValuePart();
        valPart.val = rate;
    }

    @Getter
    public static class ValuePart {
        private BigDecimal val;
    }
}

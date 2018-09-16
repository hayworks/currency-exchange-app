package com.searchmetrics.currencyexchange.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateHistoryRequest {

    private Date startDate;
    private Date endDate;

    @JsonIgnore
    public boolean isValid() {
        return startDate.compareTo(endDate) == -1;
    }
}

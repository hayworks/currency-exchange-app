package com.searchmetrics.currencyexchange.repository;

import com.searchmetrics.currencyexchange.model.CurrencyRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, Long> {

    @Query("select c from CurrencyRate c where c.createDate between :startDate and :endDate")
    List<CurrencyRate> findBetweenStartAndEndDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}

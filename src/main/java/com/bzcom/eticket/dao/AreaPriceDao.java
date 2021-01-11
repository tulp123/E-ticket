package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.AreaPrice;

import java.util.List;

public interface AreaPriceDao {

    List<AreaPrice> findByEventId(int eventId);

    AreaPrice save(AreaPrice areaPrice);

    List<AreaPrice> saveAll(List<AreaPrice> areaPrices);
}

package com.bzcom.eticket.service;

import com.bzcom.eticket.model.AreaPrice;

import java.util.List;

public interface AreaPriceService {
    List<AreaPrice> findByEventId(int eventId);

    AreaPrice save(AreaPrice areaPrice);

    List<AreaPrice> saveAll(List<AreaPrice> areaPrices);
}

package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.AreaPriceDao;
import com.bzcom.eticket.model.AreaPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaPriceServiceImpl implements AreaPriceService {

    @Autowired
    private AreaPriceDao areaPriceDao;

    @Override
    public List<AreaPrice> findByEventId(int eventId) {
        return areaPriceDao.findByEventId(eventId);
    }

    @Override
    public AreaPrice save(AreaPrice areaPrice) {
        return areaPriceDao.save(areaPrice);
    }

    @Override
    public List<AreaPrice> saveAll(List<AreaPrice> areaPrices) {
        return areaPriceDao.saveAll(areaPrices);
    }
}

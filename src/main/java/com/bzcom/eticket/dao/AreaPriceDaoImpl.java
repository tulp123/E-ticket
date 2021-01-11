package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.AreaPrice;
import com.bzcom.eticket.model.Product;
import com.bzcom.eticket.repository.AreaPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AreaPriceDaoImpl implements AreaPriceDao {

    @Autowired
    private AreaPriceRepository areaPriceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AreaPrice> findByEventId(int eventId) {
        TypedQuery<AreaPrice> q = entityManager.createQuery(
                "select ap from AreaPrice ap where ap.id.eventId = :eventId", AreaPrice.class);
        q.setParameter("eventId", eventId);

        return q.getResultList();
    }

    @Override
    public AreaPrice save(AreaPrice areaPrice) {
        return areaPriceRepository.save(areaPrice);

}
    @Override
    public List<AreaPrice> saveAll(List<AreaPrice> areaPrices) {
        return areaPriceRepository.saveAll(areaPrices);
    }
}

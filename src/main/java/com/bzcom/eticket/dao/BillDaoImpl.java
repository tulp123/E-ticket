package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Bill;
import com.bzcom.eticket.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDaoImpl implements BillDao {

    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill findById(int id) {
        return billRepository.getOne(id);
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }
}

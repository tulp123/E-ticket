package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.BillDao;
import com.bzcom.eticket.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;

    @Override
    public Bill findById(int id) {
        return billDao.findById(id);
    }

    @Override
    public List<Bill> findAll() {
        return billDao.findAll();
    }

    @Override
    public Bill save(Bill bill) {
        return billDao.save(bill);
    }
}

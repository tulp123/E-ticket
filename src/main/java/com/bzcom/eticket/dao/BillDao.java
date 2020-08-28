package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Bill;

import java.util.List;

public interface BillDao {

    Bill findById(int id);

    List<Bill> findAll();

    Bill save(Bill bill);

}

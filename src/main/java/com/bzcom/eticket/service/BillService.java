package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Bill;

import java.util.List;

public interface BillService {

    Bill findById(int id);

    List<Bill> findAll();

    Bill save(Bill bill);

}

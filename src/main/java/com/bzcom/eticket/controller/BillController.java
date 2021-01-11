package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Bill;
import com.bzcom.eticket.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/bills"})
@CrossOrigin("*")
public class BillController {

    @Autowired
    private BillService billService;

    // Get All Bill
    @GetMapping
    public List<Bill> getAllBills(){
        return billService.findAll();
    }

    // Get a Single Bill
    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable(value = "id") Integer billId) {
        return billService.findById(billId);
    }

    // Create a new Bill
    @PostMapping
    public Bill saveBill(@RequestBody Bill bill){
        return billService.save(bill);
    }

    // Update a Bill
    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable(value = "id") Integer billId, @RequestBody Bill billDetail) {
        billDetail.setId(billId);
        Bill billUpdate = billService.save(billDetail);
        return billUpdate;
    }
}

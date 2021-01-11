package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    List<Product> findByTeamId(int teamId);

    Product findById(int id);

    Product save(Product product);
}

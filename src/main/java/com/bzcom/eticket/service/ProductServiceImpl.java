package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.ProductDao;
import com.bzcom.eticket.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> findByTeamId(int teamId) {
        return productDao.findByTeamId(teamId);
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }
}

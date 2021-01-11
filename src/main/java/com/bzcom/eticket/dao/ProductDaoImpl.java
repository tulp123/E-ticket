package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.Product;
import com.bzcom.eticket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByTeamId(int teamId) {
        TypedQuery<Product> q = entityManager.createQuery(
                "select p from Product p where p.team.id = :teamId", Product.class);
        q.setParameter("teamId", teamId);

        return q.getResultList();    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.getOne(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}

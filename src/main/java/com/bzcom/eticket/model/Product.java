package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private long price;

    @Column(name = "img")
    private String img;

    @Column(name = "discount")
    private int discount;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JsonBackReference(value = "product-team")
    @JoinColumn(name = "team_id")
    private Team team;

}

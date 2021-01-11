package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonBackReference(value = "bill-user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference(value = "ticket-bill")
    @ToString.Exclude
    @OneToMany(mappedBy = "bill")
    private List<Ticket> tickets;

    @Column(name = "created_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "total_amount")
    private long totalAmount;

    @Column(name = "bill_code")
    private String billCode;

    @Column(name = "tax_code")
    private String taxCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bill_product",
            joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Product> products;

    @PrePersist
    public void setBookingDate() {
        this.createdDate = LocalDateTime.now();
    }
}
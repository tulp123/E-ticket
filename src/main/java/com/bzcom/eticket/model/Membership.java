package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "membership")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "min_amount")
    private long minAmount;

    @Column(name = "ticket_limit")
    private int ticketLimit;

    @Column(name = "discount")
    private float discount;

    @Column(name = "free_ticket_limit")
    private int freeTicketLimit;

    @JsonManagedReference(value = "membership-user")
    @ToString.Exclude
    @OneToMany(mappedBy = "membership")
    private List<Member> members;

}

package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonManagedReference(value = "bill-user")
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_num")
    private String phoneNumber;

    @Column(name = "id_card")
    private String idCard;

    @JsonBackReference(value = "organization-user")
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "created_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonManagedReference(value = "member-user")
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Member> members;

    @JsonManagedReference(value = "ticket-user")
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @PrePersist
    public void setBookingDate() {
        this.createdDate = LocalDateTime.now();
    }
}

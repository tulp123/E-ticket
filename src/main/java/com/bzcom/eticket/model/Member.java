package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonBackReference(value = "role-member")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonBackReference(value = "membership-user")
    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "img")
    private String img;

    @Column(name = "point")
    private int point;

    @Column(name = "total_paid")
    private Long totalPaid;

    @Column(name = "status")
    private int status;

    @Column(name = "member_imei")
    private Long imeiMember;

    @Column(name = "created_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonBackReference(value = "member-user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

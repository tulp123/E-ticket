package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    @JsonBackReference(value = "role-user")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

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
    private long totalPaid;

    @Column(name = "user_status")
    private int userStatus;

    @Column(name = "created_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonBackReference(value = "member-user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private String fullName;

    @Transient
    private String phoneNumber;

    @Transient
    private String email;

    @Transient
    private String address;

    @Transient
    private String roleName;

    public String getFullName() {
        if (getUser() == null){
            return fullName;
        } else {
            return getUser().getFullName();
        }
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        if (getUser() == null){
            return phoneNumber;
        } else {
            return getUser().getPhoneNumber();
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        if (getUser() == null){
            return email;
        } else {
            return getUser().getEmail();
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        if (getUser() == null){
            return address;
        } else {
            return getUser().getAddress();
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleName(){
        if (getRole() == null){
            return roleName;
        } else {
            return getRole().getName();
        }
    }
    public void setRoleName(){
        this.roleName = roleName;
    }

    @PrePersist
    public void setBookingDate() {
        this.createdDate = LocalDateTime.now();
    }

}

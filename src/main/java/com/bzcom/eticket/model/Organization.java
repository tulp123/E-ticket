package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonManagedReference(value = "organization-user")
    @ToString.Exclude
    @OneToMany(mappedBy = "organization")
    private List<User> users;

    @JsonManagedReference(value = "organization-invite")
    @ToString.Exclude
    @OneToMany(mappedBy = "organization")
    private Collection<InviteTicketQuantity> inviteTicketQuantities = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getUsers(), that.getUsers()) &&
                Objects.equals(getInviteTicketQuantities(), that.getInviteTicketQuantities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPhoneNumber(), getUsers(), getInviteTicketQuantities());
    }
}

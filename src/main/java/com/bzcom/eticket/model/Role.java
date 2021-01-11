package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

//    @JsonManagedReference(value = "role-member")
//    @ToString.Exclude
//    @OneToMany(mappedBy = "role")
//    private List<Member> members;
    public Role(ERole name){
        this.name = name;
    }
}

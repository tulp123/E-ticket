package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "gate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonBackReference(value = "gate-area")
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "gate_name")
    private String name;

    @Column(name = "color")
    private String color;

}

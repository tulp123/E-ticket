package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "team_info")
    private String teamInfo;

    @Column(name = "logo")
    private String logo;

    @JsonManagedReference(value = "game-teamA")
    @ToString.Exclude
    @OneToMany(mappedBy = "teamA")
    private List<Game> listGameA;

    @JsonManagedReference(value = "game-teamB")
    @ToString.Exclude
    @OneToMany(mappedBy = "teamB")
    private List<Game> listGameB;

}

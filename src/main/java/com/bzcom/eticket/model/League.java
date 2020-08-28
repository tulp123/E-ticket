package com.bzcom.eticket.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "league")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "league_name")
    private String leagueName;

    @Column(name = "league_info")
    private String leagueInfo;

    @ManyToMany(mappedBy = "leagues", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Location> locations;

}

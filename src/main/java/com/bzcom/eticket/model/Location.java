package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "location_name")
    private String name;

    @Column(name = "location_info")
    private String info;


    @Column(name = "address")
    private String address;


    @Column(name = "latitude")
    private String latitude;


    @Column(name = "longitude")
    private String longitude;


    @Column(name = "img")
    private String img;


    @Column(name = "map")
    private String map;

    @JsonManagedReference(value = "area-location")
    @ToString.Exclude
    @OneToMany(mappedBy = "location")
    private List<Area> areas;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "location_league",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id")
    )
    private List<League> leagues;

    @JsonManagedReference(value = "location-event")
    @ToString.Exclude
    @OneToMany(mappedBy = "location")
    private List<Event> events;
}

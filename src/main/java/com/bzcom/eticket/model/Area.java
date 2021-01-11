package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "area")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JsonBackReference(value = "area-location")
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "area_name")
    private String name;

    @Column(name = "total_seat")
    private int totalSeat;

    @JsonManagedReference(value = "seat-area")
    @ToString.Exclude
    @OneToMany(mappedBy = "area")
    private List<Seat> seats;

    @JsonManagedReference(value = "gate-area")
    @ToString.Exclude
    @OneToMany(mappedBy = "area")
    private List<Gate> gates;

    @JsonManagedReference(value = "pk_area_price")
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
    private Collection<AreaPrice> areaPrices = new ArrayList<>();

    public Area(int id) {
        super();
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Area)) return false;
        Area area = (Area) o;
        return getId() == area.getId() &&
                getTotalSeat() == area.getTotalSeat() &&
                Objects.equals(getLocation(), area.getLocation()) &&
                Objects.equals(getName(), area.getName()) &&
                Objects.equals(getSeats(), area.getSeats()) &&
                Objects.equals(getGates(), area.getGates()) &&
                Objects.equals(getAreaPrices(), area.getAreaPrices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocation(), getName(), getTotalSeat(), getSeats(), getGates(), getAreaPrices());
    }


}

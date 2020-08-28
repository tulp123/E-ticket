package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "area_price")
    private long price;

    @JsonManagedReference(value = "seat-area")
    @ToString.Exclude
    @OneToMany(mappedBy = "area")
    private List<Seat> seats;

    @JsonManagedReference(value = "gate-area")
    @ToString.Exclude
    @OneToMany(mappedBy = "area")
    private List<Gate> gates;

    @Transient
    private int locationId;

    @Transient
    private String locationName;

    public int getLocationId() {
        if (getLocation() == null) {
            return locationId;
        } else {
            return location.getId();
        }
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        if (getLocation() == null) {
            return locationName;
        } else {
            return location.getName();
        }
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}

package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonManagedReference(value = "ticket-event")
    @ToString.Exclude
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Game game;

    @Column(name = "poster")
    private String poster;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(name = "total_ticket")
    private int totalTicket;

    @Column(name = "status")
    private boolean eventStatus;

    @ManyToOne
    @JsonBackReference(value = "location-event")
    @JoinColumn(name = "location_id")
    private Location location;

    @Transient
    private String locationName;

    @Transient
    private int locationId;

    public String getLocationName() {
        if (getLocation() == null) {
            return locationName;
        } else {
            return getLocation().getName();
        }
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getLocationId() {
       if(getLocation()==null){
           return locationId;
       }else{
           return getLocation().getId();
       }
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}

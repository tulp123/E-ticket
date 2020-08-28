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
@Table(name = "seat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonManagedReference(value = "ticket-seat")
    @ToString.Exclude
    @OneToMany(mappedBy = "seat")
    private List<Ticket> tickets;

    @ManyToOne
    @JsonBackReference(value = "seat-area")
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "seat_row")
    private String seatRow;

    @Column(name = "seat_column")
    private String seatColumn;

    @Column(name = "seat_price")
    private long price;

    @Column(name = "status")
    private boolean seatStatus;
}

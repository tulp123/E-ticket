package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

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

    @JsonManagedReference(value = "event-price")
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private Collection<AreaPrice> areaPrices = new ArrayList<>();

    @JsonManagedReference(value = "event-invite")
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private Collection<InviteTicketQuantity> inviteTicketQuantities = new ArrayList<>();

    public Event(int id) {
        super();
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return getId() == event.getId() &&
                getTotalTicket() == event.getTotalTicket() &&
                isEventStatus() == event.isEventStatus() &&
                Objects.equals(getTickets(), event.getTickets()) &&
                Objects.equals(getGame(), event.getGame()) &&
                Objects.equals(getPoster(), event.getPoster()) &&
                Objects.equals(getStartDate(), event.getStartDate()) &&
                Objects.equals(getEndDate(), event.getEndDate()) &&
                Objects.equals(getLocation(), event.getLocation()) &&
                Objects.equals(getAreaPrices(), event.getAreaPrices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTickets(), getGame(), getPoster(), getStartDate(), getEndDate(), getTotalTicket(), isEventStatus(), getLocation(), getAreaPrices());
    }
}

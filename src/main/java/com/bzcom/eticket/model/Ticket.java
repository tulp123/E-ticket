package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonBackReference(value = "ticket-event")
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @JsonBackReference(value = "ticket-bill")
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @JsonBackReference(value = "ticket-seat")
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "ticket_serial")
    private String ticketSerial;

    @Column(name = "booking_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime bookingDate;

    @Column(name = "issue_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date issueDate;

    @Column(name = "payment_method")
    private int paymentMethod;

    @Column(name = "booking_code")
    private String bookingCode;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "ticket_status")
    private boolean ticketStatus;

    @Transient
    private int eventId;

    @Transient
    private int totalTicket;

    @Transient
    private int userId;

    public int getEventId() {
        if (getEvent() == null){
            return eventId;
        } else {
            return getEvent().getId();
        }
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @PrePersist
    public void setBookingDate() {
        this.bookingDate = LocalDateTime.now();
    }
}

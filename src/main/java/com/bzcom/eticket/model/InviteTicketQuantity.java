package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "invite_ticket_quantity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteTicketQuantity {

    @EmbeddedId
    private InviteTicketQuantityKey id;

    @JsonBackReference(value = "event-invite")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    private Event event;

    @JsonBackReference(value = "organization-invite")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("organizationId")
    private Organization organization;

    @Column(name = "quantity")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InviteTicketQuantity)) return false;
        InviteTicketQuantity that = (InviteTicketQuantity) o;
        return getQuantity() == that.getQuantity() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getEvent(), that.getEvent()) &&
                Objects.equals(getOrganization(), that.getOrganization());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEvent(), getOrganization(), getQuantity());
    }
}

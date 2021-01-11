package com.bzcom.eticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteTicketQuantityKey implements Serializable {

    @Column(name = "event_id")
    private int eventId;

    @Column(name = "organization_id")
    private int organizationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InviteTicketQuantityKey)) return false;
        InviteTicketQuantityKey quantityKey = (InviteTicketQuantityKey) o;
        return Objects.equals(getOrganizationId(), quantityKey.getOrganizationId()) &&
                Objects.equals(getEventId(), quantityKey.getEventId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrganizationId(), getEventId());
    }
}

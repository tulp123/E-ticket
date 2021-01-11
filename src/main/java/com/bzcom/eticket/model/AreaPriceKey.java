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
public class AreaPriceKey implements Serializable {

    @Column(name = "event_id")
    private int eventId;

    @Column(name = "area_id")
    private int areaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaPriceKey)) return false;
        AreaPriceKey areaPriceKey = (AreaPriceKey) o;
        return Objects.equals(getEventId(), areaPriceKey.getEventId()) &&
                Objects.equals(getAreaId(), areaPriceKey.getAreaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventId(), getAreaId());
    }
}

package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@ToString(exclude = "event, area")
@EqualsAndHashCode(exclude = "event, area")
@Entity
@Table(name = "event_area_price")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaPrice {

    @EmbeddedId
    private AreaPriceKey id;

    @JsonBackReference(value = "event-price")
    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @JsonBackReference(value = "pk_area_price")
    @ManyToOne
    @MapsId("areaId")
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "area_price")
    private Long price;

    @Column(name = "area_total_ticket")
    private int areaTotalTicket;

    @Transient
    private int remainTicket;

    @Transient
    private String areaName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaPrice)) return false;
        AreaPrice areaPrice = (AreaPrice) o;
        return Objects.equals(getAreaTotalTicket(), areaPrice.getAreaTotalTicket()) &&
                Objects.equals(getId(), areaPrice.getId()) &&
                Objects.equals(getEvent(), areaPrice.getEvent()) &&
                Objects.equals(getArea(), areaPrice.getArea()) &&
                Objects.equals(getPrice(), areaPrice.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEvent(), getArea(), getPrice(), getAreaTotalTicket());
    }

    public String getAreaName() {
        if (this.area != null) {
            areaName = this.area.getName();
        }
        return areaName;
    }
}

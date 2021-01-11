package com.bzcom.eticket.dto;

import com.bzcom.eticket.model.AreaPrice;
import com.bzcom.eticket.model.Event;
import com.bzcom.eticket.model.Game;
import com.bzcom.eticket.model.InviteTicketQuantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private int id;
    private Game game;
    private String poster;
    private Date startDate;
    private Date endDate;
    private int totalTicket;
    private Collection<AreaPrice> areaPrices;

    public EventDTO(Event event) {
        this.id = event.getId();
        this.game = event.getGame();
        this.poster = event.getPoster();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.totalTicket = event.getTotalTicket();
        this.areaPrices = event.getAreaPrices();
    }
}

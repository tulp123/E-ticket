package com.bzcom.eticket.dto;

import com.bzcom.eticket.model.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Data
public class TicketDTO {
    private Ticket ticket;
    private int seatId;
    private int areaId;
    private String areaName;
    private Long areaPrice;
    private List<Gate> gates;
    private MatchDTO matchDTO;

    public TicketDTO(Ticket ticket) {
        this.ticket = ticket;
        this.seatId = ticket.getSeat().getId();
        this.areaId = ticket.getSeat().getArea().getId();
        this.areaName = ticket.getSeat().getArea().getName();
        Collection<AreaPrice> areaPrices = ticket.getEvent().getAreaPrices();
        for (AreaPrice areaPrice : areaPrices) {
            if (areaPrice.getId().getAreaId() == areaId) {
                this.setAreaPrice(areaPrice.getPrice());
                break;
            }
        }
        this.gates = ticket.getSeat().getArea().getGates();
        this.matchDTO = new MatchDTO(ticket.getEvent());
    }
}

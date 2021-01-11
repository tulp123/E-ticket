package com.bzcom.eticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private int eventId;

    private String teamB;

    // id = 1
    private int totalTicketZoneA;

    // id = 2
    private int totalTicketZoneB;

    // id = 3
    private int totalTicketZoneC;

    // id = 4
    private int totalTicketZoneD;
}

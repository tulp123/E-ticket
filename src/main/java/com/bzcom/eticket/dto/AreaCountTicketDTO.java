package com.bzcom.eticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaCountTicketDTO {
    private int areaId;
    private long totalTicket;
}

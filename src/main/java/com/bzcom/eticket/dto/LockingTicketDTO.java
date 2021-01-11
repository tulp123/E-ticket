package com.bzcom.eticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockingTicketDTO {
    private int eventID;
    private List<AreaBookingDTO> areaBookingDTOList;
    private int totalTicket;
}

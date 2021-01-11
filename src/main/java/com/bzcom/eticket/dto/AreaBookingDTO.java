package com.bzcom.eticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaBookingDTO {
    private int areaId;
    private int totalSeat;
}

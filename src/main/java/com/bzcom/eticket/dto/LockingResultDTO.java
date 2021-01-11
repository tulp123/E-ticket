package com.bzcom.eticket.dto;

import com.bzcom.eticket.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockingResultDTO {
    private List<Ticket> tickets;
    private long remainAreaA;
    private long remainAreaB;
    private long remainAreaC;
    private long remainAreaD;
}

package com.bzcom.eticket.dto;

import com.bzcom.eticket.model.Ticket;
import com.bzcom.eticket.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingTicketDTO {
    private List<Ticket> tickets;
    private User user;
}

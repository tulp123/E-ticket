package com.bzcom.eticket.service;

import com.bzcom.eticket.model.ConfirmationToken;
import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.model.Ticket;

import java.util.List;
import java.util.Map;

public interface EmailService {
    void sendTicket(List<Ticket> tickets, Map<String, Object> model);

    void sendBookingCode(List<Ticket> tickets);

    void sendEmailConfirmCreateMember(Member member, ConfirmationToken confirmationToken);
}

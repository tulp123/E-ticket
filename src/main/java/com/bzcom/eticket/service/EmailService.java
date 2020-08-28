package com.bzcom.eticket.service;

import com.bzcom.eticket.model.ConfirmationToken;
import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.model.Ticket;

import java.util.List;
import java.util.Map;

public interface EmailService {
    public void sendTicket(List<Ticket> tickets, Map<String, Object> model);

    public void sendEmailConfirmCreateMember(Member member, ConfirmationToken confirmationToken);
}

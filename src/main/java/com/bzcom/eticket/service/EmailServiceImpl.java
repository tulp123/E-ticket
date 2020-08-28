package com.bzcom.eticket.service;

import com.bzcom.eticket.model.ConfirmationToken;
import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.model.Ticket;
import com.bzcom.eticket.model.User;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    @Override
    public void sendTicket(List<Ticket> tickets, Map<String, Object> model) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            User user = userService.findById(tickets.get(0).getUserId());

            Template template = configuration.getTemplate("ticket.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

//            helper.setTo("bzcommailserver@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Ticket information");
            helper.setText(html, true);
            javaMailSender.send(message);

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailConfirmCreateMember(Member member, ConfirmationToken confirmationToken) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(member.getEmail());
            helper.setSubject("CONFIRM CREATE ACCOUNT " + member.getUsername());
            helper.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/members/confirm-account?token="+ confirmationToken.getConfirmationToken(), true);
            javaMailSender.send(message);

        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}

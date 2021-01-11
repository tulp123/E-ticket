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
            User user = userService.findById(tickets.get(0).getUser().getId());

            Template template = configuration.getTemplate("ticket.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setFrom("bzcommailserver@gmail.com");
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
    public void sendBookingCode(List<Ticket> tickets) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        StringBuilder html = new StringBuilder();

        html.append("Kính gửi quý Khách hàng<br>");
        html.append("<br>Xin trân trọng cảm ơn quý khách đã lựa chọn sử dụng dịch vụ đặt vé của Công ty TNHH BZCOM<br>");
        html.append("Quý khách có thể xem vé đã đặt tại trang web: ");
        html.append("http://localhost:3000/searchTicket?bookingCode=" + tickets.get(0).getBookingCode() + "&email=" + tickets.get(0).getUser().getEmail() + "&phoneNum=" + tickets.get(0).getUser().getPhoneNumber());
//        html.append("http://ohticket.bzcom.vn/searchTicket?bookingCode=" + tickets.get(0).getBookingCode() + "&email=" + tickets.get(0).getUser().getEmail() + "&phoneNum=" + tickets.get(0).getUser().getPhoneNumber());
        html.append("<br>Chú ý:<br>" +
                "- Để đảm bảo quyền lợi của mình, quý khách vui lòng không chia sẻ thông tin vé.<br>" +
                "Đây là email gửi tự động. Xin vui lòng không trả lời email này.<br>" +
                "Quý khách có thể liên hệ với trung tâm hỗ trợ khách hàng 0243 200 4887 để được trợ giúp.<br>" +
                "Xin Trân trọng cảm ơn!<br>");
        html.append("<br>OhTicket - CÔNG TY TNHH BZCOM");


        try {
            helper.setFrom("bzcommailserver@gmail.com");
            helper.setTo(tickets.get(0).getUser().getEmail());
            helper.setSubject("[OHTICKET] THÔNG BÁO ĐẶT VÉ THÀNH CÔNG");
            helper.setText(String.valueOf(html), true);
            javaMailSender.send(message);

        } catch (MessagingException me) {
            me.printStackTrace();
        }

    }

    @Override
    public void sendEmailConfirmCreateMember(Member member, ConfirmationToken confirmationToken) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
//            helper.setTo(member.getEmail());
            helper.setSubject("CONFIRM CREATE ACCOUNT " + member.getUsername());
            helper.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/members/confirm-account?token="+ confirmationToken.getConfirmationToken(), true);
            javaMailSender.send(message);

        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}

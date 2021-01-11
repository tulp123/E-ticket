package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.HistoryScan;
import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.model.Seat;
import com.bzcom.eticket.model.Ticket;
import com.bzcom.eticket.repository.TicketRepository;
import com.bzcom.eticket.service.HistoryScanService;
import com.bzcom.eticket.service.MemberService;
import com.bzcom.eticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/scan")
@CrossOrigin("*")
public class HistoryScanController {
    @Autowired
    HistoryScanService historyScanService;

    @Autowired
    TicketService ticketService;

    @Autowired
    MemberService memberService;

    @GetMapping
    public List<HistoryScan> getAllHistoryScan() {
        return historyScanService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> creatHistoryScan(@RequestHeader(value = "imei") Long imei,
                                              @RequestHeader(value = "status") boolean status,
                                              @RequestHeader(value = "event") String event) throws ParseException {
        Member member = memberService.findMemberByImeiMember(imei);
        Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(event);
        HistoryScan historyScan = historyScanService.findHistoryScanByDateScanAndIdMember(newDate, member.getId());

        if (member == null) {
            return ResponseEntity.badRequest().body("Lỗi : Thiết bị bạn dùng không đúng ...");
        } else {
            if (historyScan == null) {
                HistoryScan history = new HistoryScan();
                if (status) {
                    history.setTicketOrror(0);
                    history.setTicketSuccess(1);
                }else{
                    history.setTicketOrror(1);
                    history.setTicketSuccess(0);
                }
                history.setIdMember(member.getId());
                historyScanService.save(history);
                return ResponseEntity.ok("Quét thành công");
            } else {
                if (status) {
                    historyScan.setTicketSuccess(historyScan.getTicketSuccess() + 1);
                    historyScanService.save(historyScan);
                    return ResponseEntity.ok("Quét vé thành công!");
                }else{
                    historyScan.setTicketOrror(historyScan.getTicketOrror() + 1);
                    historyScanService.save(historyScan);
                    return ResponseEntity.ok("Quét vé thất bại :vé đã được sử dụng.");
                }
            }
        }
    }
}

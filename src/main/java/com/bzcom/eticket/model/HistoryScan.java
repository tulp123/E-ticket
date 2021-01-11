package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "history_scan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryScan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date_scan")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateScan;

    @Column(name = "ticket_orror")
    private int ticketOrror;

    @Column(name = "ticket_success")
    private int ticketSuccess;

    @Column(name = "id_member_scan")
    private int idMember;
}

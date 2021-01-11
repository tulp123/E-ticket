package com.bzcom.eticket.repository;

import com.bzcom.eticket.model.HistoryScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HistoryScanRepository extends JpaRepository<HistoryScan , Integer> {


    HistoryScan findHistoryScanByDateScanAndIdMember(Date dateScan , int idMember);

    HistoryScan findHistoryScanByDateScan(Date date);
}

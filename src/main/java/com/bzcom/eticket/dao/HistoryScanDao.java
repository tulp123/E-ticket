package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.HistoryScan;

import java.util.Date;
import java.util.List;

public interface HistoryScanDao {

    List<HistoryScan> findAll();

    HistoryScan findById(int id);

    HistoryScan save(HistoryScan historyScan);

    HistoryScan findHistoryScanByDateScanAndIdMember(Date dateScan , int idMember);

    HistoryScan findHistoryScanByDateScan(Date dateScan);

}

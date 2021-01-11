package com.bzcom.eticket.dao;

import com.bzcom.eticket.model.HistoryScan;
import com.bzcom.eticket.repository.HistoryScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class HistoryScanDaoImpl implements HistoryScanDao {

    @Autowired
    private HistoryScanRepository historyScanRepository;

    @Override
    public List<HistoryScan> findAll() {
        return historyScanRepository.findAll();
    }

    @Override
    public HistoryScan findById(int id) {
        return historyScanRepository.getOne(id);
    }

    @Override
    public HistoryScan save(HistoryScan historyScan) {
        return historyScanRepository.save(historyScan);
    }

    @Override
    public HistoryScan findHistoryScanByDateScanAndIdMember(Date dateScan , int idMember) {
        return historyScanRepository.findHistoryScanByDateScanAndIdMember(dateScan , idMember);
    }

    @Override
    public HistoryScan findHistoryScanByDateScan(Date dateScan) {
        return historyScanRepository.findHistoryScanByDateScan(dateScan);
    }

}

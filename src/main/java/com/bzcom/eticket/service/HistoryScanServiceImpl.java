package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.HistoryScanDao;
import com.bzcom.eticket.model.HistoryScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryScanServiceImpl implements HistoryScanService {

    @Autowired
    private HistoryScanDao historyScanDao;

    @Override
    public List<HistoryScan> findAll() {
        return historyScanDao.findAll();
    }

    @Override
    public HistoryScan findById(int id) {
        return historyScanDao.findById(id);
    }

    @Override
    public HistoryScan save(HistoryScan historyScan) {
        return historyScanDao.save(historyScan);
    }

    @Override
    public HistoryScan findHistoryScanByDateScanAndIdMember(Date dateScan, int idMember) {
        return historyScanDao.findHistoryScanByDateScanAndIdMember(dateScan , idMember);
    }

    @Override
    public HistoryScan findHistoryScanByDateScan(Date dateScan) {
        return historyScanDao.findHistoryScanByDateScan(dateScan);
    }


}

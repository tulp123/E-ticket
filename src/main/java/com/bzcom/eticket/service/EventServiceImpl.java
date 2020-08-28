package com.bzcom.eticket.service;

import com.bzcom.eticket.dao.EventDao;
import com.bzcom.eticket.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    public List<Event> findAllByMatchTimeAsc() {
        return eventDao.findAllByMatchTimeAsc();
    }

    @Override
    public List<Event> findAllByMatchTimeDesc() {
        return eventDao.findAllByMatchTimeDesc();
    }

    @Override
    public Event findById(int id) {
        return eventDao.findById(id);
    }

    @Override
    public Event save(Event event) {
        return eventDao.save(event);
    }
}

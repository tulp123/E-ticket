package com.bzcom.eticket.service;

import com.bzcom.eticket.model.Event;

import java.util.List;

public interface EventService {
    List<Event> findAll();

    List<Event> findAllByMatchTimeAsc();

    List<Event> findAllByMatchTimeDesc();

    Event findById(int id);

    Event save(Event event);
}

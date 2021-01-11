package com.bzcom.eticket.dao;

import com.bzcom.eticket.dto.AreaCountTicketDTO;
import com.bzcom.eticket.model.Area;
import com.bzcom.eticket.model.Location;
import com.bzcom.eticket.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AreaDaoImpl implements AreaDao {

    @Autowired
    private AreaRepository areaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    @Override
    public Area findById(int id) {
        return areaRepository.getOne(id);
    }

    @Override
    public Area save(Area area) {
        return areaRepository.save(area);
    }

    @Override
    public List<Area> findAllByLocation(Location location) {
        return areaRepository.findAllByLocation(location);
    }

    @Override
    public List<AreaCountTicketDTO> areaCountRemainTicket(int eventId) {
        TypedQuery<AreaCountTicketDTO> query = entityManager.createQuery("SELECT NEW com.bzcom.eticket.dto.AreaCountTicketDTO(a.id, count (distinct t.id)) " +
                "FROM Area a join Seat s on s.area.id = a.id join Ticket t on t.seat.id = s.id " +
                "WHERE t.event.id = ?1 and t.bookingStatus in (0, 3) group by a.id", AreaCountTicketDTO.class);
        query.setParameter(1, eventId);

        List<AreaCountTicketDTO> results = query.getResultList();
        return results;
    }
}

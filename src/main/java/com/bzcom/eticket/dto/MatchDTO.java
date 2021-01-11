package com.bzcom.eticket.dto;

import com.bzcom.eticket.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private EventDTO event;
    private int locationId;
    private String locationName;
    private TeamDTO teamA;
    private TeamDTO teamB;
    private int leagueId;
    private String leagueName;

    public MatchDTO(Event event) {
        this.event = new EventDTO(event);
        this.locationId = event.getLocation().getId();
        this.locationName = event.getLocation().getName();
        this.teamA = new TeamDTO(event.getGame().getTeamA());
        this.teamB = new TeamDTO(event.getGame().getTeamB());
        this.leagueId = event.getGame().getLeague().getId();
        this.leagueName = event.getGame().getLeague().getLeagueName();
    }
}

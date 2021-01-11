package com.bzcom.eticket.dto;

import com.bzcom.eticket.model.Team;
import lombok.Data;

@Data
public class TeamDTO {
    private int id;
    private String name;
    private String logo;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.logo = team.getLogo();
    }
}

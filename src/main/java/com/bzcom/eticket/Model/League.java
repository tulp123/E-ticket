package com.bzcom.eticket.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "league")
public class League {

    @Id
    @GeneratedValue
    private Integer id;

    private String league_name;

    private String league_info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getLeague_info() {
        return league_info;
    }

    public void setLeague_info(String league_info) {
        this.league_info = league_info;
    }
}

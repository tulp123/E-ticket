package com.bzcom.eticket.Exception;

public class LeagueNotFoundException extends Exception{
    private Integer league_id;
    public  LeagueNotFoundException(Integer league_id){
        super(String.format("League is not found with id : '%s'", league_id));
    }
}

package com.bzcom.eticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @MapsId
//    @OneToOne(mappedBy = "game")
//    @JoinColumn(name = "id")
//    private Event event;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JsonBackReference(value = "game-teamA")
    @JoinColumn(name = "team_a_id")
    private Team teamA;

    @ManyToOne
    @JsonBackReference(value = "game-teamB")
    @JoinColumn(name = "team_b_id")
    private Team teamB;;

    @Column(name = "game_info")
    private String gameInfo;

    @Column(name = "match_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date matchTime;

    @Column(name = "round")
    private int round;

    @Transient
    private String aTeamName;

    @Transient
    private String bTeamName;

    @Transient
    private int aTeamId;

    @Transient
    private int bTeamId;

    @Transient
    private String aTeamLogo;

    @Transient
    private String bTeamLogo;

    public String getaTeamLogo() {
        if (getTeamA() == null) {
            return aTeamLogo;
        } else {
            return getTeamA().getLogo();
        }
    }

    public void setaTeamLogo(String aTeamLogo) {
        this.aTeamLogo = aTeamLogo;
    }

    public String getbTeamLogo() {
        if (getTeamB() == null) {
            return bTeamLogo;
        } else {
            return getTeamB().getLogo();
        }
    }

    public void setbTeamLogo(String bTeamLogo) {
        this.bTeamLogo = bTeamLogo;
    }

    public String getaTeamName() {
        if (getTeamA() == null) {
            return aTeamName;
        } else {
            return getTeamA().getName();
        }
    }

    public void setaTeamName(String aTeamName) {
        this.aTeamName = aTeamName;
    }

    public String getbTeamName() {
        if (getTeamB() == null) {
            return bTeamName;
        } else {
            return getTeamB().getName();
        }
    }

    public void setbTeamName(String bTeamName) {
        this.bTeamName = bTeamName;
    }

    public int getaTeamId() {
        if (getTeamA() == null) {
            return aTeamId;
        } else {
            return getTeamA().getId();
        }
    }

    public void setaTeamId(int aTeamId) {
        this.aTeamId = aTeamId;
    }

    public int getbTeamId() {
        if (getTeamA() == null) {
            return  bTeamId;
        } else {
            return getTeamB().getId();
        }
    }

    public void setbTeamId(int bTeamId) {
        this.bTeamId = bTeamId;
    }
}

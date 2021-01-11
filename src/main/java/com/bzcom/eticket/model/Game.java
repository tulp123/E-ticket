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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm yyyy-MM-dd")
    private Date matchTime;

    @Column(name = "round")
    private int round;

    @ManyToOne
    @JsonBackReference(value = "league-game")
    @JoinColumn(name = "league_id")
    private League league;
}

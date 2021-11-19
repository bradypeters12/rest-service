package com.example.accessingdatarest;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PastTournaments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate tournamentDate;
    @ManyToOne
    @JoinColumn(name = "past_tournament_id")
    private Tournament tournament;
//Getters for PastTournaments class

    public Tournament getPastTournament() { return tournament; }
    public LocalDate getTournamentDate() { return tournamentDate; }
    public Tournament getTournament() { return tournament; }

//Setters for PastTournament class

    public void setTournamentDate(LocalDate tournamentDate) { this.tournamentDate = tournamentDate; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
}

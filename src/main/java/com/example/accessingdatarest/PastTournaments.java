package com.example.accessingdatarest;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PastTournaments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate pastTournamentDate;
    @ManyToOne
    @JoinColumn(name = "past_tournament_id")
    private Tournament tournament;

    public PastTournaments(LocalDate pastTournamentDate, Tournament tournament ) {
        this.pastTournamentDate = pastTournamentDate;
        this.tournament = tournament;
    }




    //Getters for PastTournaments class

    public Tournament getPastTournament() { return tournament; }
    public LocalDate getPastTournamentDate() { return pastTournamentDate; }
    public Tournament getTournament() { return tournament; }
    public long getid() {return id;}

//Setters for PastTournament class

    public void setPastTournamentDate(LocalDate tournamentDate) { this.pastTournamentDate = tournamentDate; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public void setId(long id) { this.id = id; }
}

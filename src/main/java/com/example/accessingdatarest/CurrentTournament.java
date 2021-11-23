package com.example.accessingdatarest;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CurrentTournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate CurrentTournamentDate;
    @ManyToOne
    @JoinColumn(name = "current_tournament_id")
    private Tournament tournament;

    public CurrentTournament(LocalDate currentTournamentDate, Tournament tournament) {
        this.CurrentTournamentDate = currentTournamentDate;
        this.tournament = tournament;
    }

    //Getters for Current Tournament
    public Tournament getTournament() { return tournament; }
    public LocalDate getCurrentTournamentDate() { return CurrentTournamentDate; }
    public long getId() { return id; }

    //Setters for Current Tournament
    public void setTournament(Tournament tournament) {this.tournament = tournament;}
    public void setCurrentTournamentDate(LocalDate currentTournamentDate) { this.CurrentTournamentDate = currentTournamentDate; }
    public void setId(long id) { this.id = id;}




}

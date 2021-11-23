package com.example.accessingdatarest;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class FutureTournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate futureTournamentDate;
    @ManyToOne
    @JoinColumn(name = "future_tournament_Id")
    private Tournament tournament;


    public FutureTournament(LocalDate futureTournamentDate, Tournament tournament) {
        this.futureTournamentDate = futureTournamentDate;
        this.tournament = tournament;
    }
//Getters for Future Tournaments
    public long getId() {return id; }
    public LocalDate getFutureTournamentDate() { return futureTournamentDate; }
    public Tournament getTournament() {return tournament; }

    //Setters for Future Tournaments
    public void setId(long id) {this.id = id;}
    public void setTournament(Tournament tournament) {this.tournament = tournament;}
    public void setFutureTournamentDate(LocalDate futureTournamentDate) {this.futureTournamentDate = futureTournamentDate;}


}

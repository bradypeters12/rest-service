package com.example.accessingdatarest;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private double entryFee;
    private double cashPrize;
    @ManyToOne
    @JoinColumn(name = "participants_id")
    private Person members;
    @OneToOne
    @JoinColumn(name ="final_standings_id")
    private FinalStandings finalStandings;

    public Tournament(long id, LocalDate startDate, LocalDate endDate, String location, double entryFee, double cashPrize, Person members, FinalStandings finalStandings) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrize = cashPrize;
        this.members = members;
        this.finalStandings = finalStandings;
    }

    public Tournament(Person members, double cashPrize, LocalDate endDate, double entryFee, String location, FinalStandings finalStandings) {

    }

    //Getters for Tournament
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getLocation() { return location; }
    public double getEntryFee() { return entryFee; }
    public double getCashPrize(){ return cashPrize; }
    public Person getMembers() { return members; }
    public FinalStandings getFinalStandings() { return finalStandings; }

    //Setters for Tournament
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setLocation(String location) { this.location = location; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }
    public void setCashPrize(double cashPrize) { this.cashPrize = cashPrize; }
    public void setMembers(Person members) { this.members = members; }
    public void setFinalStandings(FinalStandings finalStandings) {this.finalStandings = finalStandings; }
}

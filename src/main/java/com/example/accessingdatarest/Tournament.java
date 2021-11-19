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

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getLocation() { return location; }
    public double getEntryFee() { return entryFee; }
    public double getCashPrize(){ return cashPrize; }
    public Person getMembers() { return members; }
    public FinalStandings getFinalStandings() { return finalStandings; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setLocation(String location) { this.location = location; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }
    public void setCashPrize(double cashPrize) { this.cashPrize = cashPrize; }
    public void setMembers(Person members) { this.members = members; }
    public void setFinalStandings(FinalStandings finalStandings) {this.finalStandings = finalStandings; }
}

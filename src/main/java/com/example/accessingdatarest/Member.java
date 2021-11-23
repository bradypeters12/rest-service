package com.example.accessingdatarest;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private LocalDate membershipStart;
    private LocalDate membershipDuration;
    private String membershipType;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    @ManyToOne
    @JoinColumn(name = "past_tournament_id")
    private PastTournaments pastTournaments;


    public Member(Person person, LocalDate membershipStart, LocalDate membershipDuration, String membershipType) {
        this.id = id;
        this.person = person;
        this.membershipStart = membershipStart;
        this.membershipDuration = membershipDuration;
        this.membershipType = membershipType;
        this.tournament = null;
        this.pastTournaments = null;
    }


    //Getters for membership class
    public Person getPerson() { return person; }
    public LocalDate getMembershipStart() { return membershipStart; }
    public LocalDate getMembershipDuration() { return membershipDuration; }
    public String getMembershipType() { return membershipType; }
    public Tournament getTournaments() { return tournament; }
    public PastTournaments getPastTournaments() { return pastTournaments; }

//Setters for membership class
    public void setPerson(Person person) { this.person = person; }
    public void setMembershipStart(LocalDate membershipStart) { this.membershipStart = membershipStart;}
    public void setMembershipDuration(LocalDate membershipDuration) { this.membershipDuration = membershipDuration; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
    public void setPastTournaments(PastTournaments pastTournaments) { this.pastTournaments = pastTournaments; }
}

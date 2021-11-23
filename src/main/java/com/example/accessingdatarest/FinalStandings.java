package com.example.accessingdatarest;

import javax.persistence.*;

@Entity
public class FinalStandings{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private int score;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public FinalStandings(Person person, int score, Tournament tournament) {
        this.person = person;
        this.score = score;
        this.tournament = tournament;
    }

    //Getters for FinalStandings class
    public Tournament getTournament() { return tournament; }
    public Person getPerson() { return person; }
    public int getScore() { return score; }

    //Setters for FinalStandings class
    public void setPerson(Person person){ this.person = person; }
    public void setScore(int score){ this.score = score; }
    public void setTournament(Tournament tournament) { this.tournament = tournament;}
}

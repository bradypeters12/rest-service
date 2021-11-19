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

    //Getters for FinalStandings class

    public Person getPerson() { return person; }
    public int getScore() { return score; }

    //Setters for FinalStandings class
    public void setPerson(Person person){ this.person = person; }
    public void setScore(int score){ this.score = score; }
}

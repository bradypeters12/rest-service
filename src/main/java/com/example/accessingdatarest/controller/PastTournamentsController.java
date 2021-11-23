package com.example.accessingdatarest.controller;



import com.example.accessingdatarest.FinalStandings;
import com.example.accessingdatarest.PastTournaments;
import com.example.accessingdatarest.Tournament;
import com.example.accessingdatarest.repository.PastTournamentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PastTournamentsController {

    @Autowired
    PastTournamentsRepository pastTournamentsRepository;
//Get method for Past Tournaments
    @GetMapping("/pastTournaments")
    public ResponseEntity<List<PastTournaments>> getPastTournaments(@RequestParam(required = false) String pastTournamentDate) {
        try {
            List<PastTournaments> pastTournaments = new ArrayList<PastTournaments>();

            if (pastTournamentDate == null) {
                pastTournamentsRepository.findAll().forEach(pastTournaments::add);
            } else
                pastTournaments.addAll(pastTournamentsRepository.findByPastTournamentDate(pastTournamentDate));

            if (pastTournaments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pastTournaments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//Post method for Past Tournaments
    @PostMapping("/pastTournaments")
    public ResponseEntity<PastTournaments> postPastTournament(@RequestBody PastTournaments pastTournament) {
        try {
            PastTournaments _pastTournament = pastTournamentsRepository
                    .save(new PastTournaments(pastTournament.getPastTournamentDate(), pastTournament.getPastTournament()));
            return new ResponseEntity<>(_pastTournament, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//Put method for Past Tournaments
    @PutMapping("/PastTournaments/{id}")
    public ResponseEntity<PastTournaments> updatePastTournaments(@PathVariable(value = "id") long id, @RequestBody PastTournaments PastTournament) {
        Optional<PastTournaments> pastTournamentsInfo = pastTournamentsRepository.findById(id);

        if (pastTournamentsInfo.isPresent()) {
            PastTournaments _pastTournaments = pastTournamentsInfo.get();
            _pastTournaments.setTournament(_pastTournaments.getTournament());
            _pastTournaments.setPastTournamentDate(_pastTournaments.getPastTournamentDate());

            return new ResponseEntity<>(pastTournamentsRepository.save(_pastTournaments), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
// Delete method for Past Tournaments
    @DeleteMapping("/pastTournaments/{id}")
    public ResponseEntity<HttpStatus> deletePastTournament(@PathVariable(value = "id") long id) {
        try {
            pastTournamentsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}









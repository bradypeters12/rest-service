package com.example.accessingdatarest.controller;


import com.example.accessingdatarest.CurrentTournament;
import com.example.accessingdatarest.FutureTournament;
import com.example.accessingdatarest.PastTournaments;
import com.example.accessingdatarest.repository.CurrentTournamentRepository;
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
public class CurrentTournamentsController {
    @Autowired
    CurrentTournamentRepository currentTournamentRepository;
//Get method for Current Tournaments
    @GetMapping("/currentTournaments")
    public ResponseEntity<List<CurrentTournament>> getCurrentTournaments(@RequestParam(required = false) String currentTournamentDate) {
        try {
            List<CurrentTournament> currentTournaments = new ArrayList<CurrentTournament>();

            if (currentTournamentDate == null) {
                currentTournamentRepository.findAll().forEach(currentTournaments::add);
            } else
                currentTournaments.addAll(currentTournamentRepository.findByCurrentTournamentDate(currentTournamentDate));

            if (currentTournaments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(currentTournaments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//Post method for Current Tournaments
    @PostMapping("/currentTournaments")
    public ResponseEntity<CurrentTournament> postCurrentTournament(@RequestBody CurrentTournament currentTournament) {
        try {
            CurrentTournament _currentTournament = currentTournamentRepository
                    .save(new CurrentTournament(currentTournament.getCurrentTournamentDate(), currentTournament.getTournament()));
            return new ResponseEntity<>(_currentTournament, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//Put method for Current Tournaments
    @PutMapping("/currentTournaments/{id}")
    public ResponseEntity<CurrentTournament> updateCurrentTournament(@PathVariable(value = "id") long id, @RequestBody CurrentTournament currentTournament) {
        Optional<CurrentTournament> currentTournamentData = currentTournamentRepository.findById(id);

        if (currentTournamentData.isPresent()) {
            CurrentTournament _currentTournament = currentTournamentData.get();
            _currentTournament.setCurrentTournamentDate(_currentTournament.getCurrentTournamentDate());
            _currentTournament.setTournament(_currentTournament.getTournament());
            return new ResponseEntity<>(currentTournamentRepository.save(_currentTournament), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete method for Current Tournaments
    @DeleteMapping("/currentTournaments/{id}")
    public ResponseEntity<HttpStatus> deleteCurrentTournament(@PathVariable(value = "id") long id) {
        try {
            currentTournamentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

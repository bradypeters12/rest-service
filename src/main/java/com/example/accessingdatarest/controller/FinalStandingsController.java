package com.example.accessingdatarest.controller;


import com.example.accessingdatarest.FinalStandings;
import com.example.accessingdatarest.repository.FinalStandingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class FinalStandingsController {
    @Autowired
    FinalStandingsRepository finalStandingsRepository;
//Get method for finalStandings
    @GetMapping("/finalStandings")
    public ResponseEntity<List<FinalStandings>> getFinalStandings(@RequestParam(required = false) Integer id) {
        try {
            List<FinalStandings> finalStandings = new ArrayList<FinalStandings>();

            if (id == null) {
                finalStandingsRepository.findAll().forEach(finalStandings::add);
            } else
                finalStandings.addAll(finalStandingsRepository.findFinalStandingsById(id));

            if (finalStandings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(finalStandings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//Post method for FinalStandings
    @PostMapping("/finalStandings")
    public ResponseEntity<FinalStandings> postFinalStandings(@RequestBody FinalStandings finalStandings) {
        try {
            FinalStandings _finalStandings = finalStandingsRepository
                    .save(new FinalStandings(finalStandings.getPerson(), finalStandings.getScore(), finalStandings.getTournament()));
            return new ResponseEntity<>(_finalStandings, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//Put method for FinalStandings
    @PutMapping("/finalStandings/{id}")
    public ResponseEntity<FinalStandings> updateFinalStandings(@PathVariable(value = "id") long id, @RequestBody FinalStandings finalStandings) {
        Optional<FinalStandings> FinalStandingsInfo = finalStandingsRepository.findById(id);

        if (FinalStandingsInfo.isPresent()) {
            FinalStandings _finalStandings = FinalStandingsInfo.get();
            _finalStandings.setScore(finalStandings.getScore());
            _finalStandings.setPerson(finalStandings.getPerson());
            _finalStandings.setTournament(finalStandings.getTournament());

            return new ResponseEntity<>(finalStandingsRepository.save(_finalStandings), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//Delete method for FinalStandings
    @DeleteMapping("/finalStandings/{id}")
    public ResponseEntity<HttpStatus> deleteFinalStandings(@PathVariable(value = "id") long id) {
        try {
            finalStandingsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



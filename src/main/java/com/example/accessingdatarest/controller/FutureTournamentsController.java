package com.example.accessingdatarest.controller;


import com.example.accessingdatarest.FutureTournament;
import com.example.accessingdatarest.PastTournaments;
import com.example.accessingdatarest.repository.FutureTournamentRepository;
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
public class FutureTournamentsController {

    @Autowired
    FutureTournamentRepository futureTournamentRepository;
//Get method for Future Tournaments
    @GetMapping("/futureTournaments")
    public ResponseEntity<List<FutureTournament>> getFutureTournament(@RequestParam(required = false) String futureTournamentDate){
        try {
            List<FutureTournament> futureTournament = new ArrayList<FutureTournament>();

            if (futureTournamentDate == null) {
                futureTournamentRepository.findAll().forEach(futureTournament::add);
            } else
                futureTournament.addAll(futureTournamentRepository.findByFutureTournamentDate(futureTournamentDate));

            if (futureTournament.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(futureTournament, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//Post method for Future Tournaments
    @PostMapping("/futureTournaments")
    public ResponseEntity<FutureTournament> postFutureTournaments(@RequestBody FutureTournament futureTournament){
        try {
            FutureTournament _futureTournament = futureTournamentRepository
                    .save(new FutureTournament(futureTournament.getFutureTournamentDate(), futureTournament.getTournament()));
            return new ResponseEntity<>(_futureTournament, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//Put method for Future Tournaments
    @PutMapping("/futureTournaments/{id}")
    public ResponseEntity<FutureTournament> updateFutureTournament(@PathVariable(value = "id" )long id, @RequestBody PastTournaments FutureTournament){
        Optional<FutureTournament> FutureTournamentInfo = futureTournamentRepository.findById(id);

        if(FutureTournamentInfo.isPresent()) {
            FutureTournament _futureTournament = FutureTournamentInfo.get();
            _futureTournament.setFutureTournamentDate(_futureTournament.getFutureTournamentDate());
            _futureTournament.setTournament(_futureTournament.getTournament());
            return new ResponseEntity<>(futureTournamentRepository.save(_futureTournament), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//Delete method for Future Tournaments
    @DeleteMapping("/futureTournaments/{id}")
    public ResponseEntity<HttpStatus> deleteFutureTournament(@PathVariable("id") long id) {
        try {
            futureTournamentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

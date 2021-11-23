package com.example.accessingdatarest.controller;



import com.example.accessingdatarest.Tournament;
import com.example.accessingdatarest.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TournamentController{

    @Autowired
    TournamentRepository tournamentRepository;
// Get Method for Tournaments
    @GetMapping("/tournament")
    public ResponseEntity<List<Tournament>> getTournaments(@RequestParam(required = false) String startDate){
        try {
            List<Tournament> tournament = new ArrayList<Tournament>();

            if (startDate == null)
                tournamentRepository.findAll().forEach(tournament::add);
            else
                tournament.addAll(tournamentRepository.findByStartDate(startDate));

            if (tournament.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tournament, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//Post Method for Tournaments
    @PostMapping("/tournament")
    public ResponseEntity<Tournament> postTournament(@RequestBody Tournament tournament) {
        try {
            Tournament _tournament = tournamentRepository
                    .save(new Tournament(tournament.getMembers(), tournament.getCashPrize(), tournament.getEndDate(), tournament.getEntryFee(), tournament.getLocation(), tournament.getFinalStandings()));
            return new ResponseEntity<>(_tournament, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//Put method for Tournaments
    @PutMapping("/tournament/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable(value = "id") long id, @RequestBody Tournament tournament) {
        Optional<Tournament> tournamentInfo = tournamentRepository.findById(id);

        if (tournamentInfo.isPresent()) {
            Tournament _tournament = tournamentInfo.get();
            _tournament.setMembers(tournament.getMembers());
            _tournament.setCashPrize(tournament.getCashPrize());
            _tournament.setEndDate(tournament.getEndDate());
            _tournament.setEntryFee(tournament.getEntryFee());
            _tournament.setLocation(tournament.getLocation());
            _tournament.setFinalStandings(tournament.getFinalStandings());

            return new ResponseEntity<>(tournamentRepository.save(_tournament), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//Delete method for Tournaments
    @DeleteMapping("/tournament/{id}")
    public ResponseEntity<HttpStatus> deleteTournament(@PathVariable(value = "id") long id) {
        try {
            tournamentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

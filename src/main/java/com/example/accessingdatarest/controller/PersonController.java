package com.example.accessingdatarest.controller;
import com.example.accessingdatarest.Person;


import com.example.accessingdatarest.repository.PersonRepository;
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
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/people")
    public ResponseEntity<List<Person>> getPeople(@RequestParam(required = false) String firstName, String lastName) {
        try {
            List<Person> people = new ArrayList<Person>();

            if (lastName == null && firstName == null)
                personRepository.findAll().forEach(people::add);
            else if (lastName == null)
                people.addAll(personRepository.findByFirstName(firstName));
            else
                people.addAll(personRepository.findByLastName(lastName));

            if (people.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(people, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Method for posting people
    @PostMapping("/people")
    public ResponseEntity<Person> postPeople(@RequestBody Person person) {
        try {
            Person _person = personRepository
                    .save(new Person(person.getFirstName(), person.getLastName(), person.getEmail(), person.getPhoneNumber()));
            return new ResponseEntity<>(_person, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update method for people
    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePeople(@PathVariable(value = "id") long id, @RequestBody Person person) {
        Optional<Person> personInfo = personRepository.findById(id);

        if (personInfo.isPresent()) {
            Person _person = personInfo.get();
            _person.setFirstName(person.getFirstName());
            _person.setLastName(person.getLastName());
            _person.setEmail(person.getEmail());
            _person.setPhoneNumber(person.getPhoneNumber());

            return new ResponseEntity<>(personRepository.save(_person), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete method for people

    @DeleteMapping("/people/{id}")
    public ResponseEntity<HttpStatus> deletePeople(@PathVariable(value = "id") long id) {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
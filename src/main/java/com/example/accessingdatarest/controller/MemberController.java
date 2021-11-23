package com.example.accessingdatarest.controller;


import com.example.accessingdatarest.Member;

import com.example.accessingdatarest.repository.MemberRepository;
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
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
//Get method for Members
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getMembers(@RequestParam(required = false) Integer id) {
        try {
            List<Member> member = new ArrayList<Member>();

            if (id == null) {
                memberRepository.findAll().forEach(member::add);
            } else
                member.addAll(memberRepository.findById(id));

            if (member.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//Post method for Members
    @PostMapping("/members")
    public ResponseEntity<Member> postMembers(@RequestBody Member member) {
        try {
            Member _member = memberRepository
                    .save(new Member(member.getPerson(), member.getMembershipStart(), member.getMembershipDuration(), member.getMembershipType()));
            return new ResponseEntity<>(_member, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//Put method for Members
    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMembers(@PathVariable(value = "id") long id, @RequestBody Member member) {
        Optional<Member> memberInfo = memberRepository.findById(id);

        if (memberInfo.isPresent()) {
            Member _member = memberInfo.get();
            _member.setPerson(member.getPerson());
            _member.setMembershipStart(member.getMembershipStart());
            _member.setMembershipType(member.getMembershipType());
            _member.setMembershipDuration(member.getMembershipDuration());

            return new ResponseEntity<>(memberRepository.save(_member), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//Delete method for Members
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteMembers(@PathVariable(value = "id") long id) {
        try {
            memberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

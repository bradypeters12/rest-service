package com.example.accessingdatarest.repository;

import java.util.List;

import com.example.accessingdatarest.PastTournaments;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pastTournaments", path = "pastTournaments")
public interface PastTournamentsRepository extends PagingAndSortingRepository<PastTournaments, Long> {

    List<PastTournaments> findByPastTournamentDate(@Param("pastTournamentDate") String pastTournamentDate);

}

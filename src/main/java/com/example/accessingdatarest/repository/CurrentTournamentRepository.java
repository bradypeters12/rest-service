package com.example.accessingdatarest.repository;

import java.util.List;

import com.example.accessingdatarest.CurrentTournament;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Current Tournament Repository
@RepositoryRestResource(collectionResourceRel = "currentTournaments", path = "currentTournaments")
public interface CurrentTournamentRepository extends PagingAndSortingRepository<CurrentTournament, Long> {
    List<CurrentTournament> findByCurrentTournamentDate(@Param("currentTournamentDate") String currentTournamentDate);
}

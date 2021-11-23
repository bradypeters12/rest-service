package com.example.accessingdatarest.repository;


import com.example.accessingdatarest.FutureTournament;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// Future Tournament Repository
@RepositoryRestResource(collectionResourceRel = "futureTournaments", path = "futureTournaments")
public interface FutureTournamentRepository extends PagingAndSortingRepository<FutureTournament, Long> {
    List<FutureTournament> findByFutureTournamentDate(@Param("futureTournamentDate") String futureTournamentDate);
}

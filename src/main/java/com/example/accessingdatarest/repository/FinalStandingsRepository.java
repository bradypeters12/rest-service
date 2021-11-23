package com.example.accessingdatarest.repository;

import java.util.List;

import com.example.accessingdatarest.FinalStandings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//Final Standings Repository
@RepositoryRestResource(collectionResourceRel = "finalStandings", path = "finalStandings")
public interface FinalStandingsRepository extends PagingAndSortingRepository<FinalStandings, Long> {

    List<FinalStandings> findFinalStandingsById(@Param("id") Integer id);

}

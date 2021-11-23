package com.example.accessingdatarest.repository;

import java.util.List;

import com.example.accessingdatarest.Tournament;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//Tournament Repository
@RepositoryRestResource(collectionResourceRel = "tournament", path = "tournament")
public interface TournamentRepository extends PagingAndSortingRepository<Tournament, Long> {

    List<Tournament> findByStartDate(@Param("startdate") String startDate);

}

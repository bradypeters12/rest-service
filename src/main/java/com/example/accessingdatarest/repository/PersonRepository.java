package com.example.accessingdatarest.repository;

import java.util.List;

import com.example.accessingdatarest.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//Person Repository
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByLastName(@Param("firstName") String name);
    List<Person> findByFirstName(@Param("lastName") String name);
}
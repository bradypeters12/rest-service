package com.example.accessingdatarest.repository;

import java.util.List;

import com.example.accessingdatarest.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "members", path = "members")
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

    List<Member> findById(@Param("id") Integer id);

}
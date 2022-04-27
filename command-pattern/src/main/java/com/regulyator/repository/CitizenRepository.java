package com.regulyator.repository;

import com.regulyator.entity.Citizen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends CrudRepository<Citizen, Long> {
}

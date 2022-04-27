package com.regulyator.repository;

import com.regulyator.entity.Citizenship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenshipRepository extends CrudRepository<Citizenship, Long> {
}

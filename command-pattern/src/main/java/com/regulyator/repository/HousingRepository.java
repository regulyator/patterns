package com.regulyator.repository;

import com.regulyator.entity.Housing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingRepository extends CrudRepository<Housing, Long> {
}

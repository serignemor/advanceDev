package com.uadb.advancedev.repositories;

import com.uadb.advancedev.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RateRepository extends JpaRepository<Rate, Long>{
}

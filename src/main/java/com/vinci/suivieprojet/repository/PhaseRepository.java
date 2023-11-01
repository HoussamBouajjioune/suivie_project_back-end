package com.vinci.suivieprojet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinci.suivieprojet.models.Phase;

@Repository
public interface PhaseRepository extends CrudRepository<Phase, Long>{

}

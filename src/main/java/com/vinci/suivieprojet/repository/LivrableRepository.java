package com.vinci.suivieprojet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinci.suivieprojet.models.Livrable;
import com.vinci.suivieprojet.models.Phase;

@Repository
public interface LivrableRepository extends CrudRepository<Livrable, Long>{

	Optional<Livrable> findByPhase(Phase phase);
}

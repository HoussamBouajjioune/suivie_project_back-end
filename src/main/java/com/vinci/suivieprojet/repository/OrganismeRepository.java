package com.vinci.suivieprojet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinci.suivieprojet.models.Organisme;

@Repository
public interface OrganismeRepository extends CrudRepository<Organisme, Long>{


}

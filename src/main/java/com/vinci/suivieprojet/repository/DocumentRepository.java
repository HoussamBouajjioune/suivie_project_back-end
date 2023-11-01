package com.vinci.suivieprojet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinci.suivieprojet.models.Document;


@Repository
public interface DocumentRepository extends CrudRepository<Document, Long>{


}

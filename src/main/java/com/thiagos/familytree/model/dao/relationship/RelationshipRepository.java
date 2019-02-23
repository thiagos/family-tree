package com.thiagos.familytree.model.dao.relationship;

import com.thiagos.familytree.model.dto.Relationship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends CrudRepository<Relationship, Long> {
}

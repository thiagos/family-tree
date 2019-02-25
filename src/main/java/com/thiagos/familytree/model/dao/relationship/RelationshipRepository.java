package com.thiagos.familytree.model.dao.relationship;

import com.thiagos.familytree.model.dto.RelationType;
import com.thiagos.familytree.model.dto.Relationship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends CrudRepository<Relationship, Long> {

    public List<Relationship> findByPersonId(Long personId);
    public List<Relationship> findByFatherId(Long fatherId);
    public List<Relationship> findByMotherId(Long motherId);

}

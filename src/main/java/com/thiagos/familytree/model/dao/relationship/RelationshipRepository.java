package com.thiagos.familytree.model.dao.relationship;

import com.thiagos.familytree.model.dto.RelationType;
import com.thiagos.familytree.model.dto.Relationship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends CrudRepository<Relationship, Long> {

    public List<Relationship> findByRelativeIdAndRelationType(Long relativeId, RelationType relationType);
    public List<Relationship> findByPersonIdAndRelationType(Long personId, RelationType relationType);

}

package com.thiagos.familytree.model.dao.relationship;

import com.thiagos.familytree.model.dto.RelationType;
import com.thiagos.familytree.model.dto.Relationship;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelationshipDAO {
    private RelationshipRepository repository;

    public RelationshipDAO(RelationshipRepository repository) { this.repository = repository; }

    public List<Relationship> findByPersonIdAndRelationType(Long personId, RelationType relationType) {
        return repository.findByPersonIdAndRelationType(personId, relationType);
    }

    public List<Relationship> findByRelativeIdAndRelationType(Long relativeId, RelationType relationType) {
        return repository.findByRelativeIdAndRelationType(relativeId, relationType);
    }

    public void save(Relationship relationship) { repository.save(relationship); }

    public void saveAll(List<Relationship> relationships) { repository.saveAll(relationships); }
}

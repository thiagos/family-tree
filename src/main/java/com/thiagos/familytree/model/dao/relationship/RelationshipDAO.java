package com.thiagos.familytree.model.dao.relationship;

import com.thiagos.familytree.model.dto.Relationship;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelationshipDAO {
    private RelationshipRepository repository;

    public RelationshipDAO(RelationshipRepository repository) { this.repository = repository; }

    public Relationship findByPersonId(Long personId) {
        List<Relationship> relationshipList = repository.findByPersonId(personId);
        if (relationshipList.isEmpty()) return null;
        return relationshipList.get(0);
    }

    public List<Relationship> findByFatherId(Long fatherId) {
        return repository.findByFatherId(fatherId);
    }

    public List<Relationship> findByMotherId(Long motherId) {
        return repository.findByMotherId(motherId);
    }

    public void save(Relationship relationship) { repository.save(relationship); }

    public void saveAll(List<Relationship> relationships) { repository.saveAll(relationships); }
}

package com.thiagos.familytree.model.dao.relationship;

import com.thiagos.familytree.model.dto.Relationship;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelationshipDAO {
    private RelationshipRepository repository;

    public RelationshipDAO(RelationshipRepository repository) { this.repository = repository; }

    public void save(Relationship relationship) { repository.save(relationship); }

    public void saveAll(List<Relationship> relationships) { repository.saveAll(relationships); }
}

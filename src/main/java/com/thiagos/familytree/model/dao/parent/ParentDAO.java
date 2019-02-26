package com.thiagos.familytree.model.dao.parent;

import com.thiagos.familytree.model.dto.Parent;
import com.thiagos.familytree.model.exception.DataException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Component
public class ParentDAO {
    private ParentRepository repository;

    public ParentDAO(ParentRepository repository) { this.repository = repository; }

    public Parent findByPersonId(Long personId) {
        List<Parent> parentList = repository.findByPersonId(personId);
        if (parentList.isEmpty()) return null;
        return parentList.get(0);
    }

    public List<Parent> findByFatherId(Long fatherId) {
        return repository.findByFatherId(fatherId);
    }

    public List<Parent> findByMotherId(Long motherId) {
        return repository.findByMotherId(motherId);
    }

    public void save(Parent parent) { repository.save(parent); }

    public void saveAll(List<Parent> parents) {
        try {
            repository.saveAll(parents);
        } catch (Exception e) {
        }
    }
}

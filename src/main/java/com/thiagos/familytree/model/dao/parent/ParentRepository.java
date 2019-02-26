package com.thiagos.familytree.model.dao.parent;

import com.thiagos.familytree.model.dto.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends CrudRepository<Parent, Long> {

    public List<Parent> findByPersonId(Long personId);
    public List<Parent> findByFatherId(Long fatherId);
    public List<Parent> findByMotherId(Long motherId);

}

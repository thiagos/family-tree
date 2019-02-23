package com.thiagos.familytree.controller;

import com.thiagos.familytree.model.request.AddPersonsRequest;
import com.thiagos.familytree.model.request.AddRelationshipsRequest;
import com.thiagos.familytree.model.response.GenericResponse;
import com.thiagos.familytree.service.FamilyTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyTreeController {

    @Autowired
    private FamilyTreeService familyTreeService;

    @PostMapping("/addPersons")
    public GenericResponse addPerson(@RequestBody AddPersonsRequest addPersonsRequest) {
        familyTreeService.addPersons(addPersonsRequest.getPersons());
        return new GenericResponse(0l, "Success");
    }

    @PostMapping("/addRelationships")
    public GenericResponse addRelationships(@RequestBody AddRelationshipsRequest addRelationshipsRequest) {
        familyTreeService.addRelationships(addRelationshipsRequest.getRelationships());
        return new GenericResponse(0l, "Success");
    }


}

package com.thiagos.familytree.controller;

import com.thiagos.familytree.model.request.AddPersonsRequest;
import com.thiagos.familytree.model.request.AddRelationshipsRequest;
import com.thiagos.familytree.model.request.GetFamilyTreeRequest;
import com.thiagos.familytree.model.response.FamilyTreeResponse;
import com.thiagos.familytree.model.response.GenericResponse;
import com.thiagos.familytree.service.FamilyTreeService;
import com.thiagos.familytree.service.treeBuilder.AhnentafelTreeBuilder;
import com.thiagos.familytree.util.FamilyNode;
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

    @PostMapping("/getFamilyTree")
    public FamilyTreeResponse getFamilyTree(@RequestBody GetFamilyTreeRequest getFamilyTreeRequest) {
        // build response
        FamilyTreeResponse familyTreeResponse = new FamilyTreeResponse();
        familyTreeResponse.setResultCode(0l);
        familyTreeResponse.setResultMessage("Success");

        // dynamically change treeBuilder
        familyTreeService.setTreeBuilderByTreeType(getFamilyTreeRequest.getFamilyTreeType());

        // create the tree based on the request personId and populate in the response
        FamilyNode familyNode = familyTreeService.getFamilyTree(getFamilyTreeRequest.getPersonId());
        familyTreeResponse.setFamilyNode(familyNode);

        familyTreeResponse.setResultMessage(familyTreeService.getTreePrint(familyNode));

        return familyTreeResponse;
    }

    @PostMapping("getFamilyTreeText")
    public GenericResponse getFamilyTreeText(@RequestBody GetFamilyTreeRequest getFamilyTreeRequest) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResultCode(0l);

        // dynamically change treeBuilder
        familyTreeService.setTreeBuilderByTreeType(getFamilyTreeRequest.getFamilyTreeType());

        // create the tree based on the request personId and populate in the response
        FamilyNode familyNode = familyTreeService.getFamilyTree(getFamilyTreeRequest.getPersonId());

        genericResponse.setResultMessage(familyTreeService.getTreePrint(familyNode));

        return genericResponse;
    }



    }

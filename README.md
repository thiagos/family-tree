# Family Tree Application
This application manages persons and their parent-child relationships, organizing them into a family tree.

## APIs

A set of JSON POST APIs are available to create Persons, Parent relationships, and retrieve a family tree for a given person.

### addPersons
Allows a bulk insertion of persons, as a list of JSON entities:

````
{
  "persons": [
     {
       "personId": 1,
       "name": "David",
       "gender": "MALE"
     },
     {
       "personId": 2,
       "name": "Cathy",
       "gender": "FEMALE"
     },
     {
       "personId": 3,
       "name": "Emily",
       "gender": "FEMALE"
     },
     {
     	"personId": 4,
     	"name": "Barb",
     	"gender": "FEMALE"
     },
     {
     	"personId": 5,
     	"name": "Albert",
     	"gender": "MALE"
     }
  ]
}
````

### addParents
Allows a bulk insertion of parents, as a list of JSON entities:
````
{
  "parents": [
     {
       "personId": 3,
       "fatherId": 1,
       "motherId": 2
     },
     {
       "personId": 2,
       "fatherId": 5,
       "motherId": 4
     }
  ]
}
````
Each JSON element is like a "birth certificate", indicating who are the father and mother of a specific person.

### getFamilyTree
Retrieves the family tree for a given Person in a JSON format.
````
{
  "personId": 7
  "familyTreeType": AHNENTAFEL
}
````

There are multiple ways of creating a family tree of a given person.

For instance, the Ahnentafel representation (https://en.wikipedia.org/wiki/Family_tree#Ahnentafel) focus on the *ancestors*. 
Another tree can focus only on *descendants*, while a third type could consist of a mix of both *ancestors* and *descendants*, including cousins or not, etc.

Two trees were implemented with the values accepted on *familyTreeType* field: 
- AHNENTAFEL
- DESCENDANTS

This API returns a familyTreeResponse:

````
{
    "resultCode": 0,
    "resultMessage": "Success",
    "familyNode": {
        "name": "George",
        "personId": 7,
        "gender": "MALE",
        "father": {
            "name": "Frank",
            "personId": 6,
            "gender": "MALE"
        },
        "mother": {
            "name": "Emily",
            "personId": 3,
            "gender": "FEMALE",
            "father": {
                "name": "David",
                "personId": 1,
                "gender": "MALE"
            },
            "mother": {
                "name": "Cathy",
                "personId": 2,
                "gender": "FEMALE",
                "father": {
                    "name": "Albert",
                    "personId": 5,
                    "gender": "MALE"
                },
                "mother": {
                    "name": "Barb",
                    "personId": 4,
                    "gender": "FEMALE"
                }
            }
        }
    }
}
````

### getFamilyTreeText
Retrieves the family tree again, but now returns a text description of the family instead of a full family JSON response.
````
{
  "personId": 4
  "familyTreeType": DESCENDANTS
}
````

With a text description of the family in 'treeText' response field:

````
{
    "resultCode": 0,
    "resultMessage": "Success",
    "treeText": "self: Barb, with spouse: Albert, daughter: Cathy, with spouse: David, granddaughter: Emily, with spouse: Frank, great-grandson: George, "
}
````

## Running the application

After cloning the project, the application can be run directly from an IDE (Intellij was used for this development).

Another option is to build the project using maven:

``
mvn clean package
``

And application executed via its jar file:

``
java -jar target/family-tree-0.0.1-SNAPSHOT.jar
``

Application exposes the APIs on `http://localhost:8080` 

Sample requests can be found under `resources/samples`. 
For instance, using Postman or curl to send file `addPersons.json` to `http://localhost:8080/addPersons`
should insert 7 entries in H2 database, Person table.

Requirements are:
- Be a POST request;
- Request header "Content-Type: application/json" has to be present.

## Technical Details

### Framework

Spring Boot was chosen to create this application, as it makes it fairly easy to achieve the basic funcionalities required,
like exposing REST APIs, integration with database, etc.

### Database

This project uses an in memory H2 database made available by Spring Boot, which works well for a PoC/small project.

Two tables, *Person* and *Parent* exist for now, having the API inputs as columns.

### Multiple implementations

As mentioned earlier, there are multiple ways of representing a tree.
Also, there are multiple ways to visualize the tree: with an image, textual only, in JSON format, etc.

Based on this, interfaces with multiple implementations might be a good approach for classes that will implement these functionalities.

### Error Handling

A couple basic error handlings were implemented:
- avoid duplicated *PersonId* on *addPersons* API.
- handle non-existent *PersonId* on *getFamilyTree* API.

Many extra validations would need to be implemented to have a real functional application.

## Gaps/Next Steps
- Add extra validations (for instance, validate that all persons exist before adding parent)
- This simplified version does not allow "holes" in the family tree, i.e., linking a person directly to a grandparent is not allowed. A Parent link is required.
- Provide extra "RUD (Create already available), to fix eventual input mistakes on Person and Parent
- Enhance `getFamilyTree` API to allow for filtering relatives (max tree depth for instance, etc)
- If it is easier for an eventual client system, ids could be replaced by name in `addParents` API, but persons with exact same name could be a problem.
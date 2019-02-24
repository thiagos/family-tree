# Family Tree Application
This application manages persons and their family relationships,  organizing them into a family tree.

## APIs

A set of JSON APIs are available to create Persons, Relationships, and retrieve a family tree for a given person.

### addPersons
Allows a bulk insertion of persons, as a list of JSON entities:

````
{
  "persons": [
     {
       "personId": 123,
       "name": "John Doe",
       "gender": "MALE"
     },
     {
       "personId": 456,
       "name": "Jane Done",
       "gender": "FEMALE"
     },
     {
       "personId": 789,
       "name": "Mary Doe",
       "gender": "FEMALE"
     }
  ]
}
````

Possible values for gender: MALE, FEMALE, OTHER

### addRelationships
Allows a bulk insertion of relationships, as a list of JSON entities:
````
{
  "relationships": [
     {
       "personId": 123,
       "relativeId": 456,
       "relationType": "PARTNER"
     },
     {
       "personId": 123,
       "relativeId": 789,
       "relationType": "PARENT"
     },
     {
     	"personId": 456,
       "relativeId": 789,
       "relationType": "PARENT"
     }
  ]
}
````
Possible values for relationType: PARTNER, PARENT

### getFamilyTree
Retrieves the family tree for a given Person.
````
{
  "personId": 123
}
````

## General Implementation Details

There are multiple types of family tree - based on direct ancestors only, having both ancestors and descendants, etc.

Also, there are multiple ways to represent the family tree: graphical, textual only, left to right, etc.

Based on this, interfaces with multiple implementations might be a good approach here.

## Gaps/Next Steps
- Add validations (for instance, validate persons exist before adding relationship)
- This simplified version does not allow "holes" in the family tree, i.e., linking a person directly to a grandparent is not allowed. A Parent is required.
- Provide extra "RUD (Create already available), to fix eventual input mistakes
- Enhance `getFamilyTree` API to allow for filtering relatives (only ancestors, max tree depth, etc)
- If easier for an eventual client system, ids could be replaced by name in `addRelationships` API, but persons with exact same name could be a problem.
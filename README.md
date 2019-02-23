# Family Tree Application
This application manages persons and their family relationships,  organizing them into a family tree.

## APIs

A set of JSON APIs

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

## Considerations/Next Steps
- Add validations (for instance, validate persons exist before adding relationship)
- This simplified version does not allow "holes" in the family tree, i.e., linking a person directly to a grandparent is not allowed. A Parent is required.
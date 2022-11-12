# Java Spring Assignment

This is a Spring Project that contains the solution to given tasks in TestGorilla.

To run the project you will need to have Java 11 installed to your machine.
How to do so is out of the scope of this document.

To build and run the project please follow these steps:

1. run `mvn clean install`
2. run `mvn spring-boot:run`
3. access the project at localhost:8080

Alternatively you can import it in you IDE and run it from there


Endpoints Description IS DONE OVERTIME :) 

Endpoints:

**Create Word Relation**

POST /api/v1/word-relations

Request Body: `{wordOne: "string", wordTwo: "string", relation: "string"}`

Response Body: `{id: number, wordOne: "string", wordTwo: "string", relation: "string", inverse: boolean}`


**List Word Relations**

GET /api/v1/word-relations

Query Params: 

`filter` - optional, string, filters all by relations

`inverse` - optional, boolean, indicates if the inverse relations should be included

Response Body: `[ {id: number, wordOne: "string", wordTwo: "string", relation: "string", inverse: boolean} ]`


**Create Word Relation**

GET /api/v1/word-relations/path

Query Params:

`source` - required, string, the start of the path

`target` - required, string, the target of the path

Response Body: string path example: `oo ==(ee)=> pp ==(dd)=> kk ==(zz)=> aa ==(cc)=> bb`

P.S. at the time of writing this description I've noticed that the required is missing in the code for `source` and `target` params for `GET /api/v1/word-relations/path` 
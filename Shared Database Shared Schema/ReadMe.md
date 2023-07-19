# Multi-tenancy in SpringBoot
## Shared Database and Shared Schema

- Multi-tenancy is a software architecture where a single software instance can serve multiple, distinct user groups.
- Software-as-a-service (SaaS) offerings are an example of multi-tenant architecture.
- Each customer is called a tenant. Tenants can be given the ability to customise some parts of the application
- There are three main multi-tenancy model type:
  - Separate Database
  - Shared Database and Separate Schema 
  - Shared Database and Shared Schema (this repository)

### Profile Management Application

This is a **spring boot** application which provides basic CRUD Operations to create and manage student profile.

Below Operations are provided as REST API calls.

1) Register a new student
2) Update Existing Student
3) Delete Exising Student
4) Search Student by -ID, Name, Age and Registration Number.
5) Register a New Department
6) Update Existing Department
7) Delete Existing Department
8) Search Department by -ID, Name.


###### Architecture:
* MVC (Model- View- Controller) Architecture 
* DAO Layers implementation for segregating JPA and MongoDB implementations.


###### Features:
* It can connect to both RDBMS and No-SQL database for the operations.
* It uses H2 as the Relational Database and Mongo DB as No-SQL database.
* It uses spring profiles to choose the RDBMS or No-SQL Databases.
* It uses spring-boot-data-JPA for the H2 DB.
* It also integrates with Flyway DB (https://flywaydb.org/) for database migration.


### Test


- To get list of all students of tenant TEST2
   `curl -H 'Content-Type: application/json' -H 'X-TenantID:TEST2'  http://localhost:8083/student/allStudents | python -m json.tool`
- To get list of all students of tenant TEST1 of given age
  `curl -H 'Content-Type: application/json' -H 'X-TenantID:TEST1'  http://localhost:8083/student/getStudentByAge/31 | python -m json.tool
`
- To get list of all students of tenant TEST1 of given name
  `curl -H 'Content-Type: application/json' -H 'X-TenantID:TEST1'  http://localhost:8083/student/getStudentByName/TANVI | python -m json.tool`

- To get list of all students of tenant TEST1 of given registration NUmber
  `curl -H 'Content-Type: application/json' -H 'X-TenantID:TEST2'  http://localhost:8083/student/getStudentByRegNum/G101 | python -m json.tool`

- To shut down the application
  `curl -H 'Content-Type: application/json' -H 'X-TenantID:TEST2'  http://localhost:8083/shutdown`

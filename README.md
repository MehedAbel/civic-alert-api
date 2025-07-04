# Read First
This is the backend code for the Civic Alert Project.
You can visit the UI/Frontend Repository [here](https://github.com/MehedAbel/civic-alert-ui).

# Civic Alert
## About
A web application that allows users to report different kinds of problems occuring in the public space (Infrastructure Problems, Accidents, Road Work etc..). 

## Key Features
- **Secure Login and Registration using JWT Tokens**
- **CRUD Operations for reports**

## Technology Stack
### Backend
- Java
- Spring Boot
- Spring Security

### Frontend
- React
- Tailwind CSS
- Leaflet

## To Run Application
**Prerequisites**: 
- JDK 21
- Maven
- PostgreSQL

1. Create Database
2. Open the application.yml and set your spring.datasource.url, username and password.
3. Run ```mvn clean install``` in the command line.
4. Run the CivicAlert.java from src/main/java
5. Check the UI Repository for the next steps [here](https://github.com/MehedAbel/civic-alert-ui).

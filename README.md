GALUTINIS

It will implement a back-end Spring Boot application and a front-end React application.

APPLICATIONS:

1. GALUTINIS-API:

Web Java backend application exposes a Rest API.

The application's secured endpoints can just be accessed if a valid JWT access token is provided.

galutinis-api stores its data in H2 database.
  
2. GALUTINIS-UI:
  
  In order to access the application `admin` must login using his/her `username` and `password`. All the requests coming from `front-end` to secured endpoints in `back-end` have the JWT access token. This token is generated when the `admin` logins.
  `user` does not have to login, all data might be accessed without authorization.

  
 
## Prerequisites

- [`npm`](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- [`Java 17+`](https://www.oracle.com/java/technologies/downloads/#java17)
- [`h2`](https://www.h2database.com/html/download.html)


## Start Environment

**front-end**

- In a terminal (it might be Visual Studio Code), you can download it [`here`](https://code.visualstudio.com/download), make sure you are inside `GALUTINIS-UI:` root folder;

- If you are running application for the first time; Run the following command to install npm 
   ```
   npm i
 
 or

   ```
   npm install
   ```

- Run the following command to start front end application 

   ```
   npm run dev

- **back-end**

  - Open java Compiler (it might be IntelliJ IDEA) you can download it [`here`](https://www.jetbrains.com/idea/download/?section=windows);
  - In the compiler open back-end application
  - run BackEndEplication

  

## Applications URLs

  | `back-end`     | `http://localhost:8080`      | `ADMIN`                                     |
  | `front-end`    | `http://localhost:3000/`     | `admin/admin`|

> **Note**: the credentials shown in the table are the ones already pre-defined.

- **Manual Endpoints Test using Swagger**
  
 http://localhost:8080/swagger-ui/index.html
    



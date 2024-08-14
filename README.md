spring_lms
Overview
This is a Spring Boot application named spring_lms, designed for managing a loyalty management system. It connects to a MariaDB database and supports JWT-based authentication.

Prerequisites
Java 17 or higher
Maven for building and running the project
MariaDB installed and running
Postman for testing the API endpoints (Optional)
Setup Instructions
1. Clone the Repository
bash
Copy code
git clone <repository-url>
cd spring_lms
2. Configure the Database
Ensure that MariaDB is installed and running. The application connects to a MariaDB instance with the following default properties:

URL: jdbc:mariadb://localhost/lms
Username: root
Password:no password
Create a database named lms in your MariaDB instance:

sql

CREATE DATABASE lms;

3. Application Configuration
The application properties are already configured in application.properties as follows:

properties
Copy code
spring.application.name=spring_lms
spring.datasource.url=jdbc:mariadb://localhost/lms
spring.datasource.username=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true

# JWT Section
jwt.private.key=private_key.der
jwt.public.key=public_key.der
jwt.expiration=3600000
4. Build and Run the Application
Use Maven to build and run the application:

bash
Copy code
mvn clean install
mvn spring-boot:run
5. Default User
On the first run, the application auto-generates JWT keys and creates a default user:

Username: osharif
Password: 123456789
6. Testing the API
You can interact with the API using the attached Postman workspace: New Collection.postman_collection.json.

7. Authentication
JWT-based authentication is implemented. After logging in using the default user credentials, you'll receive a token that must be included in the Authorization header for subsequent requests.

Example:

http
Copy code
Authorization: Bearer <your-token-here>
API Endpoints
For details on the available API endpoints, please refer to the attached Postman workspace or consult the application's documentation.

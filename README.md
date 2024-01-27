# spring-boot-patient-demo

Hi, 
This is a sample spring boot project in which i focused on the following components 

 - Caching in spring boot.
 - Used profile based configuration (application.properties, application-dev.properties, application-qa.properties, application-prod.properties).
 - Custom Exceptions are created and handled.
 - Sample Test one for controller, one for service , and we can test database also by using test containers for postgres.
 - dockerized the application and created compose file which can be used to run the whole application any where
 - To run the application if you have docker installaed already please navigate to root folder in your ccommand prompt and run the following command

        docker-compose up -d

 **sample requests are :**

**Create patient**   
**POST** - localhost:8080/patient  
Request body

{
    "patientName":"test",
    "mobileNumber": "7811223304",
    "email":"asaasasa@gmail.com"
}

**Get patient**
**GET** - localhost:8080/patient/id/{patientId}

**Get all patients**
**GET** - localhost:8080/patient

**Update patient**
**PUT** - localhost:8080/patient/id/{patientId}   
Request body

{
    "patientName":"testUpdateName",
    "mobileNumber": "7811223304",
    "email":"asaasasa@gmail.com"
}

**Delete patient**
**DELETE** - localhost:8080/patient/id/{patientId}
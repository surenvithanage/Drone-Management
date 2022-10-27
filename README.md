# Drone-Management
There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has: 
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Developed a service via REST API that allows clients to communicate with the drones. The specific communicaiton with the drone is outside the scope of this task. 

The developed service allows:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;

## Introduction
This assessment was completed using **Spring Boot version 2.7.5** and a **H2 InMemoryDB Database**. On the startup of the application the seeder will automatically insert records into the database. All the logs of the application will be written into to a output.log file which can be changed from the application.properties file. By default the server will be running on port **8080**. 

## Requirements
- Java 1.8
- IDE ( IntelliJ IDEA )
- Configure JAVA_HOME in Environment Variables

## Build/Run instruction
- Open the Terminal / Command Prompt in project **Root Directory** and execute **mvn spring-boot:run**
- Database is automatically seeded with the required data during the application start up
- Logs can be viewed from the **output.log** file
- Swagger can be accessed from **http://localhost:8080/swagger-ui.html#/** for testing all the endpoints


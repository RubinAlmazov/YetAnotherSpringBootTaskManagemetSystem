# YetAnotherSpringBootTaskManagementSystem

> A simple Spring Boot REST API for task management.


---

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)

---

## Overview
This repository contains a Spring Boot project named "YetAnotherSpringBootTaskManagementSystem." It is a task management application that allows users to create, read, update, and delete tasks via a RESTful API. The project demonstrates key Spring Boot concepts, including web development, data persistence with JPA and Hibernate, and clean layered architecture (controllers, services, repositories, entities). It serves as an educational example or starting point for building more advanced task trackers.

---

## Features
- Full CRUD operations for tasks.
- Task properties: title, description, status (e.g., TODO, IN_PROGRESS, DONE), priority, due date.
- Data persistence with an embedded database 
- RESTful API endpoints for task management.
- Layered architecture: controllers → services → repositories → entities.
- Basic configuration for easy running and testing.

---

## Technologies
- **Java** 
- **H2 Database** 
- **Spring Boot**
- **Hibernate**

---

## Prerequisites
- JDK 17 or higher installed.
- Apache Maven 3.6 or higher (or use the included Maven Wrapper mvnw).
- Git for cloning the repository.
---

## Installation

```bash
#1) Clone the repository
git clone https://github.com/RubinAlmazov/YetAnotherSpringBootTaskManagemetSystem.git
cd YetAnotherSpringBootTaskManagemetSystem

#2) Build via Maven Wrapper (if available)
./mvnw clean package

#3) or with the system Maven
mvn clean package

#4) Run the application
java -jar target/YetAnotherSpringBootTaskManagemetSystem-0.0.1-SNAPSHOT.jar

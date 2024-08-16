 BDD Test Automation Project

## Overview

This project is a BDD (Behavior-Driven Development) test automation framework designed to automate API testing for the endpoint `https://api.restful-api.dev/objects`. The project uses Java with Cucumber, JUnit, and Maven, and follows the Page Object Model (POM) design pattern. The framework supports scalability, configurability, reporting, and modularity.
## Prerequisites

- **Java 8** or higher installed on your machine
- **Maven** installed
- **Git** installed

## Setup Instructions

### 1. Clone the Repository
        ```bash
git clone https://github.com/your-username/bdd-test.git
cd bdd-test
2. Install Dependencies
        mvn clean install
3. Configure Project
        api.base.url=https://api.restful-api.dev/objects
4. Running the Tests
        mvn test
5. Test Reporting
        target/extentReports/extentReport.html
        
6. Scalability & Modularity
The project is designed for scalability and modularity:

Modularity: The framework follows the Page Object Model (POM) and organizes code into reusable components.
Scalability: The framework supports parallel test execution and can easily be extended to include more tests and functionalities.

7. Configurability
Configuration settings are externalized in the config.properties file, making it easy to switch environments or update settings without altering the code.

8. Build Pipeline
To set up a CI/CD pipeline for this project, you can use Jenkins, GitHub Actions, or any other CI.

# Booking API Test Automation

## Overview
This project contains automated API tests for the Restful-Booker platform using REST Assured and Cucumber. It validates various booking operations including creation, retrieval, update, and deletion of hotel bookings.

## Tech Stack
- Java 17
- Maven 3.9.6
- REST Assured
- Cucumber
- JUnit
- GitLab CI/CD

## Project Structure
```
booking-api-tests/
├── src/
│   └── test/
│       ├── java/
│       │   ├── config/
│       │   │   └── TestConfig.java
│       │   └── stepDefinitions/
│       │       ├── CommonSteps.java
│       │       ├── CreateBookingSteps.java
│       │       ├── GetBookingSteps.java
│       │       ├── GetBookingIdSteps.java
│       │       └── UpdateBookingSteps.java
│       └── resources/
│           └── features/
│               ├── CreateBooking.feature
│               ├── GetBooking.feature
│               ├── GetBookingID.feature
│               └── UpdateBooking.feature
├── .gitlab-ci.yml
├── dockerfile
├── pom.xml
└── README.md
```

## Features Tested
- Creating new bookings
- Retrieving booking details
- Updating existing bookings

## Prerequisites
- Java 17
- Maven 3.x
- JUnit 4.13.2
- REST Assured 5.5.0
- Cucumber 7.14.0
- Extent Reports (Cucumber 7 Adapter)
- Docker (optional)

## Setup
1. Clone the repository:
```bash
git clone https://gitlab.com/monikaqaengineer-group/bookingapi_test.git
cd bookingapi_test
```

2. Install dependencies:
```bash
mvn clean install
```

## Running Tests
### Local Execution
Run all tests:
```bash
mvn test
```

Run specific feature:
```bash
mvn test -Dcucumber.filter.tags="@your-tag"
```

### Docker Execution
```bash
docker build -t booking-api-tests .
docker run booking-api-tests
```

## CI/CD Pipeline
The project uses GitLab CI/CD with the following stages:
- Build: Compiles the code and creates artifacts
- Test: Runs the test suite and generates reports

Test reports are automatically collected and stored in GitLab.

## Test Reports
After test execution, reports can be found in:
- Cucumber reports: `target/cucumber-reports/`

## Contributing
1. Create a new branch
2. Make your changes
3. Run tests locally
4. Submit a merge request

## Project Structure Details
- `config/`: Contains test configuration and common utilities
- `stepDefinitions/`: Contains Cucumber step definitions
- `features/`: Contains Gherkin feature files
- `CommonSteps.java`: Shared step definitions used across features

## Maintainers
- Monika Agrawalla
# Federal Holidays API

This project is a RESTful API for managing federal holidays for countries. Currently, it supports Canada and the USA, but it is designed to be easily extendable to other countries.

## Features

- Add a new holiday
- Update an existing holiday
- List holidays by country

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (for simplicity)
- springdoc-openapi (for Swagger documentation)

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/rahul7a/holidays.git
    ```
2. Navigate to the project directory:
    ```bash
    cd holidays
    ```
3. Build the project using Maven:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

### API Endpoints

#### Get Holidays by Country

- **URL**: `/v1/holidays/{country}`
- **Method**: `GET`
- **Description**: Retrieve all holidays for a specific country.
- **Response Example**:
    ```json
    [
        {
            "id": 1,
            "country": "USA",
            "name": "New Year Eve",
            "onDay": "2020-01-01"
        }
    ]
    ```

#### Add a New Holiday

- **URL**: `/v1/holidays`
- **Method**: `POST`
- **Description**: Add a new holiday to the system.
- **Request Body Example**:
    ```json
    {
        "country": "USA",
        "name": "Christmas",
        "onDay": "2020-12-25"
    }
    ```
- **Response Example**:
    ```json
    {
        "id": 3,
        "country": "USA",
        "name": "Christmas",
        "onDay": "2020-12-25"
    }
    ```

#### Update an Existing Holiday

- **URL**: `/v1/holidays/{id}`
- **Method**: `PUT`
- **Description**: Update the details of an existing holiday.
- **Request Body Example**:
    ```json
    {
        "country": "USA",
        "name": "Christmas",
        "onDay": "2024-12-26"
    }
    ```
- **Response Example**:
    ```json
    {
        "id": 3,
        "country": "USA",
        "name": "Christmas",
        "onDay": "2024-12-26"
    }
    ```

### Swagger Documentation

The API documentation is available through Swagger. You can access it at: http://localhost:8085/swagger-ui/index.html


### Running Tests

To run the tests for the application, use the following Maven command: `mvn test`


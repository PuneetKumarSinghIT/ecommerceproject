# ecommerceproject
This repo is created for pushing the ecommerce project code to repo.

## Project Architecture

```
┌─────────────────┐
│   Controllers   │  ← REST API endpoints (ProductController)
├─────────────────┤
│    Services     │  ← Business logic (ProductService implementations)
├─────────────────┤
│   Repositories  │  ← Data access (JPA/Hibernate)
├─────────────────┤
│     Models      │  ← Entities (Product, Category, BaseModel)
├─────────────────┤
│      DTOs       │  ← Data transfer objects (ErrorDto, FakeStoreProductDto)
├─────────────────┤
│   Exceptions    │  ← Custom exceptions (ProductNotFoundException)
├─────────────────┤
│ Configurations  │  ← Spring configs (ApplicationConfiguration)
└─────────────────┘
```

### Technology Stack
- **Framework**: Spring Boot 3.5.11
- **Database**: MySQL (production), H2 (tests)
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Java Version**: 17
- **Additional**: Lombok, Spring Modulith

## Getting Started

### Prerequisites
- Java 17
- Maven 3.6+
- MySQL Server (for production)

### Setup
1. Clone the repository
2. Configure MySQL database in `src/main/resources/application.properties`
3. Run `./mvnw clean install`
4. Start the application: `./mvnw spring-boot:run`

### API Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| POST | `/product` | Create a new product | Product JSON | Product |
| GET | `/product/{id}` | Get product by ID | - | Product |

## Classes and Methods

### Controllers

#### ProductController
- **createProduct(Product product)**: Creates a new product via POST /product. Calls service to create product.
- **getProduct(long id)**: Retrieves product by ID via GET /product/{id}. Returns ResponseEntity<Product>.
- **updateProduct(Product product)**: Placeholder for updating products (not implemented).
- **deleteProduct(long id)**: Placeholder for deleting products (not implemented).
- **handleProductNotFoundException(Exception e)**: Exception handler for ProductNotFoundException. Returns ErrorDto with 404 status.

### Services

#### ProductService (Interface)
- **getSingleProduct(long productId)**: Abstract method to get a single product.
- **getAllProducts()**: Abstract method to get all products.
- **createProduct(long id, String title, String description, double price, String category, String imageUrl)**: Abstract method to create a product.

#### FakeStoreProductService (Implements ProductService)
- **getSingleProduct(long productId)**: Fetches product from FakeStore API. Throws ProductNotFoundException if not found.
- **getAllProducts()**: Returns empty list (not implemented).
- **createProduct(long id, String title, String description, double price, String category, String imageUrl)**: Creates product via FakeStore API POST.

#### DBStoreProductService (Implements ProductService)
- **getSingleProduct(long productId)**: Placeholder, returns null.
- **getAllProducts()**: Returns empty list.
- **createProduct(long id, String title, String description, double price, String category, String imageUrl)**: Placeholder, returns null.

### Models

#### BaseModel (Abstract)
- Inherits to Product and Category.
- Fields: id (auto-generated), createdAt, updatedAt, isDeleted.

#### Product (Entity)
- Fields: title, description, price, imageUrl, category (ManyToOne).
- toString() override.

#### Category (Entity)
- Fields: title.
- toString() override.

### DTOs

#### ErrorDto
- Fields: message.
- Used for error responses.

#### FakeStoreProductDto
- Fields: id, title, price, description, category, image.
- **getProduct()**: Converts DTO to Product model.

### Exceptions

#### ProductNotFoundException
- Extends Exception.
- Constructor takes message.

### Configurations

#### ApplicationConfiguration
- **restTemplate()**: Bean for RestTemplate.

### Main Application

#### EcommerseprojectApplication
- **main(String[] args)**: Spring Boot entry point.

## Extending the Project

### Adding New Endpoints
1. Add methods to ProductController with appropriate @RequestMapping annotations.
2. Implement business logic in service classes.
3. Update ProductService interface if needed.

### Adding New Entities
1. Create new model class extending BaseModel.
2. Add JPA annotations (@Entity, etc.).
3. Create corresponding service and controller.

### Switching Data Sources
- Currently uses FakeStore API. To use database:
  - Implement DBStoreProductService methods.
  - Configure @Service annotation to use DBStoreProductService instead.

### Testing
- Unit tests: Extend EcommerseprojectApplicationTests.
- Integration tests: Use @SpringBootTest with H2 database.

## Fixes Applied
The following fixes were made to get the project compiling and running correctly.

### 1. JPA annotation imports and persistence support
- Added `spring-boot-starter-data-jpa` to `pom.xml`.
- Fixed incorrect imports in model classes:
  - `BaseModel.java`: replaced invalid `JsonTypeInfo.Id` and `jakarta.annotation.Generated` imports with `jakarta.persistence.Id`, `jakarta.persistence.GeneratedValue`, `jakarta.persistence.GenerationType`, and `jakarta.persistence.MappedSuperclass`.
  - `Product.java` and `Category.java`: added `jakarta.persistence.Entity` and `jakarta.persistence.ManyToOne` imports as needed.
- Reason: The application uses JPA entities, so the persistence annotations must come from `jakarta.persistence` and the JPA starter dependency must be available.

### 2. Database configuration fix
- Corrected the MySQL datasource URL in `src/main/resources/application.properties` from `jdbc:mysql://localhost3306/ecommerce_database` to `jdbc:mysql://localhost:3306/ecommerce_database`.
- Corrected the datasource property key to `spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`.
- Updated the connection pool property to `spring.datasource.hikari.maximum-pool-size=30`.
- Reason: The original URL was malformed and the driver property key did not match Spring Boot Hikari configuration, causing application startup failure.

### 3. JDBC driver dependency
- Added `com.mysql:mysql-connector-j` dependency to `pom.xml`.
- Reason: Spring Boot requires the JDBC driver on the classpath to instantiate the MySQL datasource.

### 4. Test configuration / in-memory database
- Added `src/test/resources/application.properties` for test execution.
- Configured H2 in-memory database for tests using:
  - `spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
  - `spring.datasource.driver-class-name=org.h2.Driver`
  - `spring.jpa.hibernate.ddl-auto=create-drop`
- Reason: This allows `./mvnw test` to run without requiring a local MySQL server, so the Spring test context can start successfully.

## Verification
The following commands were verified successfully after the fixes:

- `./mvnw -q compile`
- `./mvnw -q test`

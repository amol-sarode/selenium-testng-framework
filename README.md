# Selenium TestNG Automation Framework

A maintainable, multi-layered test automation framework built using **Java 17, Selenium WebDriver, TestNG, REST Assured, JDBC, Maven, and Extent Reports**.

This framework combines **UI automation, REST API automation, and database testing** in a single Maven project. It follows a modular architecture that separates test cases, business workflows, page objects, API clients, database operations, configuration, test data, logging, and reporting.

The project demonstrates practical automation engineering concepts suitable for real-world testing and SDET interview preparation.

---

## 📌 Project Overview

The framework is designed to automate web application workflows, validate REST APIs, and perform database CRUD operations.

### Key Features

- UI automation using Selenium WebDriver and Page Object Model (POM).
- Business action layer for reusable UI workflows.
- REST API automation using REST Assured.
- POJO-based API request models and JSON test data.
- Database automation using JDBC and Microsoft SQL Server.
- CRUD operations for create, read, update, and delete scenarios.
- Centralized WebDriver management using `ThreadLocal<WebDriver>`.
- Page Object and Action Object managers.
- TestNG annotations, groups, data providers, and listeners.
- Retry mechanism for configured transient test failures.
- Centralized Extent HTML reporting.
- Log4j2-based logging.
- Failure screenshots for applicable UI test failures.
- Excel and JSON test data utilities.
- Centralized framework configuration.
- Maven-based build and dependency management.
- Separate TestNG suites for UI, API, and database tests.
- Reusable framework utilities and shared reporting components.

---

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Selenium WebDriver | Web UI automation |
| TestNG | Test execution, assertions, groups, listeners, and data providers |
| REST Assured | REST API automation |
| JDBC | Database connectivity |
| Microsoft SQL Server JDBC Driver | SQL Server connectivity |
| Maven | Build and dependency management |
| WebDriverManager | Browser driver management |
| Extent Reports | HTML execution reporting |
| Log4j2 | Framework logging |
| Apache POI | Excel test-data handling |
| Jackson | JSON serialization and deserialization |
| JSON | API payloads and external test data |
| Git and GitHub | Version control and source-code hosting |

---

## 🏗️ Framework Architecture

The framework uses separate layers for UI, API, and database automation, with shared infrastructure for reporting, configuration, logging, and test execution.

```text
                   TestNG Test Layer
                           |
             +-------------+-------------+
             |             |             |
             v             v             v
         UI Tests       API Tests       DB Tests
             |             |             |
             v             v             v
       Action Layer    API Client    Repository Layer
             |             |             |
             v             v             v
        Page Objects   REST Assured      JDBC
             |             |             |
             v             v             v
        WebDriver     REST API       SQL Server

       Shared Framework Services
       -------------------------
       Configuration | Logging
       Extent Reports | Test Data
       Listeners | Utilities
```

### Layer Responsibilities

**Test Layer**

- Defines test scenarios and business expectations.
- Uses TestNG annotations, groups, and data providers.
- Performs high-level assertions.
- Executes UI, API, and database test cases through their respective layers.

**UI Action Layer**

- Implements reusable business workflows.
- Combines multiple page operations.
- Keeps test cases readable.
- Creates logical reporting nodes where required.

**Page Object Layer**

- Stores page-specific locators.
- Performs UI interactions.
- Returns relevant UI state and data.
- Avoids business-level assertions and test-flow responsibilities.

**API Layer**

- Centralizes API specifications and reusable request configuration.
- Uses API clients to execute endpoint operations.
- Supports POJO-based request models and external JSON payloads.
- Keeps API request execution separate from TestNG assertions.

**Database Layer**

- Centralizes database connectivity.
- Encapsulates reusable SQL operations in repository classes.
- Supports database CRUD scenarios.
- Separates SQL execution from test assertions.

**Shared Framework Services**

- Centralized configuration and constants.
- Logging and Extent reporting.
- Test listeners and retry handling.
- Reusable Excel, JSON, wait, and other utilities.

---

## 📂 Project Structure

The following structure reflects the current project organization.

```text
selenium-testng-framework/
│
├── pom.xml
├── README.md
├── .gitignore
│
├── src/
│   ├── main/
│   │   ├── java/com/amol/automation/
│   │   │   ├── actions/
│   │   │   │   ├── DatabaseActions.java
│   │   │   │   ├── EndToEndActions.java
│   │   │   │   ├── LoginActions.java
│   │   │   │   └── ProductActions.java
│   │   │   │
│   │   │   ├── api/
│   │   │   │   ├── ApiSpecification.java
│   │   │   │   ├── BookingApiClient.java
│   │   │   │   └── BookingRequest.java
│   │   │   │
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java
│   │   │   │
│   │   │   ├── constants/
│   │   │   │   └── FrameworkConstants.java
│   │   │   │
│   │   │   ├── database/
│   │   │   │   ├── CustomerRepository.java
│   │   │   │   └── DatabaseConnectionManager.java
│   │   │   │
│   │   │   ├── driver/
│   │   │   │   ├── DriverFactory.java
│   │   │   │   └── DriverManager.java
│   │   │   │
│   │   │   ├── enums/
│   │   │   │   ├── BrowserType.java
│   │   │   │   └── Environment.java
│   │   │   │
│   │   │   ├── factory/
│   │   │   │   ├── ActionObjectManager.java
│   │   │   │   └── PageObjectManager.java
│   │   │   │
│   │   │   ├── listeners/
│   │   │   │   ├── RetryAnalyzer.java
│   │   │   │   └── TestListener.java
│   │   │   │
│   │   │   ├── pages/
│   │   │   │   ├── CartPage.java
│   │   │   │   ├── CheckoutCompletePage.java
│   │   │   │   ├── CheckoutOverviewPage.java
│   │   │   │   ├── CheckoutPage.java
│   │   │   │   ├── HomePage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── ProductPage.java
│   │   │   │
│   │   │   ├── reports/
│   │   │   │   └── ExtentReportManager.java
│   │   │   │
│   │   │   └── utils/
│   │   │       ├── ConfigReader.java
│   │   │       ├── DateUtil.java
│   │   │       ├── ElementUtils.java
│   │   │       ├── ExcelUtils.java
│   │   │       ├── JavaScriptUtil.java
│   │   │       ├── JsonUtils.java
│   │   │       ├── LoggerUtils.java
│   │   │       ├── PayloadUtils.java
│   │   │       ├── ScreenshotUtils.java
│   │   │       └── WaitUtils.java
│   │   │
│   │   └── resources/
│   │
│   └── test/
│       ├── java/com/amol/automation/
│       │   ├── dataproviders/
│       │   │   └── TestDataProvider.java
│       │   │
│       │   └── tests/
│       │       ├── CustomerCrudTest.java
│       │       ├── DatabaseConnectionTest.java
│       │       ├── EndToEndTest.java
│       │       ├── LoginTest.java
│       │       ├── ProductTest.java
│       │       └── api/
│       │           └── BookingTest.java
│       │
│       └── resources/
│           ├── config.properties
│           ├── log4j2.xml
│           │
│           ├── testdata/
│           │   ├── booking-data.json
│           │   ├── LoginData.xlsx
│           │   ├── ProductData.json
│           │   └── Users.json
│           │
│           └── testng/
│               ├── testng-api.xml
│               ├── testng-db.xml
│               └── testng-ui.xml
│
└── Generated files and IDE metadata
    ├── logs/
    ├── reports/
    ├── screenshots/
    ├── target/
    └── test-output/
```

*Note: Generated directories and IDE metadata may exist locally but should generally be excluded from version control.*

---

## 🧩 Core Components

### 1. UI Automation

**DriverFactory**

Creates and configures WebDriver instances. Browser-specific options can include headless execution and timeout settings.

**DriverManager**

Manages WebDriver instances using `ThreadLocal<WebDriver>` to isolate driver references across execution threads.

**PageObjectManager**

Centralizes the creation and access of page objects.

**ActionObjectManager**

Provides centralized access to reusable business action classes.

**BaseTest**

Provides common test setup and teardown for UI test execution, including browser initialization and cleanup.

**Page Objects**

Encapsulate page locators and UI operations for login, products, cart, checkout, and order completion.

### 2. API Automation

The API layer uses REST Assured to interact with REST endpoints.

| Component | Responsibility |
|---|---|
| `ApiSpecification.java` | Centralized API request specification |
| `BookingApiClient.java` | Reusable booking API operations |
| `BookingRequest.java` | POJO representation of a booking request |
| `PayloadUtils.java` | Loads and maps JSON test data |
| `BookingTest.java` | Executes API scenarios and validates expected results |

API test data is maintained separately from test implementation, allowing payloads to be reused and maintained independently.

### 3. Database Automation

The database layer uses JDBC to connect to Microsoft SQL Server.

| Component | Responsibility |
|---|---|
| `DatabaseConnectionManager.java` | Creates and manages database connections |
| `CustomerRepository.java` | Encapsulates customer database operations |
| `DatabaseActions.java` | Provides an optional reusable workflow layer for database actions |
| `DatabaseConnectionTest.java` | Validates database connectivity |
| `CustomerCrudTest.java` | Tests customer CRUD scenarios |

Database operations are kept separate from TestNG assertions so that SQL execution and test validation remain independently maintainable.

### 4. Reporting and Logging

**TestListener**

Handles TestNG lifecycle events and integrates test execution with reporting.

**ExtentReportManager**

Manages the Extent Reports instance, test entries, reporting nodes, status logging, and report flushing.

**LoggerUtils**

Provides centralized Log4j2 logger access.

**ScreenshotUtils**

Provides screenshot functionality for applicable UI failures.

Reporting is centralized to avoid duplicating lifecycle and reporting logic in individual test classes.

---

## 📊 Extent Reporting

The framework uses Extent Reports to generate HTML execution reports.

The reporting hierarchy is designed around a parent test and logical child nodes:

```text
Test
 └── Node
      ├── INFO
      ├── PASS
      └── FAIL
```

Example TestNG method:

```java
@Test(
    description = "Verify product add to cart with valid user",
    groups = {"smoke", "regression"}
)
public void verifyAddToCart() {
    // Execute workflow and validate expected behavior
}
```

The listener creates the parent test entry using TestNG metadata. Action or API workflow components can create logical reporting nodes where required.

### Reporting Responsibilities

| Component | Responsibility |
|---|---|
| `TestListener` | Test lifecycle events and final execution status |
| `ExtentReportManager` | Extent report and node management |
| `BaseTest` | Common UI test lifecycle |
| `ScreenshotUtils` | Screenshot capture |
| `LoggerUtils` | Framework logging |

---

## 🔄 Retry Mechanism

The framework includes a TestNG retry mechanism through `RetryAnalyzer.java`.

Retry can be useful for configured transient failures, such as:

- Temporary browser issues.
- Intermittent network failures.
- Timing or synchronization problems.

Retries should be limited and monitored carefully. They should not hide application defects or replace proper synchronization and root-cause analysis.

---

## 🧪 Test Data Management

External test data is maintained under:

```text
src/test/resources/testdata/
```

### Excel Test Data

`LoginData.xlsx` stores login test data.

`ExcelUtils.java` provides reusable Excel-reading functionality using Apache POI.

### JSON Test Data

JSON files support UI and API test data, including:

- `booking-data.json`
- `ProductData.json`
- `Users.json`

`JsonUtils.java` supports JSON utilities, while `PayloadUtils.java` supports loading and mapping API payloads to Java request models.

Separating test data from test implementation makes test scenarios easier to update and reuse.

---

## ⚙️ Configuration Management

Framework configuration is maintained in:

```text
src/test/resources/config.properties
```

The configuration file can hold non-secret settings such as:

- Application URL.
- Browser selection.
- Execution mode.
- Environment.
- Headless execution.
- Wait and page-load timeouts.
- API base URL and timeout settings.
- Database connection properties.

`ConfigReader.java` provides centralized configuration access.

Sensitive credentials should be supplied through environment variables or a secure secrets mechanism rather than committed to the repository.

---

## 🌐 Browser Support

The UI framework is designed to support:

- Google Chrome.
- Mozilla Firefox.
- Microsoft Edge.

Browser selection is controlled through framework configuration and the browser enum.

Headless execution can be enabled when supported by the configured browser options.

---

## 🧵 Thread Safety and Parallel Execution

`DriverManager` uses `ThreadLocal<WebDriver>` to maintain a separate driver reference for each execution thread.

Conceptually:

```text
Thread 1 → WebDriver 1
Thread 2 → WebDriver 2
Thread 3 → WebDriver 3
```

This reduces the risk of different tests accidentally sharing a WebDriver instance.

Actual parallel execution should be enabled only after validating thread safety across reporting, object managers, test data, database operations, and shared resources.

---

## 🧪 Test Scenarios

### UI Automation

**Login tests**

- Validate login-related scenarios.
- Use external Excel test data where configured.

**Product tests**

- Validate product-related workflows, including adding products to the cart.

**End-to-end tests**

- Validate business flows across login, product selection, cart, and checkout pages.

### API Automation

**Booking API tests**

- Create a booking using an external JSON payload.
- Validate HTTP status and relevant response fields.
- Extend coverage with read, update, delete, and negative scenarios as those tests are implemented and verified.

### Database Automation

**Database connection test**

- Validate connectivity to the configured SQL Server database.

**Customer CRUD tests**

- Exercise customer create, read, update, and delete operations.
- Validate expected database results.

Database tests should use isolated test records and clean up test-created data where appropriate.

---

## 🚀 Getting Started

### Prerequisites

Install the following:

- Java JDK 17 or later.
- Apache Maven.
- Git.
- A supported browser for UI automation.
- Access to the configured API for API tests.
- SQL Server and valid connection settings for database tests.

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

### Clone the Repository

```bash
git clone https://github.com/amol-sarode/selenium-testng-framework.git
cd selenium-testng-framework
```

### Build the Project

```bash
mvn clean test-compile
```

This compiles the project and test sources without requiring all test suites to run.

### Run the Configured Maven Test Suite

```bash
mvn test
```

The default suite is determined by the Maven Surefire configuration in `pom.xml`.

---

## ▶️ Run Individual TestNG Suites

The framework maintains separate suite files under:

```text
src/test/resources/testng/
```

### Run UI Tests

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng/testng-ui.xml
```

### Run API Tests

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng/testng-api.xml
```

### Run Database Tests

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng/testng-db.xml
```

These commands assume `pom.xml` is configured to pass the `suiteXmlFile` system property to TestNG. If Surefire uses a hardcoded suite path instead, update that configuration or run the appropriate suite through the IDE.

---

## 🏷️ TestNG Groups

TestNG groups can be used to organize scenarios, for example:

```java
@Test(
    description = "Verify product add to cart with valid user",
    groups = {"smoke", "regression"}
)
public void verifyAddToCart() {
    // Test implementation
}
```

Run a group using Maven:

```bash
mvn test -Dgroups=smoke
```

Or:

```bash
mvn test -Dgroups=regression
```

Group execution depends on how Maven Surefire and the selected TestNG suite are configured. Confirm that the desired suite does not override or exclude the requested groups.

---

## 📋 Logs, Reports, and Screenshots

The framework can generate execution artifacts in directories such as:

```text
logs/
reports/
screenshots/
test-output/
target/
```

- **Logs:** Framework execution and diagnostic information.
- **Reports:** Extent HTML reports.
- **Screenshots:** Failure evidence for applicable UI tests.
- **Test output:** TestNG execution artifacts.
- **Target:** Maven build output and compiled classes.

These generated artifacts should generally be excluded from Git using `.gitignore`, while configuration templates and useful documentation remain version-controlled.

---

## 🔐 Test Data and Security

Follow these practices when configuring the framework:

- Do not hardcode real passwords, API tokens, or database credentials in test classes.
- Do not commit secrets to GitHub.
- Use environment variables or a secure CI/CD secrets store for sensitive settings.
- Keep local credentials and machine-specific configuration out of version control.
- Avoid logging passwords, access tokens, or other sensitive request values.
- Use dedicated test data and clean up database records created by tests.

---

## 📈 Future Enhancements

Potential enhancements, depending on project requirements, include:

- CI/CD integration with Jenkins or GitHub Actions.
- Docker-based execution.
- Selenium Grid or cloud browser execution.
- Expanded API CRUD and negative testing coverage.
- API schema validation and reusable response validation.
- Stronger database test isolation and cleanup.
- Environment-specific configuration profiles.
- Improved parallel-execution validation.
- AI-assisted failure analysis in a separate integration or module.
- Enhanced reporting and execution dashboards.

These are planned enhancements rather than claims that every capability is already implemented.

---

## 🎯 Framework Design Principles

The framework emphasizes:

- **Single responsibility:** Each class has a clear role.
- **Separation of concerns:** Test logic, UI workflows, API calls, database operations, and shared services are separated.
- **Reusability:** Common functionality is centralized.
- **Maintainability:** Changes to locators, configuration, API requests, or SQL operations can be isolated.
- **Readable tests:** Test cases focus on scenarios and expected results.
- **Centralized reporting:** Reporting lifecycle management is shared across test execution.
- **Secure configuration:** Credentials are kept separate from source code.
- **Practical scalability:** New pages, endpoints, database operations, and tests can be added without redesigning the entire project.

---

## 👨‍💻 Author

**Amol Sarode**

Automation Engineer | SDET

GitHub: https://github.com/amol-sarode

## ⭐ Repository

https://github.com/amol-sarode/selenium-testng-framework

If you find this project useful, consider giving the repository a star.
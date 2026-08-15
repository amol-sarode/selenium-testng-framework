# Selenium TestNG Automation Framework

A scalable and maintainable **UI test automation framework** built using **Java, Selenium WebDriver, TestNG, Maven, and Extent Reports**.

The framework follows a layered architecture that separates **test cases, business actions, page objects, driver management, reporting, test data, and utilities**, making the project easier to maintain, extend, debug, and present in an enterprise automation environment.

---

## 📌 Project Overview

This framework is designed for automating web applications using Selenium WebDriver with TestNG.

It provides:

* Page Object Model (POM)
* Action/business layer
* Centralized WebDriver management
* Thread-safe driver handling
* TestNG test execution
* TestNG groups
* Retry mechanism
* External test data support
* Excel and JSON test data utilities
* Centralized configuration
* Logging using Log4j2
* Extent HTML reporting
* Failure screenshots
* Multi-browser support
* Headless execution support
* Maven-based build management
* Parallel-execution-ready architecture

---

## 🛠️ Technology Stack

| Technology         | Purpose                         |
| ------------------ | ------------------------------- |
| Java 17            | Programming language            |
| Selenium WebDriver | Web UI automation               |
| TestNG             | Test execution and assertions   |
| Maven              | Dependency and build management |
| WebDriverManager   | Browser driver management       |
| Extent Reports     | HTML execution reporting        |
| Log4j2             | Application/framework logging   |
| Apache POI         | Excel test-data handling        |
| JSON               | Test-data management            |
| Git/GitHub         | Version control                 |

---

## 🏗️ Framework Architecture

The framework follows a layered architecture:

```text
                    ┌──────────────────────┐
                    │      Test Layer      │
                    │   TestNG Test Cases  │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │    Action Layer      │
                    │ Business Workflows   │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │     Page Layer       │
                    │   Page Objects       │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   Selenium WebDriver │
                    └──────────────────────┘
```

### Layer Responsibilities

**Test Layer**

Responsible for:

* Defining test scenarios
* TestNG annotations
* Test groups
* Test data invocation
* High-level assertions

**Action Layer**

Responsible for:

* Business workflows
* Combining multiple page operations
* Keeping test cases readable
* Creating logical reporting nodes where required

**Page Layer**

Responsible for:

* Locators
* Web element interactions
* Page-specific UI operations
* Returning UI state/data

Page Objects do not contain test assertions or business-level test flow.

**Driver Layer**

Responsible for:

* WebDriver creation
* Browser configuration
* Thread-safe driver management
* Browser lifecycle

---

## 📂 Project Structure

```text
selenium-testng-framework/
│
├── src/
│   │
│   ├── main/
│   │   ├── java/
│   │   │   └── com/amol/automation/
│   │   │       │
│   │   │       ├── actions/
│   │   │       │   ├── EndToEndActions.java
│   │   │       │   ├── LoginActions.java
│   │   │       │   └── ProductActions.java
│   │   │       │
│   │   │       ├── base/
│   │   │       │   └── BaseTest.java
│   │   │       │
│   │   │       ├── constants/
│   │   │       │   └── FrameworkConstants.java
│   │   │       │
│   │   │       ├── driver/
│   │   │       │   ├── DriverFactory.java
│   │   │       │   └── DriverManager.java
│   │   │       │
│   │   │       ├── enums/
│   │   │       │   ├── BrowserType.java
│   │   │       │   └── Environment.java
│   │   │       │
│   │   │       ├── factory/
│   │   │       │   ├── ActionObjectManager.java
│   │   │       │   └── PageObjectManager.java
│   │   │       │
│   │   │       ├── listeners/
│   │   │       │   ├── RetryAnalyzer.java
│   │   │       │   └── TestListener.java
│   │   │       │
│   │   │       ├── pages/
│   │   │       │   ├── CartPage.java
│   │   │       │   ├── CheckoutCompletePage.java
│   │   │       │   ├── CheckoutOverviewPage.java
│   │   │       │   ├── CheckoutPage.java
│   │   │       │   ├── HomePage.java
│   │   │       │   ├── LoginPage.java
│   │   │       │   └── ProductPage.java
│   │   │       │
│   │   │       ├── reports/
│   │   │       │   └── ExtentReportManager.java
│   │   │       │
│   │   │       └── utils/
│   │   │           ├── ConfigReader.java
│   │   │           ├── DateUtil.java
│   │   │           ├── ElementUtils.java
│   │   │           ├── ExcelUtils.java
│   │   │           ├── JavaScriptUtil.java
│   │   │           ├── JsonUtils.java
│   │   │           ├── LoggerUtils.java
│   │   │           ├── ScreenshotUtils.java
│   │   │           └── WaitUtils.java
│   │   │
│   │   └── resources/
│   │       ├── testdata/
│   │       │   ├── LoginData.xlsx
│   │       │   ├── ProductData.json
│   │       │   └── Users.json
│   │       │
│   │       ├── config.properties
│   │       └── log4j2.xml
│   │
│   └── test/
│       └── java/
│           └── com/amol/automation/
│               ├── dataprovider/
│               │   └── TestDataProvider.java
│               │
│               └── tests/
│                   ├── EndToEndTest.java
│                   ├── LoginTest.java
│                   └── ProductTest.java
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🧩 Core Components

### DriverFactory

Creates and configures WebDriver instances.

Supported browsers include:

* Chrome
* Firefox
* Edge

Browser configuration can include:

* Headless execution
* Page-load timeout
* Implicit wait
* Browser-specific options

### DriverManager

Uses `ThreadLocal<WebDriver>` to provide thread-safe driver management and support parallel execution.

### PageObjectManager

Centralizes Page Object creation and lifecycle management.

### ActionObjectManager

Centralizes Action object creation and provides a consistent way for test classes to access business workflows.

### BaseTest

Provides common test lifecycle management such as:

```text
Before Suite
     ↓
Initialize reporting
     ↓
Before Method
     ↓
Initialize WebDriver
     ↓
Execute Test
     ↓
Capture failure information
     ↓
Quit WebDriver
     ↓
After Suite
     ↓
Flush report
```

---

## 📊 Reporting

The framework uses **Extent Reports** for execution reporting.

The reporting architecture is designed around:

```text
Test
 └── Node
      ├── INFO
      ├── PASS
      └── FAIL
```

The TestNG listener is responsible for creating the parent test automatically using the TestNG test description.

Example:

```java
@Test(description = "Verify product add to cart with valid user")
public void verifyAddToCart() {
    // test implementation
}
```

The reporting layer can create the corresponding test automatically instead of requiring every test class to manually create an Extent test.

### Reporting Responsibilities

| Component             | Responsibility                      |
| --------------------- | ----------------------------------- |
| `TestListener`        | Test lifecycle and reporting events |
| `ExtentReportManager` | Extent report management            |
| `BaseTest`            | Framework lifecycle                 |
| `ScreenshotUtils`     | Failure screenshots                 |

This keeps reporting implementation centralized instead of duplicating it across test classes.

---

## 🔄 Retry Mechanism

The framework contains a TestNG retry mechanism through:

```text
RetryAnalyzer.java
```

The retry mechanism can be used for transient failures such as:

* Temporary browser issues
* Network instability
* Application timing issues
* Unexpected UI synchronization problems

Retry should be used carefully and should not hide genuine application defects.

---

## 🧪 Test Data Management

The framework supports external test data.

### Excel

Excel data is maintained using:

```text
LoginData.xlsx
```

Excel operations are handled through:

```text
ExcelUtils.java
```

### JSON

JSON-based test data is supported through:

```text
ProductData.json
Users.json
```

JSON operations are handled through:

```text
JsonUtils.java
```

This keeps test data separate from test implementation.

---

## ⚙️ Configuration

Framework configuration is maintained in:

```text
src/main/resources/config.properties
```

Configuration can be used for values such as:

```text
browser
environment
headless
application URL
timeouts
```

Centralized configuration avoids hardcoding environment-specific values throughout the framework.

---

## 🌐 Browser Support

The framework supports:

```text
Chrome
Firefox
Edge
```

Browser selection is controlled through the framework configuration/enums.

The framework also supports headless execution where required.

---

## 🧵 Parallel Execution

The framework is designed with parallel execution in mind.

`DriverManager` uses:

```java
ThreadLocal<WebDriver>
```

This allows each TestNG execution thread to maintain its own WebDriver instance.

Conceptually:

```text
Thread 1 → WebDriver 1
Thread 2 → WebDriver 2
Thread 3 → WebDriver 3
```

This prevents WebDriver instances from being shared incorrectly between parallel tests.

---

## 📝 TestNG

The framework uses TestNG for:

* Test execution
* Assertions
* Test lifecycle
* Groups
* Data providers
* Listeners
* Retry mechanism
* Parallel execution

TestNG configuration is maintained in:

```text
src/main/resources/testng/testng.xml
```

Example groups:

```java
@Test(
    description = "Verify product add to cart with valid user",
    groups = {"smoke", "regression"}
)
```

---

## 🧪 Test Scenarios

The current framework contains test scenarios covering:

### Login

```text
LoginTest.java
```

Validates login-related functionality.

### Product

```text
ProductTest.java
```

Validates product-related functionality such as adding products to the cart.

### End-to-End

```text
EndToEndTest.java
```

Covers complete business flows across multiple application pages.

---

## 🧱 Design Principles

The framework follows these principles:

### Single Responsibility

Each component has a specific responsibility.

### Separation of Concerns

Test logic, business actions, UI interactions, reporting, driver management, and utilities are separated.

### Reusability

Common functionality is centralized in reusable managers and utilities.

### Maintainability

Changes to locators, browser configuration, reporting, or utilities can be made without unnecessarily modifying test cases.

### Scalability

The architecture is designed to allow additional:

* Pages
* Actions
* Tests
* Browsers
* Environments
* Test data
* Reporting features

without restructuring the entire framework.

---

## 🚀 How to Run the Framework

### Prerequisites

Install:

* Java 17+
* Maven
* Git
* Chrome/Firefox/Edge as required

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

---

## 📥 Clone the Repository

```bash
git clone https://github.com/amol-sarode/selenium-testng-framework.git
```

Navigate to the project:

```bash
cd selenium-testng-framework
```

---

## 📦 Install Dependencies

```bash
mvn clean install
```

---

## ▶️ Run Tests

Run the complete test suite:

```bash
mvn test
```

Run the TestNG suite:

```bash
mvn test -DsuiteXmlFile=src/main/resources/testng/testng.xml
```

---

## 🏷️ Run TestNG Groups

For example:

```bash
mvn test -Dgroups=smoke
```

or:

```bash
mvn test -Dgroups=regression
```

---

## 📋 Logs, Reports and Screenshots

Execution-generated artifacts are intentionally **not committed to GitHub**.

The framework can generate:

```text
logs/
reports/
screenshots/
test-output/
target/
```

These directories are ignored using `.gitignore`.

This keeps the GitHub repository clean and prevents generated execution artifacts from being version-controlled.

---

## 🔐 Test Data & Security

Credentials and environment-specific information should not be hardcoded directly into test classes.

Sensitive information should preferably be provided through:

* Environment variables
* External configuration
* Secure CI/CD secrets
* Local configuration excluded from Git

Do not commit real passwords, API keys, tokens, or other secrets to GitHub.

---

## 📈 Future Enhancements

Potential improvements planned for future versions include:

* CI/CD integration
* Docker-based execution
* Selenium Grid / cloud execution
* Advanced parallel execution
* Environment-specific configuration
* Better test-data abstraction
* API automation integration
* Database validation utilities
* AI-assisted test automation
* Automatic failure analysis
* Enhanced reporting dashboards

---

## 🎯 Framework Goals

The primary goals of this framework are:

```text
Maintainability
      +
Reusability
      +
Scalability
      +
Readable Tests
      +
Centralized Reporting
      +
Thread-Safe Execution
      +
Clean Architecture
```

The framework is designed to demonstrate practical automation-engineering concepts rather than simply automate individual test cases.

---

## 👨‍💻 Author

**Amol Sarode**

Automation Engineer

### GitHub

[https://github.com/amol-sarode](https://github.com/amol-sarode)

---

## ⭐ Repository

If you find this framework useful, consider giving the repository a star.

**Repository:**
[https://github.com/amol-sarode/selenium-testng-framework](https://github.com/amol-sarode/selenium-testng-framework)

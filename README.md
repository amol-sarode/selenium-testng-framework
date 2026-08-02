# Selenium TestNG Automation Framework

## Overview

This is an enterprise-level Selenium Automation Framework built using **Java, Selenium WebDriver, TestNG, Maven, Page Object Model (POM), Extent Reports, and Log4j2**.

The framework follows industry-standard automation practices and supports:

- Page Object Model architecture
- Parallel test execution
- Thread-safe WebDriver management
- Data-driven testing
- Cross-browser execution
- Automated HTML reporting
- Screenshot capture on failure
- Centralized logging
- Reusable utility components
- External configuration management


---

# Technology Stack

| Technology | Version |
|------------|---------|
| Java | 17 |
| Selenium WebDriver | 4.20.0 |
| TestNG | 7.10.2 |
| Maven | 3.x |
| Extent Reports | 5.1.1 |
| Log4j2 | 2.23.1 |
| Apache POI | 5.2.5 |
| Jackson JSON | 2.17.0 |
| WebDriverManager | 5.8.0 |


---

# Framework Architecture


```
Test Layer
     |
     |
Page Object Layer
     |
     |
Base Layer
     |
     |
Driver Management Layer
     |
     |
Utility Layer
     |
     |
Reporting & Logging Layer
```


---

# Project Structure


```
selenium-testng-framework

src/main/java

com.amol.automation

│
├── base
│   ├── BaseTest.java
│   └── BasePage.java
│
├── driver
│   ├── DriverFactory.java
│   └── DriverManager.java
│
├── factory
│   └── PageObjectManager.java
│
├── pages
│   ├── LoginPage.java
│   ├── HomePage.java
│   ├── ProductPage.java
│   ├── CartPage.java
│   ├── CheckoutPage.java
│   ├── CheckoutOverviewPage.java
│   ├── CheckoutCompletePage.java
│   └── SignupPage.java
│
├── listeners
│   └── TestListener.java
│
├── reports
│   └── ExtentReportManager.java
│
├── utils
│   ├── ConfigReader.java
│   ├── ExcelUtils.java
│   ├── JsonUtils.java
│   ├── ScreenshotUtils.java
│   ├── DateUtil.java
│   ├── RandomDataUtil.java
│   ├── JavaScriptUtil.java
│   ├── WaitUtils.java
│   └── LoggerUtils.java
│
├── constants
│   └── FrameworkConstants.java
│
├── enums
│   ├── BrowserType.java
│   └── Environment.java
│
└── exceptions
    └── FrameworkException.java



src/test/java

com.amol.automation.tests

│
├── LoginTest.java
├── ProductTest.java
└── EndToEndTest.java

```


---

# Framework Features


## 1. Page Object Model (POM)


Each application page is represented as an independent Java class.

Benefits:

- Maintainable code structure
- Reusable page methods
- Separation of UI and test logic
- Easy debugging


Example:


```
LoginTest

      |

LoginPage

      |

Web Elements
```


---

# 2. Thread Safe WebDriver Management


The framework uses:


```
ThreadLocal<WebDriver>
```


Benefits:

- Supports parallel execution
- Each test gets an independent browser instance
- Avoids driver conflicts
- Safe multi-thread execution


Flow:


```
Test Execution

        |

BaseTest

        |

DriverFactory

        |

DriverManager

        |

Browser Instance
```


---

# 3. Driver Factory Pattern


Supported browsers:


```
Chrome

Firefox

Edge
```


Browser selection is controlled through:


```
config.properties
```


Example:


```
browser=chrome
```


---

# 4. Data Driven Testing


The framework supports external test data using:


## Excel Data


```
LoginData.xlsx


username          password

standard_user     secret_sauce

locked_user       secret_sauce

```


## JSON Data


```
Users.json


{
 "validUser":
 {
   "username":"standard_user",
   "password":"secret_sauce"
 }
}

```


Benefits:

- No hard-coded test data
- Easy test data maintenance
- Multiple test scenarios support


---

# 5. TestNG Implementation


Implemented TestNG features:


- Test Annotations
- DataProvider
- Listeners
- Groups
- Parallel execution
- Suite execution


Current test classes:


```
LoginTest.java

ProductTest.java

EndToEndTest.java
```


---

# Parallel Execution


TestNG supports parallel execution using:


```
parallel="classes"

thread-count="3"
```


Execution example:


```
Thread 1 --> LoginTest

Thread 2 --> ProductTest

Thread 3 --> EndToEndTest

```


---

# Reporting Framework


The framework uses:


## Extent Reports


Features:


- HTML execution report
- Pass and fail status
- Exception details
- Screenshot attachment
- Execution information


Report location:


```
reports/

AutomationReport_timestamp.html
```


---

# Screenshot Capture


Failed test cases automatically capture screenshots.


Flow:


```
Test Failure

       |

TestListener

       |

ScreenshotUtils

       |

Screenshot Attached To Extent Report

```


Location:


```
screenshots/
```


---

# Logging Framework


The framework uses:


## Log4j2


Logs are generated for:


- Browser initialization
- URL navigation
- Element actions
- Test execution
- Errors


Location:


```
logs/

Automation.log
```


Example:


```
INFO  Initializing Chrome Browser

INFO  Opening Application URL

INFO  Clicking Login Button

ERROR Test Failed

```


---

# Configuration Management


Application configuration is maintained externally.


File:


```
src/main/resources/config.properties
```


Example:


```
browser=chrome

headless=false

app.url=https://www.saucedemo.com

implicit.wait=10

page.load.timeout=30

```


Benefits:


- Easy environment switching
- No hard-coded configuration
- Simple maintenance


---

# Maven Execution


Run complete framework:


```bash
mvn clean test
```


Run TestNG suite:


```bash
mvn test -DsuiteXmlFile=testng.xml
```


---

# Design Patterns Used


The framework implements:


## Page Object Model

Used for page interaction management.


## Factory Pattern

Used for driver and page object creation.


## Singleton Pattern

Used for configuration management.


## ThreadLocal Pattern

Used for parallel browser execution.


## Strategy Pattern

Used for browser selection.


---

# Current Automation Flow


```
Login

 |

Verify Home Page

 |

Open Product Page

 |

Add Product To Cart

 |

Checkout Information

 |

Checkout Overview

 |

Complete Order

```


---

# Best Practices Followed


✔ Clean project structure  
✔ Reusable components  
✔ Thread-safe WebDriver management  
✔ Explicit waits  
✔ External test data management  
✔ Centralized configuration  
✔ Logging implementation  
✔ Extent reporting  
✔ Screenshot capture  
✔ Maven dependency management  


---

# Future Enhancements


Possible improvements:


- Jenkins CI/CD integration
- Docker execution
- Selenium Grid execution
- Cloud execution
- API automation integration
- Database validation
- Cucumber BDD integration


---

# Author


**Amol**


Automation Engineer


Skills:

- Selenium WebDriver
- Java
- TestNG
- Maven
- API Automation
- CI/CD Concepts

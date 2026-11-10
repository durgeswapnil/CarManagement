# Car Management System (Spring Boot + Java + MySQL)

A beginner-friendly web application based on the four screens in the supplied reference:
1. Admin login
2. Dashboard
3. Add Car (name, company, color, price and optional image)
4. View Cars (list and delete)

## Technology
- Java 17
- Spring Boot 3.4.5
- Spring MVC + Thymeleaf
- Spring Data JPA / Hibernate
- MySQL 8
- Maven

## Demo login
- Username: `admin`
- Password: `admin123`

**Security note:** This is a learning/demo login stored in the controller, not production authentication. For a real deployment, use Spring Security, hashed passwords, CSRF protection and role-based authorization.

## 1. Requirements
Install:
- JDK 17 or later
- MySQL Server 8
- Eclipse IDE for Enterprise Java and Web Developers (or IntelliJ IDEA)
- Maven (Eclipse can use its embedded Maven)

Check Java:
```bash
java -version
```

## 2. Create the database
Open MySQL Workbench or MySQL command line and run:
```sql
CREATE DATABASE car_management;
```
The application creates the `cars` table automatically when it starts.

## 3. Set your MySQL password
Open:
`src/main/resources/application.properties`

Replace:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```
with the password for your local MySQL `root` account. If your MySQL username is not `root`, change `spring.datasource.username` too.

## 4. Import into Eclipse
1. Extract the ZIP file.
2. Open Eclipse.
3. Select **File > Import > Maven > Existing Maven Projects**.
4. Browse to the extracted `CarManagementSystem` folder.
5. Select the project and click **Finish**.
6. Wait for Maven dependencies to download.
7. Open `CarManagementApplication.java`.
8. Right-click it and select **Run As > Java Application** (or **Spring Boot App**, if available).

## 5. Run and open
Visit: http://localhost:8080

Sign in with the demo credentials above.

## 6. How images work
Uploaded images are saved in the `uploads/cars` folder beside the project while the app is running. Do not delete this folder if you want to keep uploaded car photos. Images are optional.

## Main project structure
```text
CarManagementSystem
├── pom.xml
├── README.md
├── uploads/
└── src/main
    ├── java/com/example/carmanagement
    │   ├── CarManagementApplication.java
    │   ├── config/WebConfig.java
    │   ├── controller/AuthController.java
    │   ├── controller/CarController.java
    │   ├── model/Car.java
    │   ├── repository/CarRepository.java
    │   └── service/CarService.java
    └── resources
        ├── application.properties
        ├── static/css/style.css
        └── templates
            ├── fragments/sidebar.html
            ├── login.html
            ├── dashboard.html
            ├── add-car.html
            └── view-cars.html
```

## Routes
| URL | Purpose |
|---|---|
| `/login` | Login page |
| `/dashboard` | Dashboard |
| `/cars/add` | Add a car |
| `/cars` | View all cars |
| `/logout` | Logout |

## Troubleshooting
- **Cannot connect to MySQL:** Check that the MySQL service is running and the username/password in `application.properties` are correct.
- **Port 8080 already in use:** Stop the other application or change `server.port=8081`.
- **Maven dependencies not loading:** Right-click project > Maven > Update Project.
- **Image not visible:** Keep the `uploads/cars` folder beside the project and restart the app after changing file permissions.

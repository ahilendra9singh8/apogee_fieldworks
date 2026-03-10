################################################## PART 1 - Basics of Spring & Spring Boot ############################

##1. How Spring Boot Application Works?

Flow:
1. Main class run hoti hai
2. @SpringBootApplication
3. Auto configuration load hoti hai
4. Beans create hote hain
5. Embedded server start hota hai

📌 Behind the scene
Spring Boot internally:
1. Reads classpath
2. Applies conditional configs
3. Registers beans in IOC container

##2. 8️⃣ Types of Applications Spring Boot is used for

1. REST APIs
2. Microservices
3. Web Applications
4. Batch Applications
5. Cloud-native apps

##3. Spring Boot Architecture (High Level)

Client
 ↓
Controller
 ↓
Service
 ↓
Repository
 ↓
Database

📌 Interview Tip
Always mention Controller → Service → Repository pattern.

##4. 🔟 What is Spring Boot Starter?

Starter = Predefined dependency bundle

Example:
spring-boot-starter-web

Includes:
1. Spring MVC
2. Jackson
3. Validation
4. Embedded Tomcat

📌 Benefit
No need to add 10 dependencies manually.

##5. Spring Boot Starter Parent

<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>3.5.6</version>
	<relativePath/> <!-- lookup parent from repository -->
</parent>

Role:
1. Dependency version management
2. Default plugin configuration
3. Java version support

##6. Default Server in Spring Boot

✅ Apache Tomcat

Other options:
1. Jetty
2. Undertow

##7. What is Embedded Server?

Server jo application ke sath hi package hota hai.

📌 Example:
java -jar app.jar

No need:
-> External Tomcat install
-> WAR deploy

##8. How Spring Boot Creates Standalone Application?

1. Uses embedded server
2. Runs using main() method
3. Packaged as executable JAR

##9. Why No XML Configuration in Spring Boot?

Because:
1. Java based config
2. Annotation driven
3. Convention over configuration

📌 Interview line

####### Spring Boot prefers annotations over XML.


##10. Spring Boot vs Spring MVC

| Spring MVC    | Spring Boot            |
| ------------- | ---------------------- |
| Web framework | Full stack solution    |
| Needs Spring  | Uses Spring internally |
| Manual config | Auto config            |
| WAR deploy    | JAR deploy             |


##11. Difference between Spring & Spring Boot

| Spring                 | Spring Boot           |
| ---------------------- | --------------------- |
| Heavy configuration    | Minimal configuration |
| XML + Java config      | No XML                |
| External server needed | Embedded server       |
| Manual dependency mgmt | Starter based         |
| Slow setup             | Very fast setup       |


##12. Spring Boot vs Spring Cloud

| Spring Boot        | Spring Cloud            |
| ------------------ | ----------------------- |
| App development    | Microservices tools     |
| Single application | Distributed systems     |
| REST, JPA          | Config, Eureka, Gateway |


📌 One-liner

Spring Boot builds apps, Spring Cloud manages microservices.


##13. ⭐ Extra Interview Questions (PART-1 Add-on)

Q: Is Spring Boot suitable for large applications?
✅ Yes, with proper modular structure.

Q: Can we disable auto-configuration?
✅ Yes
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)


##12. @SpringBootApplication

@SpringBootApplication ek meta-annotation hai
(matlab ye multiple annotations ka combination hai).

👉 Interview one-liner

####  @SpringBootApplication is a combination of multiple Spring annotations that enable auto-configuration, component scanning, and Java-based configuration.

##12.1: ✅ Internally ye 3 main annotations ka combo hai:

@SpringBootApplication
   ↓
@Configuration
@EnableAutoConfiguration
@ComponentScan

##12.2: @Configuration – Kya karta hai?

@Configuration batata hai ki
👉 ye class ek configuration class hai, java based configuration.

📌 Is class ke andar:
-> @Bean methods ho sakte hain
-> Spring IOC container ko pata chalta hai ki beans yahin se milengi

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

##12.3: @EnableAutoConfiguration – MOST IMPORTANT 🔥

Spring Boot ko bolta hai:

“Classpath dekh ke khud decide karo kaun se beans create karne hain”
scan the classpath(pom.xml) and create beans automatically.

Example:
Agar classpath me ho:

-> spring-web → DispatcherServlet auto configure
-> spring-data-jpa → EntityManager auto configure
-> H2/MySQL → DataSource auto configure

📌 Real life example
Aap dependency add karo:

spring-boot-starter-data-jpa

👉 Spring Boot automatically:
-> DataSource
-> EntityManager
-> TransactionManager
create kar deta hai

Behind the scenes:
-> Uses spring.factories
-> Uses @Conditional annotations

##12.4: @ComponentScan – Kya karta hai?

Spring ko bolta hai:

“Is package aur iske sub-packages me jitne components hain unko scan karo”

Scan karta hai:

-> @Component
-> @Service
-> @Repository
-> @Controller
-> @RestController

📌 Default rule
Main class ke package se niche sab scan hota hai.

👉 Sab beans auto register ho jaate hain.

##12.5: Agar @ComponentScan ka scope change karna ho?
@ComponentScan(basePackages = "com.myproject")

##12.6: Full Code: @SpringBootApplication Internals

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}


Internally equivalent to:

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MyApplication {
}

##12.7: SpringApplication.run() kya karta hai?
Ye internally:
1. ApplicationContext create karta hai
2. Beans load karta hai
3. Auto-configuration apply karta hai
4. Embedded server start karta hai

📌 One liner

SpringApplication.run() bootstraps the Spring Boot application.


##12.8: Common Interview Questions on @SpringBootApplication

Q1: Kya hum teeno annotations alag-alag use kar sakte hain?

✅ YES

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {
}

Q2: Agar @EnableAutoConfiguration hata dein to?

❌ Auto configuration kaam nahi karegi
Manual beans likhne padenge

Q3: Agar @ComponentScan hata dein?

❌ Controllers, services detect nahi honge
404 error aayega

##12.9: 🔟 Extra Missed Topics (PART-1 Additions)
🔹 What is IOC Container?

Spring ka core engine jo:
-> Beans create karta hai
-> Dependencies inject karta hai
-> Lifecycle manage karta hai

Types:

ApplicationContext (mostly used)
BeanFactory

🔹 What is Bean?

Bean = Object managed by Spring container

🔹 What is Dependency?

Agar class A ko class B chahiye → B is dependency

🔹 Loose Coupling kya hota hai?

Classes ek-dusre pe tightly depend na karein


################################################## PART 2 - Spring Boot Project Setup (Complete + Interview Focused) ############################

#1️. How to Create a Spring Boot Project?
✅ Ways to create Spring Boot project

1. Spring Initializr (Website)
2. IDE (STS / IntelliJ / Eclipse)
3. Command line

📌 Interview answer (short & smart)

We usually create Spring Boot projects using Spring Initializr because it generates a ready-to-run project with correct dependencies.

#2. Maven vs Gradle (Interview Favorite)

| Maven           | Gradle              |
| --------------- | ------------------- |
| XML based       | Groovy / Kotlin DSL |
| Slower build    | Faster              |
| More common     | Less common         |
| Easier to learn | Slightly complex    |


📌 3.5 yrs experience answer

I mostly use Maven as it is widely adopted and easy to manage in enterprise projects.

#3. Spring Boot Project Structure (VERY IMPORTANT 🔥)
Default structure:

src/main/java
 └── com.example.demo
      ├── DemoApplication.java
      ├── controller
      ├── service
      ├── repository
      └── entity

src/main/resources
 ├── application.properties
 ├── static
 └── templates

src/test/java

📌 Interview Tip

Always keep main class at root package
Otherwise @ComponentScan issue aata hai.

#4. Role of pom.xml

pom.xml = Project Object Model

It handles:
1. Dependencies
2. Plugins
3. Build lifecycle
4. Java version

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>

📌 Starter benefit
Ye ek hi dependency me 10 libraries le aata hai.

#5. What is spring-boot-starter-web?

It includes:
1. Spring MVC
2. Embedded Tomcat
3. Jackson (JSON)
4. Validation

#6. What is application.properties?

application.properties ek configuration file hai jahan hum:

-> DB config
-> Server port
-> Logging
-> Custom values rakhte hain

Example:
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=1234

#7. What is application.yml?

YAML based configuration file
Readable & hierarchical

Example:
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: 1234
    
    
#8. application.properties vs application.yml

| properties   | yml                       |
| ------------ | ------------------------- |
| Key-value    | Hierarchical              |
| Hard to read | Easy to read              |
| Simple       | Preferred in big projects |

📌 Interview line

YAML is more readable and structured than properties file.

#9. Can we use both .properties & .yml together?

❌ NO
Spring Boot allows only one active format at a time

#10. What happens if main class is not in root package?

❌ Beans will not be scanned
❌ Controllers not detected
❌ 404 error


#11. Packaging: JAR vs WAR

| JAR             | WAR                   |
| --------------- | --------------------- |
| Standalone      | Needs external server |
| Embedded Tomcat | External Tomcat       |
| Preferred       | Legacy systems        |

📌 Modern answer

JAR is preferred in microservices architecture.

#12. Common Interview Tricky Questions (PART-2)
Q: Can we change server port?

✅ Yes
server.port=9090

Q: How to change context path?
server.servlet.context-path=/api

Q: Where do you keep sensitive configs?
👉 External config / Environment variables

#13. 🔹 EXTRA (Missed but Important)

1. What is Fat JAR?

JAR containing:
-> App code
-> Dependencies
-> Embedded server


############################ PART-3 : Annotations + Mapping + JPQL + Native Query (COMPLETE) ###########################

#🔥 SECTION-A : CORE SPRING / SPRING BOOT ANNOTATIONS

##1. @Component

-> Generic annotation to create Spring bean.

@Component
public class EmailUtil {
}

📌 Spring automatically object bana deta hai.

##2. @Service

-> Business logic layer ke liye

@Service
public class UserService {
}

📌 Interview line

@Service is a specialization of @Component used for business logic.

##3. @Repository

-> DAO / DB layer ke liye

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

📌 Extra benefit
✔ Converts DB exceptions into Spring exceptions

##4. @Controller vs @RestController
@Controller
-> Used for MVC (HTML / JSP)

@RestController
-> Used for REST APIs

@RestController
@RequestMapping("/users")
public class UserController {
}

📌 Internally
@RestController = @Controller + @ResponseBody

##5️. @Autowired

-> Dependency Injection ke liye

@Autowired
private UserService userService;

📌 Best practice
👉 Constructor Injection use karo (interview favorite)

##6. @Qualifier & @Primary
Problem:

-> 2 beans same type ke ho

Solution:
@Qualifier("smsService")

OR

@Primary

##7️. @Configuration & @Bean

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

📌 Used when third-party class ko bean banana ho.



#🌐 SECTION-B : WEB / MAPPING ANNOTATIONS (VERY IMPORTANT 🔥)

##8️. @RequestMapping
-> Base URL mapping
@RequestMapping("/api")

##9️. @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
@GetMapping("/users")
public List<User> getUsers() {}

##10. @PathVariable
->URL se value lene ke liye (Extract value from URL path)

@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {}

📌 /users/10

##11. @RequestParam
-> Query param ke liye (Extract value from the queary string)

@GetMapping("/users")
public List<User> getByStatus(@RequestParam String status) {}

📌 /users?status=ACTIVE

##12. @RequestBody
-> JSON request ko object me convert karta hai

@PostMapping("/users")
public User save(@RequestBody User user) {}

##13. @ResponseBody
-> Java object → JSON

(Already included in @RestController)

##14. @CrossOrigin
-> CORS issue solve karta hai

@CrossOrigin(origins = "*")


#🗄️ SECTION-C : JPA ANNOTATIONS + MAPPING (🔥🔥)

##15. @Entity
-> Table ko represent karta hai

@Entity
public class User {
}

##16. @Table
-> Table name define karne ke liye

@Table(name="users")

##17. @Id & @GeneratedValue
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

##18. @Column
@Column(nullable=false, unique=true)
private String email;


#🔥 RELATIONSHIP MAPPING (VERY VERY IMPORTANT)

##19. One-to-One Mapping
@OneToOne
@JoinColumn(name="profile_id")
private Profile profile;

📌 Real example: User → Profile

##20. One-to-Many Mapping
@OneToMany(mappedBy="user")
private List<Order> orders;

📌 One User → Many Orders

##21. Many-to-One Mapping
@ManyToOne
@JoinColumn(name="user_id")
private User user;

##22. Many-to-Many Mapping
@ManyToMany
@JoinTable(
  name="user_roles",
  joinColumns=@JoinColumn(name="user_id"),
  inverseJoinColumns=@JoinColumn(name="role_id")
)
private List<Role> roles;

##23. Fetch Type: Lazy vs Eager

| Lazy                    | Eager             |
| ----------------------- | ----------------- |
| Loads on demand         | Loads immediately |
| Better performance      | Heavy             |
| Default for collections | Risky             |


📌 Interview line
Lazy loading is preferred to avoid unnecessary DB calls.


########################## 🔍 SECTION-D : JPQL & QUERY METHODS 🔥 ##########################

##24. What is JPQL?

-> JPQL = Java Persistence Query Language
-> Works on Entity names
-> DB independent

Example:
@Query("SELECT u FROM User u WHERE u.status = :status")
List<User> findByStatus(String status);

##25. Native Query

-> Direct SQL query

@Query(value="SELECT * FROM users WHERE status='ACTIVE'", nativeQuery=true)
List<User> getActiveUsers();

📌 Use when:
-> Complex query
-> Performance critical

##26. JPQL vs Native Query

| JPQL           | Native      |
| -------------- | ----------- |
| Entity based   | Table based |
| DB independent | DB specific |
| Safe           | Powerful    |

##27. Query Methods (Auto Queries)
List<User> findByEmail(String email);
List<User> findByStatusAndRole(String status, String role);

📌 Spring automatically SQL bana deta hai.

##28. agination & Sorting
Page<User> findAll(Pageable pageable);
PageRequest.of(0,10, Sort.by("name"))

##29. INTERVIEW TRICK QUESTIONS (PART-3)
Q: mappedBy kya karta hai?
👉 Relationship ka owner define karta hai.

Q: CascadeType kya hota hai?
cascade = CascadeType.ALL
Parent save → child auto save

Q: Why use @Repository?
👉 Exception translation + DB layer identity

##30. Dependency Injection 

-> Jab ek class apni dependency khud create na karke Spring se leti hai → DI

🔹 Types of DI

1. Field Injection ❌
2. Setter Injection ⚠️
3. Constructor Injection ✅ (BEST)

1.Field Injection (❌ Not Recommended)

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}

❌ Problems
-> Unit testing hard
-> Dependency hidden hoti hai
-> Immutable class nahi bana sakte

📌 Interview line
Field injection is not recommended due to poor testability.

2. Setter Injection (⚠️ Limited use)

@Autowired
public void setUserRepository(UserRepository repo) {

    this.userRepository = repo;
}

Use when:
-> Optional dependency


3. Constructor Injection (✅ BEST PRACTICE)

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
✅ Why Constructor Injection is BEST?

✔ Dependency mandatory hoti hai
✔ Class immutable ban sakti hai
✔ Unit testing easy
✔ Spring officially recommends it

📌 Golden Interview Answer

Constructor Injection ensures immutability, better testability, and makes dependencies explicit.

##31. @Qualifier & @Primary (Bean Conflict Problem)




####################################### PART-4 : IOC Container, Bean Lifecycle, Scopes & Circular Dependency ##########################################

##1. IOC (Inversion of Control)
Object creation ka control Spring ke paas hota hai, developer ke paas nahi
-> Spring Boot uses ApplicationContext as IOC container.

##2. What is a Bean?
Bean = Java object managed by Spring

Bean ka lifecycle:
-> Creation
-> Initialization
-> Use
-> Destruction

##3. How Spring Boot Creates Beans?

Spring beans create hote hain via:
-> @Component
-> @Service
-> @Repository
-> @Controller
-> @Bean

##4. Bean Lifecycle (VERY IMPORTANT)
Steps:

1. Bean instantiated
2. Dependency injection
3. @PostConstruct  ya  InitializingBean
4. Bean ready to use
5. @PreDestroy   ya DisposableBean

Example: 
@Component
public class MyBean {

    @PostConstruct
    public void init() {
        System.out.println("Bean initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean destroyed");
    }
}

📌 Interview Tip

@PostConstruct is used for initialization logic after DI.

##5. Bean Scopes (VERY IMPORTANT)
Available scopes:

| Scope       | Meaning                 |
| ----------- | ----------------------- |
| Singleton   | One instance (default)  |
| Prototype   | New instance every time |
| Request     | One per HTTP request    |
| Session     | One per HTTP session    |
| Application | One per ServletContext  |

##6. 🔹 Singleton vs Prototype
1.Singleton:
-> One object for entire app
-> Default
-> Memory efficient

2.Prototype:
-> New object each request
-> Spring manage creation only

📌 Interview Line

Spring manages full lifecycle of singleton beans but only creation of prototype beans.

@Scope("prototype")
@Component
public class MyBean {
}

##7. Lazy vs Eager Initialization
1. Eager (default):
Bean created at startup

2. Lazy:
Created when needed

@Lazy
@Component
public class HeavyBean {
}

📌 Use case
Large object → Lazy loading

##8. Circular Dependency (VERY IMPORTANT 🔥)
❓ What is Circular Dependency?

Class A needs B
Class B needs A

➡ Deadlock situation
A → B → A

❌ Example (Field Injection)

@Service
class A {

    @Autowired
    B b;
}

@Service
class B {

    @Autowired
    A a;
}

🚨 Result:
BeanCurrentlyInCreationException

##9. How to Solve Circular Dependency?
✅ Solution-1: Constructor Injection (BEST)

👉 Spring detects at compile time

✅ Solution-2: @Lazy

@Autowired
@Lazy
private A a;

✅ Solution-3: Redesign code

-> Move common logic
-> Create third service

📌 Interview Tip

Circular dependency is a design issue and should be avoided.

##10. Why Constructor Injection Prevents Circular Dependency?
-> Object creation happens at once
-> Spring fails fast

##11. Bean Scopes & Circular Dependency
Singleton → possible to resolve
Prototype → ❌ cannot be resolved

##12. Difference: @Component vs @Bean
| @Component   | @Bean        |
| ------------ | ------------ |
| Class level  | Method level |
| Auto scanned | Explicit     |
| App classes  | Third-party  |

##13. Interview Rapid-Fire Questions
Q: Default scope in Spring?
👉 Singleton

Q: When to use prototype?
👉 Stateful objects

Q: Can prototype bean inject singleton?
👉 Yes

Q: Can singleton inject prototype?
👉 Yes (but same instance reused)

Q: How to get new prototype each time?
👉 ObjectProvider / ApplicationContext

Q: @DependsOn
Bean dependency order define karta hai
@DependsOn("beanA")


#################################### PART-5 : Auto-Configuration, spring.factories, Conditional Annotations  ######################################
##1. What is Auto-Configuration?
Definition:

Auto-configuration means Spring Boot automatically configures beans based on:
-> classpath
-> existing beans
-> property settings

Aap dependency add karo,
Spring Boot bolega:
👉 “Samajh gaya, main setup kar deta hoon”

##2. Why Auto-Configuration is Needed?

Without auto-config:
-> DataSource manually create
-> EntityManager manually
-> DispatcherServlet manually

With auto-config:
-> Zero boilerplate
-> Faster development

##3. How Auto-Configuration Works? (HIGH LEVEL FLOW 🔥)

1. Application starts
2. @EnableAutoConfiguration active hota hai
3. Spring Boot reads classpath
4. Reads spring.factories
5. Applies conditional logic
6. Beans auto-register hote hain

##4. What is spring.factories?
spring.factories ek configuration file hoti hai jo batati hai:
Kaun-kaun si auto-configuration classes load karni hain

📌 Location:
META-INF/spring.factories

##5. Role of @EnableAutoConfiguration
Ye annotation:
-> Auto-configuration ko enable karta hai
-> spring.factories read karta hai

📌 Interview one-liner

@EnableAutoConfiguration loads auto-configuration classes listed in spring.factories based on conditions.

##6. Conditional Annotations (🔥 VERY IMPORTANT)
Auto-configuration blindly apply nahi hoti
Ye conditions check karti hai 👇

🔹 @ConditionalOnClass

Bean tabhi banega jab class present ho

@ConditionalOnClass(DataSource.class)

📌 Example:

JDBC jar present → DataSource bean create

🔹 @ConditionalOnMissingBean

Bean tabhi banega jab user ne custom bean define nahi ki ho

@ConditionalOnMissingBean(DataSource.class)

📌 Interview trick

Spring Boot never overrides user-defined beans.

🔹 @ConditionalOnProperty

Property ke base pe config

@ConditionalOnProperty(
  name="feature.enabled",
  havingValue="true"
)
🔹 @ConditionalOnBean

Bean exist karta ho tab

🔹 @ConditionalOnWebApplication

Sirf web app ke liye

##7. How to Disable Auto-Configuration?
Method-1: Exclude in main class
@SpringBootApplication(
 exclude = DataSourceAutoConfiguration.class
)

Method-2: properties file
spring.autoconfigure.exclude=\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

##8. Custom Auto-Configuration (ADVANCED 🔥)
Use case:
-> Reusable library
-> Company common starter

Steps (High level):
1. Create configuration class
2. Add conditional annotations
3. Register in spring.factories

Example:

@Configuration
@ConditionalOnClass(MyService.class)
public class MyAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public MyService myService() {
        return new MyService();
    }
}

Register it:

EnableAutoConfiguration=\
com.example.MyAutoConfig

📌 Interview bonus

Custom auto-configuration is used while creating Spring Boot starters.

##9. How Starters use Auto-Configuration?

Example:

spring-boot-starter-data-jpa

Includes:
1. Hibernate auto config
2. DataSource auto config
3. TransactionManager auto config

##10. Common Interview Questions (Auto-Config)
Q: How Spring Boot knows which DB to configure?
👉 Via classpath + properties

Q: Can we override auto-config?
👉 Yes, by defining our own bean

Q: Where auto-config classes are defined?
👉 spring.factories

##11. Difference between @Configuration & Auto-Configuration?

| Configuration | Auto-Configuration |
| ------------- | ------------------ |
| Manual        | Conditional        |
| App specific  | Framework driven   |


##12. Debug Auto-Configuration (Interview PRO)
debug=true

OR

--debug

👉 Console me dikhega:
-> Which auto-config applied
-> Which skipped

📌 Impressive interview answer 🔥

##13. 🔸 Auto-Configuration Order
Controlled by
 
@AutoConfigureBefore
@AutoConfigureAfter

##14. Spring Boot 2.7+ → spring.factories replaced by:

META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

📌 But interview me spring.factories hi bolo (safe)


############################## PART-6 : REST API Development + Exception Handling (COMPLETE) #########################

##1.What is REST?
REST = Representational State Transfer
REST ek architectural style hai jo client–server communication ke rules define karta hai.

📌 Simple Hinglish

REST bolta hai: URL clean rakho, HTTP methods ka sahi use karo.

##2. RESTful Web Services kya hote hain?
Web services jo:
-> REST principles follow karti hain
-> JSON/XML use karti hain
-> Stateless hoti hain

##3. REST Principles (VERY IMPORTANT)

| Principle         | Meaning                                |
| ----------------- | -------------------------------------- |
| Stateless         | Server client ka state save nahi karta |
| Client–Server     | Separation of concern                  |
| Uniform Interface | Standard URLs + methods                |
| Cacheable         | Response cache ho sakta hai            |


📌 Interview line
REST APIs are stateless and resource-based.

##4. HTTP Methods (Interview Favourite)
1. GET
Data read
Idempotent

@GetMapping("/users")

2. POST
Create new resource

@PostMapping("/users")

##PUT vs PATCH (VERY IMPORTANT)
3. PUT
Full update
Idempotent

4. PATCH
Partial update

📌 Interview one-liner

PUT replaces entire resource, PATCH updates only selected fields.

5. DELETE
@DeleteMapping("/users/{id}")

##5. HTTP Status Codes

| Code | Meaning      |
| ---- | ------------ |
| 200  | OK           |
| 201  | Created      |
| 400  | Bad Request  |
| 401  | Unauthorized |
| 403  | Forbidden    |
| 404  | Not Found    |
| 500  | Server Error |

📌 Interview Tip

Always return proper HTTP status codes.

##6. Request–Response Flow (Behind the Scenes)
1. Client sends HTTP request
2. DispatcherServlet receives
3. Controller method called
4. Service → Repository
5. Response converted to JSON
6. Sent back to client

##7. Creating REST API (Complete Example)

1. Entity

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

2. Repository

public interface UserRepository extends JpaRepository<User, Long> {
}

3. Service

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User save(User user) {
        return repo.save(user);
    }
}


4. Controller

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
    }
}


#8. Exception Handling (VERY IMPORTANT 🔥)

##8.1: What is Exception?
Runtime error jo application ka normal flow tod deta hai.

##8.2: Checked vs Unchecked Exception

| Checked      | Unchecked            |
| ------------ | -------------------- |
| Compile time | Runtime              |
| IOException  | NullPointerException |

##8.3: ❌ Bad Way (try-catch in controller)
try {
} catch(Exception e) {}

📌 Interview me ❌

##8.4: ✅ 9️⃣ @ExceptionHandler
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<String> handle(UserNotFoundException ex) {

    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
}

##8.5: Global Exception Handling (@ControllerAdvice)
Best Practice ✅

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handle(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

📌 Interview Line

@ControllerAdvice provides centralized exception handling.

##8.6: Custom Exception
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {
        super(msg);
    }
}

##8.7: Proper Error Response Structure (INTERVIEW BONUS)
{
  "timestamp": "2026-01-05",
  "status": 404,
  "error": "Not Found",
  "message": "User not found"
}

##8.8: ResponseEntity (IMPORTANT)
Why use ResponseEntity?
1. Control status code
2. Add headers

return ResponseEntity.ok(user);

##8.9: Common Interview Questions (REST)
Q: Why REST is stateless?
REST stateless hota hai ka matlab:
-> Server har request ko independent treat karta hai.
-> Server client ki previous request ka data ya session memory me store nahi karta.
👉 Scalability(System zyada users handle kar sake bina slow hue)  + performance(Fast response time) 

Q: REST vs SOAP?

| REST        | SOAP  |
| ----------- | ----- |
| Lightweight | Heavy |
| JSON        | XML   |
| Fast        | Slow  |


Q: Can REST return XML?
👉 Yes (but JSON preferred)

##8.10: @Valid – Validation ke liye
-> @Valid use hota hai request data ko validate karne ke liye.
-> Jab client API ko data bhejta hai (JSON), to hum check kar sakte hain ki data sahi hai ya nahi — jaise:
1. name blank na ho
2. email valid format me ho
3. age 18 se kam na ho

@PostMapping
public User save(@Valid @RequestBody User user) {}

Yaha kya ho raha hai?
-> @RequestBody → JSON ko User object me convert karega
-> @Valid → User class me lage validation rules ko check karega

##8.11: @ResponseStatus – Custom HTTP Status bhejne ke liye
-> Ye annotation use hota hai jab hum manually batana chahte hain ki API ka response kaunsa HTTP status return kare.

Example:
@ResponseStatus(HttpStatus.NOT_FOUND)

Matlab: Jab ye method ya exception chalega → 404 status return hoga.

Example 1 – Method ke upar
@GetMapping("/{id}")
@ResponseStatus(HttpStatus.OK)
public User getUser(@PathVariable int id) {

    return service.findById(id);
}

Yaha response ka status 200 OK hoga.


Example 2 – Exception ke sath (Real use case)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
}

Ab jab bhi UserNotFoundException throw hogi →
Automatically 404 status return hoga.



###################################### PART-7 : Spring Data JPA – Deep Dive (REAL WORLD) ##########################################

##1. What is JPA?
-> JPA = Java Persistence API
-> Specification (rule book)
-> Object ↔ Table mapping define karta hai

📌 Interview line

JPA is a specification, not an implementation.

##2. What is Hibernate?
-> JPA ka implementation
-> ORM framework

📌 One-liner

Hibernate is the most popular JPA implementation.

##3. What is Spring Data JPA?
Spring Data JPA:
-> JPA + Spring ka wrapper
-> Boilerplate code remove karta hai

##4. JPA vs Hibernate vs Spring Data JPA

| JPA       | Hibernate      | Spring Data JPA  |
| --------- | -------------- | ---------------- |
| Spec      | Implementation | Abstraction      |
| Interface | ORM tool       | Repository layer |
| No code   | Heavy code     | Minimal code     |

##5. Repository Interfaces (VERY IMPORTANT)
1. CrudRepository
-> Basic CRUD

CrudRepository<User, Long>

2. PagingAndSortingRepository

Pagination + Sorting

3. JpaRepository (MOST USED 🔥)

-> CRUD
-> Pagination
-> Sorting
-> Batch operations

JpaRepository<User, Long>

📌 Interview tip

JpaRepository is preferred in real projects.

##6. Query Methods (Auto Query Generation)
findByName(String name);
findByEmailAndStatus(String email, String status);
findByAgeGreaterThan(int age);

📌 Method name se query ban jati hai.

##7. JPQL vs Native Query (RECAP + DEEP)
1. JPQL

@Query("SELECT u FROM User u WHERE u.status = :status")
List<User> findActive(String status);

-> Entity based
-> DB independent

2. Native Query

@Query(value="SELECT * FROM users WHERE status='ACTIVE'", nativeQuery=true)
List<User> findActiveUsers();

-> Table based
-> DB specific

📌 When to use Native?

-> Complex joins
-> Performance critical SQL

##8. Transactions (VERY IMPORTANT)
#8.1:  What is Transaction?

Transaction = Database operations ka group

Rule:
👉 Ya to sab operations success honge
👉 Ya agar ek bhi fail ho gaya to sab rollback (undo) ho jayenge

#8.2: ACID Properties (Interview Must 🔥)
1. Atomicity

"All or Nothing"

Sab operations complete honge ya sab cancel.

2. Consistency

Database hamesha valid state me rahe.

Example:
Balance negative nahi hona chahiye.

3. Isolation

Ek transaction doosre ko disturb nahi kare.

Agar 2 log same account update kar rahe ho → conflict handle hota hai.

4. Durability

Once data saved → permanently saved
Server crash ke baad bhi data safe.

#8.3: Spring Boot me @Transactional ka use
Spring Boot me transaction manage karne ke liye use karte hain:

@Transactional

Ye mostly Service layer me lagate hain.

#8.4: With @Transactional

@Transactional
public void transfer(Long fromId, Long toId, double amount) {

    Account from = accountRepo.findById(fromId).get();
    Account to = accountRepo.findById(toId).get();

    from.setBalance(from.getBalance() - amount);
    accountRepo.save(from);

    int x = 10 / 0; // error

    to.setBalance(to.getBalance() + amount);
    accountRepo.save(to);
}

Ab kya hoga?
-> Error aate hi
-> Pura transaction rollback
-> From account ka balance bhi revert
✔ Database safe

4. Rollback Rules:

| Exception         | Rollback    |
| ----------------- | ----------- |
| RuntimeException  | ✅           |
| Checked Exception | ❌ (default) |


#8.5: Example
@Transactional
public void saveUser() throws Exception {
    userRepo.save(new User());
    throw new Exception("Error");
}

Yaha:

❌ Default me rollback nahi hoga
Kyuki ye checked exception hai

#8.6: 
Example:

@Transactional(rollbackFor = Exception.class)
public void saveUser() throws Exception {

    userRepo.save(new User());
    throw new Exception("Error");
}

Ab rollback ho jayega ✅

#8.7: 🔥 Interview Me Bolne Layak Line

"@Transactional ensures atomicity in Spring Boot.
By default it rolls back on RuntimeException,
and for checked exceptions we must specify rollbackFor."

##9. Lazy vs Eager Loading (DEEP)

1. Lazy (Recommended)
-> Data load on demand
-> Performance friendly

2. Eager (Risky)
-> Auto load
-> Performance hit

📌 Common Issue
LazyInitializationException

3. 🔥 Solution:
-> Use @Transactional
-> Fetch Join
-> DTO projection

##10. N+1 Query Problem (VERY IMPORTANT 🔥)
What is N+1?
-> 1 query for parent
-> N queries for children

Example:

List<User> users = userRepo.findAll();
users.forEach(u -> u.getOrders().size());

❌ Problem:
100 users → 101 queries

✅ Solutions:
-> JOIN FETCH
-> @EntityGraph
-> Batch fetching

##11. Performance Optimization Tips

✔ Index DB columns
✔ Pagination mandatory
✔ Use DTOs
✔ Avoid EAGER
✔ Use projections

##12. Interview Rapid Fire (PART-7)
Q: Why JpaRepository preferred?
👉 More features

Q: Default isolation level?
👉 DB dependent (usually READ_COMMITTED)

"Default isolation level is database dependent.
Most relational databases like PostgreSQL, MySQL and Oracle use READ_COMMITTED as default."

Q: Does @Transactional work on private method?

👉 ❌ No

Q: Self-invocation works?

👉 ❌ No (proxy issue)
"@Transactional does not work in self-invocation because Spring uses proxy-based AOP, and internal method calls bypass the proxy."




########################## PART-8 = Exception Handling (Deep Dive + Production Scenarios) ##########################################
Q: Should we catch exception in service?

👉 ❌ No (let it propagate)

Q: Where to put @Transactional?

👉 Service layer

Q: Why RuntimeException preferred?

👉 Auto rollback + clean code

🔹 EXTRA (Often Asked)
🔸 Spring Boot Default Error Handling

/error endpoint
Controlled by BasicErrorController

🔸 Customize Error Page

Override ErrorController



##################################  PART-9 : Spring Boot Security (Complete + Interview Ready)  #######################################

#PART 1 – SPRING SECURITY BASICS

##1️. What is Spring Security?
Spring Security is a powerful authentication & authorization framework for Spring applications.

👉 Authentication = Who are you?
👉 Authorization = What can you access?

📌 Interview Line:

Spring Security provides authentication, authorization, and protection against common attacks like CSRF.

#PART 2 – BASIC AUTHENTICATION
🧩 Concept

Client sends:
Authorization: Basic base64(username:password)

Example:
Authorization: Basic cmFqdTpyYWp1MTIz

Server decodes → validates → allows access.

Stateless → no session stored.

##⚙️ Ways to Enable Basic Auth

##1️. Default (Auto-configured)
Add dependency:
spring-boot-starter-security

Default user:

username: user
password: auto-generated (console)

##2️. application.properties
spring.security.user.name=raju
spring.security.user.password=raju123

##3️. Custom SecurityConfig (Recommended)

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login","/register").permitAll()
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults());
    return http.build();
}

##4. Limitations
❌ Base64 ≠ Encryption
❌ Must use HTTPS
❌ Credentials sent every request

##5. 📌 When to Use?
✔ Internal APIs
✔ Microservices inside private network


#🔑 PART 3 – JWT AUTHENTICATION (TOKEN-BASED)

##1. 🧠 What is JWT?
JWT = JSON Web Token
Stateless authentication mechanism.

Instead of sending username/password every time →
Send token.

##2. 🧱 JWT Structure
Header.Payload.Signature

1️. Header → Algorithm
2️. Payload → Claims (username, role)
3️. Signature → Ensures token not tampered

##3. 🔄 JWT COMPLETE FLOW (STEP-BY-STEP)

### STEP 1 – Register User

Client → /register
Password saved with BCrypt.

### STEP 2 – Login

Client sends:

{
  "username": "raj",
  "password": "raj123"
}

Service:
-> Validate credentials
-> Check approval
-> Extract roles
-> Generate JWT


### STEP 3 – Generate Token

Jwts.builder()
   .setSubject(username)
   .claim("role","ROLE_ADMIN")
   .setExpiration(...)
   .signWith(key, HS256)
   .compact();
   
### STEP 4 – Client Stores Token
Usually:
-> Local storage
-> Session storage
-> HTTP-only cookie

### STEP 5 – Call Protected API
Authorization: Bearer <JWT_TOKEN>

### STEP 6 – JwtRequestFilter Executes
1. Extract header
2. Validate token
3. Load user from DB
4. Set Authentication in SecurityContext

### STEP 7 – Role Check
@PreAuthorize("hasRole('ADMIN')")

Spring checks authorities → allows/denies.

### STEP 8 - FINAL JWT EXECUTION FLOW

Client Request
   ↓
SecurityFilterChain
   ↓
JwtRequestFilter
   ↓
JwtUtil (validate)
   ↓
SecurityContextHolder
   ↓
Controller
   ↓
Response

## STEP 9 - JWT INTERVIEW QUESTIONS

Q: Why STATELESS session policy?
👉 Because server doesn’t store session. Token contains auth info.

Q: Difference Basic vs JWT?

| Basic             | JWT         |
| ----------------- | ----------- |
| Sends credentials | Sends token |
| Less secure       | More secure |
| Simple            | Scalable    |


# PART 4 – OAUTH 2.0

## 1.What is OAuth2?
OAuth2 is an Authorization Protocol, not authentication.

Used for:
-> Login with Google
-> Login with GitHub
-> Enterprise SSO

##2. 👥 4 Main Components

1️⃣ Resource Owner → User
2️⃣ Client → App
3️⃣ Authorization Server → Google/Keycloak
4️⃣ Resource Server → API

##3. 🔄 OAuth2 Flow
1. User clicks Login with Google
2. Redirect to Google
3. User grants permission
4. Google issues Access Token
5. Client sends token to backend
6. Backend validates token
7. Access granted

##4. 📌 OAuth2 vs JWT

OAuth2 = Protocol
JWT = Token format

OAuth2 may use JWT internally.

#Common Interview Questions (PART-9)
Q: Why JWT over session?

👉 Stateless, scalable

Q: Where JWT stored?

👉 LocalStorage / Cookie

Q: Is JWT secure?

👉 Yes, if HTTPS + expiry

Q: How to logout in JWT?

👉 Client deletes token


#############################################  PART-10 : Spring Boot Profiles & Configuration (Complete)  #########################################

#1️. What are Spring Profiles?
Spring Profile =
Different environment ke liye different configuration

Example:
-> Dev → local DB
-> Test → staging DB
-> Prod → production DB

📌 Interview line

Profiles help in environment-specific configuration.

#2. Why Profiles are Needed? (Real Project Reason)
-> Alag DB credentials
-> Alag logging level
-> Alag API URLs
-> Alag feature flags

#3️. Types of Profiles (Mostly Used)

| Profile   | Usage       |
| --------- | ----------- |
| dev       | Development |
| test / qa | Testing     |
| prod      | Production  |


#4️. Profile-based Configuration Files
-> application-dev.properties
-> application-test.properties
-> application-prod.properties

Spring Boot automatically picks based on active profile.

#5️. How to Activate Profile?

## Method 1: application.properties
spring.profiles.active=dev

## Method 2: JVM argument (Production best)
-Dspring.profiles.active=prod

## Method 3: Environment variable
SPRING_PROFILES_ACTIVE=prod


📌 Interview Tip

Environment variable preferred in production.

#6. @Profile Annotation
@Profile("dev")
@Bean
public DataSource devDataSource() {

    return new HikariDataSource();
}

=> Only active when dev profile enabled.

#7. 🔥 7️⃣ Multiple Profiles

spring.profiles.active=dev,swagger

OR

@Profile({"dev","test"})

#8. @Value Annotation
❌ Not recommended for many properties

1. application.properties

server.port=8080
app.name=FieldWorks
app.timeout=30
app.version=1.0

2. Java Class

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppInfo {

    @Value("${server.port}")
    private int port;

    @Value("${app.name}")
    private String appName;

    public void printInfo() {
        System.out.println("Port: " + port + ", App Name: " + appName);
    }
}

#9. @ConfigurationProperties (BEST PRACTICE)

1. application.properties

server.port=8080
app.name=FieldWorks
app.timeout=30
app.version=1.0

2. Java Class

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "app")  // prefix ke saare properties yahan map honge
@Data  // lombok ka getter/setter automatically generate karega
public class AppConfig {

    private String name;
    private int timeout;
    private String version;
}

3. Java Class me use karna

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    private final AppConfig appConfig;

    @Autowired
    public AppService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void printAppInfo() {
        System.out.println("Name: " + appConfig.getName());
        System.out.println("Timeout: " + appConfig.getTimeout());
        System.out.println("Version: " + appConfig.getVersion());
    }
}

#10. Real Interview Questions (PART-10)
Q: Can we change config without redeploy?
👉 Yes (external config)

Q: Where to keep secrets?
👉 Env variables / Vault

Q: Can we have DB config per profile?
👉 Yes




############################ PART-11 : Spring Boot Actuator (Complete + Real Use) ##############################################

#1. What is Spring Boot Actuator?

Actuator ek Spring Boot ka tool hai jo aapke application ke health aur internal info ko easily dikhata hai.

Socho: aapka app chal raha hai, aur aapko ye pata karna hai:
-> App ka status theek hai ya nahi?
-> Kitne requests aayi ya response time kaisa hai?
-> Memory ya CPU usage kitni hai?

Actuator ye sab ready-made endpoints provide karta hai, jaise:


| Endpoint            | Kya dikhaata hai                          |
| ------------------- | ----------------------------------------- |
| `/actuator/health`  | App ka health status (UP/DOWN)            |
| `/actuator/metrics` | Requests, memory, CPU, DB metrics         |
| `/actuator/env`     | Application ke environment variables      |
| `/actuator/info`    | App ke version, description, aur metadata |


#2. Actuator Dependency
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>


#3. Important Actuator Endpoints

| Endpoint          | Purpose      |
| ----------------- | ------------ |
| /actuator/health  | App health   |
| /actuator/info    | App info     |
| /actuator/metrics | JVM metrics  |
| /actuator/env     | Environment  |
| /actuator/beans   | Loaded beans |
| /actuator/loggers | Log levels   |


#4. Enable Actuator Endpoints

management.endpoints.web.exposure.include=health,info,metrics

OR all:

management.endpoints.web.exposure.include=*

⚠️ Production me * avoid karein.

#5. /health Endpoint

Response:

{
  "status": "UP"
}

With DB:

{
  "status": "UP",
  "components": {
    "db": { "status": "UP" }
  }
}

📌 Interview Tip

Health endpoint shows overall application status.

#6. Metrics Endpoint

Example:
/actuator/metrics/jvm.memory.used

Shows:
-> Heap
-> Non-heap
-> GC

#7. Real Interview Questions (PART-11)
Q: Difference between /health and /metrics?

👉 Health = status, Metrics = performance

Q: Can we add custom metrics?

👉 Yes (Micrometer)

Q: Actuator default port?

👉 Same as app

Q: Separate Actuator Port

👉 management.server.port=9001



####################################  PART-12 : Logging (Complete + Production Ready)  #########################################

#1️. What is Logging?

Logging = Application ke runtime behavior ko track karna

Use cases:
-> Debug issues
-> Monitor production
-> Audit events

📌 Interview line

#### Logging helps in debugging and monitoring applications.

#2. Default Logger in Spring Boot
👉 Logback (via SLF4J)

Dependency automatically aati hai:

spring-boot-starter-logging

#3. Logging Levels (VERY IMPORTANT)

| Level | Usage           |
| ----- | --------------- |
| TRACE | Very detailed   |
| DEBUG | Development     |
| INFO  | Business flow   |
| WARN  | Potential issue |
| ERROR | Failure         |

📌 Interview Tip

Use INFO in production, DEBUG in dev.

#4. How to Log in Code?

@Slf4j
@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {
        log.info("Fetching users");
        return service.getAll();
    }
}

OR

private static final Logger log = LoggerFactory.getLogger(UserController.class);


#5. Logging Configuration (application.properties)
logging.level.root=INFO
logging.level.com.myapp=DEBUG


#6. Logback Configuration File
logback-spring.xml

<configuration>
  <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>logs/app.log</file>
  </appender>

  <root level="INFO">
    <appender-ref ref="FILE"/>
  </root>
</configuration>

#7. Console vs File Logging

| Console    | File       |
| ---------- | ---------- |
| Dev        | Production |
| Short term | Long term  |

#8. Logging in Exception Handling
log.error("Error while saving user", ex);

❌ Never log sensitive data (passwords)

#9. Logging Best Practices (INTERVIEW GOLD)
✅ Use parameterized logs

log.info("User {} logged in", username);

❌ Avoid string concatenation

#10. Profile-based Logging
1. dev
logging.level.root=DEBUG

2. prod
logging.level.root=INFO

#11. Log Rotation (VERY IMPORTANT)

<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
  <fileNamePattern>logs/app-%d{yyyy-MM-dd}.log</fileNamePattern>
  <maxHistory>30</maxHistory>
</rollingPolicy>

#12. Real Interview Questions (PART-12)
Q: Why SLF4J?

👉 Logging abstraction

Q: Logback vs Log4j?

👉 Logback is default, faster

Q: How to reduce log size?

👉 Log rotation + proper levels




##############################  PART-13 = Testing in Spring Boot (Unit + Integration + Mockito)  ###########################

#

##Junit5 ==> Testing specific units of code independently.
Testing small parts(units) of a spring boot application, like methods or classes, to ensure work correct
Junit5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
1.JUnit Platform – Test ko discover aur run karta hai
2.JUnit Jupiter – Ye JUnit 5 ke liye new programming model aur extension model provide karta hai
3.JUnit Vintage – Purane JUnit 3/4 ke tests ko support karta hai

##Types of Software testing
1.Functional Testing (Validating Features)
-> It ensures the software works as expected
a.)Unit Testing -> Tests individual methods/classes (done by developers)
b.)Integration Testing -> Tests Interaction between multiple components
c.)System Testing -> Tests the whole application as a single unit.
d.)User Acceptance Testing (UAT) --> End-users test if the software meets business needs.

2.Non-Functional Testing (Performance & Security)
-> It ensures system reliability, performance, and security
a.)Performance Testing -> Checks speed & scalability.
b.)Load Testing -> Simulates real-world usage.
c.)Security Testing -> Identifies Vulnerabilities.
d.)Usability Testing -> Tests user-friendliness.

##Advantages ==> 
1.find bugs early.
2.Easy to fix bugs.
3.Reduce the cost and time.

##Annotations ==>
@Test               --> 	Is method ko test case banata hai
@BeforeEach	        -->     Har test se pehle ye method chalega
@AfterEach	        -->     Har test ke baad ye method chalega
@BeforeAll	        -->     Sabhi tests ke start hone se pehle chalega (static hona chahiye)
@AfterAll	        -->     Sabhi tests ke baad chalega (static hona chahiye)
@DisplayName	    -->     Test ka readable naam dikhata hai
@Disabled	        -->     Kisi test ko temporarily disable karne ke liye
@Nested	            -->     Nested test classes define karne ke liye
@Tag	            -->     Tests ko tag karne ke liye (filtering ke liye helpful)
@ParameterizedTest	-->     Multiple values ke sath test run karne ke liye
@ValueSource	    -->     @ParameterizedTest me values dene ke liye use hota hai



##Mockito -->
Mockito is used with JUnit 5 to mock dependencies in unit tests so that you can test a class in isolation without relying on real implementations.
Mockito ek mocking framework hai jo humare test me fake objects (mocks) banata hai taaki hum dependencies ke bina apni class test kar sakein.

##Annotations ==>
1.@ExtendWith(MockitoExtension.class)      -->       Mockito ko JUnit 5 ke saath integrate karne ke liye extension use kiya gaya.(Mockito extension ko enable karna), 
  Must be added at the class level.,Mockito ke annotations (@Mock, @InjectMocks, etc.) ko initialize karna.
2.@Mock	                                 -->       Creates a mock instance of a class/interface.(Iska use as: categoryRepository ko mock karne ke liye kiya gaya.)
3.@InjectMocks	                         -->       Injects mock dependencies into the class under test.(Mocked dependencies ko real class me inject karta hai)
4.when(...).thenReturn(...)              -->       Mock method ka behavior set karta hai
5.verify(...)                            -->       Check karta hai ki method call hua ya nahi
6.MockMvc                                -->       MockMvc ek Spring Test utility hai jo aapko HTTP requests aur responses ko simulate karne ka mauka deta hai — 
  bina actual web server (Tomcat, Jetty, etc.) ko start kiye.
7.@WebMvcTest                            -->       It’s used to test only the controller layer in Spring Boot (without starting full context).
8.MockMvcBuilders.standaloneSetup()      -->       Setup controller test without Spring context
9.mockMvc.perform(...)                   -->       HTTP request simulate karna
10.@DataJpaTest                           -->      Used for testing Spring Data JPA repositories using h2 database.
@Spy	                                 -->       Wraps a real object, allowing some real methods and some mocked.
@Captor	                                 -->       Creates an ArgumentCaptor for capturing method arguments.
@MockBean	                             -->       (Spring Boot only) Mocks a bean in the Spring application context.



===========>
1.Unit Test	                                        -->       Tests a single method/class in isolation (no DB or server)
2.Integration Test	                                -->       Tests multiple components together (like controller + DB)

@Mock==>
@Mock fake (dummy) object banata hai.
CategoryRepository ka real code nahi chalega
Sirf ek fake object milega
Iska use categoryRepository ko mock karne ke liye kiya gaya.
	
@InjectMocks ==>	
@InjectMocks real object banata hai
aur uske andar jo dependencies hain, unme @Mock wale objects inject kar deta hai.
Mocked dependencies ko real class me inject karta hai

#

#1. Why Testing is Important?

-> Bugs jaldi milte hain
-> Regression avoid hota hai
-> Production stability


📌 Interview line

Testing improves code quality and reliability.

#2. Types of Testing

| Type        | Purpose             |
| ----------- | ------------------- |
| Unit        | Single class        |
| Integration | Multiple components |
| System      | End-to-end          |


#3️. Unit Testing in Spring Boot
👉 Focus:

-> Service layer
-> Business logic

#4️. Mockito Basics
Mockito = Mock objects banata hai

Dependency:

spring-boot-starter-test

#5. @Mock vs @MockBean (VERY IMPORTANT)

| @Mock        | @MockBean        |
| ------------ | ---------------- |
| Pure Mockito | Spring context   |
| Unit test    | Integration test |


#6.Real Interview Questions (PART-13)
Q: Unit vs Integration?

👉 Unit = fast, isolated
👉 Integration = slow, real beans

Q: Why Mockito?

👉 To isolate dependencies

Q: Why @MockBean?

👉 Replace Spring beans



###################################### PART-14 = Build & Deployment (Maven, JAR/WAR, Docker) ########################################

#1. What is Maven?

Maven ek => build automation tool hai
Matlab — ye aapke Java project ko banane (build karne), manage karne aur package karne me help karta hai.

Maven ek tool hai jo project ki libraries manage karta hai aur project ko build karke runnable file(.war ya .jar) banata hai.

#2. aven Lifecycle
clean → compile → test → package → install → deploy

#3. pom.xml (VERY IMPORTANT)
Contains:
-> Project info
-> Dependencies
-> Plugins

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
</dependencies>

#4. Maven Plugins
Spring Boot Plugin

<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>

Purpose:
-> Create executable JAR
-> Run app

#5. Gradle vs Maven

| Maven      | Gradle        |
| ---------- | ------------- |
| XML        | Groovy/Kotlin |
| Slower     | Faster        |
| Convention | Flexible      |


#6. JAR vs WAR (INTERVIEW FAVORITE)

| JAR                 | WAR             |
| ------------------- | --------------- |
| Standalone          | External server |
| Embedded server     | Needs Tomcat    |
| Spring Boot default | Legacy          |

📌 Interview line

Spring Boot promotes executable JAR.

#7. Embedded Server Deployment
java -jar app.jar

Default server:
👉 Tomcat

#8. External Server Deployment (WAR)
Steps:
-> Extend SpringBootServletInitializer
-> Package as WAR
-> Deploy on Tomcat

#9. Docker with Spring Boot (Basic)
Dockerfile Example

FROM openjdk:17
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]


Build & Run => 

docker build -t springboot-app .
docker run -p 8080:8080 springboot-app

📌 Interview line

Docker provides containerized deployment.

#10. Real Interview Questions (PART-14)
Q: Why Docker?

👉 Environment consistency

Q: JAR or WAR preferred?

👉 JAR

Q: How to reduce image size?

👉 Multi-stage build



######################################  PART-15 : Advanced / Scenario + HR + Project Explanation  #########################################

#1️. Why Spring Boot Application Becomes Slow?
Reasons:
-> Too many beans
-> Heavy auto-configuration
-> Large context loading
-> Unused dependencies
-> DB connection issues


Solution:
-> Lazy loading
-> Exclude auto-config
-> Optimize queries
-> Reduce scanning packages

📌 Interview Line

Application slowness is often due to heavy context and DB inefficiencies.

#2. How to Solve Memory Issues?
-> Heap dump analysis
-> Proper GC
-> Close DB connections
-> Avoid static collections

📌 Tools:
-> JVisualVM
-> JProfiler

#3. Bean Conflict (VERY COMMON)
Problem:

Two beans of same type

Solution:
@Primary
@Bean

OR

@Qualifier("beanName")
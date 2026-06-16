#1.Microservice
Microservices is a software architecture where a big application is divided into many small independent services.
-> Each service does one specific job and can work, update, and deploy separately.
-> Services communicate with each other using HTTP APIs (REST APIs).
-> Each service runs independently.

##Example:
In an online shopping app:
-> User Service → manages users
-> Product Service → manages products
-> Order Service → manages orders

#2:service registry and Service Discovery
##2.1: service registry
A Service Registry is a place where all microservices register their addresses.
##2.2: Service Discovery
Service Discovery helps one microservice find another microservice automatically.

####Example:
-> Order Service wants to call Product Service.
-> Instead of remembering IP addresses, it asks the Service Registry.
-> Registry gives the current address of Product Service.

#3.API Gateway
API Gateway is a single entry point for all client requests in microservices.
It receives requests from users and sends them to the correct microservice.

##Responsibilities:
-> Routing requests
-> Security
-> Authentication
-> Load balancing
-> Logging

##Example:
Mobile app → API Gateway → Different microservices

#4.RestTemplate and Feign Client
Both are used for communication between microservices.
##4.1: RestTemplate
RestTemplate is a class used to call REST APIs manually.
###Example:
restTemplate.getForObject(url, Product.class);

#4.2: Feign Client
Feign Client is a declarative way to call APIs.
You only create an interface, and Feign handles the rest automatically.
###Example:
@FeignClient(name="PRODUCT-SERVICE")
public interface ProductClient {
    @GetMapping("/product")
    Product getProduct();
}

#5.Config Server
Config Server is used to manage configuration files of all microservices from one central place.
###Example:
Instead of storing:
-> database URL
-> passwords
-> port numbers
inside every service separately, all configs are stored in one Config Server.
-> This makes management easier.

#6.Slueth and zipkin
These are used for **distributed tracing** in microservices.
##6.1: Sleuth
-> Sleuth adds unique trace IDs and span IDs to requests.
-> It helps track a request flow between services.

##6.2: Zipkin
-> Zipkin collects and shows tracing information visually.

###It helps developers:
-> find slow services
-> debug errors
-> monitor request flow

###Example:
User Request:
API Gateway → Order Service → Payment Service → Product Service

-> Zipkin shows the complete path of the request.



#7.Fault Tolerance(Resilience4j, Hystrix) => Fault tolerance concept hai, Resilience4j tool/library hai
-> Fault Tolerance means a system can continue working even if some services fail.
-> Resilience4j and Hystrix are libraries/tools used to handle failures in microservices.
###Example:
-> If Payment Service is down, the application should not completely crash.

##7.1-> Circuit Breaker
-> Circuit Breaker ka kaam hota hai: Agar koi service baar-baar fail ho rahi hai to uske calls temporarily band kar do.
-> Circuit Breaker stops repeated calls to a failed service for some time.

###Example:
If Product Service fails many times:
-> Circuit Breaker opens
-> Further requests are blocked temporarily
-> System avoids overload
Like an electric circuit breaker in homes.

###States:
1. Closed → Sab kuch sahi, request forward hoti hai.
2. Open → Bahut failures, request ko forward hi nahi karta (direct fallback).
3. Half-Open → Retry karta hai dekhne ke liye service wapas sahi hui ya nahi.

##7.2-> Retry
-> Retry automatically tries the failed request again.
-> Jab service fail ho, to ek ya zyada baar automatically retry karna.
-> Kabhi-kabhi network ya temporary issue hota hai jo ek retry me theek ho jaata hai.

###Example:
If a service fails because of a temporary network issue:
-> Retry sends the request again 2–3 times.
-> Useful for temporary failures.

##7.3-> Rate Limiter
-> Rate Limiter controls how many requests users can send in a specific time.
-> Ek service ko limited number of requests hi allow karta hai ek samay me.

###Example:
A user can send:
-> only 100 requests per minute.
-> This protects the system from overload.

##7.4-> Bulkhead
-> Bulkhead isolates resources so one failure does not affect the whole system.
-> Har service ke liye alag-alag thread pool ya isolation.
-> Isse ek service agar overload ho to dusri services affect na ho.
-> Socho ek ship me compartments hote hain — ek leak ho to poora ship na doobe.

###Example:
If one service becomes slow:
-> only that part is affected
-> other services continue working normally.

##7.5-> Fallback
-> Fallback provides an **alternative response** when a service fails.
-> Jab koi call fail ho jaaye (due to timeout, error, etc.) to ek alternative response dena.
-> Fallback method aap define karte ho.

#8.RabbitMQ
-> RabbitMQ is a **message broker(dalal)** used for communication between services asynchronously.
-> It sends messages using queues.
-> Message consume hone ke baad delete ho jata hai

###Example:
Order Service → sends message → Queue → Email Service

Email Service processes it later.

###Benefits:
-> Loose coupling
-> Fast communication
-> Better performance

#9.Apache kafka (alternative of RabbitMQ)
-> Apache Kafka is a distributed messaging system used for high-speed data streaming.
-> It is an alternative to RabbitMQ and handles very large amounts of data.
-> Apache Kafka ek "distributed event streaming platform(Real-time data streaming)" hai jo large-scale data ko publish, store, aur subscribe karne ke liye use hota hai.
-> Message delete nahi hota, store rehta hai

Example:
-> CCTV recording
-> Banking transactions
-> Real-time notifications
-> Live analytics

Kafka is very fast and scalable.

#10.Spring Cloud LoadBalancer (Ribbon – Deprecated)
-> Spring Cloud LoadBalancer distributes requests among multiple instances of a service.
-> **Ribbon** was the old load balancer, now deprecated.

###Example:
If Product Service has:
-> Server 1
-> Server 2
-> Server 3

LoadBalancer sends requests equally to all servers.

###This improves:
-> performance
-> availability
-> scalability

#11.Spring cloud
-> Spring Cloud is a collection of tools used to build microservices easily.

###It provides features like:
-> Service Discovery
-> API Gateway
-> Config Server
-> Load Balancing
-> Circuit Breaker

###Example:
-> Spring Cloud helps developers manage complex microservice systems easily.

#12.Centralized logging in Microservices (ELK Stack (ElaticSearch, Logstash, Kibana))
-> Centralized Logging means collecting logs from all microservices in one place.
-> ELK Stack is commonly used for this.

##ELK Stack Components
###1.Elasticsearch
 Stores and indexes logs.

###2. Logstash
Processes and ships logs.

###3. Kibana
Shows logs in dashboards and graphs.

#13. Microservices communication with each other -> 
1. Synchronous -> Rest Apis- HTTP Based (RestTemplate, OpenFeign)
2. Asynchronous -> Message Brokers like RabbitMQ and Apache kafka

#14. Short One-Line Definitions

| Topic                     | One-Line Simple Definition                                     |
| ------------------------- | -------------------------------------------------------------- |
| Microservices             | Small independent services working together in one application |
| Service Registry          | A place where all microservices register themselves            |
| Service Discovery         | Automatically finding other microservices                      |
| API Gateway               | Single entry point for all client requests                     |
| RestTemplate              | Manual way to call REST APIs                                   |
| Feign Client              | Interface-based easy REST API caller                           |
| Config Server             | Centralized configuration manager for microservices            |
| Sleuth                    | Adds trace IDs to track requests between services              |
| Zipkin                    | Visual tool to monitor request flow in microservices           |
| Fault Tolerance           | Ability of system to continue working after failures           |
| Resilience4j              | Modern fault tolerance library for microservices               |
| Hystrix                   | Netflix fault tolerance library (now deprecated)               |
| Circuit Breaker           | Stops repeated calls to failed services temporarily            |
| Retry                     | Automatically retries failed requests                          |
| Rate Limiter              | Controls number of requests in a time limit                    |
| Bulkhead                  | Isolates failures so one service does not affect others        |
| Fallback                  | Provides alternative response when service fails               |
| RabbitMQ                  | Queue-based message broker for asynchronous communication      |
| Apache Kafka              | High-speed distributed event streaming platform                |
| Spring Cloud LoadBalancer | Distributes requests among multiple service instances          |
| Ribbon                    | Old client-side load balancer (deprecated)                     |
| Spring Cloud              | Collection of tools for building microservices                 |
| Centralized Logging       | Collecting logs from all services in one place                 |
| ELK Stack                 | Logging stack using Elasticsearch, Logstash, and Kibana        |
| Elasticsearch             | Stores and searches logs/data                                  |
| Logstash                  | Collects and processes logs                                    |
| Kibana                    | Dashboard tool for visualizing logs and monitoring             |


#15. Simple one-line summary:
Kafka → services ke beech data/events bhejne ke liye
Docker → app ko container me pack/run karne ke liye
Kubernetes → containers ko manage & scale karne ke liye

#16. learn these topics also

| Topic                                     | Why Important                            |
| ----------------------------------------- | ---------------------------------------- |
| Database per Service                      | Most asked microservice design concept   |
| Synchronous vs Asynchronous Communication | Very common interview question           |
| DTO and Entity                            | Used in API communication                |
| Distributed Transactions                  | Advanced microservice concept            |
| Saga Pattern                              | Important for transaction management     |
| Event-Driven Architecture                 | Kafka/RabbitMQ based systems             |
| API Communication Types                   | REST vs gRPC vs Messaging                |
| Docker                                    | Deployment of microservices              |
| Kubernetes                                | Managing containers at scale             |
| Monitoring                                | Production-level microservice management |
| Actuator                                  | Health check and monitoring              |
| Security (JWT/OAuth2)                     | Authentication & Authorization           |
| API Versioning                            | Managing API changes                     |
| Idempotency                               | Prevent duplicate operations             |
| CAP Theorem                               | Distributed systems concept              |
| Distributed Caching (Redis)               | Performance optimization                 |
| CI/CD Pipeline                            | Deployment automation                    |
| Blue-Green / Canary Deployment            | Zero downtime deployment                 |
| OpenAPI / Swagger                         | API documentation                        |
| Database Scaling                          | Sharding, Replication basics             |


#####################  6. Fault Tolerance  #####################

✅ Fault Tolerance kya hota hai?
Fault Tolerance ek concept hai, koi library nahi.
Iska matlab hota hai:
"System tab bhi sahi kaam kare jab koi component fail ho jaye.(mtlb agar ek service or api fail hone pr other services pr effect n pade) "
Microservices me, fault tolerance important hai kyunki:
1.Har service network ke through connect hoti hai
2.Agar ek service down ho jaaye, to dusri services bhi fail ho sakti hain
3.Isiliye hum chahte hain ki agar koi service temporarily fail ho, to hamara system gracefully handle kare (e.g. retry kare, fallback response de, etc.)

🛠️ Resilience4j (ya "R4j") kya hai?
Resilience4j ek Java library hai jo microservices me fault tolerance mechanisms provide karti hai.

✅ Resilience4j tool/library hai
✅ Fault tolerance concept hai

So, Resilience4j = Tool to implement Fault Tolerance

🔁 Fault Tolerance ke Major Patterns (with Explanation):
Ab hum explain karte hain ye 5 features:

1. 🧯 Circuit Breaker
Circuit Breaker ka kaam hota hai: Agar koi service baar-baar fail ho rahi hai to uske calls temporarily band kar do.

Ye ek electric circuit jaise kaam karta hai:

States:

🔓 Closed → Sab kuch sahi, request forward hoti hai.

🔌 Open → Bahut failures, request ko forward hi nahi karta (direct fallback).

🔄 Half-Open → Retry karta hai dekhne ke liye service wapas sahi hui ya nahi.

Example:

@CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackMethod")
public String getInventory() {
    // risky call
}

2. 🔁 Retry
Jab service fail ho, to ek ya zyada baar automatically retry karna.

Kabhi-kabhi network ya temporary issue hota hai jo ek retry me theek ho jaata hai.

Example:

@Retry(name = "paymentService", fallbackMethod = "fallbackPayment")
public String makePayment() {
    // risky call
}

3. 🚦 Rate Limiter
Ek service ko limited number of requests hi allow karta hai ek samay me.

DDoS ya overload se bachaata hai.

Example:

@RateLimiter(name = "userService")
public String getUserData() {
    // limited access
}

4. 🧱 Bulkhead
Har service ke liye alag-alag thread pool ya isolation.

Isse ek service agar overload ho to dusri services affect na ho.

Socho ek ship me compartments hote hain — ek leak ho to poora ship na doobe.

Example:

@Bulkhead(name = "productService", type = Bulkhead.Type.THREADPOOL)
public String getProductDetails() {
    // isolated threads
}

5. 🧰 Fallback
Jab koi call fail ho jaaye (due to timeout, error, etc.) to ek alternative response dena.

Fallback method aap define karte ho.

Example:

@CircuitBreaker(name = "inventory", fallbackMethod = "defaultInventory")
public String getInventory() {
    // risky call
}

public String defaultInventory(Throwable t) {
    return "Inventory service unavailable";
}


🔚 Summary Table
Feature	                                 Purpose	                                       Real-World Analogy
Circuit Breaker	             Repeated failure ke baad calls band karna	          Electrical fuse
Retry	                     Fail hone par phir se try karna	                  Redialing a phone call
Rate Limiter	             Request limit lagana	                          Toll booth with limited entry
Bulkhead	             Resource isolation(thread pool)	                                  Ship compartments
Fallback	             Default response dena failure ke time	          Backup plan when main fails


Note --> Hystrix bhi R4j jesa same hi h, but ab yah legacy(purana) ho gya aur iska use nhi krte h.


EXAMPLE ==> 

1. application.yml

resilience4j:
  circuitbreaker:
    instances:
      inventoryCB:
        slidingWindowSize: 5
        failureRateThreshold: 50
        waitDurationInOpenState: 5s
  retry:
    instances:
      paymentRetry:
        maxAttempts: 3
        waitDuration: 1s
  ratelimiter:
    instances:
      userRateLimiter:
        limitForPeriod: 2
        limitRefreshPeriod: 5s
        timeoutDuration: 0
  bulkhead:
    instances:
      productBulkhead:
        maxThreadPoolSize: 2
        coreThreadPoolSize: 1
        queueCapacity: 2


2. TestController.java

@RestController
@RequestMapping("/api")
public class TestController {

    private AtomicInteger counter = new AtomicInteger();

    // ✅ CIRCUIT BREAKER + FALLBACK
    @GetMapping("/inventory")
    @CircuitBreaker(name = "inventoryCB", fallbackMethod = "inventoryFallback")
    public String getInventory() {
        throw new RuntimeException("Inventory service failed");
    }

    public String inventoryFallback(Throwable t) {
        return "Fallback: Inventory temporarily unavailable";
    }

    // ✅ RETRY + FALLBACK
    @GetMapping("/payment")
    @Retry(name = "paymentRetry", fallbackMethod = "paymentFallback")
    public String makePayment() {
        if (counter.incrementAndGet() < 3) {
            throw new RuntimeException("Temporary Payment Error");
        }
        return "Payment Success!";
    }

    public String paymentFallback(Throwable t) {
        return "Fallback: Payment service down";
    }

    // ✅ RATE LIMITER
    @GetMapping("/user")
    @RateLimiter(name = "userRateLimiter")
    public String getUser() {
        return "User data";
    }

    // ✅ BULKHEAD
    @GetMapping("/product")
    @Bulkhead(name = "productBulkhead", type = Bulkhead.Type.THREADPOOL)
    public String getProduct() throws InterruptedException {
        Thread.sleep(1000); // simulate delay
        return "Product info";
    }
}


################### 7. RabbitMQ  ##################
🐇 RabbitMQ – Definition
RabbitMQ ek "message broker(dalal)" hai jo services ke beech asynchronous message passing karta hai using queues.
Message consume hone ke baad delete ho jata hai
ex->Post Office

🎯 Use in Microservices:
Service-to-service communication (decoupling)

Retry, delayed messages

Task queues (e.g., send email, log events)

###########  8. Apache kafka (alternative of RabbitMQ) #############
🐘 Apache Kafka – Definition
Apache Kafka ek "distributed event streaming platform(Real-time data streaming)" hai jo large-scale data ko publish, store, aur subscribe karne ke liye use hota hai.
Message delete nahi hota, store rehta hai
ex->CCTV recording

🎯 Use in Microservices:
Real-time event processing (e.g., activity logs, analytics)

Event-driven architecture (event sourcing, CQRS)

High-throughput messaging between services

############ 9. Spring Cloud LoadBalancer #############

==========> Note start
🔀 Ribbon – Deprecated
Ribbon ek client-side load balancer tha jo microservices ke multiple instances me load distribute karta tha.
❌ Netflix ne ise deprecate kar diya hai.

==========> Note end

✅ Latest Replacement – Spring Cloud LoadBalancer
Spring Cloud LoadBalancer ek lightweight client-side load balancer hai jo Ribbon ka modern alternative hai, and Spring Boot ke saath natively support karta hai.

🎯 Use in Microservices:
Load balancing between service instances (like user-service:8081, 8082, etc.)

Works with service discovery tools like Eureka

Retry, failover handling during service call



📌 Summary:
Tool	                              Definition	                      Microservices Use
RabbitMQ	                 Message broker using queues	        Asynchronous messaging, task processing
Apache Kafka	             Distributed event streaming platform	Real-time event-driven systems
Spring Cloud LoadBalancer	 Load balancer (Ribbon replacement)	Distribute REST calls to multiple service instances



EXAMPLE --> 
✅ Scenario: Simple E-commerce System
Services:
order-service — Order create karta hai
payment-service — Load-balanced service jo payment process karta hai
notification-service — RabbitMQ ya Kafka se message receive karke email bhejta hai

🧩 1. RabbitMQ Use (Spring Boot)
➤ order-service (Producer):
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        rabbitTemplate.convertAndSend("order.exchange", "order.key", order);
        return "Order Placed!";
    }
}


➤ Configuration:
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

➤ notification-service (Consumer):
@RabbitListener(queues = "order.queue")
public void handleOrder(Order order) {
    System.out.println("Sending email for order: " + order.getId());
}


🐘2. Kafka Example
➤ order-service sending Kafka event: (Producer)
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

@PostMapping("/kafka")
public String sendKafka() {
    kafkaTemplate.send("orders", "Order placed: #123");
    return "Kafka event sent!";
}

➤ notification-service receiving Kafka event: (Consumer)
@KafkaListener(topics = "orders", groupId = "notify-group")
public void consume(String message) {
    System.out.println("Kafka received: " + message);
}

🔁 3. Spring Cloud LoadBalancer Use (using RestTemplate)
➤ order-service calling payment-service:
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/pay")
    public String makePayment() {
        return restTemplate.getForObject("http://payment-service/pay", String.class);
    }
}


➤ RestTemplate Bean with LoadBalancer:
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}

Note ==> ✅ we can use LoadBalancer with OpenFeign + Spring Cloud LoadBalancer Example
🎯 Scenario:
order-service → Call karega → payment-service (multiple instances with LoadBalancer)

⚙️ Step 1: Add Dependencies (pom.xml)
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>

🧩 Step 2: Enable Feign in order-service
@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

🧩 Step 3: Create Feign Client for payment-service
@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/pay")
    String makePayment();
}

⚙️ name = "payment-service" should match with the registered name in Eureka or service discovery, or your hosts if static.

🧩 Step 4: Use the Feign Client in Controller
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PaymentClient paymentClient;

    @GetMapping("/pay")
    public String placeOrderAndPay() {
        return paymentClient.makePayment();
    }
}

📌 Eureka Optional (for Discovery)
If you're using Eureka, the LoadBalancer will resolve the service name to the proper instance. Otherwise, you can define static URLs using properties:
payment-service:
  ribbon:
    listOfServers: localhost:8082,localhost:8083

(If not using Eureka, but Ribbon is deprecated, better define a custom LoadBalancer configuration instead.)

Note ==>> 
payment-service is not a real URL

Spring Cloud LoadBalancer intercepts this request

It:

Resolves "payment-service" to available service instances (e.g., from Eureka)

Selects one instance (e.g., localhost:8082)

Routes the request to that instance

Automatically handles retries / failover if configured

==> So LoadBalancer works implicitly with OpenFeign — you don’t need to configure it manually like with @LoadBalanced RestTemplate.


###############################
Microservices communication with each other -> 
1. Synchronous -> Rest Apis- HTTP Based (RestTemplate, OpenFeign)
2. Asynchronous -> Message Brokers like RabbitMQ and Apache kafka

Spring cloud ->
spring cloud is a framework that provides tools for developers to quickly build microservices. its support service discovery, circuit breakers, config server server, API gateway etc.

Centralized logging in Microservices ->
collecting logs from multiple services into a single system like ELK Stack (ElaticSearch, Logstash, Kibana) or EFK(with Fluentd) for easier monitoring and debugging.
ElaticSearch - Stores and indexes logs
Logstash - Processes and ships logs
Kibana - Visualizes logs on a dashboard
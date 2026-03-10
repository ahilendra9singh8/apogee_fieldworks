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
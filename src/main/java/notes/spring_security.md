🌐 Spring Security ==>
Spring Security is a powerful and customizable authentication and access-control framework for securing Spring-based applications.
It handles Authentication (who are you?) and Authorization (what can you do?).
1.Basic Authentication
2.Token-Based Authentication
3.OAuth2

############################### 🔒 1. Basic Authentication
🧩 Concept:
Basic Authentication is the simplest method of securing APIs.
The client sends the username and password in every request’s HTTP Authorization header encoded in Base64 format.

Authorization: Basic base64(username:password)

Example:
Authorization: Basic cmFqdTpyYWp1MTIzNDU=

⚙️ How it works:
1.Client → sends a request with Authorization: Basic <encoded_credentials>.
2.Server → decodes Base64 string → verifies credentials.
3.If valid → allows access, otherwise → returns HTTP 401 Unauthorized.
4.Every request repeats this process → stateless (no session maintained).

✅ Advantages:
1.✅ Very simple to implement.
2.✅ Stateless → no server-side session storage.
3.✅ Works well for internal APIs or small projects.
4.✅ Minimal configuration (works automatically once dependency is added).

⚠️ Limitations:
1.❌ Credentials are only Base64 encoded, not encrypted.
2.❌ Needs HTTPS for security (to avoid credentials leaking in transit).
3.❌ Not ideal for large-scale, public applications.
4.❌ User has to send credentials with every request.
5.Credentials are easily decoded, so its important to use HTTPS.

🧱 Dependency (Required)
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-security</artifactId>
</dependency>

⚙️ 3 Ways to Use Basic Authentication in Spring Boot

🟩 1. Default Security (Out-of-the-box)
When you add the dependency, Spring Security auto-configures Basic Auth.
->Default username → user
->Password → shown in console log (auto-generated)

->Using generated security password: 69095b0f-9b74-40d3-a79d-f9f1b4073315

->All endpoints require authentication.

🟩 2. Define credentials in application.properties
->spring.security.user.name=raju
->spring.security.user.password=raju12345

→ Simple for testing or small internal apps.

🟩 3. Custom Security Configuration (Recommended)
When you need to control which endpoints are public or restricted,
you create a SecurityConfig class.

🧰 Example: SecurityConfig.java

package fieldworks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF (useful for REST APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/register", "/user/login").permitAll() // Public endpoints
                .requestMatchers(HttpMethod.GET, "/**").permitAll() // Allow all GET requests
                .anyRequest().authenticated() // Authentication required for others
            )
            .httpBasic(Customizer.withDefaults()); // Enable Basic Auth (browser popup or header)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



🔐 Important Terms
Term	                                                           Description
Authentication	                                 Verifying user identity (e.g., login with username/password).
Authorization	                                 Granting or denying access to specific resources based on roles.
PasswordEncoder	                                 Encrypts passwords before storing (BCrypt recommended).
CSRF	                                         Cross-Site Request Forgery protection (disabled for REST APIs).
Stateless	                                     Each request is independent (no server session maintained).



💬 Example Interview Questions

Q1. What is Basic Authentication?
→ It’s a simple mechanism where credentials (username/password) are sent in the Authorization header encoded in Base64 with every request.

Q2. Is Basic Auth stateful or stateless?
→ Stateless — server doesn’t maintain session information.

Q3. Why is HTTPS required for Basic Auth?
→ Because credentials are easily decoded from Base64; HTTPS encrypts the entire communication.

Q4. How can we configure Basic Auth in Spring Boot?
→ 3 ways:
1.Default user generated by Spring
2.Hardcoded credentials in application.properties
3.Custom SecurityConfig class with HttpSecurity configuration.

Q5. What is the role of PasswordEncoder?
→ It encrypts passwords using algorithms like BCrypt before comparing with stored values in the database.

Q6. When to use Basic Authentication?
→ “Basic Authentication is best suited for small internal applications or microservices where HTTPS is guaranteed and token-based systems (like JWT) are unnecessary overhead.”

	
	
	
	
	 
 
 ###################################### ⚙️ 2. JWT – Token-Based Authentication
 
🔑 Concept:
👉 JWT (JSON Web Token) ek secure token-based authentication mechanism hai, jisme user username & password se login karta hai.
Server ek token generate karta hai aur client ko send karta hai.
Client jab bhi koi protected API call karega, wo token header ke “Authorization” field me send karega.

Server us token ko verify karega → agar valid hai → access milega,
agar invalid hai → 401 Unauthorized response milega.


💡 Basic Understanding:
JWT = JSON Web Token
->Secure way to share user info between client & server.
->Mostly used for stateless authentication in REST APIs.

🧱 JWT Structure:
A JWT has three parts, separated by dots (.):
Header.Payload.Signature

1.Header → Token type & algorithm info
Example: { "alg": "HS256", "typ": "JWT" }

2.Payload → User data (claims) like username, roles, etc.
Example: { "sub": "raju@gmail.com", "roles": ["ROLE_ADMIN"], "exp": "..." }

3.Signature → Secret key se sign hoti hai to ensure token is not tampered. This is like the seal of approval, ensuring the tokens integrity.

"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTc2NzE2MTMwMiwianRpIjoiMmUxNTZjMjUtYzUyYi00NTExLWI1NDUtZmI3NzAyMGQ3OTU3IiwidHlwZSI6ImFjY2VzcyIsInN1YiI6eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJzdGFrZWhvbGRlcjEiLCJyb2xlIjoidXNlciJ9LCJuYmYiOjE3NjcxNjEzMDIsImV4cCI6MTc2NzE2ODUwMn0.
UBbxHFs37hwQrKaW0F5h7niX_mnCKO3q61B0i-DozKs"


🧩 Dependency for JWT

 <dependency>
   <groupId>io.jsonwebtoken</groupId>
   <artifactId>jjwt-api</artifactId>
   <version>0.11.5</version>
</dependency>
<dependency>
   <groupId>io.jsonwebtoken</groupId>
   <artifactId>jjwt-impl</artifactId>
   <version>0.11.5</version>
   <scope>runtime</scope>
</dependency>
<dependency>
   <groupId>io.jsonwebtoken</groupId>
   <artifactId>jjwt-jackson</artifactId>
   <version>0.11.5</version>
   <scope>runtime</scope>
</dependency>
 
 
🧠 Flow of JWT Authentication
1️⃣ User Registration → Save user (with encrypted password)
2️⃣ User Login → Validate username/password → Generate JWT token
3️⃣ Access API → Send token in Header → Backend verifies token
4️⃣ Authorization → Allow/deny based on token roles


🧾 Step-by-Step Implementation

✅ Step 1 – Employee Registration


File: EmployeeController.java

@PostMapping("/register")
	public ResponseEntity<ApiResponse<Employee>> register(@Valid @RequestBody EmployeeDTO reqEmpDTO) {
	
		Employee emp = service.register(reqEmpDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created("Employee registered", emp));
	}
	

Request Example:

{
  "name": "stakeholder user 1",
  "user_name": "stakeholder1",
  "password": "test123",
  "contact_number": "9876543211",
  "role": "stakeholder",
  "superior_fk_id": "",
  "privilege": "user"
}


✅ Step 2 – Save user in database

File: EmployeeService.java

public Employee register(EmployeeDTO req) {

		if (repo.findByContactNumber(req.getContact_number()).isPresent()) {
			throw new RuntimeException("User already exists");
		}

		Employee emp = new Employee();
		emp.setName(req.getName());
		emp.setUserName(req.getUser_name());
		emp.setPassword(passwordEncoder.encode(req.getPassword()));
		emp.setContactNumber(req.getContact_number());
		emp.setContactEmail(req.getContact_email());
		emp.setAuthToken(req.getAuth_token());
		emp.setActive(req.getActive());
		emp.setIsAvailable(req.getIsAvailable());
		emp.setRemark(req.getRemark());
		emp.setRole(req.getRole());
		emp.setSuperiorFkId(req.getSuperior_fk_id());
		emp.setPrivilege(req.getPrivilege());

		if (req.getCreated_by() != null)
			emp.setCreatedBy(req.getCreated_by());

		return repo.save(emp);
}

File: Employee.java
Contains: id, username, password, role, privilege.

File: EmployeeRepository.java
Optional<Employee> findByUserName(String userName);



✅ Step 3 – JWT Token Generation (Login)

File: EmployeeController.java

@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

		Map<String, Object> response = new HashMap<>();
		try {
			Map<String, Object> serviceResponse = service.login(request);
			int code = (int) serviceResponse.get("responseCode");
			return ResponseEntity.status(code == 0 ? 200 : 401).body(serviceResponse);
		} catch (Exception ex) {
			response.put("title", "Error");
			response.put("message", "Internal Server Error");
			response.put("responseCode", 1);
			return ResponseEntity.status(500).body(response);
		}
	}


File: EmployeeService.java

   public Map<String, Object> login(Map<String, String> request) {

		Map<String, Object> response = new HashMap<>();
		Map<String, Object> payload = new HashMap<>();
		try {
			String username = request.get("user_name");
			String password = request.get("password");
			if (username == null || password == null) {
				response.put("title", "Error");
				response.put("message", "Username & password required");
				response.put("responseCode", 1);
				return response;
			}
			// Fetch user from DB
			Optional<Employee> opt = repo.findByUserName(username);
			if (opt.isEmpty()) {
				response.put("title", "Error");
				response.put("message", "User not found");
				response.put("responseCode", 1);
				return response;
			}
			Employee user = opt.get();
			// Password check (BCrypt)
			if (!passwordEncoder.matches(password, user.getPassword())) {
				response.put("title", "Error");
				response.put("message", "Invalid password");
				response.put("responseCode", 1);
				return response;
			}
			if (user.getRole() == null) {
				response.put("title", "Error");
				response.put("message", "Role not assigned to user");
				response.put("responseCode", 1);
				return response;
			}
			String actualRole = user.getRole().toLowerCase();
			String systemRole;
			if (List.of("pd", "ro", "gm", "cgm", "member").contains(actualRole)) {
				systemRole = "admin";
			} else if (actualRole.equals("stakeholder")) {
				systemRole = "user";
			} else {
				response.put("title", "Error");
				response.put("message", "Invalid role: " + actualRole);
				response.put("responseCode", 1);
				return response;
			}
			// Custom Token Payload
			Map<String, Object> tokenPayload = new HashMap<>();
			tokenPayload.put("user_id", user.getId());
			tokenPayload.put("user_name", user.getUserName());
			tokenPayload.put("role", "ROLE_" + systemRole.toUpperCase());
			String token = jwtUtil.generateCustomToken(tokenPayload, user.getUserName());
			payload.put("token", token);
			response.put("title", "Success");
			response.put("message", "Login successful");
			response.put("payload", payload);
			response.put("responseCode", 0);
			return response;
		} catch (Exception e) {
			response.put("title", "Error");
			response.put("message", "Internal Server Error");
			response.put("responseCode", 1);
			return response;
		}
	}
	
	
File: EmployeePrincipal.java	

public class EmployeePrincipal implements UserDetails {

    private Employee emp;

    public EmployeePrincipal(Employee emp) {
        this.emp = emp;
    }
    
    // get role from db
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + emp.getPrivilege().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return emp.getPassword();
    }

    @Override
    public String getUsername() {
        return emp.getUserName();
    }

    @Override
    public boolean isEnabled() {
        return emp.getActive();
    }

    public String getPrivilege() {
        return emp.getPrivilege();
    }
}



✅ Step 4 – Generate JWT Token

@Component
public class JwtUtil {

	private String secretKey;

	public JwtUtil() {
		SecureRandom random = new SecureRandom();
		byte[] key = new byte[32]; // 256 bits
		random.nextBytes(key);
		secretKey = Base64.getEncoder().encodeToString(key);
	}

	public String generateCustomToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
				.signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
	}

	Key getSignedKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}


✅ When you login → you get a token in response.


✅ Step 5 – Verify Token on Every Request

When you call any other API (like /product/getProducts),
you send this token in the header:


✅ Step 6 – JWT Filter

File: JwtRequestFilter.java

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;

	public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			username = extractUsername(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (validToken(token, userDetails.getUsername())) {
				String role = extractRole(token);
				var authorities = List.of(new SimpleGrantedAuthority(role));
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, authorities);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		chain.doFilter(request, response);
	}

//	When come any request so check token is correct or not  --> JwtRequestFilter
	public Boolean validToken(String token, String username) {
		return (extractUsername(token).equals(username) && !isTokenExpired(token));
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

//	public List<String> extractRole(String token) {
//		return extractClaim(token, claims -> claims.get("roles", List.class));
//	}
	
	public String extractRole(String token) {
	    return extractClaim(token, claims -> claims.get("role", String.class));
	}


	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parserBuilder().setSigningKey(jwtUtil.getSignedKey()).build().parseClaimsJws(token)
				.getBody();
		return claimsResolver.apply(claims);
	}
}


✅ Step 7 – Spring Security Configuration

File: SecurityConfig.java

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				.requestMatchers("/employee/page/**", "/WEB-INF/jsp/**", "/css/**", "/js/**").permitAll()
				.requestMatchers("/employee/ajax/**").permitAll()
				.requestMatchers("/api/employees/**").permitAll()
				.requestMatchers("/employee/login", "/employee/register").permitAll().anyRequest().authenticated())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(EmployeeService employeeService,
			PasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(employeeService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
}


✅ Step 8 – Role-Based Access Control

File: EmployeeCrudController.java

@RestController
@RequestMapping("/api/employees")
public class EmployeeCrudController {

	@Autowired
	private EmployeeCrudService service;

	@Autowired
	private EmployeeDocumentService documentService;

	@Autowired
	private EmployeeRepository employeeRepo;

	@GetMapping("/dummy")
	public String dummy() {
		return "Hiiiii";
	}

	/* CREATE → ADMIN */
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<Employee>> create(@Valid @RequestBody Employee emp) {
		Employee respemp = service.create(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created("Employee created", respemp));
	}

	/* READ → ADMIN + USER */
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ApiResponse<Employee>> get(@PathVariable @Min(1) Long id) {
		Employee getEmpById = service.getById(id);
		return ResponseEntity.ok(ApiResponse.success("Employee fetched", getEmpById));
	}

	/* UPDATE → ADMIN */
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<Employee>> update(@PathVariable Long id, @RequestBody Employee emp) {
		Employee updateEmp = service.update(id, emp);
		return ResponseEntity.ok(ApiResponse.success("Employee updated", updateEmp));
	}

	/* DELETE (SOFT) → ADMIN */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
		service.deactivate(id);
		return ResponseEntity.ok(ApiResponse.success("Employee deactivated", null));
	}
}



🧭 End-to-End Flow Summary
Step	         Action	                                    Description
1	         Register User	                   Save user in DB with encrypted password
2	         Login	                           Validate credentials & generate JWT token
3	         Call API	                       Pass token in Authorization header
4	         JwtRequestFilter	               Extract & validate token,  Verify signature, expiration & username
5	         SecurityConfig	                   Makes Spring Security stateless
6	         @PreAuthorize	                   Checks user roles for authorization



🔁 Final Request Flow (Execution Order)

Client → /product/getProducts
   ↓
SecurityFilterChain → JwtRequestFilter
   ↓
Validate JWT token via JwtUtil
   ↓
Set Authentication in SecurityContext
   ↓
@PreAuthorize("hasRole('DEALER')")
   ↓
ProductController executes → returns response


 
 
 🧠 Interview Questions

Q1. What is JWT?
→ JWT (JSON Web Token) is a stateless authentication mechanism using digitally signed tokens.

Q2. Difference between Basic Auth & JWT?

Basic Auth	                                       JWT
Sends username/password each time	           Sends token
Needs server-side validation	               Stateless (no session)
Simpler but less secure	                       More secure, scalable

Q3. What is the use of JwtRequestFilter?
→ Intercepts every API request, extracts token from header, validates it, and sets authentication in context.

Q4. Why do we set session policy as STATELESS?
→ Because JWT doesn’t use server sessions. Each request carries its own authentication data (token).

Q5. How to add roles in JWT?
→ Add as claims in JwtUtil → claim("roles", roles).
 

 ==> 2️⃣ Controller calls loadUserByUsername()
→ Returns a UserPrincipal object wrapping User.

2️⃣ Controller calls loadUserByUsername()
→ Returns a UserPrincipal object wrapping User.

3️⃣ UserPrincipal checks:

Username / Password

Approval flag

Roles (like ADMIN, DEALER, SELLER)

4️⃣ If approved & credentials match →
JwtUtil.generateToken(username, roles) creates JWT like:




yah uper ki hi pura h bs ek flow chart ki tarah
🌐 JWT Authentication Full Flow (Spring Boot Security)

🧩 Step 1: Registration (User Creation)
Client → POST /user/register → UserController → MyUserDetailsService → UserRepository → Database

🧩 Step 2: Admin Approves User
Admin → PUT /admin/approve/{id} → AdminController → AdminService → UserRepository → DB (approval=true)

🧩 Step 3: Login (Generate Token)
Client → POST /user/login → UserController → MyUserDetailsService → UserPrincipal → JwtUtil → Token Response

🧠 Explanation:
1️⃣ User sends login credentials →

{
  "username": "raj@gmail.com",
  "password": "raj@123"
}

2️⃣ Controller calls loadUserByUsername()
→ Returns a UserPrincipal object wrapping User.

3️⃣ UserPrincipal checks:
Username / Password
Approval flag
Roles (like ADMIN, DEALER, SELLER)

4️⃣ If approved & credentials match →
JwtUtil.generateToken(username, roles) creates JWT like:



🧩 Step 4: API Call (Protected Resource Access)
GET /product/getProducts
Authorization: Bearer <JWT_TOKEN>

Client → API Request → Security Filter Chain → JwtRequestFilter → JwtUtil → SecurityContext → Controller


🔁 Detailed Backend Flow Sequence

(1) Client → Sends HTTP Request with Token in Header
     ↓
(2) Spring Security Intercepts Request (SecurityFilterChain)
     ↓
(3) JwtRequestFilter executes (OncePerRequestFilter)
     |
     |-- Extracts "Authorization" header
     |-- Validates if header starts with "Bearer "
     |-- Extracts token from header
     |-- Calls jwtUtil.extractUsername(token)
     ↓
(4) MyUserDetailsService.loadUserByUsername(username)
     |-- Fetches user from DB
     |-- Returns UserPrincipal (includes role & approval)
     ↓
(5) jwtUtil.validToken(token, username)
     |-- Checks signature + expiry
     |-- Valid → create Authentication object
     ↓
(6) SecurityContextHolder.setAuthentication(authToken)
     → User now marked as authenticated
     ↓
(7) Controller Method (like ProductController)
     |
     |-- @PreAuthorize("hasRole('DEALER')") executes
     |-- Reads roles from UserPrincipal.getAuthorities()
     |-- If role matches → API executes
     |-- Else → AccessDenied (403)
     ↓
(8) Response returned to Client


🧠 Flow Visualization (Diagram)

                +--------------------+
                |  User Registration |
                | (Username, Pass, Role)
                +--------------------+
                           ↓
                           ↓
        +------------------------------------+
        |  User Stored in DB (approval=false) |
        +------------------------------------+
                           ↓
                           ↓
                +--------------------+
                |  Admin Approves    |
                |  /admin/approve/id |
                +--------------------+
                           ↓
                           ↓
                +--------------------+
                |     Login API      |
                | /user/login        |
                +--------------------+
                           ↓
          +------------------------------------+
          | MyUserDetailsService + UserPrincipal|
          | Validate credentials & approval     |
          +------------------------------------+
                           ↓
                           ↓
                +--------------------+
                |  JWT Token Created |
                | (JwtUtil.generate) |
                +--------------------+
                           ↓
                           ↓
                +--------------------------+
                |  Token Sent to Client    |
                |  Client stores locally   |
                +--------------------------+
                           ↓
                           ↓
     +--------------------------------------------------+
     | Client sends API request                         |
     | Authorization: Bearer <JWT_TOKEN>                |
     +--------------------------------------------------+
                           ↓
                           ↓
                +--------------------------+
                | Spring Security Chain    |
                +--------------------------+
                           ↓
                           ↓
                +--------------------------+
                | JwtRequestFilter         |
                | - Extract token          |
                | - Validate using JwtUtil |
                | - Set Authentication     |
                +--------------------------+
                           ↓
                           ↓
                +--------------------------+
                | SecurityContextHolder    |
                | (Stores UserPrincipal)   |
                +--------------------------+
                           ↓
                           ↓
                +--------------------------+
                | Controller Executes      |
                | @PreAuthorize Roles Check|
                +--------------------------+
                           ↓
                           ↓
                +--------------------------+
                |     API Response         |
                +--------------------------+




 
 #####################################  3. 🧩 OAuth 2.0 (Open Authorization 2.0)

🔹 Definition (Simple Terms)

OAuth 2.0 is an authorization protocol, not authentication.
It allows one application (Client) to access limited resources of a user from another service (like Google, GitHub) without sharing the user’s password.

➡️ In short:

“User kisi app ko permission deta hai ki wo uske account ka kuchh part access kar sake — bina password diye.”

🔐 Why OAuth 2.0?

✅ Password share nahi hota (secure delegation)

✅ User control karta hai ki kya access diya jaye

✅ Limited access milta hai (e.g., sirf email, not full account)

✅ Third-party apps ke liye safe authentication

🌍 Real-life Example

“Login with Google”

1️⃣ Aap XYZ App me login karte ho
2️⃣ XYZ App bolta hai Google se — “Mujhe user ka data chahiye”
3️⃣ Google user se puchta hai — “Kya XYZ App ko permission di jaye?”
4️⃣ User “Allow” karta hai
5️⃣ Google ek Access Token XYZ App ko deta hai
6️⃣ XYZ App us token se limited data (jaise name, email) access karta hai
👉 Password kabhi share nahi hota

🧠 Basic OAuth2 Flow
+-----------+        +---------------+        +----------------+
|  User     |        |  XYZ App      |        | Authorization  |
|           |        | (Client)      |        | Server (Google)|
+-----------+        +---------------+        +----------------+
      |                       |                        |
      |--- Wants to Login --->|                        |
      |                       |--- Redirect ---------->|
      |                       |     to Google Login    |
      |                       |<-- User Grants Access--|
      |                       |                        |
      |                       |<-- Access Token -------|
      |                       |                        |
      |                       |-- Call API with Token->|
      |                       |                        |

🔑 Key Components
Component	Description
Resource Owner	User (the one giving access)
Client	Application requesting access (XYZ App)
Authorization Server	Issues token after permission (Google, Facebook, Keycloak, etc.)
Resource Server	API or service that hosts user’s data (like Gmail API, GitHub API)
🏦 Authorization Servers (Examples)
Type	                                                  Examples	                           Description
Public Authorization Server	                        Google, Facebook, GitHub	          Used for social logins
Private / Self-Hosted	                            Keycloak, Okta	                      Used by enterprises for internal apps
Cloud Provider	                                    AWS Cognito	                          Authorization for AWS services


🧩 OAuth2 with Spring Boot (Overview)

When we use Keycloak / Okta / Google login in Spring Boot:

1️⃣ Spring Boot app (Client) connects to Authorization Server (Keycloak, Google).
2️⃣ Authorization Server verifies credentials and gives an Access Token (JWT).
3️⃣ Client sends this token with every API call in the Authorization header.
4️⃣ Spring Security (with OAuth2 filter) validates token with Authorization Server.
5️⃣ If valid → access granted, else 401 Unauthorized.

🔁 Simplified Step-by-Step Flow (Spring Boot + Keycloak)

1️⃣ User tries to access protected API
2️⃣ Spring redirects user to Keycloak login page
3️⃣ User logs in → Keycloak authenticates user
4️⃣ Keycloak issues Access Token (and optionally Refresh Token)
5️⃣ Token sent back to client
6️⃣ Every subsequent API call → token sent in header
7️⃣ Spring Security validates token → allows or denies request

⚙️ Common OAuth2 Grant Types (Interview Must-Know)
Grant Type	Use Case
Authorization Code	Web / Mobile apps (most common, secure)
Client Credentials	Server-to-server communication
Password Grant	Legacy, now discouraged
Implicit	Used for browser-based apps (now deprecated)
Refresh Token	Used to get new access token without login again


💡 Interview Quick Points
Question	                                                                          Short Answer
What is OAuth2?	                                                           Authorization protocol for delegated access
What’s the difference between Authentication & Authorization?	           Authentication = who you are; Authorization = what you can access
What is an Access Token?	                                               A credential allowing access to user data without password
What is Refresh Token?	                                                   Used to get a new Access Token after expiry
How is OAuth2 different from JWT?	                                       OAuth2 is a protocol, JWT is a token format often used inside OAuth2
What is Keycloak?	                                                       An open-source Identity and Access Management (IAM) server that implements OAuth2 & OpenID Connect


🚀 In Short (One-Liner for Interview)

“OAuth2.0 ek authorization framework hai jisme ek user kisi third-party app ko apne data ka limited access de sakta hai bina apna password diye — jaise Login with Google.”

Would you like me to also make a small diagram (visual chart) for this OAuth2 flow (User → Client → Auth Server → Token → API)?
It’ll look just like the JWT one — very clear and can be pasted in notes or PowerPoint.


============================>


Here’s a simple + clear visual flowchart for your OAuth 2.0 (Open Authorization 2) notes — it fits perfectly for revision or interview prep.

🎯 OAuth2.0 Flow (Step-by-Step Diagram)
          ┌────────────────────┐
          │     User (You)     │
          └─────────┬──────────┘
                    │
        ① Wants to login using Google (or Keycloak)
                    │
                    ▼
          ┌────────────────────┐
          │     Client App     │   ← (XYZ App / Your Spring Boot App)
          └─────────┬──────────┘
                    │
        ② Redirects user to Authorization Server
                    │
                    ▼
          ┌─────────────────────────────┐
          │   Authorization Server      │
          │ (Google / Keycloak / Okta)  │
          └─────────┬───────────────────┘
                    │
       ③ User logs in & grants permission
                    │
                    ▼
       ④ Auth Server issues Access Token
                    │
                    ▼
          ┌────────────────────┐
          │     Client App     │
          └─────────┬──────────┘
                    │
        ⑤ Sends Access Token in API request
         (Authorization: Bearer <token>)
                    │
                    ▼
          ┌────────────────────┐
          │   Resource Server  │   ← (Your backend APIs)
          └─────────┬──────────┘
                    │
       ⑥ Validates token with Auth Server
                    │
                    ▼
       ⑦ If token valid → Grants access
          Else → 401 Unauthorized
		  
		  

⚙️ Simple Explanation of Each Step
Step	Action	                                               Description
①	User initiates login	                          User chooses "Login with Google" or similar
②	Redirect	                                      App redirects user to Authorization Server
③	Permission	                                      User logs in and gives permission
④	Token issued	                                  Auth Server returns an Access Token
⑤	Token used	                                      App calls APIs using that token
⑥	Token validation	                              Resource Server checks if token is valid
⑦	Access granted	                                 If valid → access allowed; else rejected


🧠 Memory Shortcut (Interview Tip)

“OAuth2 = 4 Parties”
1️⃣ User — Data owner
2️⃣ Client — App requesting access
3️⃣ Authorization Server — Issues token
4️⃣ Resource Server — Provides data after validation


🌐 Example in Real Life

You open an app and click “Login with Google”

Google asks: “Allow this app to access your profile?”

You click Allow

Google sends an Access Token to the app

App calls Google API with that token to get your name/email
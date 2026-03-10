################################# PART 1: MySQL Core Fundamentals (Interview Foundation) ############################################

#1️. What is MySQL?

MySQL is an open-source Relational Database Management System (RDBMS) that stores data in tables (rows & columns) and uses SQL (Structured Query Language) to manage data.

👉 Used widely with Java (Spring Boot, Hibernate), PHP, Python.

#2. What type of database is MySQL?

MySQL is a Relational Database (RDBMS).

✔️ Data stored in tables
✔️ Relationships via Foreign Keys
✔️ Follows ACID properties (with InnoDB engine)

#3. Difference between SQL and MySQL

| SQL                   | MySQL              |
| --------------------- | ------------------ |
| Language              | Database software  |
| Used to write queries | Used to store data |
| ANSI standard         | Implements SQL     |


👉 Interview one-liner:

SQL is a language, MySQL is a database that uses SQL.

#4. What is RDBMS?
RDBMS stores data in tables with relationships.

Example:
-> users table
-> orders table

Connected via user_id (foreign key)

#5️. Table, Row, Column

| Term   | Meaning            |
| ------ | ------------------ |
| Table  | Collection of data |
| Row    | Single record      |
| Column | Attribute          |


CREATE TABLE users (
  id INT,
  name VARCHAR(50),
  email VARCHAR(100)
);

#6. What is a Primary Key?
A Primary Key uniquely identifies each record in a table.

✔️ Must be UNIQUE
✔️ Cannot be NULL
✔️ Only one primary key per table

CREATE TABLE users (
  id INT PRIMARY KEY,
  name VARCHAR(50)
);

#7. Why primary key cannot be NULL?
✔️ Because it is used to uniquely identify a row.

NULL means “unknown”, which breaks uniqueness.

#8. What is a Foreign Key?
A Foreign Key creates a relationship between two tables.

CREATE TABLE orders (
  order_id INT PRIMARY KEY,
  user_id INT,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

✔️ Maintains referential integrity

#9. Candidate Key vs Primary Key

| Candidate Key            | Primary Key      |
| ------------------------ | ---------------- |
| All possible unique keys | One selected key |
| Can be multiple          | Only one         |


Example:

-> Email
-> Aadhaar
   Both are candidate keys → one becomes primary key.
   
#10. Composite Key
Primary key made using multiple columns.

PRIMARY KEY (order_id, product_id)

✔️ Used in junction tables

#11. What is NULL?
NULL means no value / unknown value

✔️ NULL ≠ 0
✔️ NULL ≠ empty string

SELECT * FROM users WHERE email IS NULL;

#12. Data Types in MySQL (🔥 Frequently Asked)

##12.1: Numeric Types

| Type    | Use                  |
| ------- | -------------------- |
| INT     | Normal numbers       |
| BIGINT  | Large numbers        |
| FLOAT   | Approx decimal       |
| DOUBLE  | More precision       |
| DECIMAL | Exact values (money) |

👉 Interview Tip:
Use DECIMAL for currency, not FLOAT.

##12.2: String Types
CHAR vs VARCHAR (🔥 Very Common)

| CHAR         | VARCHAR          |
| ------------ | ---------------- |
| Fixed length | Variable length  |
| Faster       | Slower than CHAR  |


name CHAR(10)
email VARCHAR(100)

✔️ Use CHAR for fixed size
✔️ VARCHAR for variable size

##12.3: TEXT Types

| Type       | Size |
| ---------- | ---- |
| TINYTEXT   | 255  |
| TEXT       | 64KB |
| MEDIUMTEXT | 16MB |
| LONGTEXT   | 4GB  |

##12.4: Date & Time Types (🔥 MUST)
DATETIME vs TIMESTAMP (Interview favorite)

| DATETIME       | TIMESTAMP            |
| -------------- | -------------------- |
| No timezone    | Timezone aware       |
| Larger         | Smaller              |
| No auto update | Supports auto update |

✔️ Used for created_at / updated_at

==> ❓ Why TIMESTAMP is preferred?

✔️ Handles timezone
✔️ Auto updates
✔️ Used heavily in backend systems

#13. Interview Trap Questions
Q: How to set auto current time?

created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
ON UPDATE CURRENT_TIMESTAMP;

#14. PART-1 SUMMARY (You must remember)

✔️ MySQL = RDBMS
✔️ PK = unique + not null
✔️ FK = relationship
✔️ CHAR vs VARCHAR
✔️ DATETIME vs TIMESTAMP
✔️ DECIMAL for money



#################################### PART 2: SQL COMMANDS + CONSTRAINTS (Very Important for Interviews) #########################################

#SQL Commands – Classification (Basic but Mandatory)

#1. What are the types of SQL commands?
SQL commands are divided into 4 categories:

| Category | Purpose              |
| -------- | -------------------- |
| DDL      | Structure definition |
| DML      | Data manipulation    |
| DCL      | Access control       |
| TCL      | Transaction control  |

##1.1: DDL – Data Definition Language
Used to define or change database structure.

Commands
-> CREATE
-> ALTER
-> DROP
-> TRUNCATE


### CREATE

CREATE TABLE users (
  id INT PRIMARY KEY,
  name VARCHAR(50)
);

✔️ Creates table structure


### ALTER

Used to modify table structure

ALTER TABLE users ADD email VARCHAR(100);

✔️ Adds column
❌ Does NOT change existing data


### DROP

Deletes entire object permanently

DROP TABLE users;

✔️ Data + structure removed
❌ Cannot rollback

### TRUNCATE (🔥 Interview favorite)

TRUNCATE TABLE users;

✔️ Removes all records
✔️ Faster than DELETE
✔️ Cannot rollback
✔️ Resets auto-increment

### ❓ DELETE vs TRUNCATE vs DROP (🔥 Must)

| DELETE            | TRUNCATE     | DROP          |
| ----------------- | ------------ | ------------- |
| Row-wise          | Whole table  | Table removed |
| WHERE allowed     | No WHERE     | No table      |
| Rollback possible | Not possible | Not possible  |
| Slow              | Fast         | Fast          |


### Delete

DELETE FROM users
WHERE id = 5;

##1.2: DML – Data Manipulation Language
Used to work with data.

Commands
-> INSERT
-> UPDATE
-> DELETE
-> SELECT

### INSERT
INSERT INTO users VALUES (1, 'Amit', 'amit@gmail.com');

### UPDATE
UPDATE users SET name = 'Rahul' WHERE id = 1;

⚠️ Interview Tip:
Without WHERE → updates all rows (dangerous)

###DELETE
DELETE FROM users WHERE id = 1;

✔️ Row-level delete
✔️ Can rollback (inside transaction)

##1.3: DCL – Data Control Language
DCL ka use database me access control karne ke liye hota hai.
Matlab — kaun user kya kar sakta hai database me, ye decide karna.

👉 Ye mostly permissions dene ya wapas lene ke liye hota hai.

### GRANT

GRANT ka matlab:
Kisi user ko permission dena

GRANT SELECT ON users TO read_user;

Iska matlab:

👉 read_user naam ka user
👉 users table ko
👉 Sirf SELECT (read) kar sakta hai
👉 Insert, Delete, Update nahi kar sakta

###REVOKE

REVOKE ka matlab:
Di hui permission wapas lena

Example:

REVOKE SELECT ON users FROM read_user;

Iska matlab:
👉 Ab read_user users table ko read bhi nahi kar sakta

##1.4: TCL – Transaction Control Language (🔥 Important)
Commands
-> COMMIT
-> ROLLBACK
-> SAVEPOINT

### What is a transaction?
👉 Transaction = SQL operations ka ek group jo ek unit ki tarah execute hota hai.

Matlab:
Ya to sab changes save honge,
ya kuch bhi save nahi hoga.

Isko bolte hain: All or Nothing rule

###Example 1: ROLLBACK

START TRANSACTION;
INSERT INTO users VALUES (2, 'Neha', 'neha@gmail.com');
ROLLBACK;

✔️ Data not saved

###Example 2: COMMIT
COMMIT kya hota hai?
Agar aap likhte:

COMMIT;

👉 To changes permanently save ho jate hain.

###Example 3: SAVEPOINT

SAVEPOINT kya hota hai?
SAVEPOINT ek checkpoint jaisa hota hai.


START TRANSACTION;

INSERT INTO users VALUES (1, 'Aman', 'a@gmail.com');

SAVEPOINT sp1;

INSERT INTO users VALUES (2, 'Neha', 'neha@gmail.com');

ROLLBACK TO sp1;

COMMIT;

Kya hoga?
👉 Aman save ho jayega
👉 Neha rollback ho jayegi

Matlab: partial rollback
 
 
1️⃣ START TRANSACTION
2️⃣ Insert Aman
3️⃣ SAVEPOINT sp1  ← checkpoint
4️⃣ Insert Neha
5️⃣ ROLLBACK TO sp1  ← Neha hata di
6️⃣ COMMIT

ROLLBACK TO sp1 ka matlab:
sp1 ke baad jo bhi hua hai, use undo karo.

###Important Points (Interview ke liye)
-> Transaction ensures data consistency
-> START TRANSACTION se start hota hai
-> COMMIT = permanently save
-> ROLLBACK = undo changes
-> SAVEPOINT = partial rollback


#2. Constraints (🔥 Extremely Important)
Constraints enforce rules on data.

Constraints MySQL me rules hote hain jo table ke data ko sahi aur safe rakhte hain.
Ye galat, duplicate ya incomplete data ko database me enter hone se rok dete hain.

##2.1: 1️⃣ PRIMARY KEY
✔️ Unique
✔️ Not NULL
✔️ One per table
Ye NOT NULL + UNIQUE ka combination hota hai.
Har row ki unique pehchaan hoti hai.

CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    name VARCHAR(50)
);

##2.2: UNIQUE
email VARCHAR(100) UNIQUE

✔️ Prevents duplicates
✔️ Allows NULL (multiple)

## UNIQUE vs PRIMARY KEY

| PRIMARY KEY     | UNIQUE           |
| --------------- | ---------------- |
| One only        | Multiple allowed |
| No NULL         | NULL allowed     |
| Clustered index | Non-clustered    |

##2.3: NOT NULL
name VARCHAR(50) NOT NULL

Iska matlab hai column me value dena compulsory hai. Blank (NULL) nahi chhod sakte.

##2.4: DEFAULT
status VARCHAR(20) DEFAULT 'ACTIVE'

Agar value na do to automatically ek default value set ho jati hai.

##2.5: CHECK (🔥 Tricky Question)
CREATE TABLE products (
    price INT CHECK (price > 0)
);

👉 price 0 se bada hi hona chahiye.

✔️ Works only from MySQL 8+
❌ Older versions ignore it

##2.6: FOREIGN KEY (Very Important)
FOREIGN KEY (user_id) REFERENCES users(id)
ON DELETE CASCADE

✔️ Maintains integrity
✔️ CASCADE deletes child records automatically
✔️Ye do tables ke beech relation banata hai.

#3. Common Interview Follow-ups
❓ What is ON DELETE CASCADE?
✔️ When parent record is deleted → child records deleted automatically

❓ Can TRUNCATE be rolled back?
❌ No (DDL operation internally)


#4. ✅ FULL END-TO-END EXAMPLE (Covers Entire PART-2)
🎯 Scenario
User → Orders system with constraints & transactions

##4.1: Create Tables (DDL + Constraints)

CREATE TABLE users (
  id INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE,
  status VARCHAR(20) DEFAULT 'ACTIVE',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders (
  order_id INT PRIMARY KEY,
  user_id INT,
  amount DECIMAL(10,2) CHECK (amount > 0),
  FOREIGN KEY (user_id) REFERENCES users(id)
  ON DELETE CASCADE
);

##4.2: Insert Data (DML)

INSERT INTO users (id, name, email)
VALUES (1, 'Amit', 'amit@gmail.com');

INSERT INTO orders
VALUES (101, 1, 500.00);

##4.3: Transaction Handling (TCL)

START TRANSACTION;

INSERT INTO orders VALUES (102, 1, 1000.00);

ROLLBACK;

✔️ Order not saved

##4.4: DELETE vs TRUNCATE

DELETE FROM orders WHERE order_id = 101;

TRUNCATE TABLE orders;

##4.5: CASCADE Effect

DELETE FROM users WHERE id = 1;

✔️ User deleted
✔️ Orders auto deleted

#5. What is autocommit?
✔️ MySQL commits every statement automatically by default.

SET autocommit = 0;

👉 In Spring Boot + JPA → handled internally by @Transactional




################################## PART-3: INDEXES (🔥 MOST IMPORTANT TOPIC) ###################################################

#1. What is an Index?
An index is a data structure that improves SELECT query performance by reducing table scans.

👉 Similar to book index

#2. Why Index is Used?
✔️ Faster search
✔️ Faster joins
✔️ Faster WHERE, ORDER BY, GROUP BY

❌ Slower INSERT / UPDATE / DELETE

#3. Types of Index in MySQL

##3.1️: B-Tree Index (Default)
CREATE INDEX idx_email ON users(email);

✔️ Used for =, <, >, BETWEEN 

##3.2: Composite Index (🔥 Very Important)
CREATE INDEX idx_user_status ON users(user_id, status);

✔️ Order matters
✔️ Used left-to-right

❌ status alone will NOT use this index

##3.3: UNIQUE Index

Created automatically by UNIQUE constraint.

#4. Clustered vs Non-Clustered Index (🔥 Interview Favorite)

| Clustered     | Non-Clustered     |
| ------------- | ----------------- |
| Data sorted   | Pointer only      |
| One per table | Multiple          |
| PK based      | Secondary indexes |

👉 Primary Key = Clustered Index

#5. When Index is NOT Used?
✔️ Small tables
✔️ Using functions on column
✔️ Using %LIKE% at start
✔️ Low selectivity column

#6. Index Drawbacks (Senior Question)
❌ Extra storage
❌ Slower writes
❌ Maintenance cost


#7. FULL REAL-WORLD EXAMPLE (Covers EVERYTHING)
🔥 Transactions + Constraints + Indexes together

🎯 Scenario: Banking + Orders System

##7.1: Table Creation (DDL + Constraints)

CREATE TABLE users (
  id INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE,
  status VARCHAR(20) DEFAULT 'ACTIVE',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE accounts (
  acc_id INT PRIMARY KEY,
  user_id INT,
  balance DECIMAL(10,2) CHECK (balance >= 0),
  FOREIGN KEY (user_id) REFERENCES users(id)
  ON DELETE CASCADE
);

##7.2: Index Creation (Performance)
CREATE INDEX idx_user_status ON users(id, status);

##7.3: Transaction – Money Transfer
START TRANSACTION;

UPDATE accounts SET balance = balance - 1000 WHERE acc_id = 1;
SAVEPOINT sp1;

UPDATE accounts SET balance = balance + 1000 WHERE acc_id = 2;

COMMIT;
✔️ Atomic
✔️ Consistent
✔️ Indexed
✔️ Safe

##7.4: Rollback Scenario
ROLLBACK TO sp1;

✔️ Partial recovery


###############################################  📌 PART-4: JOINS, SUBQUERIES & REAL INTERVIEW QUERIES  #############################################

#1️. JOIN Basics (Very Short Recap)
❓ What is a JOIN?

JOIN is used to combine rows from multiple tables using a related column.

#2. TABLE SETUP (USED IN ALL EXAMPLES)
USERS TABLE

| user_id | name  | city   |
| ------- | ----- | ------ |
| 1       | Amit  | Delhi  |
| 2       | Rahul | Mumbai |
| 3       | Neha  | Pune   |
| 4       | Karan | Delhi  |


ORDERS TABLE

| order_id | user_id | amount |
| -------- | ------- | ------ |
| 101      | 1       | 500    |
| 102      | 1       | 800    |
| 103      | 2       | 1000   |
| 104      | 5       | 300    |

👉 user_id = 5 does NOT exist in users (important for joins)

##2.1: INNER JOIN (🔥 Most Asked)

Returns only matching records from both tables.

SELECT u.name, o.order_id, o.amount
FROM users u
INNER JOIN orders o ON u.user_id = o.user_id;

###How JOIN Works (Step-by-Step)

1️. MySQL compares users.user_id with orders.user_id
2️. Keeps only common values (1, 2)
3️. Removes unmatched rows (user_id = 3, 4, 5)

🔹 Output:

| name  | order_id | amount |
| ----- | -------- | ------ |
| Amit  | 101      | 500    |
| Amit  | 102      | 800    |
| Rahul | 103      | 1000   |


🧠 Interview Tip

INNER JOIN ignores non-matching data

##2.2: LEFT JOIN (🔥 Very Important)
LEFT JOIN returns all records from the left table and the matched records from the right table. If there is no match, NULL values are returned for the right table columns.

SELECT u.name, o.order_id, o.amount
FROM users u
LEFT JOIN orders o ON u.user_id = o.user_id;

Output: 

| name  | order_id | amount |
| ----- | -------- | ------ |
| Amit  | 101      | 500    |
| Amit  | 102      | 800    |
| Rahul | 103      | 1000   |
| Neha  | NULL     | NULL   |
| Karan | NULL     | NULL   |


🧠 Explanation

✔️ Users without orders still appear
✔️ Order columns become NULL

##2.3: RIGHT JOIN (Less Used but Asked)
RIGHT JOIN returns all records from the right table and the matched records from the left table. If there is no match, NULL values are returned for the left table columns.

SELECT u.name, o.order_id, o.amount
FROM users u
RIGHT JOIN orders o ON u.user_id = o.user_id;

Output: 

| name  | order_id | amount |
| ----- | -------- | ------ |
| Amit  | 101      | 500    |
| Amit  | 102      | 800    |
| Rahul | 103      | 1000   |
| NULL  | 104      | 300    |

🧠 Explanation

✔️ Orders without users appear
✔️ User fields become NULL

##2.4: FULL JOIN (🔥 TRICK QUESTION)
❓ Does MySQL support FULL JOIN?
❌ No (directly)

=> FULL JOIN returns all records when there is a match in either the left table or the right table. If there is no match, NULL values are returned for the    missing side.

=> MySQL me direct FULL OUTER JOIN supported nahi hota.
   Usko generally LEFT JOIN + RIGHT JOIN + UNION se achieve kiya jata hai.
   
SELECT u.name, o.order_id, o.amount
FROM users u
LEFT JOIN orders o ON u.user_id = o.user_id

UNION

SELECT u.name, o.order_id, o.amount
FROM users u
RIGHT JOIN orders o ON u.user_id = o.user_id;

Output (Combined) : 

| name  | order_id | amount |
| ----- | -------- | ------ |
| Amit  | 101      | 500    |
| Amit  | 102      | 800    |
| Rahul | 103      | 1000   |
| Neha  | NULL     | NULL   |
| Karan | NULL     | NULL   |
| NULL  | 104      | 300    |


##2.5: SELF JOIN (🔥 Experience Question)
Self Join me ek hi table ko usi table ke sath join kiya jata hai, jab hume usi table ke rows ko compare karna hota hai.

Employees with same city

TABLE: EMPLOYEES

| emp_id | name  | city  |
| ------ | ----- | ----- |
| 1      | Amit  | Delhi |
| 2      | Rahul | Delhi |
| 3      | Neha  | Pune  |


SELECT e1.name, e2.name
FROM employees e1
JOIN employees e2
ON e1.city = e2.city
AND e1.emp_id < e2.emp_id;

Output: 

| name | name  |
| ---- | ----- |
| Amit | Rahul |


#3. SQL Joins Quick Summary

| Join Type      | Description (Simple/Interview Version)                                                               |
| -------------- | ---------------------------------------------------------------------------------------------------- |
| **INNER JOIN** | Sirf dono tables ke **matching rows** aate hain (intersection).                                      |
| **LEFT JOIN**  | **Left table ka pura data** + matching right table data. Non-matching right columns me NULL.         |
| **RIGHT JOIN** | **Right table ka pura data** + matching left table data. Non-matching left columns me NULL.          |
| **FULL JOIN**  | **Dono tables ka pura data**. Jaha match nahi mile, missing side me NULL.                            |
| **SELF JOIN**  | **Ek hi table ko usi table ke sath join** karte hain, alias use karke rows ko compare karne ke liye. |


#4. SUBQUERY (🔥 Very Important)
A query inside another query.

🔹 Example
Find users who placed orders

SELECT name
FROM users
WHERE user_id IN (
  SELECT user_id FROM orders
);

🔹 How it Works
1️. Inner query runs first
2️. Returns {1, 2, 5}
3️. Outer query matches users

Output:

| name  |
| ----- |
| Amit  |
| Rahul |

##4.1 EXISTS vs IN (🔥 Interview Favorite)
### 1: EXISTS

SELECT name
FROM users u
WHERE EXISTS (
  SELECT 1 FROM orders o
  WHERE o.user_id = u.user_id
);

✔️ Faster for large data
✔️ Stops at first match
✔️ EXISTS ek boolean return karta hai – TRUE ya FALSE

### 2: IN

SELECT name
FROM users
WHERE user_id IN (SELECT user_id FROM orders);

❌ Slower on large datasets
✔️ IN subquery se ek list return karta hai

### 🧠 Interview One-Liner
EXISTS is better when subquery returns large result sets.

#5. Fetch all users with total order amount (include users without orders)

SELECT u.name, SUM(o.amount) AS total_amount
FROM users u
LEFT JOIN orders o ON u.user_id = o.user_id
GROUP BY u.name;

Output: 

| name  | total_amount |
| ----- | ------------ |
| Amit  | 1300         |
| Rahul | 1000         |
| Neha  | NULL         |
| Karan | NULL         |



############################### PART-5: GROUP BY, HAVING & WINDOW FUNCTIONS (🔥 VERY IMPORTANT) ##################

#🔷 TABLE SETUP (USED IN ALL EXAMPLES)

USERS

| user_id | name  | city   |
| ------- | ----- | ------ |
| 1       | Amit  | Delhi  |
| 2       | Rahul | Mumbai |
| 3       | Neha  | Pune   |
| 4       | Karan | Delhi  |

ORDERS

| order_id | user_id | amount | order_date |
| -------- | ------- | ------ | ---------- |
| 101      | 1       | 500    | 2024-01-01 |
| 102      | 1       | 800    | 2024-01-05 |
| 103      | 2       | 1000   | 2024-01-03 |
| 104      | 2       | 400    | 2024-01-10 |
| 105      | 3       | 200    | 2024-01-12 |


#1. GROUP BY (🔥 MOST ASKED)
GROUP BY is used to group rows with same values and apply aggregate functions.

🔹 Query: Total order amount per user

SELECT user_id, SUM(amount) AS total_amount
FROM orders
GROUP BY user_id;

🔹 Output:

| user_id | total_amount |
| ------- | ------------ |
| 1       | 1300         |
| 2       | 1400         |
| 3       | 200          |


🧠 Interview Rule (VERY IMPORTANT)
❗ Every column in SELECT must be either
-> inside aggregate function
-> OR present in GROUP BY

#2. GROUP BY with JOIN (🔥 Real Interview Scenario)
Get user name + total order amount

SELECT u.name, SUM(o.amount) AS total_amount
FROM users u
JOIN orders o ON u.user_id = o.user_id
GROUP BY u.name;

Output:

| name  | total_amount |
| ----- | ------------ |
| Amit  | 1300         |
| Rahul | 1400         |
| Neha  | 200          |


#3. HAVING (🔥 INTERVIEW FAVORITE)
Difference between WHERE and HAVING?

| WHERE                 | HAVING              |
| --------------------- | ------------------- |
| Filters rows          | Filters groups      |
| Used before GROUP BY  | Used after GROUP BY |
| Cannot use aggregates | Can use aggregates  |


###🔹 Requirement
Users whose total order amount > 1000

SELECT user_id, SUM(amount) AS total_amount
FROM orders
GROUP BY user_id
HAVING SUM(amount) > 1000;

output:

| user_id | total_amount |
| ------- | ------------ |
| 1       | 1300         |
| 2       | 1400         |


🧠 Interview Trap

❌ WHERE SUM(amount) > 1000 → INVALID


# 4. ORDER OF EXECUTION (🔥 VERY IMPORTANT)
SQL execution order?

FROM
JOIN
WHERE
GROUP BY
HAVING
SELECT
ORDER BY
LIMIT


#5.WINDOW FUNCTIONS (🔥 ADVANCED – PRODUCT COMPANIES)
These do NOT reduce rows (unlike GROUP BY)

## 5.1 ROW_NUMBER()
🔹 Requirement

Give order ranking per user

SELECT user_id, order_id, amount,
ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY amount DESC) AS rn
FROM orders;

Output: 

| user_id | order_id | amount | rn |
| ------- | -------- | ------ | -- |
| 1       | 102      | 800    | 1  |
| 1       | 101      | 500    | 2  |
| 2       | 103      | 1000   | 1  |
| 2       | 104      | 400    | 2  |
| 3       | 105      | 200    | 1  |

🧠 Explanation

✔️ PARTITION BY = group
✔️ ORDER BY = ranking order

##5.2 RANK() vs DENSE_RANK() (🔥 VERY COMMON)
🔹 Sample Data (User-1 orders)

| amount |
| ------ |
| 800    |
| 800    |
| 500    |


SELECT amount,
RANK() OVER (ORDER BY amount DESC) AS rnk,
DENSE_RANK() OVER (ORDER BY amount DESC) AS dense_rnk
FROM orders WHERE user_id = 1;

Output: 

| amount | rnk | dense_rnk |
| ------ | --- | --------- |
| 800    | 1   | 1         |
| 800    | 1   | 1         |
| 500    | 3   | 2         |

🧠 Interview One-liner

RANK skips numbers, DENSE_RANK does not.

##5.3 Running Total (🔥 Asked in Analytics)
🔹 Requirement

Running total of orders by date

SELECT order_date, amount,
SUM(amount) OVER (ORDER BY order_date) AS running_total
FROM orders;

Output:

| order_date | amount | running_total |
| ---------- | ------ | ------------- |
| 2024-01-01 | 500    | 500           |
| 2024-01-03 | 1000   | 1500          |
| 2024-01-05 | 800    | 2300          |
| 2024-01-10 | 400    | 2700          |
| 2024-01-12 | 200    | 2900          |




################################################ PART-6: REAL SQL INTERVIEW PROBLEMS (🔥 MOST ASKED) #########################################

#🔷 TABLE SETUP (USED IN MOST QUESTIONS)
EMPLOYEE

| emp_id | name  | dept | salary |
| ------ | ----- | ---- | ------ |
| 1      | Amit  | IT   | 80000  |
| 2      | Rahul | IT   | 60000  |
| 3      | Neha  | HR   | 50000  |
| 4      | Karan | IT   | 80000  |
| 5      | Pooja | HR   | 70000  |


#1. Find Nth Highest Salary (🔥 CLASSIC QUESTION)

##1.1: Find 2nd highest salary

SELECT DISTINCT salary
FROM employee
ORDER BY salary DESC
LIMIT 1 OFFSET 1;

🔹 Output: 

| salary |
| ------ |
| 70000  |

🧠 Explanation
-> ORDER BY DESC → highest first
-> OFFSET 1 → skip highest
-> LIMIT 1 → fetch next

#2. Find Employees with Same Salary (🔥 VERY COMMON)

SELECT salary, COUNT(*) AS count
FROM employee
GROUP BY salary
HAVING COUNT(*) > 1;

Output: 

| salary | count |
| ------ | ----- |
| 80000  | 2     |


#3. DELETE Duplicate Records (🔥 VERY IMPORTANT)
🔹 TABLE: USERS

| id | email        |
| -- | -------------|
| 1  |  a@gmail.com |
| 2  |  b@gmail.com |
| 3  |  a@gmail.com |
| 4  |  c@gmail.com |

🔹 Delete duplicates (keep lowest id)

DELETE u1
FROM users u1
JOIN users u2
ON u1.email = u2.email
AND u1.id > u2.id;

🔹 Output Table

| id | email                             |
| -- | --------------------------------- |
| 1  | [a@gmail.com](mailto:a@gmail.com) |
| 2  | [b@gmail.com](mailto:b@gmail.com) |
| 4  | [c@gmail.com](mailto:c@gmail.com) |

🧠 Interview Tip

Always confirm which record to keep (MIN id / MAX id)

#4. Employees Who Earn More Than Average Salary

SELECT name, salary
FROM employee
WHERE salary > (SELECT AVG(salary) FROM employee);

Output: 

| name  | salary |
| ----- | ------ |
| Amit  | 80000  |
| Karan | 80000  |
| Pooja | 70000  |


#5. Fetch Top 2 Salaries Per Department (🔥 ADVANCED)

#6. Pagination Query (🔥 Java + DB Interview Favorite)
Fetch page 2 with page size 2

SELECT * FROM employee
ORDER BY emp_id
LIMIT 2 OFFSET 2;

Output: 

| emp_id | name  | dept | salary |
| ------ | ----- | ---- | ------ |
| 3      | Neha  | HR   | 50000  |
| 4      | Karan | IT   | 80000  |

🧠 Java Mapping
PageRequest.of(page, size)

#7. Find Departments with More Than 2 Employees
🔹 Query

SELECT dept, COUNT(*) AS total
FROM employee
GROUP BY dept
HAVING COUNT(*) > 2;

Output: 

| dept | total |
| ---- | ----- |
| IT   | 3     |

#8. EXISTS vs JOIN (🔥 EXPERIENCE QUESTION)
🔹 Requirement

Employees who belong to IT department

###🔹 EXISTS

SELECT e.name
FROM employee e
WHERE EXISTS (
  SELECT 1 FROM employee x
  WHERE x.dept = 'IT' AND x.emp_id = e.emp_id
);

###🔹 JOIN (Better)
SELECT name
FROM employee
WHERE dept = 'IT';

🧠 Interview One-liner

JOIN is usually faster and cleaner than subquery.

#9. Count Employees Department-wise

SELECT dept, COUNT(*) AS total
FROM employee
GROUP BY dept;

#10. Highest Salary in Each Department

SELECT dept, MAX(salary)
FROM employee
GROUP BY dept;

#11. 


#######################################  PART-7: SQL OPTIMIZATION, EXPLAIN PLAN & JAVA + DB SCENARIOS (🔥 FINAL)  #################################

#1️. SQL PERFORMANCE & OPTIMIZATION (Top Interview Area)
A query is slow. How will you optimize it?

✔️ Expected Answer (Step-wise)
1. Check indexes
2. Use EXPLAIN / EXPLAIN ANALYZE
3. Avoid SELECT *
4. Optimize JOINs
5. Reduce subqueries
6. Use proper data types
7. Check query execution order

#2. EXPLAIN QUERY PLAN (🔥 MUST KNOW)
❓ What does EXPLAIN do?

✔️ Answer
EXPLAIN shows how MySQL executes a query, including:
-> Index usage
-> Table scan
-> Join type
-> Estimated rows

###Example Table

CREATE TABLE orders (
  order_id INT PRIMARY KEY,
  user_id INT,
  amount DECIMAL(10,2),
  INDEX idx_user_id (user_id)
);

### Query

EXPLAIN
SELECT * FROM orders WHERE user_id = 10;

##2.1: Important Columns to Explain (Interview Focus)

| Column | Meaning                              |
| ------ | ------------------------------------ |
| type   | Access type (ALL, index, ref, range) |
| key    | Index used                           |
| rows   | Rows scanned                         |
| Extra  | Using index / filesort               |

##2.2: Access Types (Very Important)

| Type  | Meaning               |
| ----- | --------------------- |
| ALL   | Full table scan (BAD) |
| index | Index scan            |
| range | Range scan            |
| ref   | Index lookup (GOOD)   |
| const | Best (PK lookup)      |


👉 Interview One-liner:

Avoid type = ALL for large tables.

#3. INDEX OPTIMIZATION RULES (🔥 ASKED A LOT)
❓ When Index is NOT Used?

❌ Column wrapped in function
❌ %LIKE starting with wildcard
❌ Data type mismatch
❌ Low cardinality column

-- BAD
SELECT * FROM users WHERE YEAR(created_at) = 2024;

-- GOOD
SELECT * FROM users
WHERE created_at BETWEEN '2024-01-01' AND '2024-12-31';

#4. Composite Index Order (🔥 EXPERIENCE QUESTION)

CREATE INDEX idx_user_status ON users(user_id, status);

###Queries

| Query                              | Uses Index? |
| ---------------------------------- | ----------- |
| WHERE user_id = 1                  | ✅           |
| WHERE user_id = 1 AND status = 'A' | ✅           |
| WHERE status = 'A'                 | ❌           |


👉 Left-most prefix rule

#5. SELECT * vs SELECT Columns
❓ Why avoid SELECT *?

❌ Fetches unnecessary data
❌ Breaks covering index
❌ Affects network + memory

✔️ Always select required columns only

#6. MySQL vs PostgreSQL – Interview Traps (🔥 FINAL COMPARISON)

| Topic             | MySQL           | PostgreSQL      |
| ----------------- | --------------- | --------------- |
| Default isolation | REPEATABLE READ | READ COMMITTED  |
| Window functions  | Limited         | Excellent       |
| JSON              | Basic           | Advanced        |
| Index types       | B-Tree, Hash    | GIN, GiST, BRIN |
| Analytics         | Average         | Strong          |
| Use case          | Read heavy      | Complex queries |


###❓ Interview Question
Why PostgreSQL is preferred in analytics?

✔️ Better planner
✔️ Advanced indexes
✔️ Better window functions

#7. Java + DB (🔥 VERY IMPORTANT)

##7.1: JDBC vs JPA vs Hibernate

| JDBC       | JPA               | Hibernate       |
| ---------- | ----------------- | --------------- |
| Low-level  | Specification     | Implementation  |
| Manual SQL | ORM               | ORM             |
| No cache   | First-level cache | 1st + 2nd level |


##7.2: PreparedStatement (🔥 SECURITY)
❓ Why PreparedStatement?

✔️ Prevents SQL Injection
✔️ Faster execution
✔️ Precompiled query

String sql = "SELECT * FROM users WHERE email = ?";
PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, email);

=> SQL Injection ek hacking technique hai jisme attacker input field (login form, search box, etc.) me malicious SQL code daal kar database ko manipulate  karta hai.

##7.3: SQL Injection Example (Interview Favorite)
SELECT * FROM users WHERE email = 'a@gmail.com' OR '1'='1';

✔️ PreparedStatement prevents this.

==> Yaha '1'='1' hamesha true hota hai, isliye pura condition true ho jayega aur:
isi ko PreparedStatement rokta.

##7.4: Pagination in Java (Spring Boot)
###SQL
SELECT * FROM users ORDER BY id LIMIT 10 OFFSET 20;

==> 10(10,10) ke do page skip kr diye 3rd page ke 10 row get kr liya.

###Spring Data
PageRequest.of(2, 10);

==> springboot me page index 0 se start hota h to yha pr 2 third page hi h like(0,1,2)

##7.5: MYSQL TRANSACTION vs @Transactional

###🗄️ MySQL me

Agar tum manually MySQL me likho:

START TRANSACTION;

UPDATE account SET balance = balance - 1000 WHERE id = 1;
UPDATE account SET balance = balance + 1000 WHERE id = 2;

COMMIT;

Agar beech me error aaye aur COMMIT na ho → ROLLBACK;

Yahi kaam Spring Boot me @Transactional karta hai automatically.


### Spring Boot + MySQL Example

###1️. Entity

@Entity
public class Account {

    @Id
    private Long id;
    private Double balance;
}


###2️ Repository

public interface AccountRepository extends JpaRepository<Account, Long> {
}

###3️. Service Layer (Yaha lagta hai @Transactional)

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    @Transactional
    public void transferMoney(Long fromId, Long toId, Double amount) {

        Account from = repo.findById(fromId).orElseThrow();
        Account to = repo.findById(toId).orElseThrow();

        from.setBalance(from.getBalance() - amount);
        repo.save(from);

        // Suppose error happens here
        if (true) {
            throw new RuntimeException("Something went wrong");
        }

        to.setBalance(to.getBalance() + amount);
        repo.save(to);
    }
}

###4. 🔥 Ab Kya Hoga?

-> Debit ho gaya
-> Error aa gaya
-> RuntimeException throw hua
-> Spring automatically ROLLBACK karega
-> Database me koi change save nahi hoga

👉 Ye sab @Transactional handle karta hai.

###5. 📌 @Transactional Kaha Lagana Chahiye?

✅ Service Layer pe
❌ Controller pe nahi
❌ Repository pe usually nahi

Best Practice:

Controller → Service (@Transactional) → Repository → DB

###6. 🧠 MySQL vs Spring Boot Comparison

| MySQL             | Spring Boot           |
| ----------------- | --------------------- |
| START TRANSACTION | @Transactional start  |
| COMMIT            | Method successful end |
| ROLLBACK          | RuntimeException      |



#8. MySQL vs PostgreSQL

| Point      | MySQL               | PostgreSQL      | Meaning (Easy Words)              |
| ---------- | ------------------- | --------------- | --------------------------------- |
| Type       | RDBMS               | Advanced RDBMS  | PostgreSQL has more features      |
| Speed      | Very fast for reads | Slightly slower | MySQL good for websites           |
| Complexity | Simple              | Complex         | PostgreSQL needs deeper knowledge |
| ACID       | Good                | Very strong     | PostgreSQL is safer               |
| JSON       | Basic               | Very powerful   | PostgreSQL better for JSON        |
| Indexes    | Limited             | Many types      | PostgreSQL faster in analytics    |
| Use case   | Normal apps         | Enterprise apps | Depends on project                |


##8.1: ✔️ BEST ANSWER (Simple)

-> If application is simple CRUD and read-heavy, I prefer MySQL.
-> If application has complex queries, analytics, reports, I prefer PostgreSQL.


##8.2: Real Example (Very Easy)

###MySQL Use:
-> Login system
-> User management
-> E-commerce basic app

###PostgreSQL Use:
-> Reporting system
-> Financial systems
-> Data analytics

#9. SQL vs NoSQL

| SQL              | NoSQL              | Meaning                  |
| ---------------- | ------------------ | ------------------------ |
| Table based      | Document based     | SQL = rows, NoSQL = JSON |
| Fixed schema     | No fixed schema    | NoSQL more flexible      |
| Relations        | No relations       | SQL is strict            |
| ACID             | BASE               | SQL safer                |
| Vertical scaling | Horizontal scaling | NoSQL handles big data   |


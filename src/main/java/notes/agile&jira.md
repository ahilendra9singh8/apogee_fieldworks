############################################## Agile Methodology #################################################

#1. Agile Methodology Kya Hai
Agile ek software development methodology hai jisme project ko chhote-chhote parts (iterations / sprints) me develop kiya jata hai, aur har sprint ke baad working software deliver kiya jata hai.

Simple words me:
👉 Agile = Small steps me software develop karna + continuous feedback lena

-> Traditional method (Waterfall) me pura software end me deliver hota hai.
-> Agile me har 2–3 week me working feature deliver hota hai.

#2. Agile Kyu Use Kiya Jata Hai
1. Fast development
2. Customer feedback jaldi milta hai
3. Changes easily handle ho jate hain
4. Continuous improvement hota hai
5. Risk kam ho jata hai

#3. Agile Principles (Important for Interview)
Agile ka focus hota hai:

| Priority                  | Meaning                                |
| ------------------------- | -------------------------------------- |
| Working Software          | Documentation se jyada working product |
| Customer Collaboration    | Customer feedback important            |
| Respond to Change         | Change ko accept karna                 |
| Individuals & Interaction | Team communication important           |


#4. Agile Ka Workflow (Step by Step)
Agile development ka basic flow:

Requirement
     ↓
Product Backlog             (Ye sabhi requirements ki list hoti hai.)
     ↓
Sprint Planning             (Team decide karti hai ki next sprint me kaunse tasks complete karne hain.)
     ↓
Sprint (2-3 weeks)          (Sprint ek fixed time period hota hai.)
     ↓
Daily Standup               (Daily 15 minute meeting hoti hai.)
     ↓
Sprint Review               (Sprint end me product demo diya jata hai.)
     ↓ 
Sprint Retrospective        (Team discuss karti hai: kya acha hua, kya improve karna hai)
     


##4.1: Product Backlog
Ye sabhi requirements ki list hoti hai.

Example:

| ID | Feature           |
| -- | ----------------- |
| 1  | User Login        |
| 2  | User Registration |
| 3  | Forgot Password   |
| 4  | Dashboard         |


-> Isko Product Owner manage karta hai.

##4.2: Sprint Planning

Team decide karti hai ki next sprint me kaunse tasks complete karne hain.

Example:

Sprint Duration = 2 weeks

Sprint me selected tasks:
-> Login
-> Registration


##4.3: Sprint
Sprint ek fixed time period hota hai.

Usually:
-> 2 weeks
-> 3 weeks

Sprint ke end me working feature deliver hota hai.

Example:

Sprint 1 Output
✔ Login
✔ Registration

##4.4: Daily Standup Meeting
Daily 15 minute meeting hoti hai.

3 questions discuss hote hain:
1️. Kal kya kiya
2️. Aaj kya karoge
3️. Koi blocker hai kya


###Example:

Yesterday: Login API complete ki
Today: Login UI banaunga
Blocker: None

##4.5: Sprint Review
Sprint end me product demo diya jata hai.

Customer ko dikhaya jata hai:
-> Login feature ready hai
-> Registration ready hai

Customer feedback deta hai.

##4.6: Sprint Retrospective
Team discuss karti hai:
-> kya acha hua
-> kya improve karna hai

Example:

Problem: Testing late hui
Improvement: Next sprint me testing jaldi start karenge


#5. Agile Team Roles

| Role             | Responsibility                   |
| ---------------- | -------------------------------- |
| Product Owner    | Requirements define karta hai    |
| Scrum Master     | Agile process follow karwata hai |
| Development Team | Software develop karti hai       |




#################################################### Jira #################################################################

#1. Jira Kya Hai
Jira ek project management tool hai jo Agile teams use karti hain task tracking aur sprint management ke liye.

Simple words:

👉 Jira = Tool to manage Agile project

Company me mostly Agile projects Jira par manage hote hain.

#2. Jira Me Important Concepts
##2.1: Issue

Issue = Task / work item

Example:
-> Create Login API
-> Fix Registration Bug
-> Create Dashboard UI

##2.2: Issue Types

| Type  | Meaning          |
| ----- | ---------------- |
| Epic  | Large feature    |
| Story | User requirement |
| Task  | Small work       |
| Bug   | Error fix        |

###Epic
-> User Authentication

###Stories
-> User Login
-> User Registration
-> Forgot Password


##2.3: Epic
Epic = Large Feature

Example:

Epic: User Management

Under Epic:
-> Login
-> Registration
-> Profile Update

##2.4: User Story
User story ek requirement ko simple sentence me likhna.

Format:
-> As a <user>
-> I want <feature>
-> So that <benefit>

Example:
-> As a user
-> I want to login
-> So that I can access dashboard

##2.5: Sprint in Jira
Jira me sprint create kiya jata hai.

Example:
-> Sprint 1
-> Duration: 2 weeks

Tasks assign kiye jate hain.
-> Login API
-> Login UI
-> Registration API

#3. Jira Workflow

Typical workflow:

To Do
   ↓
In Progress
   ↓
Code Review
   ↓
Testing
   ↓
Done


Example:

Task: Login API
To Do → In Progress → Testing → Done


#4. Agile Aur Jira Ka Relation
Simple words:

-> Agile = Methodology (process)
-> Jira = Tool

Example:
-> Agile process follow karne ke liye Jira use hota hai.

Agile process
     ↓
Tasks manage karne ke liye
     ↓
Jira tool use hota hai


#5. Real Project Example

Suppose company E-commerce website bana rahi hai.

Product Backlog:
-> Login
-> Registration
-> Add to Cart
-> Payment
-> Order History

Sprint 1:
-> Login
-> Registration

Jira me tasks create honge:
-> Story: User Login
-> Task: Create Login API
-> Task: Create Login UI
-> Bug: Login validation issue

Team Jira par progress track karegi.


#6. Interview Me Short Answer

##6.1: Agile kya hai?
Agile ek software development methodology hai jisme project ko small iterations yani sprints me develop kiya jata hai. Har sprint ke end me working software deliver hota hai aur continuous customer feedback liya jata hai.

##6.2: Jira kya hai?
Jira ek project management tool hai jo Agile teams use karti hain task tracking, sprint planning aur bug tracking ke liye.

##6.3: Agile aur Jira me difference?
Agile ek development methodology hai jabki Jira ek tool hai jo Agile process ko manage karne ke liye use hota hai.


| Point          | Agile                                                     | Jira                                                               |
| -------------- | --------------------------------------------------------- | ------------------------------------------------------------------ |
| Type           | Software development **methodology / framework**          | **Project management tool**                                        |
| Purpose        | Software ko **iterative way (sprints)** me develop karna  | **Tasks, bugs aur sprints ko manage aur track karna**              |
| Nature         | **Process / approach**                                    | **Software application**                                           |
| Developed By   | Agile ek **concept / methodology** hai (Agile Manifesto)  | Jira ko **Atlassian company** ne develop kiya                      |
| Usage          | Development team **project ka workflow follow** karti hai | Team **tasks create, assign aur track** karti hai                  |
| Main Focus     | **Continuous delivery, collaboration, flexibility**       | **Issue tracking, sprint management, reporting**                   |
| Work Structure | **Product Backlog → Sprint → Review → Retrospective**     | **Epic → Story → Task → Bug**                                      |
| Implementation | Scrum, Kanban jaise frameworks me implement hota hai      | Jira in frameworks ko **manage karne me help karta hai**           |
| Output         | **Working software after every sprint**                   | **Task progress aur project tracking**                             |
| Dependency     | Agile tool ke bina bhi follow ho sakta hai                | Jira mostly **Agile process ko manage karne ke liye use hota hai** |

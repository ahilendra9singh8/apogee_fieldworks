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






################################################## Scrum ##################################################

#1. Scrum Kya Hai
Scrum ek Agile framework hai jiska use Agile process ko implement karne ke liye kiya jata hai.

Simple words me:
👉 Scrum = Agile ko follow karne ka practical way / framework

Agile ek methodology hai, aur Scrum us methodology ko implement karne ka process hai.

#2. Scrum Kyu Use Kiya Jata Hai
1. Team collaboration improve hoti hai
2. Work properly manage hota hai
3. Fast delivery possible hoti hai
4. Customer feedback jaldi milta hai
5. Progress easily track hoti hai

#3. Scrum Workflow

Product Backlog
      ↓
Sprint Planning
      ↓
Sprint (2-3 Weeks)
      ↓
Daily Scrum / Standup
      ↓
Sprint Review
      ↓
Sprint Retrospective
      ↓
Next Sprint


#4. Scrum Important Concepts

##4.1: Product Backlog
Product backlog me project ki sabhi requirements/features hoti hain.

Example:
-> Login
-> Registration
-> Dashboard
-> Payment

##4.2: Sprint Planning
Team decide karti hai ki next sprint me kaunse tasks complete karne hain.

Example:
Sprint Duration = 2 Weeks

Selected Tasks:
-> Login
-> Registration

##4.3: Sprint
Sprint ek fixed time period hota hai jisme selected tasks complete kiye jate hain.

Usually:
-> 2 Weeks
-> 3 Weeks

Sprint ke end me working software deliver hota hai.

##4.4: Daily Scrum / Standup
Daily 15 minute meeting hoti hai.

3 Questions:
1. Kal kya kiya?
2. Aaj kya karoge?
3. Koi blocker hai kya?

Example:
Yesterday: Login API complete ki
Today: Login UI banaunga
Blocker: None

##4.5: Sprint Review
Sprint ke end me completed work customer/client ko demo kiya jata hai.

Example:
-> Login feature ready hai
-> Registration ready hai

Customer feedback deta hai.

##4.6: Sprint Retrospective
Team discuss karti hai:
-> Kya acha hua
-> Kya improve karna hai

Example:
Problem: Testing late hui
Improvement: Next sprint me testing jaldi start karenge

#5. Scrum Roles

| Role             | Responsibility |
|------------------|----------------|
| Product Owner    | Requirements aur priorities manage karta hai |
| Scrum Master     | Scrum process properly follow karwata hai |
| Development Team | Development aur testing karti hai |

#6. Agile Aur Scrum Ka Relation

Simple words:
-> Agile = Methodology / Philosophy
-> Scrum = Agile implement karne ka framework

Example:
Agile bolta hai:
"Software small iterations me develop karo"

Scrum batata hai:
"Kaise develop karna hai using Sprint, Standup, Review, etc."

#7. Agile vs Scrum Difference

| Point | Agile | Scrum |
|------|--------|--------|
| Type | Methodology / Philosophy | Agile Framework |
| Purpose | Flexible software development | Agile ko implement karna |
| Nature | Concept | Practical process |
| Work Style | Iterative development | Sprint-based development |
| Focus | Customer collaboration & flexibility | Team management & sprint execution |
| Time Period | Fixed nahi hota | Sprint based (2-3 weeks) |
| Roles | Specific roles mandatory nahi | Product Owner, Scrum Master, Dev Team |
| Example | Agile Principles | Sprint Planning, Standup, Review |

################################################## Agile + Scrum + Jira Relation ##################################################

#Simple Relation

👉 Agile = Methodology / Philosophy  
👉 Scrum = Agile ko implement karne ka framework  
👉 Jira = Tool jo Scrum/Agile project manage karne ke liye use hota hai

Flow:

Agile Principles
       ↓
Scrum Process Follow
       ↓
Jira Tool Me Manage

################################################## Real Project Example ##################################################

Suppose company E-commerce Website bana rahi hai.

Agile:
-> Team iterative development follow karegi

Scrum:
-> Work ko 2-week sprint me divide kiya jayega

Jira:
-> Sprint aur tasks Jira me track honge

Example:

Sprint 1:
-> Login
-> Registration

Jira Tasks:
-> Story: User Login
-> Task: Create Login API
-> Task: Create Login UI
-> Bug: Login Validation Issue

################################################## Interview Short Answer (English) ##################################################

#1. What is Agile?
Agile is a software development methodology in which software is developed in small iterations called sprints. Working software is delivered frequently, and continuous customer feedback is taken.

#2. What is Scrum?
Scrum is an Agile framework used to manage and execute Agile projects through sprints, daily standups, sprint reviews, and retrospectives.

#3. What is Jira?
Jira is a project management and issue tracking tool used by Agile and Scrum teams to manage tasks, bugs, sprints, and project progress.

#5. Simple Interview Line
👉 Agile is the methodology, Scrum is the framework used to implement Agile, and Jira is the tool used to manage Agile/Scrum projects.




| Point             | Agile                                                    | Scrum                                        | Jira                                                          |
| ----------------- | -------------------------------------------------------- | -------------------------------------------- | ------------------------------------------------------------- |
| Type              | Software development methodology / philosophy            | Agile framework                              | Project management & issue tracking tool                      |
| Purpose           | Flexible and iterative software development              | Agile process ko implement karna             | Tasks, bugs aur sprints manage karna                          |
| Nature            | Concept / Approach                                       | Process / Framework                          | Software application / Tool                                   |
| Main Focus        | Customer collaboration, flexibility, continuous delivery | Sprint execution and team coordination       | Task tracking and project management                          |
| Work Style        | Iterative development                                    | Sprint-based development                     | Board/workflow-based tracking                                 |
| Time Duration     | Fixed nahi hota                                          | Fixed sprint duration (2–3 weeks)            | Sprint/task tracking according to project                     |
| Roles             | Specific roles mandatory nahi                            | Product Owner, Scrum Master, Dev Team        | Assignee, Reporter, Admin etc.                                |
| Workflow          | Backlog → Sprint → Review → Feedback                     | Sprint Planning → Sprint → Standup → Review  | To Do → In Progress → Testing → Done                          |
| Deliverable       | Working software in iterations                           | Working feature after every sprint           | Project/task progress reports                                 |
| Usage             | Development strategy define karta hai                    | Team ko Agile follow karne me help karta hai | Agile/Scrum work ko manage karta hai                          |
| Example           | Continuous feedback & iterative development              | Daily Standup, Sprint Review                 | Epic, Story, Task, Bug                                        |
| Dependency        | Tool ke bina bhi use ho sakta hai                        | Agile ke under use hota hai                  | Mostly Agile/Scrum projects me use hota hai                   |
| Developed By      | Agile Manifesto concepts                                 | Scrum framework creators                     | [Atlassian](https://www.atlassian.com?utm_source=chatgpt.com) |
| Simple Definition | “How to develop software”                                | “How to execute Agile”                       | “How to track and manage work”                                |

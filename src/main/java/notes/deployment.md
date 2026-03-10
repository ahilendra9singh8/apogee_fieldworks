Jenkins => 
Jenkins ek CI/CD tool hai jo software ko automatically build, test aur deploy karne me help karta hai.
Jenkins ek open-source automation server hai jo Continuous Integration aur Continuous Deployment ke liye use hota hai.

CI/CD => 
CI/CD pipeline ek automatic process hota hai jo code likhne se leke production tak pahunchane ka kaam karta hai — bina manual effort ke.
CI/CD pipeline code ko automatically build, test aur deploy karti hai.

🔄 CI(Continuous Integration) vs CD(Continuous Deployment)

| CI                    | CD               |
| --------------------- | ---------------- |
| Code integrate + test | Code deploy      |
| Bugs jaldi milte      | Fast release     |
| Build & test focus    | Production focus |




🔄 Complete Flow: Code → GitHub → CI → CD → AWS

🧑‍💻 1️⃣ Code Development
Developer local machine par code likhta hai
Example: Java / Node / Python application

🐙 2️⃣ Code GitHub par push hota hai
Code GitHub repository me push hota hai
GitHub ek source code management (SCM) tool hai


📌 Yahin se CI/CD pipeline start hoti hai


⚙️ 3️⃣ Continuous Integration (CI) – Jenkins ka role
👉 Jenkins yahan CI ke liye use hota hai

CI me kya hota hai?

GitHub se webhook Jenkins ko trigger karta hai
Jenkins automatically:
->Code pull karta hai
->Build karta hai
->Unit tests run karta hai

✔️ Agar test fail → pipeline stop
✔️ Agar test pass → next step

📌 CI ka goal:

Code sahi hai ya nahi ye check karna


🚀 4️⃣ Continuous Deployment (CD) – Jenkins + AWS

👉 Jenkins yahan CD ke liye bhi use hota hai

CD me kya hota hai?

Jenkins:

->Application package banata hai (jar / image)
->AWS pe deploy karta hai

AWS pe deploy kaise?

Depends on setup:

->EC2 → SSH se app deploy
->Docker + ECS / EKS → Container deploy
->Elastic Beanstalk → Auto deploy
->S3 + CloudFront → Frontend deploy

📌 CD ka goal:

Tested code ko automatically production me deploy karna


☁️ 5️⃣ AWS (Production Environment)

->User application access karta hai
->Monitoring (CloudWatch)
->Logs & alerts


Developer
   ↓
GitHub
   ↓
Jenkins (CI)
Build + Test
   ↓
Jenkins (CD)
Deploy
   ↓
AWS (EC2 / EKS / ECS)

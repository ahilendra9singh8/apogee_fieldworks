# Kubernetes (k8s)
1.Chocolatey
2.kubectl
3.Minikube
4.Kubernetes
5.Master Node
5.1=> API Server
5.2=> Scheduler
5.3=> Controller Manager
5.4=> etcd.
6.Worker Node
6.1=> Kubelet
6.2=> Kube-proxy
6.3=> Container Runtime
7.pods(smallest unit of Kubernetes)
8.Higher Level Objects(These help manage apps at a higher level)
8.1=> Deployment
8.2=> ReplicaSet
8.3=> Service
8.4=> ConfigMap & Secret
8.5=> Namespace
=====================================> setup Kubernetes
1. Chocolatey, Scoop, aur winget kya hain? --> Yeh teeno Windows ke liye package managers hain.
Jaise Android mein Play Store hota hai, jahan se aap apps install karte ho.
Waise hi Windows ke liye yeh tools hain, jinke through aap software jaldi aur easily command line se install, update, uninstall kar sakte ho — bina manually website pe jaake download karein.
--> i am using chocolatey.

2.kubectl (Kubernetes CLI)
kubectl ek command-line tool hai jiska use Kubernetes clusters ko manage karne ke liye hota hai.
kubectl = Kubernetes ka remote control
Isse aap apps ko start, stop, monitor kar sakte ho
Har command ka ek specific kaam hota hai
--> install kubectl --> on window install kubectl using Chocolatey
choco install kubernetes-cli
kubectl version --client

3.Minikube (local Kubernetes cluster)
--> yah ek cluster he (Docker Desktop bhi ek cluster h iska bhi use kr skte h)
minikube is local Kubernetes, focusing on making it easy to learn and develop for Kubernetes.
All you need is Docker (or similarly compatible) container or a Virtual Machine environment, and Kubernetes is a single command away: 
minikube start

Minikube ek tool hai jo tumhare local computer par ek chhota (mini) Kubernetes cluster run karta hai.
Real Kubernetes cluster chalana mehenga aur complex hota hai (cloud pe chalana padta hai)
Minikube se aap:
Practice kar sakte ho
Apps test kar sakte ho
Kubernetes ke concepts seekh sakte ho
Sab kuch local machine pe bina internet ke bhi kar sakte ho
Ek single-node Kubernetes cluster banata hai (yaani master aur worker ek hi jagah)
Ye cluster aapke laptop/PC par virtual machine ya container ke form mein chalta hai

==> install minikube
choco install minikube
==>Start your cluster
minikube start
==> than you can use kubectl
kubectl get nodes
kubectl apply -f app.yaml

4..kube/config setup

==========================> Kubernetes architecture


# Kubernetes kya hai?

Kubernetes (k8s) ek open-source container orchestration platform hai jo containers (jaise Docker) ko automate karta hai—deployment, scaling, aur management ke liye.
Socho aapke paas 100 containers hain—Kubernetes unhe smartly handle karta hai. Jaise ek traffic police jo sab gadiyon ko control karta hai.

Kubernetes is an open-source platform that helps you manage, deploy, and scale containers automatically.
👉 Think of it like a traffic controller that organizes hundreds of containers (like Docker) running on multiple machines. It makes sure everything runs smoothly without you manually managing each container.

# Kubernetes Architecture Overview

1. Cluster ==>
Kubernetes ka pura system ek Cluster hota hai.
Cluster = Master Node(s) + Worker Node(s)

A Kubernetes Cluster is the full system.
It contains:
one or more Master Nodes (Control Plane) – The brain.
Multiple Worker Nodes – Where apps actually run.



2. Master Node (Control Plane) ==>
Master Node sab kuch control karta hai—poora system ka brain hota hai.

It controls and manages the entire cluster.

Iske main components:

2.1: API Server -->
Ye ek gatekeeper hai. Sab request yahi se jaati hai (CLI, UI, etc).
REST API provide karta hai.

Entry point for all commands (from CLI, dashboard, etc.)
Like a gatekeeper.

2.2: Scheduler -->
Decide karta hai ki kaunsa container (Pod) kis worker node par chalega.

Decides which app (Pod) runs on which worker node.
Like a manager assigning tasks.

2.3: Controller Manager -->
Ye system ke state ko desired state ke according maintain karta hai.
Jaise: Replica Controller, Node Controller, etc.
-->Dekhta hai sab kitchen me sahi se kaam ho raha hai ya nahi.
   Agar koi dish kharab ho gayi ya cook absent hai, to naya chef assign karta hai.
   
Ensures the desired state of the cluster is maintained.
If a pod fails, it creates a new one automatically.

2.4: etcd (key-value Database) -->
Ye ek key-value store hai jisme cluster ki sari information hoti hai (configuration, state, etc).
Bahut important component hai—jaise memory.

Stores all cluster data (like config, status).
Like a memory book of the cluster.



3. Worker Node (Minions) ==>
Yahan actual containers chalte hain. Ye master ke under kaam karte hain.

This is where the actual containers run.

Iske components:

3.1: Kubelet -->
Worker node pe chalne wala agent hai.
Master se instruction leta hai aur node pe implement karta hai.

Communicates with the Master.
Runs and manages Pods on Worker node.

3.2: Kube-proxy -->
Networking sambhalta hai.
Load balancing karta hai, aur services ko access dilata hai.

Manages network rules.
Forwards traffic to the correct Pod.
Like a router or network manager.

3.3: Container Runtime -->
Actual container ko chalane ka kaam karta hai (jaise Docker, containerd).

Actually runs the containers.
Like the engine that powers apps.

4. Pods (Smallest Unit in Kubernetes) ==>
Pod = 1 ya 1 se zyada containers (mostly 1)
Ye containers ek hi IP aur storage share karte hain.
Kubernetes me aap directly containers nahi chalate, aap Pods chalate ho.

A Pod is the smallest deployable unit.
Usually contains one container (sometimes more).
Pods share the same IP and Storage.
💡 Kubernetes never runs containers directly, it always runs Pods.


5. Higher Level Objects (Deployment, ReplicaSet, Service etc.) ==> 
These help manage apps at a higher level:

5.1: Deployment -->
Ye batata hai ki koi app kitne replicas me chale.
Agar ek pod down ho jaye, to naya pod le aata hai.

Tells Kubernetes how many replicas (copies) of a Pod you want.
Handles rolling updates and restarts.

5.2: ReplicaSet -->
Ensure karta hai ki specific number of pod instances running rahe.

Makes sure the correct number of Pods are running.
Replaces crashed ones.

5.3: Service -->
Pods ke aage ek static IP banata hai.
Load balancing aur communication simplify karta hai.

Gives a stable IP to access Pods, even if Pods change.
Helps in load balancing.


5.4: ConfigMap & Secret -->
App ke configuration aur sensitive info (jaise passwords) handle karte hain.

Store app configurations and sensitive data (like passwords).
Secret encrypts the data.

5.5: Namespace -->
Ek cluster ko logical parts me divide karta hai—alag-alag teams/apps ke liye.

Divides the cluster into logical sections (e.g., dev, test, prod).
Helps in organizing large systems.

6.
Kubernetes Cluster
│
├── Master Node (Control Plane)
│   ├── API Server
│   ├── Scheduler
│   ├── Controller Manager
│   └── etcd (Database)
│
├── Worker Node(s)
│   ├── Kubelet
│   ├── Kube-proxy
│   └── Container Runtime (Docker)
│
├── Pods
│   └── Containers
│
├── Deployments / ReplicaSets
│
├── Services
│
├── ConfigMap / Secret
│
└── Namespaces


Part	                        Role
Control Plane   	   Sochta hai, manage karta hai
Worker Node	           Kaam karta hai (containers chalata hai)
Pod	                   Container ka wrapper hai
Kubelet	               Master ki baat sunta hai
Scheduler  	           Decide karta hai kaha kya chalega
API Server  	       Gatekeeper hai
etcd  	               Configuration ka dimaag (database)



"Kubernetes is an open-source container orchestration platform that helps manage containerized applications at scale. 
Its architecture includes a Control Plane (Master Node) that makes decisions and Worker Nodes that run the actual applications inside Pods. 
Key components include API Server, Scheduler, Controller Manager, and etcd for the Control Plane, and Kubelet, Kube-proxy, and Container Runtime for the Worker Nodes. 
Pods are the smallest deployable units, and tools like Deployments and Services help manage and expose them."


========================================> Deploy project on kebernetes
start cluster or kubernates -> minikube start

1.docker image chahiye (docker hub se utha lo)
2.docker container chahiye
3.sabse pahle pods bnayege jiske andar container rahege

kubectl create deployment my-nginx --image=nginx:latest
kubectl get deployments
kubectl get pods
minikube dashboard
-> abhi mera nginix start ho gya h but yah access nhi hoga kyuki yah pods ke andar jise direct access nhi kr skte uske liye ek tarika jise service object bolte h.

kubectl expose deployment my-nginx --port=80 --type=LoadBalancer
kubectl get services
minikube service my-nginx
-> now nginix get on browser

-> abhi tak jo cmd use ki vh

minikube start/delete
minikube status
minikube dashboard

kubectl create deployment my-app --image=link
kubectl get deployments
kubectl get pods
kubectl logs <pods name>
kubectl describe pods
kubectl delete deployment my-app

kubectl expose deployment my-app --port=80 --type=LoadBalancer
kubectl get services
minikube service my-app


-> mene ek reactjs project bnaya dockerfile bnayi phir docker image aur is docker image ko dockerhub pr push kr diya
ab mujhe is image ko kubernatics pr deploy krna h (jb bhi kisi project ko kubernetes pr deploy krna hota h to uski image docker hub se get krte h)

minikube status (check kubernetes is started or not)
kubectl create deployment my-app --image=link

-> simple h ya apni dockerfile push kre ya dockerhub se koi bhi get kr le kubernates pr aur use kubernates ki madad se run kr de.

-> agar ham apne project me kuchh changes krte h to vh live update kese hoga kubernetes pr
kubectl set image deployment my-webapp reactjs-docker=shailendra277/reactjs-docker:v02

here reactjs-docker is container name and give update image so here give v02
jb tak new image puri tarah deploy nhi ho jayegi tab tak purana pods running rhega than terminate ho jayega.


==========================> some new Terms

1. Rollback of Project
agar image ka galat version de dege jo dockerHub pr h hi nhi to image update show karega but ImagePullBackOff(atak jayega hamesha ke liye) ho jayegi.
ImagePullBackOff ko hatane ke liye cmd

kubectl rollout undo deployment my-webapp

2. Self Healing (self upchar - automatic restart)
ek website kubernate cluster -> pods -> container ke andar char rhi h aur yhi kisi small error ke karan band ho jati h isko kubernetes kese handle krta h isi ko self healing kahate h ,
 me chahta hu ye hamesha chalu rhe.
-> iske liye Express-Demo project use kiya h.
-> jese mera project start use forcefully stop kr diya to server stopped ho gya
-> Express-demo repository from Dockerhub
docker pull philippaul/node-demo-app:latest
-> create deployments
kubectl create deployment node-app --image=philippaul/node-demo-app:01
kubectl get deployments
kubectl get pods
-> check service
kubectl get services
-> create service
kubectl expose deployment node-app --port=3000 --type=LoadBalancer
-> after create service use minikube
minikube service node-app

--> ab yadi ham apni service ko forcefully exit kr dete h to kubernetes dashboard pr dekehge ki yah red dot ho jayege but kubernetes automatically inko start kr dega.

3.Scalling of App
-> uper hamne dekha bhale hi kubernetes ne app ko restart krke upin running rkha but kuchh second ke liye to app down ho gya tha ham kuchh second ke liye bhi down nhi rakh skte,
 is issue ko resolve krne ke liye ham multi instance (scale up kr dege) bna dege.

kubectl scale deployment node-app --replicas=4

-> hame hamri website 4 alag alag pods me chalegi.ab me band(exit) karege to bilkul bhi downtime nhi h ek instance band hogi turant dusari start ho jayegi.load distribute ho jayega.

4. YAML Config
-> abhi tak ek website ko chalane ke liye bhut sare steps manage kr rhe the jese - deployment bnana, service banana, saclling krna etc.
-> ab ham ek yaml configuration file me sab kuchh daal dege aur usko start karege
phle ham deplyment aur service yaml alag bnayege phir dono yaml ek me hi krege. in files ka kuchh bhi name de skte h.

A.)deployment-node-app.yml --> 
-> deployment ke liye
-> yh file bnayege kisi bhi location pr aur isme configuration karege.
-> ye file jis bhi directory me h vha pr jakar is file ko start karege to deplyoment create ho jayega aur bhi cong hogi vh bhi

kubectl apply -f deployment-node-app.yml
kubectl get deployments
kubectl get pods
-> delete/remove deplyoment
kubectl delete -f deployment-node-app.yml

B.)service-node-app.yml -->
-> service ke liye
kubectl get services
kubectl delete service my-webapp
-> start service-node-app.yml
kubectl apply -f service-node-app.yml
minikube service service-my-node-app
-> delete/remove service 
kubectl delete -f service-node-app.yml 

c.) Single K8s Config File -->
isme deployment-node-app.yml and service-node-app.yml i dono ko ek single file me kr dete h aur run kr dete h.

5. Multiple Container App
-> multiple container bali application ko manage krna. abhi yha two application mongodb or node-app chalayege. 
-> project-> db-demo-app (or) repo is --> philippaul/node-mongo-db
-> pull
docker pull philippaul/node-mongo-db
--> Run in Local
-> To run mongodb
docker run -d -p 27017:27017 --network my-net --name mongo mongo
-> To run our Node App
docker run --network my-net -p 3000:3000 --name myapp philippaul/node-mongo-db

--> Run on Kubernetes
There are two ways to run application with multiple containers - 
A.)Run multiple containers in same pod -- its containers use same resources, same network etc.
B.) Run each container in seprate pod 

A.) Run multiple containers in same pod ==>
-> two docker images
philippaul/node-mongo-db:02
mongo
-> yah deployment .yml file ka use krke karege. iske liye in yml file me iske according change kr lege
kubectl apply -f deployment-node-app.yml
kubectl apply -f service-node-app.yml

-> Problems
abhi hamare pass two pods(replicas) to two database alag alag pod me bn jayege, jisse ek pod down hone pr data dusre pod ki db me chla jayega yadi me jb access kr rha hu tab first pod chal rha h
 to usko data nhi milega.


B.) Run each container in seprate pod (Multiple Containers in Separate PODS) ==>
mongo-config.yml ->
MONGO_HOST or MONGO_PORT ko dynamic krne ke liye is file me configuration krte phir jha bhi use krna rhta h vha pr process.env.MONGO_HOST krke get kr lete h.



6.) Kubernetes Volumes and Data
-> abhi ham apna database container ke andar store kr rhe h, yadi kabhi ese hota h ki container down ho jata to new container bnta jisse purana container ke andar ke db ka data lost ho jata.
yadi ham db ko container ke bhar pod me rkhte h phir bhi pods bhi down ho jata 
to iska solution ham db ko pods ke bhar bhi cluster me hi rakh lete h.
aur best ham is db ke data ko cloud(AWS) pr bhi rakh lete h.

-> abhi ham pods ke bhar cluster me db manage kregem uske liye ham persistance volume use karege.
==> persistance volume(pv) -> yadi pod band bhi ho gya phir bhi yh exist krega.
ek yml file persistance volume ke configuration ke liye bnayege.
ek yml file persistance volume claim (pvc) ke configuration ke liye bnayege.
phir hmane jo mongodb ke liye configuration file bnayi thi usme bta dege ki hame persistance volume use krna h.aur isme hi bta dege yh cluster me chale.
-> ab ham test krege kuchh data db me add krege than db ko band kr dege than again start krege to data persist rhega.










=================================================================> AWS
================================> GitHub, CI/CD Pipeline -Jenkins, Docker, DockerHub, K8s, AWS, RDS(optional)

ab ham apna project github pr push krege aur jenkins ko ec2 me install krke sabhi configuration kr dege, ab jenkins ke dwara github se project ko dockerhub pr image push kr dege 
aur aws pr ec2 me docker, K8s install kr lege aur jenkins ke dwara is image ko K8s cluster me daal dege jisme hmara .yml use hoga project ko deployment aur run krne ke liye
-> yadi RDS ka use kr rhe h to mysql docker image ki jarurat nhi h. in dono me se koi ek hi use kroge.

=> quik overview

Tool/Service	                                    Kaam

GitHub Actions	                      Code Hosting( Automated build, docker push, deploy using jenkins)
Docker	                              Spring Boot project ko image me convert krna
Docker Hub	                      Public/private registry for image
Jenkins	                              CI/CD pipeline executor
Kubernetes	                      Container ko manage krna (deployment, scaling, restart)
AWS EC2	                              Server jahan K8s cluster chalayenge
AWS RDS (optional)	              MySQL ka managed version


=>

1. Create SpringBoot Project(JPA) with Dockerfile use MySQL Database

2.Docker
Aapke Spring Boot application ko container ke रूप में package करता है (image बनाता है). is image ko dockerhub pr push kr dete h. aur mysql image bhi 
Ye image portable hoti hai – kahin bhi chala सकते हैं, बस Docker हो.

3.☸️ Kubernetes
Ye container orchestration करता है – यानी multiple containers ko manage karta hai (scaling, load balancing, restart, service discovery).
Aapka Docker image Kubernetes pods में चलेगा.

4.☁️ AWS (Amazon Web Services)
Ye infra provide करता है जहां humara containerized project चलेगा.
Hum EC2 का use करेंगे Kubernetes cluster chalाने के लिए.

Optional: RDS (Relational Database Service) – MySQL का managed version (agar aap local MySQL ko cloud par shift krna chahein)., agar RDS use kr rhe h to docker image (mysql) ka use nhi karege.

Note--> jis tarah ham local machine me K8s cluster me docker image ko chalate h. usi tarah is image ko K8s cluster me clowd(AWS) pr chalate h.

4.1) AWS EC2 Instance बनाना
4.2) EC2 पर Docker और Kubernetes Install करना
     kubernetes ke liye Minikube OR kubeadm dono me se kuchh bhi use kr skte h inka.
    
Minikube vs Kubeadm – क्या फर्क है?

Feature	                                 Minikube	                                  Kubeadm
Use Case	                    Local Development/Test	                  Production-level Cluster
Installation	                    Simple (1 command)	                          Manual setup
Cluster Type	                     Single node	                          Multi-node (real cluster)

4.3) Docker Image को EC2 पर लाना
     Two way
     Aapne jo Docker image Docker Hub pr push ki hai usko EC2 ke andar pull करें:
     Ya, Kubernetes YAML में image का नाम डाल देंगे तो Kubernetes khud hi pull कर लेगा.

4.4)

Questions.) YAML file local vs AWS me kaise use hoti hai?
          Option 1: Local YAML file ko EC2 par copy karke deploy karein phir use ec2 pr hi run kr de
          Option 2: AWS (EKS) use karein
          kubectl configured with EKS cluster (via aws eks update-kubeconfig).
          Local se hi command chalega:








IMportant ==> 
1. create springboot project
2. push on github
3. push on dockerhub using jenkins
4. create Ec2 and on ec2 setup - jenkins, docker, K8s 
5. ddeploy project on AWS using jenkins by .yml file - ec2-> K8s -> springboot project
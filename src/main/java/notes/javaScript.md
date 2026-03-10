################################ PART 1 (Core Basics + Execution + Functions) ##############################

#🟢 SECTION 1: JavaScript Basics (Strong Foundation)

#1. JavaScript kya hai? Is it synchronous or asynchronous?

JavaScript ek high-level, interpreted, single-threaded language hai jo browser & server (Node.js) dono me chalti hai.

👉 JavaScript by default synchronous hoti hai
👉 Lekin asynchronous behavior support karti hai

###Example:
console.log("A");
console.log("B");
console.log("C");

➡ Output:
A B C

###Async example:
console.log("A");

setTimeout(() => {
  console.log("B");
}, 1000);

console.log("C");

➡ Output:
A
C
B

#2. JavaScript single-threaded hote hue asynchronous kaise hoti hai?

##JavaScript Single Threaded Kya Hai?
-> JS ek time par sirf ek kaam karta hai
-> Ek hi Call Stack hoti hai
-> Isliye by default synchronous

##🧠 Fir JS Asynchronous Kaise?
-> JS khud single-threaded hai
-> Lekin browser/Node ke paas extra features hote hain:

✅ Web APIs
✅ Microtask Queue
✅ Callback Queue
✅ Event Loop

Ye sab milkar JS ko non-blocking banate hain.

##2.1: Call Stack
Yahan normal JS code line-by-line execute hota hai.

##2.2: Web APIs
Jab setTimeout, fetch, DOM events jaise async kaam milte hain,
to JS unhe Web APIs ko de deta hai (browser handle karta hai).

##2.3: Queue
Kaam complete hone ke baad callback Queue me chala jata hai.

1. Promise → Microtask Queue
2. setTimeout, events → Callback Queue

##2.4: Event Loop
Event Loop check karta hai:
👉 "Call Stack khali hai kya?"
Agar haan, to Queue se function uthake Stack me daal deta hai.

Note => aage chalkar isko example ke sath likh de.


#3. var, let, const difference (🔥 interview favorite)

| Feature   | var             | let       | const     |
| --------- | --------------- | --------- | --------- |
| Scope     | Function        | Block     | Block     |
| Redeclare | Yes             | No        | No        |
| Reassign  | Yes             | Yes       | No        |
| Hoisting  | Yes (undefined) | Yes (TDZ) | Yes (TDZ) |

##Redeclaration
1. var → ✅ Allowed

var a = 10;
var a = 20;  // ✅ No error
console.log(a); // 20

2. let → ❌ Not Allowed

let b = 10;
let b = 20;  // ❌ SyntaxError

3. const → ❌ Not Allowed

const c = 10;
const c = 20; // ❌ SyntaxError

###Reassignment
1. var → ✅ Allowed

var a = 10;
a = 50;  // ✅
console.log(a); // 50

2. let → ✅ Allowed

let b = 10;
b = 60;  // ✅
console.log(b); // 60

3. const → ❌ Not Allowed

const c = 10;
c = 70;  // ❌ TypeError

👉 const value cannot be changed.

#4. Hoisting kya hoti hai?
JS me variables & functions memory me pehle load ho jaate hain.

##Example (var):

console.log(a);
var a = 10;

➡ Output:

undefined

###Function hoisting:

hello();

function hello() {
  console.log("Hello");
}

➡ Works perfectly ✅

#5. Temporal Dead Zone (TDZ) kya hai?
let aur const hoist to hote hain, lekin
initialize hone se pehle access nahi kar sakte

###Example:

console.log(a);
let a = 10;

➡ ❌ ReferenceError (TDZ)

#6. JavaScript data types
##6.1: Primitive:
1. string
2. number
3. boolean
4. undefined
5. null
6. symbol
7. bigint

##6.2: Non-Primitive:
1. object
2. array
3. function

#7. Primitive vs Non-Primitive

| Primitive  | Non-Primitive  |
| ---------- | -------------- |
| Value copy | Reference copy |
| Immutable  | Mutable        |


##Example 1: 
let a = 10;
let b = a;
b = 20;
console.log(a); // 10

##Example 2: 
let obj1 = {x:10};
let obj2 = obj1;
obj2.x = 20;
console.log(obj1.x); // 20


#8. typeof null === "object" kyu?
Ye JavaScript ka old bug hai (backward compatibility ke wajah se).

typeof null; // "object"

#9. == vs ===
1. == → value compare
2. === → value + type compare

5 == "5"   // true
5 === "5"  // false

👉 Interview me hamesha bolo:
Use === (strict equality)

#10. Pass by value vs pass by reference
1. Primitive → pass by value
2. Object → pass by reference


#🟢 SECTION 2: Execution & Memory (VERY IMPORTANT)

#11. Execution Context kya hota hai?
Execution Context =
Environment jahan JS code execute hota hai

Contains:
-> Variable Environment
-> Scope Chain
-> this

#12. Global vs Function Execution Context
1. Global EC → page load par banta hai
2. Function EC → function call par

var x = 10;
function test(){
  var y = 20;
}

#13. Call Stack kya hota hai?
Call Stack ek LIFO structure hai jahan functions execute hote hain.

function a(){
  b();
}
function b(){
  console.log("B");
}
a();

###Stack:
a()
b()


#14. Scope & types
1. Global Scope
2. Function Scope
3. Block Scope

#15. Lexical Scope
Function apne parent scope ke variables access kar sakta hai.

function outer(){
  let x = 10;
  function inner(){
    console.log(x);
  }
  inner();
}

#16. Scope Chain
JS variable ko:
1. Current scope
2. Parent scope
3. Global scope
me search karta hai

#17. Garbage Collection
JS automatically unused memory free karta hai.

👉 Example:
function ke baad variables destroy ho jaate hain




#🟢 SECTION 3: Functions (Interview Favorite)

#18. Function Declaration vs Expression
// Declaration
function add(a,b){ return a+b; }

// Expression
const add = function(a,b){ return a+b; }

👉 Declaration hoisted hota hai
👉 Expression nahi

#19. Arrow vs Normal Function

| Normal      | Arrow         |
| ----------- | ------------- |
| Has `this`  | No own `this` |
| arguments   | No arguments  |
| Constructor | Cannot        |


#20. Arrow function me this
const obj = {
  name: "Java",
  show: () => {
    console.log(this.name);
  }
};

➡ Output: undefined

👉 Arrow function parent scope ka this use karta hai

#21. IIFE kya hota hai?
Immediately Invoked Function Expression

(function(){
  console.log("Run once");
})();

👉 Data privacy ke liye use hota hai

#22. Higher Order Function
Jo function:
1. function ko argument me le
2. ya function return kare

###Example 1 (Function as argument)

function greet(name, callback) {
  console.log("Hello " + name);
  callback();
}

function sayBye() {
  console.log("Bye!");
}

greet("Rahul", sayBye);

👉 Yaha greet ek Higher Order Function hai
Kyuki wo callback (function) ko argument me le raha hai.

###✅ Example 2 (Function return kare)

function multiplyBy(x) {
  return function(y) {
    return x * y;
  };
}

const double = multiplyBy(2);
console.log(double(5)); // 10

👉 multiplyBy ek HOF hai
Kyuki wo function return kar raha hai.

#23. Callback Function
Callback Function wo function hota hai jo:
1. Kisi dusre function ko argument me diya jata hai
2. Aur baad me execute hota hai

👉 Matlab: Callback = Jo pass kiya gaya function hai

###Example 1: 
function fetchData(cb){
  cb();
}

fetchData(() => {
  console.log("Done");
});


#24. Callback Hell
Ye ek problem/situation hai jab:

Multiple callbacks ek dusre ke andar deeply nested ho jate hain
Aur code unreadable ho jata hai

a(() => {
  b(() => {
    c(() => {
      d();
    });
  });
});

👉 Solution: Promises / async-await


################################################# PART 2 (Closures + this + call/apply/bind) #################################

#🟢 SECTION 4: Closures (🔥 VERY IMPORTANT 🔥)

#25. Closure kya hota hai?
Jab inner function apne outer function ke variables ko yaad rakhta hai,
even after outer function execute ho chuka ho — use Closure kehte hain.

👉 Function + Lexical Scope = Closure

#26. Closure ka basic example

function outer() {
  let count = 0;

  function inner() {
    count++;
    console.log(count);
  }

  return inner;
}

const fn = outer();
fn(); // 1
fn(); // 2
fn(); // 3

🔹 outer() execute ho chuka
🔹 Fir bhi inner() ko count mil raha hai
➡ Because of closure

#27. Real-world use case of Closure
✅ Data Hiding / Encapsulation

function bankAccount() {
  let balance = 1000;

  return {
    deposit(amount) {
      balance += amount;
      console.log(balance);
    },
    withdraw(amount) {
      balance -= amount;
      console.log(balance);
    }
  };
}

const account = bankAccount();
account.deposit(500);  // 1500
account.withdraw(300); // 1200

👉 balance direct access nahi ho raha
👉 Secure & private data
=> private variable bnane ke liye use krte h?

#28. Closure memory leak kaise cause karta hai?
Problem:
Agar closure unnecessary variables ko reference karta rahe
to memory free nahi hoti

function leak() {
  let bigData = new Array(1000000).fill("JS");
  return function () {
    console.log("leak");
  };
}

👉 bigData use nahi ho raha
👉 Fir bhi memory me hai

Solution:
Unwanted references ko null kar do

#29. Interview Tip (Closure)
Interviewers poochte hain:
-> Closure kya hota hai?
-> Real use?
-> Memory issue kaise avoid karoge?

👉 Encapsulation, Currying, Memoization ka naam lo ✔


#🟢 SECTION 5: this Keyword (🔥 MOST CONFUSING 🔥)

#30. this kya hota hai?
this ek keyword hai jo current execution context ko refer karta hai.

👉 Value depend karti hai:
-> kaise function call ho raha hai

#31. Global scope me this
console.log(this);

-> Browser → window
-> Node.js → {}

#32. unction ke andar this
function test() {
  console.log(this);
}
test();

➡ Browser me → window
➡ Strict mode me → undefined

#33. Object method me this
const obj = {
  name: "Java",
  show() {
    console.log(this.name);
  }
};

obj.show(); // Java

👉 this → obj

#34. Arrow function me this
const obj = {
  name: "Spring",
  show: () => {
    console.log(this.name);
  }
};

obj.show(); // undefined

👉 Arrow function ka khud ka this nahi hota
👉 Parent scope ka this leta hai

#35. Nested function trap (Interview favorite)
const obj = {
  name: "React",
  show() {
    function inner() {
      console.log(this.name);
    }
    inner();
  }
};

➡ Output: undefined


✅ Fix using arrow function:

const obj = {
  name: "React",
  show() {
    const inner = () => {
      console.log(this.name);
    };
    inner();
  }
};


#🟢 SECTION 6: call / apply / bind (🔥 MUST KNOW 🔥)


#36. call, apply, bind kya hain?
Ye methods this ko manually set karne ke liye use hote hain.

#37. call() example
function greet(city) {
  console.log(this.name + " from " + city);
}

const user = { name: "Shailendra" };

greet.call(user, "Delhi");

➡ Output:

Shailendra from Delhi

#38. apply() example

greet.apply(user, ["Mumbai"]);

👉 Difference sirf arguments ka hai
=> call → comma separated
=> apply → array

#39. bind() example (🔥 real use case)
const boundFn = greet.bind(user, "Pune");
boundFn();

👉 bind new function return karta hai
👉 React / event handlers me use hota hai

#40. call vs apply vs bind (Interview table)

| Feature            | call | apply | bind |
| ------------------ | ---- | ----- | ---- |
| Invoke immediately | ✅    | ✅     | ❌    |
| Arguments          | List | Array | List |
| Returns function   | ❌    | ❌     | ✅    |


#41. Real Interview Question
Q: React me bind kyu use hota hai?
A:Event handler me this lose ho jata hai
👉 bind se correct context fix kar dete hain



###########################  PART 3 (Objects + Prototypes + Inheritance + ES6 Basics) ###########################################

#🟢 SECTION 7: Objects in JavaScript

#42. Object kya hota hai? (Definition)
Object ek collection of key–value pairs hota hai jisme data (properties) aur behaviour (methods) hote hain.

👉 JavaScript prototype-based language hai (class-based nahi)

#43. Object create karne ke tarike

##1. Object Literal (Most common)
const user = {
  name: "Shailendra",
  role: "Full Stack",
  greet() {
    console.log("Hello");
  }
};

##2. Using new Object()
const user = new Object();
user.name = "Java Dev";

##3. Constructor Function
function User(name, role) {
  this.name = name;
  this.role = role;
}

const u1 = new User("Amit", "Backend");

##4. Object.create()
const parent = { company: "IT" };
const child = Object.create(parent);

#44. Object properties access kaise karte hain?
user.name       // dot notation
user["name"]    // bracket notation

👉 Bracket notation dynamic keys ke liye use hota hai


#🟢 SECTION 8: Prototype (🔥 VERY IMPORTANT 🔥)


#45. Prototype kya hota hai? (Definition)
Prototype ek hidden object hota hai jisme shared properties & methods hote hain.

👉 JavaScript me har object ke paas prototype hota hai

#46. Prototype ka basic example
function Person(name) {
  this.name = name;
}

Person.prototype.sayHello = function () {
  console.log("Hello " + this.name);
};

const p1 = new Person("Java");
p1.sayHello(); // Hello Java

👉 Method memory me ek hi baar banta hai
👉 Sab objects use kar sakte hain

#47. Prototype chain kya hoti hai? (Definition)
Jab JS kisi property ko object me nahi paata,
to prototype → parent prototype → Object.prototype me dhundhta hai
isi process ko Prototype Chain kehte hain.

#48. Prototype chain example
const arr = [];
arr.push(10);

Flow:

arr → Array.prototype → Object.prototype → null

#49. __proto__ vs prototype

| `__proto__`         | `prototype`                 |
| ------------------- | --------------------------- |
| Object ka reference | Constructor ka property     |
| Runtime access      | Method define karne ke liye |
| Rarely use karo     | Recommended                 |


obj.__proto__ === Constructor.prototype // true

#50. JavaScript me inheritance kaise hota hai?
JS me inheritance prototype ke through hota hai.

function Animal(name) {
  this.name = name;
}

Animal.prototype.eat = function () {
  console.log("Eating");
};

function Dog(name) {
  Animal.call(this, name);
}

Dog.prototype = Object.create(Animal.prototype);

const d = new Dog("Bruno");
d.eat();



#🟢 SECTION 9: ES6 Basics (Interview Oriented)

#51. ES6 kya hai? (Definition)
ES6 (ECMAScript 2015) JavaScript ka major upgrade hai
jo clean syntax, performance & maintainability improve karta hai.

#52. let & const kyu introduce hue?

Problems with var:
1. Function scope
2. Redeclaration allowed
3. Bugs

Solution:

1. let → block scope
2. const → immutable reference

#53. Arrow Function (Definition + Benefit)
Arrow function ek shorter syntax function hai jo
lexical this follow karta hai.

const add = (a, b) => a + b;

Benefits:
-> Short syntax
-> No own this
-> Useful in callbacks

#54. Template Literals
const name = "JS";
console.log(`Hello ${name}`);

👉 String + variable easy ho jata hai

#55. Default Parameters
function greet(name = "User") {
  console.log(name);
}

#56. Rest vs Spread (Definition)
##1. Rest (...)

function sum(...nums) {
  return nums.reduce((a,b) => a+b);
}

👉 Multiple values ko array me convert karta hai

##2. Spread (...)
const arr1 = [1,2];
const arr2 = [...arr1, 3];

👉 Array / object copy & merge ke liye

#57. Destructuring (Array & Object)

const user = { name: "Java", role: "FS" };
const { name, role } = user;

const arr = [10, 20];
const [a, b] = arr;

#58. Enhanced Object Literals

Enhanced Object Literals ka matlab hai JavaScript me object ko short aur cleaner syntax se create karna, jisme variable ko directly property bana sakte hain aur methods ko bhi simple tarike se likh sakte hain.

const name = "JS";

const obj = {
  name,
  greet() {
    console.log("Hello");
  }
};



#🟢 SECTION 10: Common Interview Traps

#59. Object freeze vs seal
JavaScript me Object.freeze() aur Object.seal() dono methods object ko restrict (lock) karne ke liye use hote hain

##1. Object.freeze()
Object.freeze() kisi object ko completely immutable (change na hone wala) bana deta hai. Matlab us object me na koi property add kar sakte ho, na update, na delete.

const obj = {
  name: "Rahul",
  age: 20
};

Object.freeze(obj);

obj.age = 25;     // update nahi hoga
obj.city = "Delhi"; // add nahi hoga
delete obj.name;  // delete nahi hoga

console.log(obj);

✅ Result: Object bilkul change nahi hoga.

##2. Object.seal()
Object.seal() object ko partially lock karta hai. Isme nayi property add nahi kar sakte aur delete nahi kar sakte, lekin existing property ko update kar sakte ho.

const obj = {
  name: "Rahul",
  age: 20
};

Object.seal(obj);

obj.age = 25;   // update ho jayega
obj.city = "Delhi"; // add nahi hoga
delete obj.name; // delete nahi hoga

console.log(obj);

✅ Result: value change ho sakti hai, par structure same rahega.

#60. Shallow vs Deep Copy

##1. Shallow Copy
Shallow copy me object ka sirf top level copy hota hai, lekin agar uske andar koi nested object ya array hai to uska reference same rehta hai.

const obj = {
  name: "Rahul",
  address: {
    city: "Delhi"
  }
};

const copy = { ...obj };

copy.address.city = "Mumbai";

console.log(obj.address.city); // Mumbai

🔎 Kya hua?
Kyuki address nested object hai, uska reference same tha. Isliye original object bhi change ho gaya.

##2. Deep Copy
Deep copy me object ke sab levels (nested objects bhi) completely new copy ban jate hain. Isliye original object affect nahi hota.

Example: 

const obj = {
  name: "Rahul",
  address: {
    city: "Delhi"
  }
};

const deep = JSON.parse(JSON.stringify(obj));

deep.address.city = "Mumbai";

console.log(obj.address.city); // Delhi

🔎 Kya hua?
Deep copy me new object aur new nested objects bane, isliye original object change nahi hua.



################################################# PART 4(Asynchronous JavaScript – MOST IMPORTANT FOR FULL STACK) #############################

#🟢 SECTION 11: Synchronous vs Asynchronous JavaScript

#61. Synchronous JavaScript (Definition)
Synchronous code line-by-line execute hota hai.
Jab tak ek line complete na ho, next line execute nahi hoti.

console.log("A");
console.log("B");
console.log("C");

➡ Output:

A B C

#62. Asynchronous JavaScript (Definition)
Asynchronous JS me long-running tasks background me chali jaati hain,
aur JS next code execute kar leta hai.

console.log("Start");

setTimeout(() => {
  console.log("Async");
}, 1000);

console.log("End");


➡ Output:

Start
End
Async


#🟢 SECTION 12: Event Loop (🔥 VERY IMPORTANT 🔥)

#63. Event Loop kya hota hai? (Definition)
Event Loop ek mechanism hai jo Call Stack aur Queues ke beech coordination karta hai
taaki asynchronous code sahi order me execute ho.

#64. Event Loop ke main parts
1. Call Stack
2. Web APIs
3. Callback Queue (Macrotask)
4. Microtask Queue
5. Event Loop

#65. Microtask vs Macrotask

| Microtask         | Macrotask      |
| ----------------- | -------------- |
| Promise callbacks | setTimeout     |
| queueMicrotask    | setInterval    |
| Higher priority   | Lower priority |


#66. Event Loop example (Interview favorite)
console.log("A");

setTimeout(() => console.log("B"), 0);

Promise.resolve().then(() => console.log("C"));

console.log("D");

➡ Output:

A
D
C
B

Reason:
Microtask (Promise) → pehle execute
Macrotask (setTimeout) → baad me




#🟢 SECTION 13: Promises (🔥 MUST KNOW 🔥)

#67. romise kya hoti hai? (Definition)
Promise ek object hai jo future me
success (resolve) ya failure (reject) ka result deta hai.

#68. Promise states
1. Pending
2. Fulfilled
3. Rejected

#69. Promise basic example

const promise = new Promise((resolve, reject) => {
  let success = true;

  if(success){
    resolve("Data received");
  } else {
    reject("Error");
  }
});


#70. then, catch, finally
promise
  .then(res => console.log(res))
  .catch(err => console.log(err))
  .finally(() => console.log("Done"));
  

#71. Promise chaining
fetchData()
  .then(data => process(data))
  .then(result => save(result))
  .catch(err => console.log(err));

👉 Callback hell ka solution


#72. JavaScript ke Promise Concurrency Methods

##1. Promise.all()
Promise.all() multiple promises ko parallel run karta hai aur tabhi success return karta hai jab sab promises resolve ho jayein.
Agar ek bhi promise reject ho gaya, to poora Promise.all reject ho jata hai.

Example: 

function api1() {
  return Promise.resolve("User Data");
}

function api2() {
  return Promise.resolve("Posts Data");
}

Promise.all([api1(), api2()])
  .then(result => {
    console.log(result);
  })
  .catch(error => console.log(error));

✅ Output

["User Data", "Posts Data"]

📌 Rule:
👉 Ek bhi fail → sab fail


##2. Promise.race()
Promise.race() me jo promise sabse pehle settle (resolve ya reject) hota hai, uska result return ho jata hai.

Example: 

const p1 = new Promise(res => setTimeout(() => res("API 1"), 2000));
const p2 = new Promise(res => setTimeout(() => res("API 2"), 1000));

Promise.race([p1, p2])
  .then(result => console.log(result));

✅ Output

API 2

📌 Rule:
👉 Jo pehle complete kare wahi result


##3. Promise.allSettled()
Promise.allSettled() sab promises ke complete hone ka wait karta hai, chahe success ho ya failure.
Ye har promise ka status aur result return karta hai.

Example: 

const p1 = Promise.resolve("Success");
const p2 = Promise.reject("Error");

Promise.allSettled([p1, p2])
  .then(result => console.log(result));

✅ Output

[
 { status: "fulfilled", value: "Success" },
 { status: "rejected", reason: "Error" }
]

📌 Rule:
👉 Success + Failure dono ka result deta hai

##4. Promise.any()
Promise.any() jo promise sabse pehle successfully resolve hota hai, uska result return karta hai.
Agar sab promises fail ho jayein, tab error aata hai.

Example:

const p1 = Promise.reject("Fail 1");
const p2 = new Promise(res => setTimeout(() => res("Success API"), 1000));

Promise.any([p1, p2])
  .then(result => console.log(result))
  .catch(err => console.log(err));

✅ Output

Success API

📌 Rule:
👉 Jo pehla success ho wahi return

##Important

| Method             | Kab resolve hoga                  |
| ------------------ | --------------------------------- |
| Promise.all        | Jab **sab success**               |
| Promise.race       | Jo **pehle complete**             |
| Promise.allSettled | **Sabka result** (success + fail) |
| Promise.any        | **Pehla success**                 |




#🟢 SECTION 14: async / await (🔥 REAL WORLD 🔥)

#73. async/await kya hai? (Definition)
async/await Promises ko synchronous-looking code me likhne ka tarika hai.

#74. async function hamesha kya return karta hai?
👉 Promise

async function test() {
  return 10;
}

test().then(console.log); // 10

#75. async/await example (Backend API style)

async function getUser() {
  try {
    const res = await fetch("/api/user");
    const data = await res.json();
    console.log(data);
  } catch (err) {
    console.log(err);
  }
}

#76. Error handling in async/await
try {
  await apiCall();
} catch (e) {
  console.log(e);
}

👉 try-catch mandatory in interview answer

#77. Promise vs async/await

| Promise    | async/await |
| ---------- | ----------- |
| then/catch | try/catch   |
| chaining   | readable    |
| complex    | clean       |



#🟢 SECTION 15: Java + JS Connection (🔥 Interview Bonus)

#78. Frontend → Backend async flow
await fetch("/api/users");

👉 Backend:
1. Java Controller
2. Service
3. DB

👉 JS ko response Promise ke form me milta hai

#79. Interview Trick Question
Q: setTimeout(fn, 0) turant kyu nahi chalta?

A:
Kyuki:
1-> Call Stack pehle empty hota hai
2-> Microtasks pehle run hoti hain
3-> Fir Macrotask execute hoti hai



################################  PART 5 (Arrays + Performance + Advanced Concepts) ###################

#80. map() – Definition + Example
map() array ke har element par operation karke naya array return karta hai.

const nums = [1,2,3];
const result = nums.map(n => n * 2);

➡ [2,4,6]

#81. filter()
const nums = [1,2,3,4];
const even = nums.filter(n => n % 2 === 0);

➡ [2,4]

#82. reduce() (Interview favourite)
const nums = [1,2,3,4];
const sum = nums.reduce((acc, curr) => acc + curr, 0);

➡ 10

#83. map vs forEach

| map               | forEach      |
| ----------------- | ------------ |
| returns array     | no return    |
| chaining possible | not possible |


#84. Remove duplicate elements
const arr = [1,2,2,3];
const unique = [...new Set(arr)];



#🟢 SECTION 17: Debouncing & Throttling

#85. Debouncing (Definition)
Continuous events ke baad sirf last event execute hota hai.

👉 Use case: Search box

function debounce(fn, delay) {
  let timer;
  return function () {
    clearTimeout(timer);
    timer = setTimeout(fn, delay);
  };
}

#86. Throttling (Definition)
Fixed interval me function execute hota hai
chahe kitni baar event trigger ho.

👉 Use case: Scroll

#87. Debounce vs Throttle

| Debounce  | Throttle       |
| --------- | -------------- |
| last call | fixed interval |
| search    | scroll         |



#🟢 SECTION 18: Currying & Memoization

#88. Currying
Currying ek technique hai jisme multiple arguments wale function ko aise convert kar diya jata hai ki wo ek-ek argument lekar multiple functions return kare.

Simple words me:
👉 Ek function → multiple nested functions

Example
function add(a) {
  return function(b) {
    return a + b;
  };
}

add(5)(3); // 8
Samjho Step by Step
add(5)  → function return karega
phir (3) pass hoga
result = 5 + 3

#89. Memoization
Performance Optimization Technique

Memoization ek technique hai jisme function ke result ko cache (store) kar liya jata hai, taaki same input dubara aaye to function fir se calculate na kare aur stored result hi return kar de.

👉 Isse performance improve hoti hai.

Example:

function memoize(fn) {
  const cache = {};

  return function(x) {
    if (cache[x]) return cache[x];

    cache[x] = fn(x);
    return cache[x];
  };
}


Use Example:

function square(n){
  console.log("calculating...");
  return n*n;
}

const fastSquare = memoize(square);

fastSquare(5); // calculating... 25
fastSquare(5); // 25 (cache se aaya)

Kya hua?

1️. First time → function run hua
2️. Second time → cache se result mil gaya

#90. Currying vs Memoization

| Feature | Currying                                  | Memoization                            |
| ------- | ----------------------------------------- | -------------------------------------- |
| Purpose | Function ko multiple functions me convert | Result store karke performance improve |
| Use     | Functional programming                    | Optimization                           |
| Example | `add(5)(3)`                               | Cached function result                 |




##################################################### PART 6 (Browser, DOM, Storage, Tricky Questions & Final Revision) ####################################

#🟢 SECTION 19: Browser & DOM (Frontend + Full Stack)

#91. DOM kya hota hai? (Definition)
DOM (Document Object Model) ek tree-like structure hota hai
jo HTML ko JavaScript ke through manipulate karne deta hai.

👉 Browser HTML ko DOM me convert karta hai

#92. DOM access & manipulation example
const title = document.getElementById("title");
title.innerText = "Hello JavaScript";

#93. DOM vs BOM (Interview)

| DOM            | BOM             |
| -------------- | --------------- |
| HTML structure | Browser object  |
| document       | window          |
| page related   | browser related |

#94. Event Bubbling vs Capturing (🔥 very common)
#1. Event Bubbling:
Event child → parent jata hai (default)

#2. Event Capturing:
Event parent → child jata hai

Example:

div.addEventListener("click", () => {
  console.log("DIV");
}, false); // bubbling


#95. stopPropagation() vs preventDefault()

| Method          | Use                    |
| --------------- | ---------------------- |
| stopPropagation | event flow stop        |
| preventDefault  | default behaviour stop |


event.preventDefault(); // form submit roke
event.stopPropagation(); // bubbling roke



#🟢 SECTION 20: Browser Storage (🔥 Interview Favourite)


#96. LocalStorage, SessionStorage & Cookies

| Feature | LocalStorage | SessionStorage | Cookies     |
| ------- | ------------ | -------------- | ----------- |
| Size    | ~5MB         | ~5MB           | ~4KB        |
| Expiry  | Permanent    | Tab close      | Expiry time |
| Server  | ❌            | ❌              | ✅           |
| Use     | token        | temp data      | auth        |


#97. Storage example
localStorage.setItem("user", "Java");
localStorage.getItem("user");


#🟢 SECTION 21: CORS & Security

#98. CORS kya hota hai? (Definition)
CORS (Cross-Origin Resource Sharing) ek browser security rule hai
jo different origin se API call ko control karta hai.

#99. Same Origin Policy
Same:
-> Protocol
-> Domain
-> Port

Tabhi request allowed hoti hai

#100. Java Backend CORS fix example
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
}

👉 Java + JS connection ✔


#🟢 SECTION 22: Tricky JavaScript Interview Questions

#101. null vs undefined

| null                | undefined    |
| ------------------- | ------------ |
| intentionally empty | not assigned |
| object              | undefined    |


#102. NaN === NaN false kyun?
👉 IEEE standard ke according
NaN kisi ke barabar nahi hota (khud ke bhi nahi)

isNaN(NaN); // true

#103. Object.freeze() vs Object.seal()
Object.freeze(obj); // no change
Object.seal(obj);  // update allowed

#104. JSON.parse(JSON.stringify()) limitation

❌ Functions lost
❌ Date object lost
❌ Circular reference error



#🟢 SECTION 23: Performance & Optimization (3.5+ Exp)

#105. JS performance improve kaise karoge?

✔ Debouncing & throttling
✔ Lazy loading
✔ Code splitting
✔ Avoid memory leaks
✔ Minimize DOM access

#106. Memory leak kaise avoid karoge?

✔ Remove unused event listeners
✔ Clear timers
✔ Avoid global variables
✔ Proper closure use



################################# PART 7  (FINAL PART – Java vs JavaScript) ###############################

#🟢 STEP 1: Basic Introduction

##1. Java kya hai?

1. Object-Oriented
2. Strongly typed
3. Compiled language
4. Mostly Backend / Enterprise applications

##2. JavaScript kya hai?

1. Prototype-based
2. Dynamically typed
3. Interpreted / JIT compiled
4. Frontend + Backend (Node.js)

👉 Interview Line:

Java = Backend focused,
JavaScript = Frontend + Backend (Full Stack)

#🟢 STEP 2: Typing System (🔥 Interview Favorite)
##1. Java – Strongly Typed
int a = 10;
// a = "hello"; ❌ compile-time error

##2. JavaScript – Dynamically Typed
let a = 10;
a = "hello"; // ✅ allowed

| Point         | Java         | JavaScript |
| ------------- | ------------ | ---------- |
| Type checking | Compile time | Runtime    |
| Safety        | High         | Less       |


👉 Interview Tip:
Java = safe
JS = flexible

#🟢 STEP 3: Compilation vs Execution

##1. Java
.java → javac → .class → JVM → Run

##2. JavaScript
.js → Browser / Node.js → Run directly


| Feature     | Java     | JavaScript     |
| ----------- | -------- | -------------- |
| Compilation | Required | Not required   |
| Platform    | JVM      | Browser / Node |


#🟢 STEP 4: OOP Concept Support

##1. Java (Pure OOP – Almost)
-> Class
-> Object
-> Inheritance
-> Polymorphism
-> Encapsulation
-> Abstraction

class User {
  String name;
}

##2. JavaScript (Prototype-based)
-> No real classes (ES6 class = syntactic sugar)
-> Uses prototype

class User {
  constructor(name) {
    this.name = name;
  }
}


| Feature     | Java        | JavaScript      |
| ----------- | ----------- | --------------- |
| OOP         | Class-based | Prototype-based |
| Inheritance | extends     | prototype       |


#🟢 STEP 5: Memory Management
##1. Java
-> Manual reference handling
-> Garbage Collector (JVM)

##2. JavaScript
-> Automatic memory handling
-> Garbage Collector (Browser/Node)

👉 Similarity:
✔ Dono me GC hota hai

👉 Difference:
Java me memory zyada controlled
JS me accidental memory leaks common

#Concurrency & Multithreading (🔥 Important)
##1. Java
-> Multi-threaded
-> Parallel execution possible

Thread t = new Thread(() -> {
  System.out.println("Thread");
});
t.start();

##2. JavaScript
-> Single-threaded
-> Async via Event Loop

setTimeout(() => {
  console.log("Async");
}, 1000);


| Feature | Java     | JavaScript |
| ------- | -------- | ---------- |
| Threads | Multiple | Single     |
| Async   | Threads  | Event Loop |


#🟢 STEP 7: Asynchronous Programming
##1. Java
-> Future
-> CompletableFuture
-> ExecutorService

CompletableFuture.supplyAsync(() -> "Data");

##2. JavaScript
-> Callback
-> Promise
-> async/await

await fetch("/api/data");

👉 Similarity:
✔ Dono async support karte hain

👉 Difference:
Java = Thread based
JS = Event Loop based


#🟢 STEP 8: Exception / Error Handling
##1. Java
try {
  int a = 10 / 0;
} catch(Exception e) {
  e.printStackTrace();
}

##2. JavaScript
try {
  let a = 10 / 0;
} catch(e) {
  console.log(e);
}

👉 Similarity:
✔ try–catch dono me

👉 Difference:
Java = checked + unchecked
JS = runtime errors only

#🟢 STEP 9: Scope & Variables
##1. Java
-> Block scope only

{
  int x = 10;
}

##2. JavaScript
-> Global
-> Function
-> Block (let, const)

let x = 10;

👉 JS scope complex hota hai (closures)


#🟢 STEP 10: Functions vs Methods
##1. Java
-> Function = Method (class ke andar)

class Test {
  void show() {}
}

##2. JavaScript
-> Function independent bhi ho sakta hai

function show() {}


#🟢 STEP 11: Data Structures

| Java      | JavaScript   |
| --------- | ------------ |
| ArrayList | Array        |
| HashMap   | Object / Map |
| HashSet   | Set          |


Map<String,Integer> map = new HashMap<>();

const map = new Map();

#🟢 STEP 12: Framework Ecosystem
##1. Java
-> Spring Boot
-> Hibernate
-> JPA
-> Microservices

##2. JavaScript
-> React
-> Angular
-> Vue
-> Node.js

👉 Full Stack = Java + JavaScript ❤️

#🟢 STEP 13: Performance

| Java             | JavaScript        |
| ---------------- | ----------------- |
| Faster           | Slightly slower   |
| JVM optimized    | Browser dependent |
| Enterprise ready | UI heavy          |


#🟢 STEP 14: Similarities (🔥 Interview Answer)

✔ Both are Object-Oriented
✔ Both support async programming
✔ Both have garbage collection
✔ Both used in Full Stack
✔ Both support exception handling
✔ Both have rich ecosystems


#🟢 STEP 15: Differences Summary Table (Quick Revision)

| Point    | Java         | JavaScript         |
| -------- | ------------ | ------------------ |
| Typing   | Strong       | Dynamic            |
| Thread   | Multi        | Single             |
| Async    | Thread based | Event loop         |
| OOP      | Class        | Prototype          |
| Platform | JVM          | Browser / Node     |
| Use      | Backend      | Frontend + Backend |

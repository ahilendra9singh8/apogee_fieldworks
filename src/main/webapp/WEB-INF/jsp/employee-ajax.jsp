<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Employee Ajax Panel</title>

<style>
body {
    font-family: Arial;
    background: #f2f4f7;
}
.container {
    width: 1000px;
    margin: auto;
    background: #fff;
    padding: 20px;
    margin-top: 40px;
    border-radius: 8px;
}
input, button {
    padding: 8px;
    margin: 5px;
}
button {
    background: #007bff;
    color: white;
    border: none;
    cursor: pointer;
}
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}
th, td {
    border: 1px solid #ccc;
    padding: 8px;
}
.hidden {
    display: none;
}
.error {
    color: red;
}
.success {
    color: green;
}
</style>

</head>
<body>

<div class="container">

<h2>Employee Ajax Panel</h2>

<!-- LOGIN -->
<div id="loginBox">
    <h3>Login</h3>
    <input id="username" placeholder="Username">
    <input id="password" type="password" placeholder="Password">
    <button onclick="login()">Login</button>
    <div id="loginMsg" class="error"></div>
</div>

<!-- CRUD -->
<div id="crudBox" class="hidden">

    <button onclick="logout()">Logout</button>

    <h3>Create / Update Employee</h3>
    <input type="hidden" id="empId">
    <input id="name" placeholder="Name">
    <input id="role" placeholder="Role">
    <input id="privilege" placeholder="Privilege">
    <button onclick="saveEmployee()">Save</button>

    <h3>Upload Document</h3>
    <input type="file" id="file">
    <button onclick="uploadFile()">Upload</button>

    <h3>Employee List</h3>
    <table id="empTable">
        <thead>
            <tr>
                <th>ID</th><th>Name</th><th>Role</th><th>Action</th>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>

</div>

<script>
let token = null;
let selectedEmployeeId = null;

/* LOGIN */
function login() {
    fetch("/employee/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            user_name: username.value,
            password: password.value
        })
    })
    .then(res => res.json())
    .then(data => {
        if (data.responseCode === 0) {
            token = data.payload.token;
            loginBox.classList.add("hidden");
            crudBox.classList.remove("hidden");
            loadEmployees();
        } else {
            loginMsg.innerText = data.message;
        }
    });
}

/* LOGOUT */
function logout() {
    token = null;
    crudBox.classList.add("hidden");
    loginBox.classList.remove("hidden");
}

/* LOAD LIST */
function loadEmployees() {
    fetch("/api/employees?page=0&size=10", {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(res => res.json())
    .then(data => {
        let rows = "";
        data.payload.content.forEach(e => {
            rows += `
            <tr>
                <td>${e.id}</td>
                <td>${e.name}</td>
                <td>${e.role}</td>
                <td>
                    <button onclick="editEmployee(${e.id}, '${e.name}', '${e.role}', '${e.privilege}')">Edit</button>
                    <button onclick="deleteEmployee(${e.id})">Delete</button>
                </td>
            </tr>`;
        });
        document.querySelector("#empTable tbody").innerHTML = rows;
    });
}

/* SAVE */
function saveEmployee() {
    const id = empId.value;
    const method = id ? "PUT" : "POST";
    const url = id ? "/api/employees/" + id : "/api/employees";

    fetch(url, {
        method: method,
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name.value,
            role: role.value,
            privilege: privilege.value,
            active: true
        })
    })
    .then(() => {
        empId.value = "";
        loadEmployees();
    });
}

/* EDIT */
function editEmployee(id, n, r, p) {
    empId.value = id;
    name.value = n;
    role.value = r;
    privilege.value = p;
    selectedEmployeeId = id;
}

/* DELETE */
function deleteEmployee(id) {
    fetch("/api/employees/" + id, {
        method: "DELETE",
        headers: { "Authorization": "Bearer " + token }
    })
    .then(() => loadEmployees());
}

/* FILE UPLOAD */
function uploadFile() {
    if (!selectedEmployeeId) {
        alert("Select employee first");
        return;
    }

    const formData = new FormData();
    formData.append("file", file.files[0]);

    fetch("/api/employees/" + selectedEmployeeId + "/upload", {
        method: "POST",
        headers: { "Authorization": "Bearer " + token },
        body: formData
    })
    .then(res => res.json())
    .then(() => alert("Uploaded"));
}
</script>

</body>
</html>

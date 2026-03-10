<%-- <%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>3D GNSS Leveller Screen Sharing</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />

<!-- favicon fix -->
<link rel="icon" href="data:,">

<style>
/* Page background */
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  font-family: Arial, sans-serif;
  background: #000; /* full dark background */
  color: #000;
  overflow: hidden;
}

/* INTRO VIDEO */
#introContainer {
  position: fixed;
  top: 0; left: 0;
  width: 100%; height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
  background: #000;
}

#introVideo {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* MAIN WHITE PANEL */
#centerPanel {
  position: absolute;
  top: 50%; left: 50%;
  transform: translate(-50%, -50%);
  width: 880px;
  max-width: 95%;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 8px 30px rgba(0,0,0,0.5);
  padding: 22px 26px 16px 26px;
  z-index: 9000;
  display: none;
}

/* ✅ NEW — APOGEE TOP BLACK BAR */
.header-logo-wrapper {
    width: 100%;
    background: #000 !important;
    padding: 18px 0 22px 0;
    display: flex !important;
    justify-content: center !important;
    align-items: center !important;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
    margin-top: -22px;   /* pull to top edge */
    margin-left: -26px;
    width: calc(100% + 52px);
}

.header-logo {
    height: 38px;
    object-fit: contain;
    display: block;
    margin: auto;
}

/* Top bar inside panel (3D GNSS Leveller Screen Sharing title only now) */
.panelTop {
  margin-top: 10px;
  display: flex;
  align-items: center;
  gap: 12px;
  height: 48px;
  justify-content: center;
}

.panelTitle {
  font-weight: 800;
  font-size: 22px;
  color: #111;
  letter-spacing: 0.6px;
}

/* Divider */
.panelDivider {
  width: 100%;
  height: 1px;
  background: #efefef;
  margin: 12px 0 18px 0;
}

/* UID inputs */
.uidRow {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 18px;
  margin-top: 6px;
}

.uidBox {
  width: 200px;
  max-width: 24%;
  min-width: 120px;
  height: 76px;
  border-radius: 10px;
  border: 1px solid #d6d6d6;
  background: #fafafa;
  text-align: center;
  font-size: 34px;
  font-weight: 700;
  padding: 8px;
  box-sizing: border-box;
  color: #111;
  outline: none;
}

.uidDash {
  font-size: 34px;
  font-weight: 700;
  color: #333;
}

/* CONNECT BUTTON */
.connectRow {
  margin-top: 28px;
  display: flex;
  justify-content: center;
}

#connectBtn {
  width: 78%;
  max-width: 700px;
  height: 64px;
  background: #000;
  color: #fff;
  font-size: 26px;
  font-weight: 800;
  border-radius: 12px;
  border: none;
  cursor: pointer;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  padding-right: 72px;
}

#connectBtn img {
  position: absolute;
  right: 18px;
  height: 34px;
}

/* State text */
#state {
  margin-top: 12px;
  text-align: center;
  font-weight: 700;
  color: #cc0000;
}

/* Recent IDs */
#recentWrap {
  margin-top: 18px;
  padding: 8px 12px;
  display: flex;
  justify-content: center;
}

#prevList {
  width: 100%;
  display: flex;
  gap: 12px;
  overflow-x: auto;
  padding-bottom: 6px;
}

.itemBox {
  flex: 0 0 auto;
  min-width: 140px;
  height: 56px;
  background: #f7f7f7;
  border-radius: 10px;
  border: 2px solid #222;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 700;
  color: #111;
  position: relative;
  cursor: pointer;
  padding: 6px 14px;
}

.crossBtn {
  position: absolute;
  right: 8px;
  top: 4px;
  color: #d93025;
  font-weight: 700;
  font-size: 16px;
  cursor: pointer;
}

/* LIVE VIEW */
#viewerWrap {
  position: fixed;
  top: 0; left: 0;
  width: 100%; height: 100%;
  display: none;
  background: #000;
  justify-content: center;
  align-items: center;
  z-index: 10000;
}

#liveContainer {
  width: 960px;
  height: 600px;
  background: #000;
  overflow: hidden;
  border: 2px solid #00cc88;
  border-radius: 6px;
}

#liveImg {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: crosshair;
}

#disconnectBtn {
  position: absolute;
  top: 20px;
  right: 20px;
  padding: 10px 14px;
  background: #ff4444;
  color: #fff;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 700;
}

</style>
</head>
<body>

<!-- INTRO VIDEO -->
<div id="introContainer">
  <video id="introVideo" autoplay muted playsinline>
    <source src="/intro.mp4" type="video/mp4">
  </video>
</div>

<!-- ===================== CENTER PANEL ===================== -->
<div id="centerPanel">

  <!-- ✅ NEW TOP APOGEE BLACK STRIP -->
  <div class="header-logo-wrapper">
      <img src="/apogeeLeveller.png" class="header-logo">
  </div>

  <!-- 3D GNSS Leveller Screen Sharing Title -->
  <div class="panelTop">
    <div class="panelTitle">3D GNSS Leveller Screen Sharing</div>
  </div>

  <div class="panelDivider"></div>

  <!-- UID Inputs -->
  <div class="uidRow">
    <input id="uid1" class="uidBox" maxlength="4" inputmode="numeric" />
    <div class="uidDash">-</div>
    <input id="uid2" class="uidBox" maxlength="4" inputmode="numeric" />
    <div class="uidDash">-</div>
    <input id="uid3" class="uidBox" maxlength="4" inputmode="numeric" />
  </div>

  <input id="uid" type="hidden">

  <!-- CONNECT -->
  <div class="connectRow">
    <button id="connectBtn" onclick="manualConnectSplit()">
      Connect
      <img src="/fast-forward.png">
    </button>
  </div>

  <div id="state"></div>

  <!-- RECENT IDs -->
  <div id="recentWrap">
    <div id="prevList"></div>
  </div>
</div>

<!-- ===================== LIVE VIEWER ===================== -->
<div id="viewerWrap">
  <button id="disconnectBtn" onclick="requestDisconnect()">Disconnect</button>
  <div id="liveContainer">
    <img id="liveImg">
  </div>
</div>

<script>
/* -------------------- Variables -------------------- */
let intro = document.getElementById("introVideo");
let introContainer = document.getElementById("introContainer");
let centerPanel = document.getElementById("centerPanel");
let prevList = document.getElementById("prevList");
let viewerWrap = document.getElementById("viewerWrap");
let liveImg = document.getElementById("liveImg");

let currentUserId = null;
let approvalTimer = null;
let frameTimer = null;
let disconnectPending = false;
let isStreamingRunning = false;
let isClicking = false;  // Flag to prevent multiple clicks within 1 second

/* -------------------- Intro Video (unchanged) -------------------- */
intro.onended = intro.onerror = () => {
    console.log("🎬 Intro finished or error, showing connect UI");
    introContainer.style.display = "none";
    centerPanel.style.display = "block";
    loadRecent();
};

/* -------------------- Recent IDs (render/list) -------------------- */
function loadRecent() {
    let list = JSON.parse(sessionStorage.getItem("IDS") || "[]");
    renderList(list);
}

function renderList(list) {
    prevList.innerHTML = "";

    list.forEach(id => {
        let box = document.createElement("div"); 
        box.className = "itemBox";

        // Text add
        let text = document.createElement("span");
        text.innerText = id;

        // Cross button
        let cross = document.createElement("span");
        cross.className = "crossBtn";
        cross.innerText = "×";

        // CLICK HANDLERS
        box.onclick = () => {
            // fill splitted inputs too (so UI matches)
            fillSplitInputs(id);
            // Put ID in hidden field (keeps existing logic working)
            document.getElementById("uid").value = id;
            console.log("📌 ID selected =", id);
            connectFlow(id); // START CONNECT FLOW
        };

        cross.onclick = (event) => {
            event.stopPropagation();
            deleteID(id);
        };

        box.appendChild(text);
        box.appendChild(cross);
        prevList.appendChild(box);
    });
}

function deleteID(id){
    console.log("🗑 Deleting recent ID", id);
    let list = JSON.parse(sessionStorage.getItem("IDS") || "[]");
    list = list.filter(x=>x!==id);
    sessionStorage.setItem("IDS", JSON.stringify(list));
    renderList(list);
}

function addRecent(id){
    let list = JSON.parse(sessionStorage.getItem("IDS") || "[]");
    if(!list.includes(id)) list.unshift(id);
    sessionStorage.setItem("IDS", JSON.stringify(list));
    renderList(list);
}

/* -------------------- Split input helpers -------------------- */
function fillSplitInputs(uid) {
    if(!uid || uid.length < 12) return;
    document.getElementById("uid1").value = uid.substring(0,4);
    document.getElementById("uid2").value = uid.substring(4,8);
    document.getElementById("uid3").value = uid.substring(8,12);
}

/* auto advance when 4 digits typed */
["uid1","uid2","uid3"].forEach((id, idx) => {
    const el = document.getElementById(id);
    el.addEventListener("input", (e) => {
        // allow only digits
        el.value = el.value.replace(/\D/g,'').slice(0,4);
        if (el.value.length === 4 && idx < 2) {
            document.getElementById(["uid1","uid2","uid3"][idx+1]).focus();
        }
    });
});

/* -------------------- Connect (merge split inputs, keep logic intact) -------------------- */
async function manualConnectSplit(){
    const p1 = document.getElementById("uid1").value.trim();
    const p2 = document.getElementById("uid2").value.trim();
    const p3 = document.getElementById("uid3").value.trim();

    const uid = (p1 + p2 + p3).trim();

    if(uid.length !== 12){
        alert("Enter valid 12-digit ID (3 boxes of 4 digits)");
        return;
    }

    // set hidden UID field so existing code that references #uid keeps working
    document.getElementById("uid").value = uid;

    addRecent(uid);
    connectFlow(uid);
}

/* -------------------- Connect Flow & polling & streaming (unchanged logic) -------------------- */
async function connectFlow(uid){
    currentUserId = uid;
    document.getElementById("state").innerText = "Requesting...";
    disconnectPending = false;

    console.log("📡 Sending connect request for UID", uid);
    let r = await fetch("/request-connect", {
        method:"POST",
        headers:{"Content-Type":"application/x-www-form-urlencoded"},
        body:"userId="+uid
    });
    let resp = await r.text();

    console.log("📥 Connect response =", resp);

    if(resp === "NO_UNITY"){
        document.getElementById("state").innerText = "Seems Like Client is Offline";
        return;
    }

    if(resp==="SENT") startApprovalPolling();
}

function startApprovalPolling(){
    approvalTimer = setInterval(async ()=>{
        let r = await fetch("/check-status");
        let t = await r.text();
        console.log("⏱ Polling approval status =", t);

        if(t.includes(",1*")){ // APPROVED
            console.log("✅ Connect approved by Unity");
            clearInterval(approvalTimer);
            openViewer();
            startStreaming();
        }

        if(t.includes(",0*")){ // REJECTED or disconnect
            console.log("❌ Connect rejected or Unity disconnected");
            clearInterval(approvalTimer);
            stopStreaming();
            document.getElementById("state").innerText = "Client Rejected Your Request to Connect";
        }
        
        if(t==="NO_USER"){
            console.log("⚠ NO_USER received → stopping polling");
            clearInterval(approvalTimer);
            approvalTimer = null;
            stopStreaming();
            return;  // 🔥 Forced stop
        }

  
    }, 800);
}

/* -------------------- Viewer -------------------- */
function openViewer(){
    console.log("👁 Opening viewer for live stream");
    // hide center panel and prev list to show viewer full-screen
    centerPanel.style.display = "none";
    prevList.style.display = "none";
    viewerWrap.style.display = "flex";
}

/* -------------------- Streaming -------------------- */
function startStreaming(){
    isStreamingRunning = true;

    frameTimer = setInterval(async ()=>{
        if(!isStreamingRunning) return; // 🔥 stop ngay

        let r = await fetch("/live-frame");
        let frame = await r.text();

        if(frame==="DISCONNECTED"){
            console.log("⚠ Unity disconnected while streaming");
            stopStreaming();
            return;
        }
        if(frame.length>20){
            liveImg.src = "data:image/jpeg;base64,"+frame;
        }
    }, 80);
}


/* ------------------ CLICK POSITION SEND TO UNITY ------------------ */
liveImg.onclick = function (e) {
	// If already clicking, do nothing
    if (isClicking) return;
	
    const rect = liveImg.getBoundingClientRect();

    // Calculate relative click position inside the frame
    let x = e.clientX - rect.left;
    let y = e.clientY - rect.top;

    // Round values
    x = Math.floor(x);
    y = Math.floor(y);

    console.log("📍 Click Position:", x, y);

    // Send to server → server → Unity
    sendClickToUnity(x, y);
    
 // Set the flag to true to block further clicks
    isClicking = true;

    // Set timeout for 1 second, then reset the flag
    setTimeout(() => {
        isClicking = false;
    }, 1000);  // 1000 ms = 1 second delay
};

async function sendClickToUnity(x, y) {
    let uid = currentUserId;

    if (!uid) return;

    await fetch("/click-pos", {
        method: "POST",
        headers: {"Content-Type":"application/x-www-form-urlencoded"},
        body: "userId=" + uid + "&x=" + x + "&y=" + y
    });

    console.log("📨 Sent to Unity:", uid, x, y);
}

/* -------------------- Disconnect -------------------- */
async function requestDisconnect() {
    console.log("🔴 Sending disconnect request for UID", currentUserId);
    disconnectPending = true;

    await fetch("/disconnect", {
        method:"POST",
        headers: {"Content-Type":"application/x-www-form-urlencoded"},
        body:"userId=" + currentUserId
    });

    // Poll /check-status until backend confirms disconnect
    let disconnected = false;
    while (!disconnected) {
        let r = await fetch("/check-status");
        let status = await r.text();
        console.log("🔍 Disconnect poll status =", status);

        if (status.includes(",0*") || status === "NO_USER") {
            disconnected = true;
            console.log("✅ Disconnect confirmed by backend/Unity");
        } else {
            await new Promise(res => setTimeout(res, 500)); // wait 0.5s
        }
    }

    stopStreaming();
}

/* -------------------- Stop streaming & reset UI -------------------- */

function stopStreaming(){
    console.log("🛑 STOP STREAMING CALLED");

    isStreamingRunning = false;

    if(frameTimer){
        clearInterval(frameTimer);
        frameTimer = null;
    }
    if(approvalTimer){
        clearInterval(approvalTimer);
        approvalTimer = null;
    }

    // Stop pending backend disconnect loops
    disconnectPending = false;

    viewerWrap.style.display="none";
    centerPanel.style.display="block";
    prevList.style.display="flex";

    renderList(JSON.parse(sessionStorage.getItem("IDS") || "[]"));
    document.getElementById("state").innerText="";

    currentUserId = null;
}


/* init recent list on load (if intro already ended or errored) */
if (intro.readyState >= 3) { // video likely already loaded/ended
    // don't auto-show; rely on onended/onerror; but still populate list
    loadRecent();
}
</script>

</body>
</html>--%>
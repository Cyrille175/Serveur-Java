const responseBox = document.getElementById("responseBox");
const statusBadge = document.getElementById("statusBadge");
const endpointLabel = document.getElementById("endpointLabel");

const healthBtn = document.getElementById("healthBtn");
const timeBtn = document.getElementById("timeBtn");
const helloBtn = document.getElementById("helloBtn");
const echoBtn = document.getElementById("echoBtn");
const messageInput = document.getElementById("messageInput");

function setLoading(endpoint) {
    endpointLabel.textContent = endpoint;
    statusBadge.textContent = "Loading...";
    statusBadge.className = "badge neutral";
    responseBox.textContent = "Waiting for server response...";
}

function setSuccess(endpoint, data) {
    endpointLabel.textContent = endpoint;
    statusBadge.textContent = "Success";
    statusBadge.className = "badge success";

    if (typeof data === "object") {
        responseBox.textContent = JSON.stringify(data, null, 2);
    } else {
        responseBox.textContent = data;
    }
}

function setError(endpoint, message) {
    endpointLabel.textContent = endpoint;
    statusBadge.textContent = "Error";
    statusBadge.className = "badge error";
    responseBox.textContent = message;
}

async function callEndpoint(endpoint) {
    setLoading(endpoint);

    try {
        const response = await fetch(endpoint);
        const contentType = response.headers.get("content-type") || "";

        let data;
        if (contentType.includes("application/json")) {
            data = await response.json();
        } else {
            data = await response.text();
        }

        if (!response.ok) {
            setError(endpoint, typeof data === "string" ? data : JSON.stringify(data, null, 2));
            return;
        }

        setSuccess(endpoint, data);
    } catch (error) {
        setError(endpoint, "Request failed: " + error.message);
    }
}

healthBtn.addEventListener("click", () => {
    callEndpoint("/health");
});

timeBtn.addEventListener("click", () => {
    callEndpoint("/time");
});

helloBtn.addEventListener("click", () => {
    callEndpoint("/hello");
});

echoBtn.addEventListener("click", () => {
    const message = messageInput.value.trim();
    const finalMessage = message.length > 0 ? message : "Hello from the interface";
    callEndpoint("/echo?message=" + encodeURIComponent(finalMessage));
});
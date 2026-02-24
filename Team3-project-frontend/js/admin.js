const token = localStorage.getItem("token");
const role = localStorage.getItem("role");

if (!token || role !== "ADMIN") {
    showToast("Unauthorized. Please login as Admin.", "error");
    window.location.href = "login.html";
}

const API = "http://localhost:8080/api";

let agents = [];

// ------------------------------------------------
// LOAD AGENTS
// ------------------------------------------------
function loadAgents() {
    fetch(`${API}/admin/agents`, {
        headers: { "Authorization": `Bearer ${token}` }
    })
    .then(res => res.json())
    .then(data => {
        agents = data;
        console.log("Agents Loaded: ", agents);
        loadTickets();
    })
    .catch(err => {
        console.error("Error loading agents:", err);
        showToast("Failed to load agents", "error");
    });
}

// ------------------------------------------------
// LOAD TICKETS
// ------------------------------------------------
function loadTickets() {
    fetch(`${API}/tickets/all`, {
        headers: { "Authorization": `Bearer ${token}` }
    })
    .then(res => res.json())
    .then(tickets => {
        console.log("Tickets:", tickets);
        renderTickets(tickets);
    })
    .catch(err => {
        console.error(err);
        showToast("Failed to load tickets", "error");
    });
}

// ------------------------------------------------
// RENDER TICKETS
// ------------------------------------------------
function renderTickets(tickets) {
    const tbody = document.getElementById("ticketTable");
    tbody.innerHTML = "";

    tickets.forEach(t => {
        let agentOptions = `<option value="">Select Agent</option>`;
        agents.forEach(a => {
            agentOptions += `<option value="${a.userId}">${a.fullName}</option>`;
        });

        tbody.innerHTML += `
            <tr>
                <td>${t.ticketId}</td>
                <td>${t.title}</td>
                <td>${t.createdBy ? t.createdBy.fullName : "Unknown"}</td>
                <td>${t.status}</td>
                <td>${t.assignedTo ? t.assignedTo.fullName : "Not Assigned"}</td>

                <td>
                    <select id="agent_${t.ticketId}">
                        ${agentOptions}
                    </select>
                    <button onclick="assignTicket(${t.ticketId})">Assign</button>
                </td>
            </tr>
        `;
    });
}

// ------------------------------------------------
// ASSIGN TICKET
// ------------------------------------------------
function assignTicket(ticketId) {
    const agentId = document.getElementById(`agent_${ticketId}`).value;

    if (!agentId) {
        showToast("Please select an agent!", "warning");
        return;
    }

    fetch(`${API}/tickets/assign/${ticketId}/${agentId}`, {
        method: "PUT",
        headers: { "Authorization": `Bearer ${token}` }
    })
    .then(res => res.json())
    .then(result => {
        showToast(result.message || "Assigned Successfully!", "success");
        loadTickets();
    })
    .catch(err => {
        console.error("Assign Error:", err);
        showToast("Assignment Failed", "error");
    });
}

// Load everything
loadAgents();
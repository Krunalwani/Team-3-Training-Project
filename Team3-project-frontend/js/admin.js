const token = localStorage.getItem("token");
const role = localStorage.getItem("role");

if (!token || role !== "ADMIN") {
    alert("Unauthorized. Please login as Admin.");
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
    .catch(err => console.error("Error loading agents:", err));
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
    .catch(err => console.error(err));
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
        alert("Please select an agent!");
        return;
    }

    fetch(`${API}/tickets/assign/${ticketId}/${agentId}`, {
        method: "PUT",
        headers: { "Authorization": `Bearer ${token}` }
    })
    .then(res => res.json())
    .then(result => {
        alert(result.message || "Assigned!");
        loadTickets();
    })
    .catch(err => console.error("Assign Error:", err));
}

// Load everything
loadAgents();

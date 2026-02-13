async function loadAgentTickets() {
    let aid = localStorage.getItem("userId");
    let tickets = await apiGet("/agent/tickets/" + aid);

    let box = document.getElementById("agentTickets");
    box.innerHTML = "";

    tickets.forEach(t => {
        box.innerHTML += `
            <div class="card">
                <h3>${t.title}</h3>
                <p>${t.description}</p>

                <label>Status:</label>
                <select onchange="updateStatus(${t.ticketId}, this.value)">
                    <option>OPEN</option>
                    <option>IN_PROGRESS</option>
                    <option>RESOLVED</option>
                    <option>CLOSED</option>
                </select>

            </div>
        `;
    });
}

async function updateStatus(ticketId, status) {
    await apiPut(`/agent/status/${ticketId}?status=${status}`, {});
    alert("Status Updated");
}

loadAgentTickets();

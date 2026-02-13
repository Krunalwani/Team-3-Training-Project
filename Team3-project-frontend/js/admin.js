async function loadAllTickets() {
    let tickets = await apiGet("/admin/tickets");

    let box = document.getElementById("tickets");
    box.innerHTML = "";

    tickets.forEach(t => {
        box.innerHTML += `
            <div class="card">
                <h4>${t.title}</h4>
                <p>${t.description}</p>
                <p>Status: ${t.status}</p>
                <p>Assigned To: ${t.assignedToName || 'None'}</p>
            </div>
        `;
    });
}

async function assign() {
    let data = {
        ticketId: document.getElementById("ticketId").value,
        agentId: document.getElementById("agentId").value
    };

    await apiPost("/admin/assign", data);

    alert("Assigned Successfully");
    loadAllTickets();
}

loadAllTickets();

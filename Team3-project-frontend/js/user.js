async function createTicket() {
    const data = {
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        category: document.getElementById("category").value,
        priority: document.getElementById("priority").value,
        userId: localStorage.getItem("userId")
    };

    let res = await apiPost("/user/ticket", data);
    alert("Ticket Created: " + res.ticketId);

    loadTickets();
}

async function loadTickets() {
    let uid = localStorage.getItem("userId");
    let tickets = await apiGet("/user/tickets/" + uid);

    let box = document.getElementById("tickets");
    box.innerHTML = "";

    tickets.forEach(t => {
        box.innerHTML += `
            <div class="card">
                <h4>${t.title}</h4>
                <p>${t.description}</p>
                <p>Status: ${t.status}</p>
                <p>Assigned To: ${t.assignedToName || 'Not Assigned'}</p>
            </div>
        `;
    });
}

loadTickets();

const token = localStorage.getItem("token");
const userId = localStorage.getItem("userId");

if (!token || !userId) {
    alert("Session expired. Login again.");
    window.location.href = "login.html";
}

// 🔹 CREATE TICKET
async function createTicket() {
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");

    if (!token || !userId) {
        alert("Session expired. Login again.");
        window.location.href = "login.html";
        return;
    }

    const title = document.getElementById("title").value.trim();
    const description = document.getElementById("description").value.trim();
    const categoryId = document.getElementById("category").value;
    const priorityId = document.getElementById("priority").value;

    if (!title || !description || !categoryId || !priorityId) {
        alert("All fields are required");
        return;
    }

    const ticketData = {
        userId: Number(userId),
        title,
        description,
        categoryId: Number(categoryId),
        priorityId: Number(priorityId)
    };

    try {
        const response = await fetch("http://localhost:8080/api/tickets/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(ticketData)
        });

        if (!response.ok) {
            const error = await response.text();
            console.error("Backend error:", error);
            alert("Ticket creation failed!");
            return;
        }

        const data = await response.json();
        alert(data.message);
        loadMyTickets();

    } catch (err) {
        console.error("Network error:", err);
        alert("Server not reachable");
    }
}


// 🔹 LOAD USER TICKETS
async function loadMyTickets() {

    const response = await fetch(
        `http://localhost:8080/api/tickets/user/${userId}`,
        {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }
    );

    const tickets = await response.json();
    const table = document.getElementById("ticketTableBody");

    table.innerHTML = "";

    tickets.forEach(ticket => {
        const row = `
            <tr>
                <td>${ticket.ticketId}</td>
                <td>${ticket.title}</td>
                <td>${ticket.status}</td>
                <td>${ticket.assignedTo ? ticket.assignedTo.fullName : "Not Assigned"}</td>
            </tr>
        `;
        table.innerHTML += row;
    });
}


// 🔹 LOGOUT
function logout() {
    localStorage.clear();
    window.location.href = "login.html";
}

// Load tickets on page load
loadMyTickets();

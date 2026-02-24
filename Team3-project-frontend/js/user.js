const token = localStorage.getItem("token");
const userId = localStorage.getItem("userId");

if (!token || !userId) {
    showToast("Session expired. Login again.", "error");
    window.location.href = "login.html";
}

// 🔹 CREATE TICKET
async function createTicket() {
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");

    if (!token || !userId) {
        showToast("Session expired. Login again.", "error");
        window.location.href = "login.html";
        return;
    }

    const title = document.getElementById("title").value.trim();
    const description = document.getElementById("description").value.trim();
    const categoryId = document.getElementById("category").value;
    const priorityId = document.getElementById("priority").value;

    if (!title || !description || !categoryId || !priorityId) {
        showToast("All fields are required", "warning");
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
            showToast("Ticket creation failed!", "error");
            return;
        }

        const data = await response.json();
        showToast(data.message, "success");
        loadMyTickets();

    } catch (err) {
        console.error("Network error:", err);
        showToast("Server not reachable", "error");
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
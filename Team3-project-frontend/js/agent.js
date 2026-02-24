const token = localStorage.getItem("token");
const agentId = localStorage.getItem("userId");

const API = "http://localhost:8080/api/agent";

if (!token || !agentId) {
    showToast("Session expired. Login again.", "error");
    window.location.href = "login.html";
}


// LOAD ASSIGNED TICKETS
async function loadAssignedTickets() {

    try {

        const response = await fetch(`${API}/tickets/${agentId}`, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        const tickets = await response.json();

        const table = document.getElementById("ticketTableBody");

        table.innerHTML = "";

        tickets.forEach(ticket => {

            const row = `
                <tr>
                    <td>${ticket.id}</td>
                    <td>${ticket.title}</td>
                    <td>${ticket.description}</td>
                    <td>${ticket.status}</td>

                    <td>
                        <select onchange="updateStatus(${ticket.id}, this.value)">
                            <option value="">Select</option>
                            <option value="IN_PROGRESS">IN_PROGRESS</option>
                            <option value="RESOLVED">RESOLVED</option>
                            <option value="CLOSED">CLOSED</option>
                        </select>
                    </td>

                </tr>
            `;

            table.innerHTML += row;

        });

    }
    catch (error) {

        console.error(error);
        showToast("Failed to load tickets", "error");

    }

}



// UPDATE STATUS
async function updateStatus(ticketId, status) {

    if (!status) return;

    try {

        const response = await fetch(`${API}/tickets/${ticketId}/status`, {

            method: "PUT",

            headers: {

                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`

            },

            body: JSON.stringify({

                status: status

            })

        });

        if (!response.ok) {

            throw new Error("Failed to update");

        }

        showToast("Status Updated Successfully", "success");

        loadAssignedTickets();

    }
    catch (error) {

        console.error(error);
        showToast("Update Failed", "error");

    }

}


// LOGOUT
function logout() {

    localStorage.clear();
    window.location.href = "login.html";

}


// LOAD ON START
loadAssignedTickets();
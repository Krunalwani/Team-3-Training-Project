async function login() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    let res = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    let data = await res.json();

    if (data.token) {
        localStorage.setItem("token", data.token);
        localStorage.setItem("userId", data.userId);
        localStorage.setItem("role", data.role);

        if (data.role === "USER") {
            window.location.href = "user-dashboard.html";
        } else if (data.role === "AGENT") {
            window.location.href = "agent-dashboard.html";
        } else if (data.role === "ADMIN") {
            window.location.href = "admin-dashboard.html";
        }
    } else {
        document.getElementById("error").innerText = "Invalid Credentials";
    }
}

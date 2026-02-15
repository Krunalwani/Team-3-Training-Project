async function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });

        if (!response.ok) {
            document.getElementById('message').innerText = 'Login failed!';
            return;    
        }

        const data = await response.json();
        console.log("Login Response:", data);

        // ✅ SAVE EVERYTHING (MANDATORY)
        localStorage.setItem('token', data.token);     // 🔥 FIX
        localStorage.setItem('role', data.role);
        localStorage.setItem('userId', data.userId);   // 🔥 FIX

        document.getElementById('message').innerText =
            'Login successful! Redirecting...';

        // ✅ ROLE BASED REDIRECTION
        if (data.role === "ADMIN") {
            window.location.href = "admin-dashboard.html";
        } 
        else if (data.role === "AGENT") {
            window.location.href = "agent-dashboard.html";
        } 
        else if (data.role === "USER") {
            window.location.href = "user-dashboard.html";
        } 
        else {
            document.getElementById('message').innerText = "Unknown role!";
        }

    } catch (err) {
        document.getElementById('message').innerText =
            'Error: ' + err.message;
    }
}

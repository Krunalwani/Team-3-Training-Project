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
        document.getElementById('message').innerText = 'Login successful! Token: ' + data.token;

        // Save JWT token for API calls
        localStorage.setItem('jwtToken', data.token);

    } catch (err) {
        document.getElementById('message').innerText = 'Error: ' + err.message;
    }
}

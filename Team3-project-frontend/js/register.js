async function register() {
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    const messageDiv = document.getElementById('message');

    try {
        const response = await fetch('http://localhost:8080/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, email, password, role })
        });

        const message = await response.text();

        // Show message in green if success, red if error
        messageDiv.innerText = message;
        messageDiv.style.color = message.toLowerCase().includes('successfully') ? 'green' : 'red';

        if (response.ok && message.toLowerCase().includes('successfully')) {
            // Clear input fields after successful registration
            document.getElementById('name').value = '';
            document.getElementById('email').value = '';
            document.getElementById('password').value = '';
            document.getElementById('role').value = 'USER';
        }

    } catch (err) {
        messageDiv.innerText = 'Error: ' + err.message;
        messageDiv.style.color = 'red';
    }
}

async function register() {
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    try {
        const response = await fetch('http://localhost:8080/api/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, email, password, role })
        });

        const message = await response.text();

        // Toast instead of messageDiv
        if (response.ok && message.toLowerCase().includes('successfully')) {

            showToast(message, "success");

            // Clear input fields after successful registration
            document.getElementById('name').value = '';
            document.getElementById('email').value = '';
            document.getElementById('password').value = '';
            document.getElementById('role').value = 'USER';

        } else {

            showToast(message, "error");

        }

    } catch (err) {

        showToast("Error: " + err.message, "error");

    }
}
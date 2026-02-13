const BASE_URL = "http://localhost:8080";

function getHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

async function apiGet(url) {
    const res = await fetch(BASE_URL + url, { headers: getHeaders() });
    return res.json();
}

async function apiPost(url, data) {
    const res = await fetch(BASE_URL + url, {
        method: "POST",
        headers: getHeaders(),
        body: JSON.stringify(data)
    });
    return res.json();
}

async function apiPut(url, data) {
    const res = await fetch(BASE_URL + url, {
        method: "PUT",
        headers: getHeaders(),
        body: JSON.stringify(data)
    });
    return res.json();
}

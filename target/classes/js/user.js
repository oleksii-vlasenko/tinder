function sendPost(str) {
    fetch("users", {
        method: "POST",
        body: JSON.stringify({
            like: str
        }),
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    });
}
const yesButton = document.getElementById("first-btn");
yesButton.style.color = "red";

yesButton.addEventListener("click", () => sendPost("YES"));
const noButton = document.getElementById("second-btn");
noButton.addEventListener("click", () => sendPost("NO"));
const formName = document.getElementById("formName");

function sendPost(str) {
    fetch("users", {
        method: "POST",
        body: JSON.stringify(`result&=str`),
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    });
}
async function changeUsername(oldUsername, newUsername) {
    const body = {
        oldUsername: oldUsername.value,
        newUsername: newUsername.value
    }
   /* const response = await fetch('http://127.0.0.1:8080/rest/settings/changeusername', {
        method: 'POST',
        body: body
    });
    await response.json();*/
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", 'http://127.0.0.1:8080/rest/settings/changeusername', true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(body));
}
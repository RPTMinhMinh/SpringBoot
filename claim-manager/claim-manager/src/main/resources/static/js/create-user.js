const $btnSubmit = document.getElementById("submit");

const createUser = (e) => {
    e.preventDefault();
    const user = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        code: document.getElementById('code').value,
        email: document.getElementById('email').value,
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        phone: document.getElementById('phone').value,
        address: document.getElementById('address').value,
        createdDate: document.getElementById('createdDate').value,
        stringBase64Avatar: document.getElementById('uploadedAvatar').src
    }

    console.log(user);
    fetch('http://localhost:8080/api/user/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(res => res.json())
        .then(data => {
            if(!data){
                alert("Loi!");
                return;
            }
            alert("Them thanh cong !!!");
            window.location.href = "http://localhost:8080/cms/user-manager";
        }).catch(e => console.log(e))
}

$btnSubmit.onclick = createUser;
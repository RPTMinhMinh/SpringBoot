function displayUserData(user) {
    document.getElementById('id').value = user.id;
    document.getElementById('username').value = user.username;
    document.getElementById('password').value = user.password;
    document.getElementById('code').value = user.code;
    document.getElementById('email').value = user.email;
    document.getElementById('firstName').value = user.firstName;
    document.getElementById('lastName').value = user.lastName;
    document.getElementById('phone').value = user.phone;
    document.getElementById('address').value = user.address;
    document.getElementById('createdDate').value = user.createdDate;
    document.getElementById('createdBy').value = user.createdBy;
    document.getElementById('lastModifiedDate').value = user.lastModifiedDate;
    document.getElementById('lastModifiedBy').value = user.lastModifiedBy;
    document.getElementById('uploadedAvatar').src = user.stringBase64Avatar || '';
}

function renderUserById() {
    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('id');
    if (userId) {
        fetch(`http://localhost:8080/api/user/findById/${userId}`)
            .then(response => response.json())
            .then(data => {
                const user = data.data;
                if (user.stringBase64Avatar && user.stringBase64Avatar.startsWith('blob:')) {
                    fetch(user.stringBase64Avatar)
                        .then(response => response.blob())
                        .then(blob => {
                            const reader = new FileReader();
                            reader.onload = function() {
                                user.stringBase64Avatar = reader.result;
                                displayUserData(user);
                            };
                            reader.readAsDataURL(blob);
                        })
                        .catch(error => console.error('Error converting blob to Base64:', error));
                } else {
                    displayUserData(user);
                }
            })
            .catch(error => console.error('Error fetching user data:', error));
    }
}

// Hàm cập nhật người dùng
function updateUser(event) {
    event.preventDefault();
    const user = {
        id: document.getElementById('id').value,
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        code: document.getElementById('code').value,
        email: document.getElementById('email').value,
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        phone: document.getElementById('phone').value,
        address: document.getElementById('address').value,
        createdDate: document.getElementById('createdDate').value,
        createdBy: document.getElementById('createdBy').value,
        lastModifiedDate: document.getElementById('lastModifiedDate').value,
        lastModifiedBy: document.getElementById('lastModifiedBy').value,
        stringBase64Avatar: document.getElementById('uploadedAvatar').src
    };

    fetch(`http://localhost:8080/api/user/update/${user.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            alert("thanh cong !!!");
            window.location.href = "http://localhost:8080/cms/user-manager";
        })
        .catch(error => {
            alert("loiiiiiii ");
        });
}

document.getElementById('upload').addEventListener('change', function() {
    const file = this.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('uploadedAvatar').setAttribute('src', e.target.result);
        };
        reader.readAsDataURL(file);
    }
});

document.addEventListener('DOMContentLoaded', function() {
    renderUserById();
});

const $btnSubmit = document.getElementById("submit");
$btnSubmit.onclick = updateUser;

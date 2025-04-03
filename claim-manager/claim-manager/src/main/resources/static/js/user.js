function navigateToCreateUser(){
    window.location.href = 'http://localhost:8080/cms/create-user';
}

function navigateToDetailUser(userId) {
    window.location.href = `http://localhost:8080/cms/detail-user?id=${userId}`
}

const renderData = document.getElementById('render-data');
const searchButton = document.getElementById('search-button');
const pagination = document.getElementById('pagination');
let currentPage;
let totalPages;
const pageSize = 3;

function renderUser(page = 0){
    const code = document.getElementById('search-ma-yeu-cau').value;
    const fromDate = document.getElementById('from-date').value;
    const toDate = document.getElementById('to-date').value;
    const phone = document.getElementById('phone').value;

    let api = `http://localhost:8080/api/user/list?page=${page}&size=${pageSize}`;
    if(code){
        api += `&code=${code}`
    }
    if (fromDate){
        const fromDateTime = moment(fromDate).format('YYYY-MM-DDTHH:mm:ss');
        api += `&fromDate=${fromDateTime}`
    }
    if (toDate){
        const toDateTime = moment(toDate).format('YYYY-MM-DDTHH:mm:ss');
        api += `&toDate=${toDateTime}`
    }
    if (phone){
        api += `&phone=${phone}`
    }

    fetch(api).then((res) => res.json()).then(data => {
        console.log('response: ', data);
        totalPages = data.totalPage;
        currentPage = data.pageIndex;
        const dataClaim = data.data;
        console.log(dataClaim);
        let claimList = '';
        for(let data of dataClaim){
            const createdDate = moment(data.createdDate).format('YYYY-MM-DD');
            claimList += `
                    <tr>
                                  <td><input type="checkbox" class="recordCheckbox"></td>
                                  <td><strong>${data.code}</strong></td>
                                  <td><img src="data:image/jpeg;base64,${data.stringBase64Avatar}" onclick="navigateToDetailUser(${data.id})" alt="Avatar" style="width: 50px; height: 50px;"></td>
                                  <td>${data.username}</td>
                                  <td>${data.firstName + " " + data.lastName}</td>
                                  <td>${data.phone}</td>
                                  <td>${createdDate}</td>
                                  <td>${data.address}</td>
                    </tr>
                `
        }
        renderData.innerHTML = claimList;
        renderUserPagination();
    })
}

function renderUserPagination() {
    let paginationHtml = '';
    paginationHtml += `<li class="page-item ${currentPage === 0 ? 'disabled' : ''}">
        <a class="page-link" href="#" onclick="changePage(${currentPage - 1})">&laquo;</a>
    </li>`;

    for (let i = 0; i < totalPages; i++) {
        paginationHtml += `<li class="page-item ${i === currentPage ? 'active' : ''}">
            <a class="page-link" href="#" onclick="changePage(${i})">${i + 1}</a>
        </li>`;
    }
    paginationHtml += `<li class="page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}">
        <a class="page-link" href="#" onclick="changePage(${currentPage + 1})">&raquo;</a>
    </li>`;

    pagination.innerHTML = paginationHtml;
}

function changePage(page) {
    if (page >= 0 && page < totalPages) {
        renderUser(page);
    }
}

searchButton.addEventListener('click', function () {
    renderUser(0);
});

renderUser();